package com.example.ubs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Activity2 extends AppCompatActivity {
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        bt = findViewById(R.id.button2);
        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

    }

    public void openActivity2(){
        Intent intent=new Intent(Activity2.this,Home.class);
        startActivity(intent);
    }
    }
