package com.example.fitnessapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.couch.TimeSlotCouchAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.ViewHolder>{
    private  List<ArrayList<String>> timeSlots;
    private ArrayList<Integer> dataCount;

    public TimeSlotAdapter( List<ArrayList<String>> timeSlots, ArrayList<Integer> dataCount) {
        this.timeSlots = timeSlots;
        this.dataCount = dataCount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_time_slot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Calendar c = Calendar.getInstance();
        c.add(c.DATE, dataCount.get(position));
        String date_month = new SimpleDateFormat("d/M/yyyy").format(c.getTime());

        holder.timeSlotTextView.setText(date_month);

        TimeSlotCouchAdapter adapter = new TimeSlotCouchAdapter(timeSlots.get(position));
        holder.rec.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return timeSlots.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView timeSlotTextView;
        RecyclerView rec;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rec = itemView.findViewById(R.id.slot_item_recycler);
            timeSlotTextView = itemView.findViewById(R.id.time_slot);
        }
    }
}
