package com.example.fitnessapp.adapters.profile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fitnessapp.fragmentMenu.profile.UserZapisFragment;
import com.example.fitnessapp.fragmentMenu.profile.UserZapisPageFragment;
import com.example.fitnessapp.fragmentMenu.profile.UslugiPageFragment;

public class ZapicPageAdapter extends FragmentStateAdapter {


    public ZapicPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new UserZapisPageFragment(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}