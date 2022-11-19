package com.example.colombopizza;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class pizza_list extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_list);
        getWindow().setStatusBarColor(ContextCompat.getColor(pizza_list.this,R.color.Lightred));
        getSupportActionBar().hide();

        ListView proList = findViewById(R.id.listProducts);
        DB_operation db = new DB_operation(this);

        ArrayList<DB_pizza> pizza_items = db.viewAllpizza_items();
        if(pizza_items!=null) {

            Pizza_list_adapter pizza_list_adapter = new Pizza_list_adapter(this, pizza_items);
            proList.setAdapter(pizza_list_adapter);
        }else{
            Toast.makeText(this,"No products",Toast.LENGTH_SHORT).show();
        }






    }
}