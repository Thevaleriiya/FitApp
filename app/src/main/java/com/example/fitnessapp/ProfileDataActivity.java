package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.fitnessapp.fragmentMenu.profile.UserZapisFragment;
import com.example.fitnessapp.fragmentMenu.profile.UslugiFragment;

public class ProfileDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data);
        Bundle bundle = getIntent().getExtras();
        Integer dataNumberFragment = bundle.getInt("profile_data");
        Log.d("aaaa Data intent = ", Integer.toString(bundle.getInt("profile_data")));
        switch (dataNumberFragment) {
            case 0: {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.profile_data_frame, UserZapisFragment.class, null)
                        .commit();
                break;
            }
            case 1: {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.profile_data_frame, UslugiFragment.class, null)
                        .commit();
                break;
            }
            default: {

            }
        }
    }
}