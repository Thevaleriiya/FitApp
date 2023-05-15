package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.fitnessapp.adapters.TimeSlotAdapter;
import com.example.fitnessapp.adapters.profile.UslugiPageRecyclerAdapter;
import com.example.fitnessapp.classes.Service;
import com.example.fitnessapp.classes.UserUslugi;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ZapisActivity extends AppCompatActivity {

    TextView tvBack;
    RecyclerView recyclerView;
    String trenerId;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    DatabaseReference referenceTwo;
    private FirebaseAuth mAuth;


    private List<ArrayList<String>> slotDateList;
    private List<ArrayList<String>> slotDateListReshufl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapis);


        tvBack = findViewById(R.id.back);
        recyclerView = findViewById(R.id.rvZapis);
        trenerId = getIntent().getStringExtra("trener_id");
        slotDateList = new ArrayList<>();
        slotDateListReshufl = new ArrayList<>();
        Log.d("aaachekc trenerID = ", trenerId);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("zapic");
        referenceTwo = firebaseDatabase.getReference("zapic_check");

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        List<String> timeSlots = new ArrayList<>();

        timeSlots.add("10:00");
        timeSlots.add("11:00");

        dataFirebase();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void dataFirebase() {
        Calendar c = Calendar.getInstance();
        int myWeek = c.get(Calendar.WEEK_OF_YEAR);
        int myDayOfWeek = (c.get(Calendar.DAY_OF_WEEK) + 5) % 7;
        reference.child(trenerId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Integer> dayCount = new ArrayList<>();
                ArrayList<Integer> dayCountReshulf= new ArrayList<>(); //сортировка
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Integer weekTrener = ds.child("week").getValue(Integer.class);
                    if (Integer.parseInt(ds.getKey()) < myDayOfWeek && weekTrener <= myWeek) {
                        reference.child(trenerId).child(ds.getKey()).child("week").setValue(myWeek + 1);
                        referenceTwo.child(trenerId).child(ds.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot1) {
                                ArrayList<String> trenerListData = new ArrayList<>();
                                for (DataSnapshot ds1 : snapshot1.getChildren()) {
                                    String data = ds1.getValue(String.class);
                                    trenerListData.add(data);
                                }
                                reference.child(trenerId).child(ds.getKey()).child("data").setValue(trenerListData);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    ArrayList<String> dataList = new ArrayList<>();
                    for (DataSnapshot ds1 : ds.child("data").getChildren()) {
                        String data = ds1.getValue(String.class);
                        dataList.add(data);
                    }
                    if (!dataList.get(0).equals("03:00;client_id")) {
                        Integer lastWeek = 0;
                        if (weekTrener-myWeek>0){
                            lastWeek = 7*(weekTrener-myWeek);
                            dayCountReshulf.add(lastWeek+Integer.parseInt(ds.getKey()));
                            slotDateListReshufl.add(dataList);
                        }else{
                            dayCount.add(lastWeek+Integer.parseInt(ds.getKey()));
                            slotDateList.add(dataList);
                        }
                    }
                    Log.d("aaachekc ListData = ", dataList.get(0));
                }
                for (int i = 0; i < dayCountReshulf.size(); i++) {
                    dayCount.add(dayCountReshulf.get(i));
                    slotDateList.add(slotDateListReshufl.get(i));
                }
                TimeSlotAdapter adapter = new TimeSlotAdapter(slotDateList, dayCount,trenerId);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.d("aacheckService", "no");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}