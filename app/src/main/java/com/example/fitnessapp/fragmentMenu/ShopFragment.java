package com.example.fitnessapp.fragmentMenu;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.example.fitnessapp.ServiceActivity;
import com.example.fitnessapp.adapters.CardAdapter;
import com.example.fitnessapp.adapters.SaleAdapter;
import com.example.fitnessapp.classes.SaleVariant;

import java.util.ArrayList;

public class ShopFragment extends Fragment {

    RecyclerView rv;
    ArrayList<SaleVariant> saleVariants;
    SaleAdapter saleAdapter;
    GridView gridView;

    int [] numImage = {R.drawable.fitness,R.drawable.pool, R.drawable.personal,R.drawable.dop};
    String [] numberFun = {"Стандартные","С бассейном","С тренером","Дополнительно"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        rv = view.findViewById(R.id.rv_sale);
        gridView = view.findViewById(R.id.grid_view);

        CardAdapter cardAdapter = new CardAdapter(requireContext(),numImage,numberFun);
        gridView.setAdapter(cardAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(requireContext(),numberFun[+position], Toast.LENGTH_LONG).show();
                Intent intent = new Intent(requireContext(), ServiceActivity.class);
                intent.putExtra("pos",numberFun[+position]);
                startActivity(intent);
            }
        });


        Integer[] back = {R.drawable.second, R.drawable.first};

        saleVariants = new ArrayList<>();
        for (int i =0;i<back.length; i++){
            SaleVariant model = new SaleVariant(back[i]);
            saleVariants.add(model);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());

        saleAdapter = new SaleAdapter(requireContext(),saleVariants);
        rv.setAdapter(saleAdapter);

        return view;
    }
}