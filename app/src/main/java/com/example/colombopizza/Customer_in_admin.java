package com.example.colombopizza;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class Customer_in_admin extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 pager2;
    customer_frg_adapter adapter;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_in_admin);
        getWindow().setStatusBarColor(ContextCompat.getColor(Customer_in_admin.this,R.color.Lightred));
        getSupportActionBar().hide();

        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new customer_frg_adapter(fm,getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Manage Customer"));
        tabLayout.addTab(tabLayout.newTab().setText("Customer List"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        //initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigetion);

        //set emplyees selected
        bottomNavigationView.setSelectedItemId(R.id.customer);

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
                        startActivity(new Intent(getApplicationContext()
                                ,orders.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.customer:
                        return true;
                    case R.id.emplyees:
                        startActivity(new Intent(getApplicationContext()
                                ,Employee.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}