package com.example.fitnessapp.adapters.calendar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.classes.Raspisanie;

import java.util.ArrayList;

public class  RecycleCalendarAdapter extends RecyclerView.Adapter<RecycleCalendarAdapter.ViewHolder> {

    ArrayList<Raspisanie> raspisanieList;

    public RecycleCalendarAdapter(ArrayList<Raspisanie> raspisanieList) {
        this.raspisanieList = raspisanieList;
    }



    @NonNull
    @Override
    public RecycleCalendarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_view_recycle_item, parent, false);
        return new RecycleCalendarAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecycleCalendarAdapter.ViewHolder holder, int position) {
        Log.d("aaaa AdapterName", raspisanieList.get(position).getName());
        holder.name.setText(raspisanieList.get(position).getName());
        holder.trener_name.setText("Тренер: " + raspisanieList.get(position).getTrener_name());
        holder.time.setText("Время: " + raspisanieList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return raspisanieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView trener_name, time, name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trener_name = itemView.findViewById(R.id.raspisanie_trener_item);
            name = itemView.findViewById(R.id.raspisanie_name_item);
            time = itemView.findViewById(R.id.raspisanie_time_item);
        }
    }
}
