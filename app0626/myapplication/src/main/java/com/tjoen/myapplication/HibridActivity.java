package com.tjoen.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HibridActivity extends AppCompatActivity {
    EditText message;
    Button send;
    WebView hybridwebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hibrid);

        message = (EditText)findViewById(R.id.message);
        send = (Button)findViewById(R.id.send);
        hybridwebview = (WebView)findViewById(R.id.hybridwebview);

        //자바스크립트가 호출할 수 있는 메소드를 소유한 클래스
        class AndroidJavaScriptInterface{
            Context context;
            android.os.Handler handler = new android.os.Handler();

            //생성자
            public AndroidJavaScriptInterface(Context context){
                this.context = context;
            }
            @JavascriptInterface
            //지역변수에 final을 붙으는 경우 -
            public void showToastMessage(final String message){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(
                                context, message, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }

        //리다이렉트 되는 주소도 WebView에 출력할 수 있도록 설정
        hybridwebview.setWebViewClient(new WebViewClient());
        //자바스크립트를 사용할 수 있도록 설정
        hybridwebview.getSettings().setJavaScriptEnabled(true);
        //webView에 로딩
        hybridwebview.loadUrl("http://192.168.0.107:8080/hybridapp");

        //자바스크립트 함수가 메소드를 호출할 수 있도록 설정
        hybridwebview.addJavascriptInterface(
                new AndroidJavaScriptInterface(
                        HibridActivity.this), "MYApp");

        send.setOnClickListener((view) -> {
            //입력한 내용 가져오기
            String mes = message.getText().toString();
            //자바스크립트 함수 호출
            hybridwebview.loadUrl(
                    "javascript:showDisplayMessage('" + mes + "')");
            //토스트 출력
            Toast.makeText(HibridActivity.this,"메시지 전송", Toast.LENGTH_LONG).show();
            //메시지 전송은 되었는데 키보드가 사라지지 않아 글자가 메시지가 나오지 않음
        });
    }
}
