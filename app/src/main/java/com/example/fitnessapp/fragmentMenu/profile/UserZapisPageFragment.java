package com.example.fitnessapp.fragmentMenu.profile;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.fitnessapp.adapters.ServiceAdapter;
import com.example.fitnessapp.adapters.profile.UslugiPageRecyclerAdapter;
import com.example.fitnessapp.adapters.profile.ZapisPageRecyclerAdapter;
import com.example.fitnessapp.classes.Service;
import com.example.fitnessapp.classes.Trainer;
import com.example.fitnessapp.classes.UserUslugi;
import com.example.fitnessapp.classes.UserZapis;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class UserZapisPageFragment extends Fragment {
    private RecyclerView rec;
    private Integer positionPage;
    ZapisPageRecyclerAdapter recAdapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    DatabaseReference referenceTwo;
    DatabaseReference referenceThree;
    private FirebaseAuth mAuth;

    private ArrayList<UserZapis> userZapisList;
    private ArrayList<Trainer> trenerList;
    private ArrayList<Trainer> trenerAdapterList;


    public UserZapisPageFragment(Integer pos) {
        this.positionPage = pos;

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_zapis_page, container, false);
        init(view);
        if (positionPage == null) {
            positionPage = 0;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        rec.setLayoutManager(layoutManager);
        rec.setItemAnimator(new DefaultItemAnimator());
        userZapisList = new ArrayList<>();
        trenerList = new ArrayList<>();
        trenerAdapterList = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();

        referenceThree = firebaseDatabase.getReference("zapic");
        referenceTwo = firebaseDatabase.getReference("trainer");
        reference = firebaseDatabase.getReference("user");


        referenceTwo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds1 : snapshot.getChildren()) {
                    Trainer service = ds1.getValue(Trainer.class);
                    trenerList.add(service);
                }
                reference.child(mAuth.getCurrentUser().getUid()).child("zapis").child(Integer.toString(positionPage)).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            UserZapis zapis = ds.getValue(UserZapis.class);
                            if (positionPage==0 && checkDate(zapis.getData())){
                                Log.d("Key",ds.getKey());
                                String dellUslugKey = ds.getKey();
                                reference.child(mAuth.getCurrentUser().getUid()).child("zapis").child("1").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (zapis.getStatus()==0) zapis.setStatus(2);

                                        reference.child(mAuth.getCurrentUser().getUid()).child("zapis").child("1").child(Long.toString(snapshot.getChildrenCount())).setValue(zapis);
                                        reference.child(mAuth.getCurrentUser().getUid()).child("zapis").child("0").child(dellUslugKey).setValue(null);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }else{
                                userZapisList.add(zapis);
                            }

                            for (int i = 0; i < trenerList.size(); i++) {
                                if (zapis.getTrener().equals(trenerList.get(i).getId())){
                                    trenerAdapterList.add(trenerList.get(i));
                                }
                            }

                        }

                        ZapisPageRecyclerAdapter.OnStateClickListener stateClickListener = new ZapisPageRecyclerAdapter.OnStateClickListener() {
                            @Override
                            public void onStateClick(UserZapis usZapis, int position) {
                                Log.d("aaaTouch = ", "Нажали на кнопку отмены");
                                String[] dayWeek = usZapis.getDay_of_week().split(";");
                                reference.child(mAuth.getCurrentUser().getUid()).child("zapis").child("0").child(Integer.toString(position)).child("status").setValue(1);
                                referenceThree.child(usZapis.getTrener()).child(dayWeek[0]).child("data").child(dayWeek[1]).setValue(usZapis.getTime()+";client_id");
                            }
                        };

                        recAdapter = new ZapisPageRecyclerAdapter(userZapisList,trenerAdapterList,stateClickListener);
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
        rec = view.findViewById(R.id.zapis_page_recycler);

    }
}