package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TrainerTimeActivity extends AppCompatActivity {

    TextView nameRzd;
    TextView surname, name, about;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_time);
        nameRzd = findViewById(R.id.razdname);
        surname = findViewById(R.id.textView3);
        name = findViewById(R.id.textView4);
        about = findViewById(R.id.opisanieTr);
        btn = findViewById(R.id.chooseTime);

        String putName = getIntent().getStringExtra("name");
        String putSurname = getIntent().getStringExtra("surname");
        String putAbout = getIntent().getStringExtra("about");
        String trenerId= getIntent().getStringExtra("trener_id");

        name.setText(putName);
        surname.setText(putSurname);
        about.setText(putAbout);

        nameRzd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainerTimeActivity.this,ZapisActivity.class);
                intent.putExtra("trener_id",trenerId);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}