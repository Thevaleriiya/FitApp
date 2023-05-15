package com.example.fitnessapp.fragmentMenu.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.profile.UslugiPageAdapter;
import com.google.android.material.tabs.TabLayout;


public class UslugiFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 vPage;
    private UslugiPageAdapter adapter;


    public UslugiFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_uslugi, container, false);
        init(view);
        adapter = new UslugiPageAdapter(getChildFragmentManager(), getLifecycle());
        vPage.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vPage.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vPage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
        return view;
    }

    private void init(View view){
        tabLayout = view.findViewById(R.id.user_uslugi_tab);
        vPage = view.findViewById(R.id.user_uslugi_pager);
    }
}