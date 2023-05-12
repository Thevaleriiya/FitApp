package com.example.fitnessapp.fragmentMenu.calendarViewPager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.SaleAdapter;
import com.example.fitnessapp.adapters.calendar.RecyclerCalendarAdapter;
import com.example.fitnessapp.classes.SaleVariant;

import java.util.ArrayList;


public class PageinViewPageFragment extends Fragment {

    private RecyclerView rec;
    RecyclerCalendarAdapter recAdapter;


    public PageinViewPageFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pagein_view_page, container, false);
        init(view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false);
        rec.setLayoutManager(layoutManager);
        rec.setItemAnimator(new DefaultItemAnimator());
        ArrayList<String> people = new ArrayList<String>();
        // добавим в список ряд элементов
        people.add("Tom");
        people.add("Alice");
        people.add("Kate");
        people.add("Sam");

        recAdapter = new RecyclerCalendarAdapter(requireContext(), people);
        rec.setAdapter(recAdapter);
        return view;
    }

    private void init(View view){
        rec = view.findViewById(R.id.calendar_view_recycler);

    }
}