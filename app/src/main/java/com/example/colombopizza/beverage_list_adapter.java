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

public class beverage_list_adapter extends BaseAdapter {
    Context context;
    ArrayList<DB_beverage> beverage;
    TextView  lblName, lblPrice;
    ImageView imageView;
    Button btnClick;

    public beverage_list_adapter(Context context, ArrayList<DB_beverage> beverage) {
        this.context = context;
        this.beverage = beverage;
    }

    @Override
    public int getCount() {
        return beverage.size();
    }

    @Override
    public Object getItem(int position) {
        return beverage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.beverage_view,parent,false);


        lblName = v.findViewById(R.id.nameTxt);
        lblPrice = v.findViewById(R.id.txtPrice);
        imageView= v.findViewById(R.id.pizza_img);
        btnClick= v.findViewById(R.id.btnCart2);

        btnClick.setTag(position);

        DB_beverage db_beverage = beverage.get(position);

        lblName.setText(db_beverage.getF_name());
        lblPrice.setText("" + db_beverage.getF_price());
        Bitmap bitmap = BitmapFactory.decodeByteArray(db_beverage.getImg(),0,db_beverage.getImg().length);
        imageView.setImageBitmap(bitmap);
        return v;
    }
}
