package com.example.colombopizza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class customer_list_adapter extends BaseAdapter {
    Context context;
    ArrayList<Customer> Customer;
    TextView mobile, username,password;

    public customer_list_adapter(Context context, ArrayList<Customer> Customer) {
        this.context = context;
        this.Customer = Customer;
    }

    @Override
    public int getCount() {
        return Customer.size();
    }

    @Override
    public Object getItem(int position) {
        return Customer.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_customer_list,parent,false);


        mobile = v.findViewById(R.id.mobileTxt);
        username = v.findViewById(R.id.UnameTxt);
        password= v.findViewById(R.id.PasswordTxt);

        Customer customer = Customer.get(position);
        mobile.setText("" + customer.getMobile_no());
        username.setText(customer.getUname());
        password.setText(customer.getPass());
        return v;
    }
}
