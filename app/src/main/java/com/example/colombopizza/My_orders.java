package com.example.colombopizza;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class My_orders extends AppCompatActivity {
    ArrayList<DB_cart> cart;
    DB_operation db;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        getWindow().setStatusBarColor(ContextCompat.getColor(My_orders.this, R.color.Lightred));
        getSupportActionBar().hide();

        ListView proList = findViewById(R.id.listOfMyorders);
        db = new DB_operation(this);


        DB_operation db = new DB_operation(this);
        cart = db.viewAllorders();

        ArrayList<DB_cart> cart2 = db.viewAllorders();
        if(cart2!=null) {

            my_orders_adapter my_orders_adapter = new my_orders_adapter(this, cart2);
            proList.setAdapter(my_orders_adapter);
        }else{
            Toast.makeText(this,"No orders available",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(My_orders.this, Customer_account.class);
        startActivity(intent);
    }
}