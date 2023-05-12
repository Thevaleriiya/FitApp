package com.example.fitnessapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fitnessapp.R;
import com.example.fitnessapp.classes.Service;
import com.example.fitnessapp.classes.Trainer;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Trainer> {

    private Context mContext;
    private int mResource;

    public Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Trainer> objects) {
        super(context, resource, objects);
        this.mResource = resource;
        this.mContext = context;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        view = LayoutInflater.from(mContext).inflate(mResource,parent, false);

        TextView surname = view.findViewById(R.id.tvSurname);
        TextView name = view.findViewById(R.id.tvName);
        TextView about = view.findViewById(R.id.tvAbout);

        surname.setText(getItem(position).getSurname());
        name.setText(getItem(position).getName());
        about.setText(getItem(position).getAbout());

        return view;
    }
}
