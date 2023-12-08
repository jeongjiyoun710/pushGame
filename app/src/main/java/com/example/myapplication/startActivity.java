package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class startActivity extends AppCompatActivity {


    public void fn_start(View v){
        Button rank = (Button) findViewById(R.id.rankingBtn);
        Button start = (Button) findViewById(R.id.startBtn);
        Button userClick = (Button) findViewById(v.getId());


        if(userClick == start){
//            setContentView(R.layout.activity_main);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

    }
}
