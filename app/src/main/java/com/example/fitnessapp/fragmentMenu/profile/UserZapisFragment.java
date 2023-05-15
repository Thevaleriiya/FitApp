package com.example.fitnessapp.fragmentMenu.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.profile.UslugiPageAdapter;
import com.example.fitnessapp.adapters.profile.ZapicPageAdapter;
import com.google.android.material.tabs.TabLayout;


public class UserZapisFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 vPage;
    private ZapicPageAdapter adapter;


    public UserZapisFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_zapis, container, false);
        init(view);
        adapter = new ZapicPageAdapter(getChildFragmentManager(), getLifecycle());
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
        tabLayout = view.findViewById(R.id.user_zapis_tab);
        vPage = view.findViewById(R.id.user_zapis_pager);
    }
}