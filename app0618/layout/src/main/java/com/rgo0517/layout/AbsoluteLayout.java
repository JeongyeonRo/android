package com.rgo0517.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AbsoluteLayout extends AppCompatActivity {
    //view 변수를 인스턴스 변수로 만드는 이유 중의 하나는 이벤트 처리를 할 때
    //Anonymous 클래스를 많이 이용하는데
    //Anonymous 클래스에서는 일반 지역변수를 사용할 수 없고 final 이나 instance 변수만 사용할 수 있습니다.
    private TextView txtView;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absolute_layout);

        //뷰를 생성해서 부착하거나 만들어진 뷰를 찾아옵니다.
        txtView = (TextView)findViewById(R.id.txtView);
        btn =(Button)findViewById(R.id.btn);

        //버튼의 이벤트 처리

        //안드로이드에서는 뷰의 위치 변화를 메인스레드에서 동작시켜야하기 때문에 에러 발생
        btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
//                //마진의 변화를 줘서 뷰를 이동하는 것처럼 보이도록 하는 코드
//                ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(
//                        txtView.getLayoutParams());
//                layoutParams.setMargins(50,0,0,0);
//                txtView.setLayoutParams(
//                        new ViewGroup.LayoutParams(layoutParams));



            }
        });


    }
}
