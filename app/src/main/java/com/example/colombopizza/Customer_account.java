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
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Customer_account extends AppCompatActivity {
    ArrayList<DB_pizza> pizza_items;
    DB_operation db;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_account);

        getWindow().setStatusBarColor(ContextCompat.getColor(Customer_account.this, R.color.Lightred));
        getSupportActionBar().hide();

        db = new DB_operation(this);

        //initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigetion);


        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.pizza);

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
            pizza_items = db.viewAllpizza_items();

            ArrayList<DB_pizza> pizza_items = db.viewAllpizza_items();
            if(pizza_items!=null) {

                Pizza_list_adapter pizza_list_adapter = new Pizza_list_adapter(this, pizza_items);
                proList.setAdapter(pizza_list_adapter);
            }else{
                Toast.makeText(this,"No itams available",Toast.LENGTH_SHORT).show();
            }
    }

    /*public void getPizza(View view){
        int index = (int) view.getTag();


        String name = pizza_items.get(index).getF_name();
        Double price  = pizza_items.get(index).getF_price();
        Toast toast = Toast.makeText(this, " "+price+"\n"+name,Toast.LENGTH_SHORT);
        toast.show();
    }*/

    public void add_to_cart(View view){         //pizza item add to cart
        int index = (int) view.getTag();

        String name = pizza_items.get(index).getF_name();
        Double price  = pizza_items.get(index).getF_price();



        Intent intent = new Intent(Customer_account.this,Add_to_cart_list.class);
        intent.putExtra("Pname",name);
        intent.putExtra("Pprice",price);
        startActivity(intent);


    }

    /*public void rtest(View view){
        Intent intent = new Intent(Customer_account.this,Add_to_cart_list.class);
        startActivity(intent);
    }*/
    public void myOrders(View view){
        Intent intent = new Intent(Customer_account.this,My_orders.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}