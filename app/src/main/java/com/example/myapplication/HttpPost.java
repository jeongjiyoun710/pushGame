package com.example.myapplication;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpPost {
    // Volley 요청 큐 생성


    public void sendRequest(Context context,Response.Listener listener,int score, String name){

//        Log.e("호출","호출");

        RequestQueue queue = Volley.newRequestQueue(context);  //--잠깐만 이거 뭐야??? => 대충 인터넷에 요청을 보내는 명령어인 것 같다...
        // -- https://developer.android.com/training/volley/simple?hl=ko

        String url = "http://34.22.97.253:8080/test"; // POST 요청을 보낼 URL     -- POST 요청을 보낼 URL이 왜 필요한가? 스프링이 웹에서 실행되는 걸까..?

        // POST로 전송할 데이터를 JSONObject에 담기
        JSONObject postData = new JSONObject();  //--객체 형식으로 postData를 변수를 만들어준다.
        //--대충 js 형식으로는  =>  let postData = {key : value, key : value};

        try {
            postData.put("score", score);  // --score 변수를 postData에 객체 형식으로 보내주는 역할
            postData.put("name", name);  // --name 변수를 postData에 객체 형식으로 보내주는 역할

            //--여기서 의문, 이 코드를 쓰는 방법은 알겠지만, 이 코드를 그대로 가져가서 쓰면 먹힐까?

        } catch (JSONException e) {
            e.printStackTrace(); // --에러를 상세하게 표현
        }

        // -- 먼저 이 위에 값을 저장하는 형식부터 모두 이해하고 사용 후 밑 코드를 보자. ================================


        // JSON 형식의 응답을 받기 위한 POST 요청 생성
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData,listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("test", "onErrorResponse: 다시 입력해 주세요" + error.toString());
                // 응답 실패 시 처리할 내용
                // error 변수에 발생한 에러 정보가 전달됩니다.
            }
        });

        // 요청을 Volley 큐에 추가하여 전송
        queue.add(jsonObjectRequest);
    }

}
