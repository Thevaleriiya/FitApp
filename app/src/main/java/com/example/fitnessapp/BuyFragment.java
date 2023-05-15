package com.example.fitnessapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.adapters.profile.UslugiPageRecyclerAdapter;
import com.example.fitnessapp.classes.UserUslugi;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class BuyFragment extends DialogFragment {

    String name, srok, time, zamorozka, price, number;
    TextView name_tv, srok_tv, time_tv, zamorozka_tv;
    Button price_tv;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    private FirebaseAuth mAuth;
    private ArrayList<UserUslugi> userUslugiList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy, container, false);
        init(view);
        mAuth = FirebaseAuth.getInstance();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        name = sharedPreferences.getString("name", "");
        srok = sharedPreferences.getString("srok", "");
        Log.d("aaaSrok=", srok);
        time = sharedPreferences.getString("time", "");
        zamorozka = sharedPreferences.getString("zamorozka", "");
        price = sharedPreferences.getString("price", "");
        number = sharedPreferences.getString("number", "0");

        name_tv.setText(name);
        srok_tv.setText(srok);
        time_tv.setText(time);
        zamorozka_tv.setText(zamorozka);
        price_tv.setText("Оплатить " + price);

        price_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopUslugu();
            }
        });

        return view;
    }

    private void shopUslugu() {
        Log.d("MyTag", number);
        Calendar c = Calendar.getInstance();
        String date_month = new SimpleDateFormat("d/M/yyyy").format(c.getTime());

        Integer dayCount = 1;
        String[] splitSrok = srok.split(" ");
        Integer countData = Integer.parseInt(splitSrok[0]);
        Log.d("aaaa day = ", Integer.toString(countData));


        if (splitSrok[1].equals("мес") || splitSrok[1].equals("месяца") || splitSrok[1].equals("месяцев") || splitSrok[1].equals("месяц")) {
            Log.d("aaDatecheck", "mes");
            c.add(c.MONTH, countData);
        } else if (splitSrok[1].equals("дней") || splitSrok[1].equals("дня") || splitSrok[1].equals("день")) {
            Log.d("aaDatecheck", "day");
            c.add(c.DATE, countData);
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("user");
        reference.child(mAuth.getCurrentUser().getUid()).child("uslugi").child("0").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean checkDuplicate = false;
                userUslugiList = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    UserUslugi userUslug = ds.getValue(UserUslugi.class);
                    if (userUslug.getUsluga_number().equals(number)) {
                        checkDuplicate = true;
                        break;
                    }
                    userUslugiList.add(userUslug);

                }
                if (!checkDuplicate) {
                    String date_month_new = new SimpleDateFormat("d/M/yyyy").format(c.getTime());
                    UserUslugi newUslugi = new UserUslugi(date_month, date_month_new, number);
                    userUslugiList.add(newUslugi);
                    reference.child(mAuth.getCurrentUser().getUid()).child("uslugi").child("0").setValue(userUslugiList);
                    Toast.makeText(requireContext(), "Оплата прошла успешно",
                            Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(requireContext(), "Данная услуга уже куплена",
                            Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void init(View view) {
        name_tv = view.findViewById(R.id.nameCard);
        srok_tv = view.findViewById(R.id.srokCard);
        time_tv = view.findViewById(R.id.timeCard);
        zamorozka_tv = view.findViewById(R.id.zamorozkaCard);
        price_tv = view.findViewById(R.id.ButtonCard);
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ConstraintLayout.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

    }
}