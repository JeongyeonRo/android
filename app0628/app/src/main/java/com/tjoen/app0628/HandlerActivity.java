package com.tjoen.app0628;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HandlerActivity extends AppCompatActivity {

    TextView handlerdisp;
    Button handlerbtn;
    int idx;

   int [] colors = {Color.RED, Color.BLUE, Color.YELLOW};

    //넘겨받은 obj를 텍스트 뷰에 출력하는 핸들러
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            Integer data = (Integer)msg.obj;
            handlerdisp.setTextColor(colors[idx%3]);
            handlerdisp.setText("data:" + data);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        handlerdisp = (TextView)findViewById(R.id.handlerdisp);
        handlerbtn = (Button)findViewById(R.id.handlerbtn);

        handlerbtn.setOnClickListener((view) -> {
            /*
            Thread th = new Thread(){
                @Override
                public void run(){
                    for(int i=0; i<10; i=i+1){
                        try{
                            Thread.sleep(1000);
                            //메시지를 생성
                            Message msg = new Message();
                            //객체의 속성에 데이터를 대입
                            msg.obj = idx;
                            idx = idx + 1;
                            //핸들러에게 메시지를 전송
                            handler.sendMessage(msg);
                        }catch (Exception e){

                        }
                    }
                }
            };
            th.start();
            */

            //Handler를 post를 이용해서 호출
            Thread th = new Thread(){
                //비어 있는 핸들러 생성
                Handler handler = new Handler();
                public void run() {
                    //반복문
                    for (int i = 0; i < 10; i = i + 1) {
                        try {
                            Calendar cal =
                                    new GregorianCalendar(
                                            2019,5,29,
                                            16,52,0);
                            long nextDay = cal.getTimeInMillis();
                            Thread.sleep(1000);
                            handler.postAtTime(new Runnable(){
                                public void run(){
                                    handlerdisp.setText(idx + "");
                                }
                            }, nextDay);
                            idx = idx + 1;
                        }catch (Exception e){

                        }
                    }
                }
            };
            th.start();
        });
    }
}
