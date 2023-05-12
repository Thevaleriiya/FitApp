package com.example.fitnessapp.adapters.calendar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fitnessapp.fragmentMenu.calendarViewPager.PageFragment;

public class SwipeAdapter extends FragmentStateAdapter {
    public SwipeAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment frag = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("count", position+1);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
