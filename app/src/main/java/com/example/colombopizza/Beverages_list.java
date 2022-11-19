package com.example.colombopizza;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Beverages_list extends AppCompatActivity {
    ArrayList<DB_beverage> Beverage_items;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverages_list);
        getWindow().setStatusBarColor(ContextCompat.getColor(Beverages_list.this, R.color.Lightred));
        getSupportActionBar().hide();

        //initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigetion);


        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.beverages);

        //performe itemselectedListner
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.beverages:
                        return true;
                    case R.id.pizza:
                        startActivity(new Intent(getApplicationContext()
                                ,Customer_account.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.appetizers:
                        startActivity(new Intent(getApplicationContext()
                                ,Appetizers_list.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });

        ListView proList = findViewById(R.id.listProducts);

        DB_operation db = new DB_operation(this);
        Beverage_items = db.viewAllbeverage_items();

        ArrayList<DB_beverage> beverage_items = db.viewAllbeverage_items();
        if(beverage_items!=null) {

            beverage_list_adapter beverage_list_adapter = new beverage_list_adapter(this, beverage_items);
            proList.setAdapter(beverage_list_adapter);
        }else{
            Toast.makeText(this,"No itams available",Toast.LENGTH_SHORT).show();
        }
    }

    /*public void getBeverage(View view){
        int index = (int) view.getTag();


        String name = Beverage_items.get(index).getF_name();
        Double price  = Beverage_items.get(index).getF_price();
        Toast toast = Toast.makeText(this, " "+price+"\n"+name,Toast.LENGTH_SHORT);
        toast.show();
    }*/

    public void add_to_cart(View view){             //beverage item add to cart
        int index = (int) view.getTag();

        String name = Beverage_items.get(index).getF_name();
        Double price  = Beverage_items.get(index).getF_price();


        Intent intent = new Intent(Beverages_list.this,Add_to_cart_list.class);
        intent.putExtra("Pname",name);
        intent.putExtra("Pprice",price);
        startActivity(intent);

    }

    public void myOrders(View view){
        Intent intent = new Intent(Beverages_list.this,My_orders.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}