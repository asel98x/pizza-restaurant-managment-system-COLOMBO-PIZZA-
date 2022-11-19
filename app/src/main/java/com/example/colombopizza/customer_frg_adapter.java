package com.example.colombopizza;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class customer_frg_adapter extends FragmentStateAdapter {
    public customer_frg_adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new frg_customer_list();


        }

        return new frg_new_customer();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
