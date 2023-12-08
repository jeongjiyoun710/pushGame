package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class endActivity extends AppCompatActivity {

    public void fn_endlay(View v){

        Button retry = (Button) findViewById(R.id.retryBtn);
        Button upload = (Button) findViewById(R.id.uploadBtn);
        Button rank = (Button) findViewById(R.id.rankBtn);

        Button userClick = (Button) findViewById(v.getId());

        if(userClick == retry){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (userClick == upload) {
//            Intent intent = new Intent(this, upladActivity.class);
//            startActivity(intent);
        } else if (userClick == rank) {
//            Intent intent = new Intent(this, rankActivity.class);
//            startActivity(intent);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_activity);

    }

}
