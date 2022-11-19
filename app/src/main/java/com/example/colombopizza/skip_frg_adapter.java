package com.example.colombopizza;

import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class skip_frg_adapter extends FragmentStateAdapter {
    public skip_frg_adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new frg_skip_beverage();
            case 2:
                return new frg_skip_appetizer();


        }

        return new frg_skip_pizza();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
