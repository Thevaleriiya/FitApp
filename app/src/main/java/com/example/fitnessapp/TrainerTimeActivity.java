package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TrainerTimeActivity extends AppCompatActivity {

    TextView nameRzd;
    TextView surname, name, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_time);
        nameRzd = findViewById(R.id.razdname);
        surname = findViewById(R.id.textView3);
        name = findViewById(R.id.textView4);
        about = findViewById(R.id.opisanieTr);

        String putName = getIntent().getStringExtra("name");
        String putSurname = getIntent().getStringExtra("surname");
        String putAbout = getIntent().getStringExtra("about");

        name.setText(putName);
        surname.setText(putSurname);
        about.setText(putAbout);

        nameRzd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}