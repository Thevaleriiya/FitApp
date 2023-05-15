package com.example.fitnessapp.adapters.couch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.TimeSlotAdapter;

import java.util.List;

public class TimeSlotCouchAdapter  extends RecyclerView.Adapter<TimeSlotCouchAdapter.ViewHolder>{
    private List<String> timeSlots;

    public TimeSlotCouchAdapter(List<String> timeSlots) {
        this.timeSlots = timeSlots;
    }

    @NonNull
    @Override
    public TimeSlotCouchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_slot_time_rec_items, parent, false);
        return new TimeSlotCouchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeSlotCouchAdapter.ViewHolder holder, int position) {
        String[] timeSlot = timeSlots.get(position).split(";");
        holder.timeSlot.setText(timeSlot[0]);
    }

    @Override
    public int getItemCount() {
        return timeSlots.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView timeSlot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeSlot= itemView.findViewById(R.id.time_slot_rec_text);
        }
    }
}
