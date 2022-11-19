package com.example.colombopizza;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class emp_Food_frg_adapter extends FragmentStateAdapter {
    public emp_Food_frg_adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new frg_beverages();
            case 2:
                return new frg_appetizers();


        }

        return new frg_pizza();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
