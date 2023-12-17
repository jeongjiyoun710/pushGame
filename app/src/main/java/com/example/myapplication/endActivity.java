package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Array;

public class endActivity extends AppCompatActivity {

    Intent intent = new Intent();
    int score = 0;


    public void fn_save(){

        //입력한 이름을 가지고 온다.
//        name = "";
//        Editable name = ((Editable) ).getText(); //사용자 닉네임
        EditText name = (findViewById(R.id.userName));
        String strName = name.getText().toString();
//        score = score;

        HttpPost httpPost = new HttpPost(); //httpPost 타입의 명령어 생성

        httpPost.sendRequest(this, new Response.Listener<JSONObject>() {
            JSONObject postData = new JSONObject();
            @Override
            public void onResponse(JSONObject jsonObject) {

                try {
                    // 분명  실행은 시켰는데, HttpPost.java가 실행이 된다. 왜일까? => 이거 어떻게 고치죠..... 무슨 명령이 있는 거 같은데 뭐가 뭔지를 모르겠는데
                    postData.put("score", score);
                    postData.put("name", strName);
                    Log.i("태그", "obj"+postData);
//                    JSONArray array = jsonObject.getJSONArray("USER_INFO");  //이게 대체 뭘까...
//                    Log.e("리턴",((JSONObject)array.get(0)).getString("address_1"));
                } catch (JSONException e) {
                    throw new RuntimeException(e); //try 도중, 에러가 뜨면 그 에러의 세부사항을 알려주는 명령어
                }
            }
        } , score, strName); //이거는 뭐지


    }

    //우리가 해야하는 것 : 1. 스프링과 안드로이드 연결 / 2. 스프링과 DB연결(수업 때 날라감) / 3. score를 endActivity로 넘겨와야함 ==> 성공!
    // / 4. 스프링으로 userName과 score를 보내야함 (HttpPost 파일을 보면 예시가 나와있다.) => 지금 이상하게 코드가 잘못 작동되는 중.. /
    // 5. 스프링에서 userName과 score의 값을 받고, 변수로 지정해서 DB에 추가를 해야한다. 이때, 우리는 어떤 방법으로 userName과 score의 값을 받아서 변수로 지정하는가?

    //문제 첫 번째: 스프링과의 연결을 어떻게 해야할까?
    //문제 두 번째: 연결을 할 때, 우리는 userName과 score를 보내줘야한다. 하지만 getIntExtra가 사용되지 않는다.(오류발생) 왜 오류가 뜰까? ==> 성공!!!
    //문제 세 번째: 스프링에 값을 보냈을 때, 스프링에서 어떻게 인식을 시켜야하나?
    //문제 네 번째: 스프링에서 안드로이드로 어떻게 다시 값을 보내오나?

    //그 외에도 많지만 먼저 필수적인 위 문제들부터 해결해야함. 하나씩 해결해보자.

    //안드로이드와 스프링의 연결 코드의 예시는 HttpPost에 전체적으로 있다. 그저 붙여넣는 것이 아닌, 왜 그 코드를 썼고, 그 코드의 역할이 뭔지 이해하며 진행하자.





    public void fn_endlay(View v){

        Log.e("버튼 눌림 ", "버튼 클릭 ") ;

        if(v.getId() == R.id.retryBtn){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (v.getId()==R.id.uploadBtn) {

            Log.e("버튼 눌림 ", "fn_Save() ") ;

            fn_save();

            CharSequence text = "저장되었습니다!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        } else if (v.getId() == R.id.rankBtn) {
//            Intent intent = new Intent(this, rankActivity.class);
//            startActivity(intent);

        }


    }

    int[ ] rankNames = {R.id.rankName1, R.id.rankName2, R.id.rankName3, R.id.rankName4, R.id.rankName5};
    int[ ] rankScores = {R.id.rankScore1, R.id.rankScore2, R.id.rankScore3, R.id.rankScore4, R.id.rankScore5 };
    int[ ] rankDays = {R.id.rankDay1, R.id.rankDay2, R.id.rankDay3, R.id.rankDay4, R.id.rankDay5};



    // 처음에 화면 열릴떄 랭커를 조회하여 세팅한다.
    public void fn_setRanker(){


        HttpPost httpPost = new HttpPost(); //httpPost 타입의 명령어 생성

        httpPost.test(this, new Response.Listener<JSONObject>() {
            JSONObject postData = new JSONObject();
            @Override
            public void onResponse(JSONObject jsonObject) {

                try {
                  JSONArray ja = jsonObject.getJSONArray("RESULT");

                 int jaLength = ja.length();

                for(int i = 0; i<jaLength; i++){

                    JSONObject jb = ja.getJSONObject(i);

                    ((TextView)findViewById(rankNames[i])).setText(jb.getString("id"));
                    ((TextView)findViewById(rankScores[i])).setText(jb.getString("score"));
                    ((TextView)findViewById(rankDays[i]))  .setText(jb.getString("days"));

                }




                } catch (JSONException e) {
                    throw new RuntimeException(e); //try 도중, 에러가 뜨면 그 에러의 세부사항을 알려주는 명령어
                }
            }
        } ); //이거는 뭐지


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_activity);

        Intent intent = getIntent();


        score = intent.getIntExtra("점수", 0); // getIntExtra는 mainA...에서 보낸 값이 int값인 (name) 을 가져온다. 다만 int는 (name) 뒤 기본값이 있어야한다. (getStringExtra 제외)
        Log.i("점수", "점수 : "+score);
        ((TextView)findViewById(R.id.userScore)).setText("점수 : "+score);  // 안됐던 이유 : text를 넣을 때는 앞에 문자가 있어야한다. ex) "점수 : "+(변수);


        fn_setRanker();


    }

}
