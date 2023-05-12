package com.example.fitnessapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitnessapp.R;

import java.util.ConcurrentModificationException;

public class CardAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private int[] numImage;
    private String[] numberFun;

    public CardAdapter(Context context,int[] numImage, String[] numberFun) {
        this.context = context;
        this.numImage = numImage;
        this.numberFun = numberFun;
    }

    @Override
    public int getCount() {
        return numImage.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater ==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = inflater.inflate(R.layout.row_item_card,null);
        }

        ImageView imageView = convertView.findViewById(R.id.image_view);
        TextView textView = convertView.findViewById(R.id.textView);

        imageView.setPadding(0, 0, 0, 0);
        imageView.setImageResource(numImage[position]);
        textView.setText(numberFun[position]);
        return convertView;
    }
}
