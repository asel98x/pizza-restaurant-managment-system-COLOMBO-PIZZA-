package com.example.colombopizza;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Pizza_list_adapter extends BaseAdapter {
    Context context;
    ArrayList<DB_pizza> pizza;
    TextView  lblName, lblPrice,number;
    ImageView imageView;
    Button btnClick,minerBtn, pluseBtn;

    public Pizza_list_adapter(Context context, ArrayList<DB_pizza> pizza) {
        this.context = context;
        this.pizza = pizza;
    }

    @Override
    public int getCount() {
        return pizza.size();
    }

    @Override
    public Object getItem(int position) {
        return pizza.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.pizza_view,parent,false);


        lblName = v.findViewById(R.id.nameTxt);
        lblPrice = v.findViewById(R.id.txtPrice);
        imageView= v.findViewById(R.id.pizza_img);
        btnClick= v.findViewById(R.id.btnCart2);
        btnClick.setTag(position);

        DB_pizza db_pizza = pizza.get(position);
        lblName.setText(db_pizza.getF_name());
        lblPrice.setText("" + db_pizza.getF_price());
        Bitmap bitmap = BitmapFactory.decodeByteArray(db_pizza.getImg(),0,db_pizza.getImg().length);
        imageView.setImageBitmap(bitmap);
        return v;

    }
}
