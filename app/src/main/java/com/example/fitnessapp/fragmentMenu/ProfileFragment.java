package com.example.fitnessapp.fragmentMenu;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fitnessapp.AboutFitActivity;
import com.example.fitnessapp.R;

public class ProfileFragment extends Fragment {

    Button aboutFit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        aboutFit = view.findViewById(R.id.about_fit);



        aboutFit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), AboutFitActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}