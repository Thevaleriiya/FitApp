package com.example.fitnessapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.classes.SaleVariant;

import java.util.ArrayList;

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.ViewHolder> {

    ArrayList<SaleVariant> saleVariants;
    Context context;

    public SaleAdapter(Context context, ArrayList<SaleVariant> saleVariants){
        this.context = context;
        this.saleVariants = saleVariants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_sale,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(saleVariants.get(position).getLogo());


    }

    @Override
    public int getItemCount() {
        return saleVariants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
