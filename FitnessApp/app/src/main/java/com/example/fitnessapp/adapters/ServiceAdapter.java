package com.example.fitnessapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.ServiceActivity;
import com.example.fitnessapp.classes.Service;

import java.security.Provider;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder> {

    public interface OnStateClickListener{
        void onStateClick(Service service, int position);
    }

    Context context;
    ArrayList<Service> list;
    private final OnStateClickListener onClickListener;

    public ServiceAdapter(Context context, ArrayList<Service> list, OnStateClickListener onClickListener) {
        this.context = context;
        this.list = list;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_service,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Service service = list.get(position);
        holder.id.setText(service.getId());
        holder.name.setText(service.getName());
        holder.srok.setText(service.getSrok());
        holder.time.setText(service.getTime());
        holder.visit.setText(service.getVisit());
        holder.price.setText(service.getPrice());
        holder.zamorozka.setText(service.getZamorozka());

        holder.btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                // вызываем метод слушателя, передавая ему данные
                onClickListener.onStateClick(service, holder.getAdapterPosition());
            }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id,name,srok,time,visit,price,zamorozka;
        Button btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.service_id);
            name = itemView.findViewById(R.id.service_name);
            srok = itemView.findViewById(R.id.service_srok);
            time = itemView.findViewById(R.id.service_time);
            visit = itemView.findViewById(R.id.service_visit);
            price = itemView.findViewById(R.id.service_price);
            zamorozka = itemView.findViewById(R.id.service_zamor);
            btn = itemView.findViewById(R.id.btn_service);
        }
    }
}
