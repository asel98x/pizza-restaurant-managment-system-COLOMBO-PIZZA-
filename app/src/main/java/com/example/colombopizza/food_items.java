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

public class food_items extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 pager2;
    emp_Food_frg_adapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_items);

        getWindow().setStatusBarColor(ContextCompat.getColor(food_items.this,R.color.Lightred));
        getSupportActionBar().hide();

        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new emp_Food_frg_adapter(fm,getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("PIZZA "));
        tabLayout.addTab(tabLayout.newTab().setText("BEVERAGES"));
        tabLayout.addTab(tabLayout.newTab().setText("APPETIZERS"));

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


        //set foods selected
        bottomNavigationView.setSelectedItemId(R.id.foods);

        //performe itemselectedListner
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.foods:
                        return true;
                    case R.id.orders:
                        startActivity(new Intent(getApplicationContext()
                                ,orders.class));
                        overridePendingTransition(0,0);
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
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}