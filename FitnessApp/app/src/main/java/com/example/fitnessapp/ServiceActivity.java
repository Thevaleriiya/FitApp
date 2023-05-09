package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.adapters.ServiceAdapter;
import com.example.fitnessapp.classes.Service;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServiceActivity extends AppCompatActivity {

    TextView tv_name;
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference database;
    ServiceAdapter serviceAdapter;
    ArrayList<Service> list;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        tv_name = findViewById(R.id.name_razdel);
        recyclerView = findViewById(R.id.rv_service);
        dialog = new Dialog(ServiceActivity.this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        database = FirebaseDatabase.getInstance().getReference("card");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("pos").toString();

        tv_name.setText(name);
        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ServiceAdapter.OnStateClickListener stateClickListener = new ServiceAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Service state, int position) {

                Toast.makeText(getApplicationContext(), "Был выбран пункт " + state.getName(),
                        Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name",state.getName());

                Log.d("MyTag", state.getName());
                editor.putString("srok", state.getSrok());
                editor.putString("time",state.getTime());
                editor.putString("zamorozka",state.getZamorozka());
                editor.putString("price",state.getPrice());
                editor.apply();
                Log.d("MyTag", "Данные переданы");
                showCustomDialog();
            }
        };

        list = new ArrayList<>();
        serviceAdapter = new ServiceAdapter(this,list,stateClickListener);
        recyclerView.setAdapter(serviceAdapter);

        Toast.makeText(ServiceActivity.this,"Клик пик", Toast.LENGTH_SHORT).show();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Service service = dataSnapshot.getValue(Service.class);
                    if (name.equals(service.getId())) {
                        list.add(service);
                    }
                }
                serviceAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showCustomDialog() {
        BuyFragment byFragm = new BuyFragment();
        byFragm.show(getSupportFragmentManager(), "MyFragments");
//        dialog.setContentView(R.layout.fragment_buy);
//        Log.d("MyTag","123424234");
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setCancelable(true);
//        dialog.show();
//
//        getSupportFragmentManager().beginTransaction().add(R.id.fragment_buy, new BuyFragment()).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}