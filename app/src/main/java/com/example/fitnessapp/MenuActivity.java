package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

//import com.example.fitnessapp.databinding.ActivityMainBinding;
import com.example.fitnessapp.fragmentMenu.CalendarFragment;
import com.example.fitnessapp.fragmentMenu.CoachFragment;
import com.example.fitnessapp.fragmentMenu.ProfileFragment;
import com.example.fitnessapp.fragmentMenu.ShopFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);

        openFragment(new CalendarFragment());

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.calendar:
                        openFragment(new CalendarFragment());
                        return true;
                    case R.id.shop:
                        openFragment(new ShopFragment());
                        return true;
                    case R.id.coach:
                        openFragment(new CoachFragment());
                        return true;
                    case R.id.profile:
                        openFragment(new ProfileFragment());
                        return true;
                }
                return false;
            }
        });
    }

    void openFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}