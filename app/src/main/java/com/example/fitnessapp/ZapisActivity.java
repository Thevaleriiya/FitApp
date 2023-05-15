package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.fitnessapp.adapters.TimeSlotAdapter;

import java.util.ArrayList;
import java.util.List;

public class ZapisActivity extends AppCompatActivity {

    TextView tvBack;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapis);


        tvBack = findViewById(R.id.back);
        recyclerView = findViewById(R.id.rvZapis);

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        List<String> timeSlots = new ArrayList<>();

        timeSlots.add("10:00");
        timeSlots.add("11:00");

        TimeSlotAdapter adapter = new TimeSlotAdapter(timeSlots);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}