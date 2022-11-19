package com.example.colombopizza;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Employee_list_adapter extends BaseAdapter {
    Context context;
    ArrayList<DB_employee> employee;
    TextView mobile, Fname,Lname,Address,Username, Password;

    public Employee_list_adapter(Context context, ArrayList<DB_employee> employee) {
        this.context = context;
        this.employee = employee;
    }

    @Override
    public int getCount() {
        return employee.size();
    }

    @Override
    public Object getItem(int position) {
        return employee.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_employee_list,parent,false);


        mobile = v.findViewById(R.id.mobileTxt);
        Fname = v.findViewById(R.id.FnameTxt);
        Lname= v.findViewById(R.id.LnameTxt);
        Address= v.findViewById(R.id.AddressTxt);
        Username= v.findViewById(R.id.usernameTxt);
        Password= v.findViewById(R.id.passwordTxt);

        DB_employee db_employee = employee.get(position);
        mobile.setText("" + db_employee.getMobile_no());
        Fname.setText(db_employee.getFname());
        Lname.setText(db_employee.getLname());
        Address.setText(db_employee.getAddress());
        Username.setText(db_employee.getUname());
        Password.setText(db_employee.getPass());
        return v;
    }
}
