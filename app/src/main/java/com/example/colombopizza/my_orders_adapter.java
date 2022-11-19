package com.example.colombopizza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class my_orders_adapter extends BaseAdapter {
    Context context;
    ArrayList<DB_cart> cart;
    TextView name, item_name,price,quantity,fee, total;

    public my_orders_adapter(Context context, ArrayList<DB_cart> cart) {
        this.context = context;
        this.cart = cart;
    }

    @Override
    public int getCount() {
        return cart.size();
    }

    @Override
    public Object getItem(int position) {
        return cart.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_my_order,parent,false);


        name = v.findViewById(R.id.Iname);
        item_name =  v.findViewById(R.id.Iitem_name);
        price =  v.findViewById(R.id.Iprice);
        quantity =  v.findViewById(R.id.Iquantity);
        fee =  v.findViewById(R.id.Ifee);
        total =  v.findViewById(R.id.Itotal);

        DB_cart cart2 = cart.get(position);
        name.setText(cart2.getName());
        item_name.setText(cart2.getItem_name());
        price.setText("" + cart2.getPrice());
        quantity.setText("" + cart2.getQuantity());
        fee.setText("" + cart2.getFee());
        total.setText("" + cart2.getTotal());
        return v;
    }

}
