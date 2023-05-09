package com.example.fitnessapp.fragmentMenu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.example.fitnessapp.classes.Trainer;
import com.example.fitnessapp.adapters.Adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class CoachFragment extends Fragment {

    ListView listView;
    ArrayList<Trainer> arrayList;
    Adapter adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_coach, container, false);

        listView = view.findViewById(R.id.trainer_lv);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("trainer");

        arrayList = new ArrayList<Trainer>();

        adapter = new com.example.fitnessapp.adapters.Adapter(requireContext(),R.layout.items_trainers, arrayList);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(requireContext(), "любой другой", Toast.LENGTH_SHORT).show();
                arrayList.clear();
                //Toast.makeText(requireContext(), "любой", Toast.LENGTH_SHORT).show();
                for (DataSnapshot ds:snapshot.getChildren()){
                   // Toast.makeText(requireContext(), "nhenen", Toast.LENGTH_SHORT).show();
                    //Log.d("firebase1234567890","Что-нибудь просто");
                    String surname = ds.child("surname").getValue(String.class);
                    String name = ds.child("name").getValue(String.class);
                    String about = ds.child("about").getValue(String.class);
                    String foto = ds.child("foto").getValue(String.class);
                    String id = ds.child("id").getValue(String.class);
                    //ArrayList<String> spec = ds.child("spec").getValue(ArrayList.class);
                    Log.d("firebase1234567890", id);
                    arrayList.add(new Trainer(about, foto, id, name, surname, new ArrayList<String>(Arrays.asList("spec","spec2"))));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setAdapter(adapter);

        return view;
    }
}