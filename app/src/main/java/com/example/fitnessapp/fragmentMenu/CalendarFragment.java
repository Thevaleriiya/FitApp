package com.example.fitnessapp.fragmentMenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.calendar.SwipeAdapter;

import java.util.zip.Inflater;


public class CalendarFragment extends Fragment {


    private String mParam1;
    private String mParam2;
    SwipeAdapter swipeAdapter;

    private ViewPager2 viewPag;
    private RecyclerView recuclerPage;

    public CalendarFragment() {
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
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        init(view);
        swipeAdapter = new SwipeAdapter(getChildFragmentManager(),getLifecycle());
        Log.d("MyTag", swipeAdapter.toString());
        viewPag.setAdapter(swipeAdapter);
        //viewPag.setUserInputEnabled(false);
        return view;
    }

    private void init(View view){
        viewPag = view.findViewById(R.id.calendar_view_pager_id);
    }
}