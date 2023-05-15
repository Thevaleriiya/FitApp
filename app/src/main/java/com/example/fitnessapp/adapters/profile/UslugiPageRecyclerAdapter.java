package com.example.fitnessapp.adapters.profile;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.calendar.RecycleCalendarAdapter;
import com.example.fitnessapp.classes.Raspisanie;
import com.example.fitnessapp.classes.Service;
import com.example.fitnessapp.classes.UserUslugi;

import java.util.ArrayList;

public class UslugiPageRecyclerAdapter extends RecyclerView.Adapter<UslugiPageRecyclerAdapter.ViewHolder> {

    ArrayList<UserUslugi> uslugiList;
    ArrayList<Service> servicesList;
    Context context;

    public UslugiPageRecyclerAdapter(ArrayList<UserUslugi> uslugiList, ArrayList<Service> serviceList) {
        this.uslugiList = uslugiList;
        this.servicesList = serviceList;
    }


    @NonNull
    @Override
    public UslugiPageRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.uslugi_page_items, parent, false);
        return new UslugiPageRecyclerAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull UslugiPageRecyclerAdapter.ViewHolder holder, int position) {
        Log.d("aaaa AdapterName", uslugiList.get(position).getDate_start());
        holder.name.setText(servicesList.get(position).getName());
        holder.date_start.setText("Дата начала: "+uslugiList.get(position).getDate_start());
        holder.date_end.setText("Дата конца: "+uslugiList.get(position).getDate_end());
        holder.zamorozka.setText("Заморозка: "+servicesList.get(position).getZamorozka());
        holder.poseshenie.setText("Время посещения: "+servicesList.get(position).getVisit());
        holder.uslug_date.setText("Срок действия: "+servicesList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return uslugiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, uslug_date, date_start, date_end, zamorozka, poseshenie ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.uslugi_item_name);
            uslug_date = itemView.findViewById(R.id.uslugi_item_date);
            date_start= itemView.findViewById(R.id.uslugi_item_active_start);
            date_end= itemView.findViewById(R.id.uslugi_item_active_end);
           zamorozka = itemView.findViewById(R.id.uslugi_item_zamarozka);
           poseshenie = itemView.findViewById(R.id.uslugi_item_poseshenie);
        }
    }
}
