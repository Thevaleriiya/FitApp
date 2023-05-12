package com.example.fitnessapp.fragmentMenu.calendarViewPager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.calendar.SwipeAdapter;
import com.example.fitnessapp.adapters.calendar.ViewPagerAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PageFragment extends Fragment {

    ViewPagerAdapter swipeAdapter;
    Integer posotionView;

    private TextView txtV;
    private ViewPager2 viewPag;
    private TabLayout tabLayout;

    public PageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page_layout, container, false);
        init(view);
        txtV = view.findViewById(R.id.calendar_view_page_fragm_txt_id);
        Bundle bundle = getArguments();
        posotionView =  bundle.getInt("count");
        txtV.setText("My fragment number "+posotionView);
        swipeAdapter = new ViewPagerAdapter(getChildFragmentManager(),getLifecycle());
        viewPag.setAdapter(swipeAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPag.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setData(view);

        viewPag.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

        return view;
    }

    private void init(View view){
        viewPag = view.findViewById(R.id.fragment_page_view_page_id);
        tabLayout = view.findViewById(R.id.tabLayout);
    }

    private void setData(View view){
        Calendar c = Calendar.getInstance();
        String timeStamp = new SimpleDateFormat("dd").format(c.getTime());
        int week = c.get(Calendar.DAY_OF_WEEK);
        int myweek = (week+5)%7;
        Log.d("MyTag", Integer.toString(myweek));
        for (int i=0; i<7; i++){
            Calendar cc = Calendar.getInstance();
            cc.add(cc.DATE,(posotionView-1)*7);
            if (i<=myweek){
                cc.add(cc.DATE, -(myweek)+i);
                String mydate = new SimpleDateFormat("dd").format(cc.getTime());
                tabLayout.getTabAt(i).setText(mydate);
                Log.d("MyTag1", mydate);
            }else{
                cc.add(cc.DATE, i+1);
                String mydate = new SimpleDateFormat("dd").format(cc.getTime());
                tabLayout.getTabAt(i).setText(mydate);
                Log.d("MyTag3", mydate);
            }
        }
    }
}