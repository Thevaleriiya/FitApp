package com.example.fitnessapp.adapters.couch;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.adapters.TimeSlotAdapter;
import com.example.fitnessapp.classes.UserZapis;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class TimeSlotCouchAdapter extends RecyclerView.Adapter<TimeSlotCouchAdapter.ViewHolder> {
    private List<String> timeSlots;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    DatabaseReference referenceTwo;
    private FirebaseAuth mAuth;
    private String trenerID;
    private Integer dataPos;
    private String dataTime;

    public TimeSlotCouchAdapter(List<String> timeSlots, String trenerId, Integer dataPosition, String dataTime) {
        this.timeSlots = timeSlots;
        this.trenerID = trenerId;
        this.dataPos = dataPosition;
        this.dataTime = dataTime;
    }

    @NonNull
    @Override
    public TimeSlotCouchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_slot_time_rec_items, parent, false);
        mAuth = FirebaseAuth.getInstance();
        return new TimeSlotCouchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeSlotCouchAdapter.ViewHolder holder, int position) {
        String[] timeSlot = timeSlots.get(position).split(";");
        holder.timeSlot.setText(timeSlot[0]);
        if (timeSlot[1].equals("client_id")){
            holder.cons.setAlpha(0.6f);
        }
        holder.cons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("aaaaCons", "нажали на кнопку");
                firebaseDatabase = FirebaseDatabase.getInstance();
                reference = firebaseDatabase.getReference("zapic");
                referenceTwo = firebaseDatabase.getReference("user");
                reference.child(trenerID)
                        .child(Integer.toString(dataPos))
                        .child("data").child(Integer.toString(holder.getAdapterPosition()))
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String data = snapshot.getValue(String.class);
                                String[] dataArray = data.split(";");
                                Log.d("aaaaConsВфеф", data);
                                if (dataArray[1].equals("client_id")){
                                    reference.child(trenerID)
                                            .child(Integer.toString(dataPos))
                                            .child("data").child(Integer.toString(holder.getAdapterPosition()))
                                            .setValue(dataArray[0]+";"+mAuth.getUid());

                                    referenceTwo.child(mAuth.getUid())
                                            .child("zapis")
                                            .child("0").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    UserZapis userZap = new UserZapis(dataTime,timeSlot[0],trenerID, 0);
                                                    referenceTwo.child(mAuth.getUid())
                                                            .child("zapis")
                                                            .child("0").child(Long.toString(snapshot.getChildrenCount())).setValue(userZap);
                                                    Toast.makeText(holder.itemView.getContext(), "Вы записались",
                                                            Toast.LENGTH_SHORT).show();
                                                    holder.cons.setAlpha(0.6f);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });
                                }else {
                                    Toast.makeText(holder.itemView.getContext(), "Время занято",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return timeSlots.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView timeSlot;
        ConstraintLayout cons;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeSlot = itemView.findViewById(R.id.time_slot_rec_text);
            cons = itemView.findViewById(R.id.slot_time_item_layout);
        }
    }
}
