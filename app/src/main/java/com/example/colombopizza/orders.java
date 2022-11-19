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

public class orders extends AppCompatActivity {
    ArrayList<DB_cart> cart;
    ListView proList;
    DB_operation db;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        getWindow().setStatusBarColor(ContextCompat.getColor(orders.this,R.color.Lightred));
        getSupportActionBar().hide();

         proList = findViewById(R.id.listorders);
        db = new DB_operation(this);

        //initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigetion);


        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.orders);

        //performe itemselectedListner
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.foods:
                        startActivity(new Intent(getApplicationContext()
                                ,food_items.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.orders:
                        return true;
                    case R.id.emplyees:
                        startActivity(new Intent(getApplicationContext()
                                ,Employee.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.customer:
                        startActivity(new Intent(getApplicationContext()
                                ,Customer_in_admin.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });



        DB_operation db = new DB_operation(this);
        cart = db.viewAllorders();

        ArrayList<DB_cart> cart2 = db.viewAllorders();
        if(cart2!=null) {

            cart_adapter cart_adapter = new cart_adapter(this, cart2);
            proList.setAdapter(cart_adapter);
        }else{
            Toast.makeText(this,"No orders available",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void order_confirmed(View view){
        /*int index = (int) view.getTag();
        String name = cart.get(index).getName();*/
        Toast toast = Toast.makeText(this, "Confirmed",Toast.LENGTH_SHORT);
        toast.show();


    }
    /*DB_employee employee = new DB_employee();
                employee.setMobile_no(Integer.parseInt(Mnumber.getText().toString()));

                if(db.delte_employee(employee)>0){
        Toast.makeText(getActivity(),"Employee Record Deleted", Toast.LENGTH_SHORT).show();
        clear();
    }*/
}