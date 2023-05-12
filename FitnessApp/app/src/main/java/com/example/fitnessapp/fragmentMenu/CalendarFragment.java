package com.example.fitnessapp.fragmentMenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.calendar.SwipeAdapter;

import java.util.zip.Inflater;


public class CalendarFragment extends Fragment {


    private String mParam1;
    private String mParam2;
    private Integer maxView = 4;
    private Integer minView = 0;
    SwipeAdapter swipeAdapter;

    private Button btnnext;
    private Button btnback;
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
        //init(view);
        swipeAdapter = new SwipeAdapter(getChildFragmentManager(), getLifecycle());
        Log.d("MyTag", swipeAdapter.toString());
        viewPag.setAdapter(swipeAdapter);
        if (viewPag.getCurrentItem()==minView){
            //btnback.setVisibility(View.INVISIBLE);
        }else{
            btnback.setVisibility(View.VISIBLE);
        }
        if (viewPag.getCurrentItem()==maxView){
            btnnext.setVisibility(View.INVISIBLE);
        }else{
            btnnext.setVisibility(View.VISIBLE);
        }
        btnnext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (viewPag.getCurrentItem()!=4){
                            viewPag.setCurrentItem(viewPag.getCurrentItem()+1);
                            Log.d("aaa", "aaa");
                        }

                    }
                }

        );
        btnback.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (viewPag.getCurrentItem()!=0){
                            viewPag.setCurrentItem(viewPag.getCurrentItem()-1);
                            Log.d("aaa", "aaa");
                        }

                    }
                }

        );
        //новый тег
        viewPag.setUserInputEnabled(false);
        return view;
    }

    private void init(View view) {
        btnback= view.findViewById(R.id.button_back);
        btnnext= view.findViewById(R.id.button_next);
        viewPag = view.findViewById(R.id.calendar_view_pager_id);
    }
}