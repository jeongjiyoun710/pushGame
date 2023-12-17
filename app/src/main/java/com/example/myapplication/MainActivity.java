package com.example.myapplication;

import static android.os.VibrationEffect.createOneShot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int score = 0 ;

    int btns[] = {
            R.id.btn1,
            R.id.btn2,
            R.id.btn3,
            R.id.btn4,
            R.id.btn5,
            R.id.btn6,
            R.id.btn7,
            R.id.btn8,
            R.id.btn9,
            R.id.btn10,
            R.id.btn11,
            R.id.btn12
    };

    float count = 30; //30초

    int number[] = {0,0,0,0,0};

    public void fn_init() {
        Random random = new Random();
        int x = 0;

        while ( x < 5 ){
            int tmpValue = random.nextInt(12) ;

            int i = 0 ;

            while ( i < 5 ) {
                if ( tmpValue == number[i]){
                    break;
                }else {
                    i++;
                }
            }

            if ( i == 5 ) {
                number[x] = tmpValue;
            }else{
                continue;
            }
            x ++ ;
        }

        BitmapDrawable dg = (BitmapDrawable)getResources().getDrawable(R.drawable.click_green);
        BitmapDrawable dr = (BitmapDrawable)getResources().getDrawable(R.drawable.click_red);
        for ( int z = 0 ; z < 5 ; z ++ ){

//            ((Button)findViewById(btns[number[z]])).setText("*");
//
//            ((ImageView)findViewById(btns[number[z]])).(BitmapDrawable)getResources().getDrawable(R.drawable.click_green);
            ImageView onbtn = (ImageView)findViewById(btns[number[z]]);
            onbtn.setImageDrawable(dg);

        }



    }



    int btnValue = 5;

    public void fn_Clicker(View v){


//        if ( "*".equals( ((Button)findViewById(v.getId())).getText() )){
//            score ++ ;
//            ((Button) findViewById(v.getId())).setText("");
//            btnValue --;
//
//        }else{
//            score -- ;
//        }
//
//        ((TextView)findViewById(R.id.score)).setText("score : " + score);

//
//        if (==(((ImageView)findViewById((v.getId))).getDrawable())) //어떻게 이미지 비교를 하지?


//        이미지 바꾸는 코드입니다, 이해해봅시다...............


//        업로드 테스트 문구입니다. 무시해주세요
//        업로드 테스트 문구 2입니다. 무시해주세요.


        Drawable sDraw = getDrawable(R.drawable.click_green);
        Bitmap bt1 = ((BitmapDrawable)sDraw).getBitmap();

        Drawable bDraw = ((ImageView)findViewById(v.getId())).getDrawable();
        Bitmap bt2 =  ((BitmapDrawable)bDraw).getBitmap();
//bt1.sameAs(bt2)
        if ( bt1 == bt2 ){
            score = score + 1 ;
//            Log.i("test", "score :" + score);
            ((ImageView)findViewById((v.getId()))).setImageDrawable((BitmapDrawable) getResources().getDrawable(R.drawable.click_red));
            btnValue --;
        }else{
            score -- ;
        }

        ((TextView)findViewById(R.id.score)).setText("score : " + score);



        if(btnValue == 0){
            fn_init();
            btnValue = 5;
        }

        if ( score < 0 ) {
//            finish();
        }
    }


// =========================== 아래 : 타이머 / 위 : 스코어 및 기능 ==================================

    Timer timer; //timer import




    public void fn_timer(){

        if(count <= 0){
//          setContentView(R.layout.activity_main);
            //Intent intent = new Intent(this, endActivity.class);
            //startActivity(intent);
            Log.e("PUSHGAME","게임종료");
            timer.cancel();
            Intent intent = new Intent(MainActivity.this, endActivity.class); // Intent (전송경로)에 전송 위치와 전송 받을 위치를 지정한다.
            intent.putExtra("점수",score); // "점수"라는 이름으로 score라는 int값을 넣어준다. (예시로 택배 상자에 물건을 넣는 느낌)
            startActivity(intent); // 전송
        }else{
            count-=0.01;

            //지금 화면 출력하는 애한테 run()으로 무엇을 출력할 건지 알려주고 출력을 요청해줘
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((TextView)findViewById(R.id.time)).setText("time : "+String.format("%.2f", count));

                }
            });
        }




    } //시간을 만든 스레드의 시간을 지금 현재 출력되는 메인 스레드에 요소를 추가하는 변수


    //새로운 스레드(화면을 출력하고 있는 메인 스레드와는 다른 활동을 하고 있는 스레드
    public void fn_timerSet(){

        //새로운 timer를 만들어줘!
        timer = new Timer();

        //timerTask로 새로운 timerTask는 실행 됐을 때, fn_timer()를 실행시켜줘
        TimerTask timerTask = new TimerTask() {public void run() { fn_timer(); }  } ;

        //0초 뒤에 1초마다 timerTask를 실행시켜줘
        timer.schedule(timerTask, 0, 10);


    }



//    public void vibrate (long[] pattern,
//                         int repeat){
//        vibrate(100000L, -1, 100);
//    }
//public permission (void){
//
//}
//    public void vibrate (VibrationEffect vibe,
//                         VibrationAttributes attributes){
//        VibrationEffect.createOneShot(500, 1);
//    } //현재 버전이 낮아서 안됌

//    public void vibrate (long[] pattern,
//                         int repeat){
//
//    }

//    Button gameStart = (Button) findViewById(R.id.gameStart);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fn_timerSet();
        //초기화 해준다
        fn_init();


    }



}