package com.example.fitnessapp.adapters.profile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fitnessapp.fragmentMenu.profile.UslugiFragment;
import com.example.fitnessapp.fragmentMenu.profile.UslugiPageFragment;

public class UslugiPageAdapter extends FragmentStateAdapter {

    public UslugiPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new UslugiPageFragment(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
