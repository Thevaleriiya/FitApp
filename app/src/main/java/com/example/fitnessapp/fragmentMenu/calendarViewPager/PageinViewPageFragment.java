package com.example.fitnessapp.fragmentMenu.calendarViewPager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.SaleAdapter;
import com.example.fitnessapp.adapters.calendar.RecycleCalendarAdapter;
import com.example.fitnessapp.classes.Raspisanie;
import com.example.fitnessapp.classes.SaleVariant;
import com.example.fitnessapp.classes.Trainer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;


public class PageinViewPageFragment extends Fragment {

    private RecyclerView rec;
    RecycleCalendarAdapter recAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    private ArrayList<Raspisanie> raspisanieList;
    private Integer weekPosotion;


    public PageinViewPageFragment() {
        // Required empty public constructor
    }

    public PageinViewPageFragment(Integer weekPos) {
        this.weekPosotion = weekPos;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pagein_view_page, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("raspisanie");
        if (weekPosotion == null) {
            weekPosotion = 0;
        }
        reference.child(Integer.toString(weekPosotion)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                raspisanieList = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Raspisanie raspisanie = ds.getValue(Raspisanie.class);
                    //Log.d("aaaName", raspisanie.getName());
                    raspisanieList.add(raspisanie);
                }
                recAdapter = new RecycleCalendarAdapter(raspisanieList);
                rec.setAdapter(recAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        init(view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        rec.setLayoutManager(layoutManager);
        rec.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    private void init(View view) {
        rec = view.findViewById(R.id.calendar_view_recycler);

    }
}
