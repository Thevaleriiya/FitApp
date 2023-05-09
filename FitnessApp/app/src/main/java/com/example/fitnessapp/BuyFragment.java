package com.example.fitnessapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class BuyFragment extends DialogFragment {

    String name,srok,time,zamorozka,price;
    TextView name_tv,srok_tv,time_tv,zamorozka_tv;
    Button price_tv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy, container, false);

        Log.d("MyTag","123424234");
        name_tv = view.findViewById(R.id.nameCard);
        srok_tv = view.findViewById(R.id.srokCard);
        time_tv = view.findViewById(R.id.timeCard);
        zamorozka_tv = view.findViewById(R.id.zamorozkaCard);
        price_tv = view.findViewById(R.id.ButtonCard);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        name = sharedPreferences.getString("name", "");
        srok = sharedPreferences.getString("srok", "");
        time = sharedPreferences.getString("time", "");
        zamorozka = sharedPreferences.getString("zamorozka", "");
        price = sharedPreferences.getString("price", "");

        Log.d("MyTag", "Но не до конца");

        name_tv.setText(name);
        srok_tv.setText(srok);
        time_tv.setText(time);
        zamorozka_tv.setText(zamorozka);
        price_tv.setText("Оплатить "+price);

        return view;
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