package com.example.fitnessapp.fragmentMenu.profile;

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

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.profile.UslugiPageRecyclerAdapter;
import com.example.fitnessapp.classes.Service;
import com.example.fitnessapp.classes.UserUslugi;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class UslugiPageFragment extends Fragment {
    private RecyclerView rec;
    private Integer positionPage;
    UslugiPageRecyclerAdapter recAdapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    DatabaseReference referenceTwo;
    private FirebaseAuth mAuth;

    private ArrayList<UserUslugi> userUslugiList;
    private ArrayList<Service> servicesList;
    private ArrayList<Service> servicesAdapterList;


    public UslugiPageFragment(Integer positionPage) {
        this.positionPage = positionPage;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_uslugi_page, container, false);
        init(view);

        firebaseDatabase = FirebaseDatabase.getInstance();
        referenceTwo = firebaseDatabase.getReference("card");
        reference = firebaseDatabase.getReference("user");

        if (positionPage == null) {
            positionPage = 0;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        rec.setLayoutManager(layoutManager);
        rec.setItemAnimator(new DefaultItemAnimator());
        userUslugiList = new ArrayList<>();
        servicesList = new ArrayList<>();
        servicesAdapterList = new ArrayList<>();


        referenceTwo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds1 : snapshot.getChildren()) {
                    Service service = ds1.getValue(Service.class);
                    servicesList.add(service);
                }
                reference.child(mAuth.getCurrentUser().getUid()).child("uslugi").child(Integer.toString(positionPage)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            UserUslugi userUslug = ds.getValue(UserUslugi.class);
                            if (positionPage==0 && checkDate(userUslug.getDate_end())){
                                Log.d("Key",ds.getKey());
                                String dellUslugKey = ds.getKey();
                                reference.child(mAuth.getCurrentUser().getUid()).child("uslugi").child("1").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        reference.child(mAuth.getCurrentUser().getUid()).child("uslugi").child("1").child(Long.toString(snapshot.getChildrenCount())).setValue(userUslug);
                                        reference.child(mAuth.getCurrentUser().getUid()).child("uslugi").child("0").child(dellUslugKey).setValue(null);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                                Log.d("aaaZachel", "gol");
                            }else{
                                userUslugiList.add(userUslug);
                            }
                            for (int i = 0; i < servicesList.size(); i++) {
                                if (userUslug.getUsluga_number().equals(servicesList.get(i).getNumber())){
                                    servicesAdapterList.add(servicesList.get(i));
                                }
                            }

                        }
                        recAdapter = new UslugiPageRecyclerAdapter(userUslugiList,servicesAdapterList);
                        rec.setAdapter(recAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("aacheckService", "no");
            }
        });

        return view;
    }

    private boolean checkDate(String d2){
        Calendar c = Calendar.getInstance();
        String d1 = new SimpleDateFormat("d/M/yyyy").format(c.getTime());

        String pattern = "d/M/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        try {
            cal1.setTime(sdf.parse(d1));
            cal2.setTime(sdf.parse(d2));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        if (cal1.after(cal2)) {
            Log.d("aaaa-----",""+ d1+ "is after "+d2);
            return true;
        }
        else if (cal1.before(cal2)) {
            Log.d("aaaa-----",""+ d1+ "is befor "+d2);
        }
        else if (cal1.equals(cal2)) {
            System.out.print("Both dates are equal");
        }
        return false;
    }

    private void init(View view) {
        rec = view.findViewById(R.id.uslugi_page_recycler);

    }
}