package com.rgo0517.view0617;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MixLayout extends AppCompatActivity {

    private Button btn1, btn2, btn3;
    private LinearLayout page1, page2, page3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mix_layout);

        //xml 에 디자인 한 뷰 찾아오기
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);

        page1 = (LinearLayout)findViewById(R.id.page1);
        page2 = (LinearLayout)findViewById(R.id.page2);
        page3 = (LinearLayout)findViewById(R.id.page3);

        //btn1.setVisibility(View.INVISIBLE);
        btn1.setVisibility(View.GONE);
        btn2.setVisibility(View.VISIBLE);
        btn3.setVisibility(View.VISIBLE);

        page1.setVisibility(View.VISIBLE);
        page2.setVisibility(View.INVISIBLE);
        page3.setVisibility(View.INVISIBLE);

        btn1.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.VISIBLE);

                page1.setVisibility(View.VISIBLE);
                page2.setVisibility(View.INVISIBLE);
                page3.setVisibility(View.INVISIBLE);
            }
        });
        btn2.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                btn2.setVisibility(View.GONE);
                btn1.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.VISIBLE);

                page2.setVisibility(View.VISIBLE);
                page1.setVisibility(View.INVISIBLE);
                page3.setVisibility(View.INVISIBLE);
            }
        });
        btn3.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                btn3.setVisibility(View.GONE);
                btn1.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);

                page3.setVisibility(View.VISIBLE);
                page1.setVisibility(View.INVISIBLE);
                page2.setVisibility(View.INVISIBLE);
            }
        });
    }
}
