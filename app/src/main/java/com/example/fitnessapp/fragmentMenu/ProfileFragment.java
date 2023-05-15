package com.example.fitnessapp.fragmentMenu;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowId;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.AboutFitActivity;
import com.example.fitnessapp.ProfileDataActivity;
import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.calendar.RecycleCalendarAdapter;
import com.example.fitnessapp.classes.Raspisanie;
import com.example.fitnessapp.classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    Button aboutFit;
    Button uslugiFit;
    Button myZapis;
    TextView userMail;
    TextView userName;

    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        aboutFit = view.findViewById(R.id.about_fit);

        Log.d("aaa check Profile Mail = ", mAuth.getCurrentUser().getEmail());
        init(view);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("user");
        reference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    User user = snapshot.getValue(User.class);
                    userName.setText(user.getName());
                }else{
                    Toast.makeText(requireContext(), "Ошибка. Данные не найдены", Toast.LENGTH_SHORT).show();
                    Log.d("aaa check mAuth", "notSnapshot");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(requireContext(), "Ошибка соединения", Toast.LENGTH_SHORT).show();
                Log.d("aaa check mAuth", "No");
            }
        });


        aboutFit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), AboutFitActivity.class);
                startActivity(intent);
            }
        });
        myZapis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ProfileDataActivity.class);
                intent.putExtra("profile_data", 0);
                startActivity(intent);
            }
        });


        uslugiFit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(requireContext(), ProfileDataActivity.class);
                intent.putExtra("profile_data", 1);
                startActivity(intent);
            }
        });

        return view;
    }

    private void init(View view){
        uslugiFit = view.findViewById(R.id.profile_uslugi);
        myZapis = view.findViewById(R.id.btn_new);
        userMail = view.findViewById(R.id.profile_email);
        userName = view.findViewById(R.id.profile_name);


        userMail.setText(mAuth.getCurrentUser().getEmail());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }
}