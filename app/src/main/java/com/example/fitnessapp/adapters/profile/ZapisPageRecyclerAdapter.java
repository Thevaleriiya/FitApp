package com.example.fitnessapp.adapters.profile;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.ServiceAdapter;
import com.example.fitnessapp.classes.Service;
import com.example.fitnessapp.classes.Trainer;
import com.example.fitnessapp.classes.UserUslugi;
import com.example.fitnessapp.classes.UserZapis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ZapisPageRecyclerAdapter extends RecyclerView.Adapter<ZapisPageRecyclerAdapter.ViewHolder> {

    ArrayList<UserZapis> zapisList;
    ArrayList<Trainer> trenerList;
    Context context;

    public interface OnStateClickListener{
        void onStateClick(UserZapis service, int position);
    }

    private final ZapisPageRecyclerAdapter.OnStateClickListener onClickListener;

    public ZapisPageRecyclerAdapter(ArrayList<UserZapis> uslugiList, ArrayList<Trainer> serviceList, ZapisPageRecyclerAdapter.OnStateClickListener onClickListener) {
        this.zapisList = uslugiList;
        this.trenerList = serviceList;
        this.onClickListener = onClickListener;
    }


    @NonNull
    @Override
    public ZapisPageRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zapis_page_items, parent, false);
        return new ZapisPageRecyclerAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ZapisPageRecyclerAdapter.ViewHolder holder, int position) {
        Log.d("aaaa AdapterName", zapisList.get(position).getData());
        holder.name.setText(trenerList.get(position).getSurname()+" "+trenerList.get(position).getName());
        holder.time.setText("Время: "+zapisList.get(position).getTime());
        holder.status.setText("Вы записаны");
        holder.status.setTextColor(Color.GRAY);

        if (zapisList.get(position).getStatus()==1){
            holder.status.setText("Отменено");
            holder.btn_otmena.setVisibility(View.INVISIBLE);
        }
        else {
            holder.status.setText("Пройдено");
        }
        switch (checkDate(zapisList.get(position).getData())){
            case 0:{
                holder.date.setText("Дата: "+zapisList.get(position).getData());

                holder.status.setTextColor(Color.BLACK);
                holder.btn_otmena.setVisibility(View.INVISIBLE);
                break;
            }
            case 1: {
                holder.date.setText("Дата: "+zapisList.get(position).getData());
                break;
            }
            case 2:{
                holder.date.setText("Дата: Сегодня");
                holder.date.setTextColor(Color.DKGRAY);
                break;
            }
            default:{
                holder.date.setText("None");
                holder.date.setTextColor(Color.RED);
                break;
            }


        }

        holder.btn_otmena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserZapis userZapis = zapisList.get(holder.getAdapterPosition());
                if (userZapis.getStatus()!=1){
                    onClickListener.onStateClick(userZapis, holder.getAdapterPosition());
                    holder.status.setText("Отменено");
                    holder.status.setTextColor(Color.RED);
                }
                holder.btn_otmena.setVisibility(View.INVISIBLE);
            }
        });

    }

    private Integer checkDate(String d2){
        Calendar c = Calendar.getInstance();
        String d1 = new SimpleDateFormat("d/M/yyyy").format(c.getTime());

        String pattern = "d/M/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        try {
            cal1.setTime(sdf.parse(d1));
            cal2.setTime(sdf.parse(d2));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        if (cal1.after(cal2)) {
            Log.d("aaaa-----",""+ d1+ "is after "+d2);
            return 0;
        }
        else if (cal1.before(cal2)) {
            Log.d("aaaa-----",""+ d1+ "is befor "+d2);
            return 1;
        }
        else if (cal1.equals(cal2)) {
            System.out.print("Both dates are equal");
            return 2;
        }
        return 3;
    }


    @Override
    public int getItemCount() {
        return zapisList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, time, status ;
        Button btn_otmena;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.zapis_item_name);
            date = itemView.findViewById(R.id.zapis_item_date);
            time = itemView.findViewById(R.id.zapis_item_time);
            status = itemView.findViewById(R.id.zapic_item_status);
            btn_otmena = itemView.findViewById(R.id.zapis_btn_otmena);
        }
    }
}
