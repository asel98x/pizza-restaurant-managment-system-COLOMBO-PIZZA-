package com.example.colombopizza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class cart_adapter extends BaseAdapter {
    Context context;
    ArrayList<DB_cart> cart;
    TextView id, name, address, mobile, item_name,price,quantity,fee, total;

    public cart_adapter(Context context, ArrayList<DB_cart> cart) {
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
        View v = inflater.inflate(R.layout.custom_order_list,parent,false);


        id = v.findViewById(R.id.Iid);
        name = v.findViewById(R.id.Iname);
        address =  v.findViewById(R.id.Iaddress);
        mobile =  v.findViewById(R.id.Imobile);
        item_name =  v.findViewById(R.id.Iitem_name);
        price =  v.findViewById(R.id.Iprice);
        quantity =  v.findViewById(R.id.Iquantity);
        fee =  v.findViewById(R.id.Ifee);
        total =  v.findViewById(R.id.Itotal);

        DB_cart cart2 = cart.get(position);
        id.setText("" + cart2.getPid());
        name.setText(cart2.getName());
        address.setText(cart2.getAddress());
        mobile.setText(""+cart2.getMobile());
        item_name.setText(cart2.getItem_name());
        price.setText("" + cart2.getPrice());
        quantity.setText("" + cart2.getQuantity());
        fee.setText("" + cart2.getFee());
        total.setText("" + cart2.getTotal());
        return v;
    }
}
