package com.rgo0517.app0620;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InnerClassActivity extends AppCompatActivity {

    private TextView disp;
    private Button innerbtn;

    //내부 클래스를 만들면 외부 클래스들의 인스턴스 변수를 사용 가능
    class InnerEventHandler implements View.OnClickListener{
        public void onClick(View v){
            disp.setText("이너 클래스를 이용한 이벤트 처리");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_class);

        disp = (TextView)findViewById(R.id.disp);
        innerbtn = (Button)findViewById(R.id.innerbtn);

        InnerEventHandler eventHandler = new InnerEventHandler();
        innerbtn.setOnClickListener(eventHandler);
    }
}
