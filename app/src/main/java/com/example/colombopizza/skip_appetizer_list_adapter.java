package com.example.colombopizza;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class skip_appetizer_list_adapter extends BaseAdapter {
    Context context;
    ArrayList<DB_apprtizer> apprtize;
    TextView lblName, lblPrice;
    ImageView imageView;

    public skip_appetizer_list_adapter(Context context, ArrayList<DB_apprtizer> apprtizer) {
        this.context = context;
        this.apprtize = apprtizer;
    }

    @Override
    public int getCount() {
        return apprtize.size();
    }

    @Override
    public Object getItem(int position) {
        return apprtize.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_skip_appetizer,parent,false);


        lblName = v.findViewById(R.id.nameTxt);
        lblPrice = v.findViewById(R.id.txtPrice);
        imageView= v.findViewById(R.id.pizza_img);

        DB_apprtizer db_apprtizer = apprtize.get(position);
        lblName.setText(db_apprtizer.getF_name());
        lblPrice.setText("" + db_apprtizer.getF_price());
        Bitmap bitmap = BitmapFactory.decodeByteArray(db_apprtizer.getImg(),0,db_apprtizer.getImg().length);
        imageView.setImageBitmap(bitmap);
        return v;
    }
}
