package com.rgo0517.app0620;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventRoutingActivity extends AppCompatActivity {

     private TextView display;
     private Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_routing);

        display = (TextView)findViewById(R.id.routedisplay);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);

        View.OnClickListener router = (view) -> {
            if(view == btn1){
                display.setText("Java java");
            }else {
                display.setText("Kotlin kotlin");
            }
        };
//        btn1.setOnClickListener(view -> {
//            display.setText("Java");
//        });
//        btn2.setOnClickListener(view -> {
//            display.setText("Kotlin");
//        });
        //2개의 뷰에 발생한 이벤트를 하나의 객체를 가지고 처리
        //이것을 이벤트 라우팅이라고 합니다.
        btn1.setOnClickListener(router);
        btn2.setOnClickListener(router);

    }
}
