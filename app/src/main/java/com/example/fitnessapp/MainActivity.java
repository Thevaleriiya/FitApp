package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText login_ed, password_ed;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login_ed.getText().toString().isEmpty() || password_ed.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Поля не могут быть пустыми!", Toast.LENGTH_LONG).show();
                } else {
                    mAuth.signInWithEmailAndPassword(login_ed.getText().toString(), password_ed.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent i = new Intent(MainActivity.this, MenuActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(MainActivity.this, "Аккаунта не существует или возникли ошибки", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if (cUser != null){
            Intent i = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(i);
            Toast.makeText(MainActivity.this, "Пользователь найден", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(MainActivity.this, "Пользователь не найден", Toast.LENGTH_LONG).show();
        }
    }

    public void init(){
        mAuth = FirebaseAuth.getInstance();
        button=findViewById(R.id.loginButton);
        login_ed = findViewById(R.id.username);
        password_ed = findViewById(R.id.userpassword);
    }
}