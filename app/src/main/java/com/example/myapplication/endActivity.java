package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class endActivity extends AppCompatActivity {

    Intent intent = new Intent();
    int score = 0;

//    EditText nameBox = (EditText)findViewById(R.id.userName);

//    Intent intent = getIntent();
//    score = intent.getIntExtra("SCORE1", score);

    public void fn_save(){

        //입력한 이름을 가지고 온다.
//        name = "";
//        Editable name = nameBox.getText(); //사용자 닉네임

//        score = score;

        HttpPost httpPost = new HttpPost();
        httpPost.sendRequest(this, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                try {
                    JSONArray array = jsonObject.getJSONArray("USER_INFO");
                    Log.e("리턴",((JSONObject)array.get(0)).getString("address_1"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        } , score, "");

    }


    public void fn_endlay(View v){

        Button retry = (Button) findViewById(R.id.retryBtn);
        Button upload = (Button) findViewById(R.id.uploadBtn);
        Button rank = (Button) findViewById(R.id.rankBtn);

        Button userClick = (Button) findViewById(v.getId());

        if(userClick == retry){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (userClick == upload) {
            fn_save();
        } else if (userClick == rank) {
//            Intent intent = new Intent(this, rankActivity.class);
//            startActivity(intent);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_activity);

        Intent intent = new Intent();


//        score = intent.getIntExtra("SCORE1", score);
//        ((TextView)findViewById(R.id.userScore)).setText(score);
    }

}
