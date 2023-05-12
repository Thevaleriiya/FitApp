package com.example.fitnessapp.adapters.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.SaleAdapter;
import com.example.fitnessapp.classes.SaleVariant;

import java.util.ArrayList;

public class RecyclerCalendarAdapter extends RecyclerView.Adapter<RecyclerCalendarAdapter.ViewHolder> {

    ArrayList<String> saleVariants;
    Context context;

    public RecyclerCalendarAdapter(Context context, ArrayList<String> saleVariants){
        this.context = context;
        this.saleVariants = saleVariants;
    }

    @NonNull
    @Override
    public RecyclerCalendarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calendar_view_recycle_item,parent,false);
        return new RecyclerCalendarAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerCalendarAdapter.ViewHolder holder, int position) {
        holder.imageView.setText(saleVariants.get(position));


    }

    @Override
    public int getItemCount() {
        return saleVariants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.txt_calendar_recycler);
        }
    }
}

