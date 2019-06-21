package com.rgo0517.app0620;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {
    private TextView number;
    private Button startcount, endcount;

    //인덱스 변수
    private int idx = 0;
    //타이머 변수
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        number = (TextView)findViewById(R.id.number);
        startcount = (Button)findViewById(R.id.startcount);
        endcount = (Button)findViewById(R.id.endcount);

        //구문으로 볼 때는 1부터 10까지 1초마다 텍스트 뷰에 출력해야 하지만 실제로 출력하는 부분은 모두 모아서 한꺼번에 처리
        //idx는 1초 마다 1씩 증가하지만 출력은 10초 후에 한꺼번에 10번을 수행
        //화면 상에서는 10초 후에 10을 출력하는 것으로 보입니다.
//        try{
//            for(int i=0; i < 10; i=i+1){
//                idx = idx + 1;
//                Log.e("idx", idx+"");
//                number.setText(idx+"");
//                Thread.sleep(1000);
//            }
//        }catch (Exception e){
//
//        }

        //타이머를 이용
        //일정한 주기마다 메인 스레드에게 onTick 메소드의 수행을 요청
//        CountDownTimer timer = new CountDownTimer(
//                //10초 동안 1초마다 수행
//                10000,1000
//        ) {
//            @Override
//            public void onTick(long l) {
//                idx = idx + 1;
//                number.setText(idx + "");
//            }
//            @Override
//            public void onFinish() {
//            }
//        };
//        timer.start();

        startcount.setOnClickListener((view) -> {
            timer = new CountDownTimer(
                    10000, 1000
            ) {
                @Override
                public void onTick(long l) {
                    idx = idx + 1;
                    number.setText(idx + "");
                }
                @Override
                public void onFinish() {
                }
            };
            timer.start();
        });
        endcount.setOnClickListener((view) -> {
            if(timer != null){
                timer.cancel();
                timer = null;
            }
        });
    }
}
