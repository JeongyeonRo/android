package com.tjoen.logindb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

public class LoginActivity extends AppCompatActivity {

    EditText memberid, memberpw;
    LinearLayout mainView;
    Button loginbtn;

    //mainview의 색상을 변경하는 핸들러를 생성
    //일반 스레드에서는 뷰의 화면 변경을 하면 안됩니다.
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            String result = (String)msg.obj;
            if(result.equals("true")){
                mainView.setBackgroundColor(Color.GREEN);
            }else {
                mainView.setBackgroundColor(Color.RED);
            }
            InputMethodManager imm =
                    (InputMethodManager)getSystemService(
                            Context.INPUT_METHOD_SERVICE
                    );
            imm.hideSoftInputFromWindow(
                    memberpw.getWindowToken(), 0
            );
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        memberid = (EditText)findViewById(R.id.memberid);
        memberpw = (EditText)findViewById(R.id.memberpw);
        loginbtn = (Button)findViewById(R.id.loginbtn);
        mainView = (LinearLayout)findViewById(R.id.mainview);

        loginbtn.setOnClickListener((view) -> {
            Thread th = new Thread(){
                @Override
                public void run(){
                    String json = null;
                    try{
                        String addr =
                                "http://192.168.0.107:8080/AndroidMember/login?id=" +
                                        memberid.getText().toString().trim().toUpperCase() +
                                        "&pw=" + memberpw.getText().toString().trim();
                        URL url = new URL(addr);
                        HttpURLConnection con =
                                (HttpURLConnection)url.openConnection();
                        BufferedReader br =
                                new BufferedReader(
                                        new InputStreamReader(
                                                con.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        while (true){
                            String line = br.readLine();
                            if(line == null){
                                break;
                            }
                            sb.append(line);
                        }
                        Log.e("데이터", sb.toString());
                        json = sb.toString();

                        br.close();
                        con.disconnect();

                    }catch (Exception e){
                        Log.e("다운로드 예외", e.getMessage());
                    }

                    try {
                        if (json != null) {
                            JSONObject result = new JSONObject(json);
                            String msg = result.getString("result");
                            Message message = new Message();
                            message.obj = msg;
                            handler.sendMessage(message);
                        }
                    }catch (Exception e){
                        Log.e("파싱 예외", e.getMessage());
                    }
                }
            };
            th.start();
        });
    }
}
