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

public class Appetizers_list extends AppCompatActivity {
    ListView proList;
    ArrayList<DB_apprtizer> apprtizer;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appetizers_list);
        getWindow().setStatusBarColor(ContextCompat.getColor(Appetizers_list.this,R.color.Lightred));
        getSupportActionBar().hide();

        //initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigetion);


        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.appetizers);

        //performe itemselectedListner
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.beverages:
                        startActivity(new Intent(getApplicationContext()
                                ,Beverages_list.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.pizza:
                        startActivity(new Intent(getApplicationContext()
                                ,Customer_account.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.appetizers:
                        return true;
                }

                return false;
            }
        });

        proList = findViewById(R.id.listProducts);
        DB_operation db = new DB_operation(this);
        apprtizer = db.viewAllapprtizer_items();

        ArrayList<DB_apprtizer> apprtizer = db.viewAllapprtizer_items();
        if(apprtizer!=null) {

            apprtizer_list_adapter apprtizer_list_adapter = new apprtizer_list_adapter(this, apprtizer);
            proList.setAdapter(apprtizer_list_adapter);
        }else{
            Toast.makeText(this,"No itams available",Toast.LENGTH_SHORT).show();
        }
    }

    /*public void getAppatizer(View view){
        int index = (int) view.getTag();


        String name = apprtizer.get(index).getF_name();
        Double price  = apprtizer.get(index).getF_price();
        Toast toast = Toast.makeText(this, " "+price+"\n"+name,Toast.LENGTH_SHORT);
        toast.show();
    }*/

    public void add_to_cart(View view){         //appatizer item add to cart
        int index = (int) view.getTag();

        String name = apprtizer.get(index).getF_name();
        Double price  = apprtizer.get(index).getF_price();


        Intent intent = new Intent(Appetizers_list.this,Add_to_cart_list.class);
        intent.putExtra("Pname",name);
        intent.putExtra("Pprice",price);
        startActivity(intent);

    }
    public void myOrders(View view){
        Intent intent = new Intent(Appetizers_list.this,My_orders.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}

