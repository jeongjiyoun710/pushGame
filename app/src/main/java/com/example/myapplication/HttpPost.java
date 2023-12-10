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

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://10.171.6.155:8888/login"; // POST 요청을 보낼 URL

        // POST로 전송할 데이터를 JSONObject에 담기
        JSONObject postData = new JSONObject();
        try {
            postData.put("score", score);
            postData.put("name", name);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        // JSON 형식의 응답을 받기 위한 POST 요청 생성
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData,listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("test", "onErrorResponse: 다시 입력해 주세요");
                // 응답 실패 시 처리할 내용
                // error 변수에 발생한 에러 정보가 전달됩니다.
            }
        });

        // 요청을 Volley 큐에 추가하여 전송
        queue.add(jsonObjectRequest);
    }

}
