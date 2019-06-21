package com.rgo0517.persistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //anonymous나 람다에서 사용할 수 있도록 final로 변수를 생성
        final EditText display =
                (EditText) findViewById(R.id.disp);
        //파일을 생성해서 데이터를 저장하기 위한 버튼의 클릭 이벤트 처리
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener((view) -> {
            //try ~ resources 구문
            try (FileOutputStream fos = openFileOutput("test.txt", Context.MODE_PRIVATE)) {
                fos.write("안드로이드 파일 출력".getBytes());
                fos.flush();
                display.setText("저장 성공");

            } catch (Exception e) {
                Log.e("파일 저장 실패", e.getMessage());
            }
        });

        Button read = (Button) findViewById(R.id.read);
        read.setOnClickListener((view) -> {
            try (FileInputStream fis = openFileInput("test.txt")) {
                //파일의 내용을 읽을 저장공간 만들기
                byte[] buf = new byte[fis.available()];
                //파일의 내용을 읽기
                fis.read(buf);
                display.setText(new String(buf));

            } catch (Exception e) {
                Log.e("파일 읽기 실패", e.getMessage());
            }
        });

        Button delete = (Button)findViewById(R.id.delete);
        delete.setOnClickListener((view) -> {
            boolean result = deleteFile("test.txt");
            if(result){
                display.setText("삭제성공");
            }else{
                display.setText("삭제실패");
            }
        });

        Button resread = (Button)findViewById(R.id.resread);
        resread.setOnClickListener((view) -> {
//            InputStream fis = null;
//            try{
//                fis = getResources().openRawResource(R.raw.data);
//                byte [] buf = new byte[fis.available()];
//            }catch (Exception e){
//                Log.e("리소스 파일 읽기 오류", e.getMessage());
//            }finally {
//                try{
//                    if(fis != null)
//                        fis.close();
//                }catch (Exception e){
//                }

            try(InputStream fis = getResources().openRawResource(R.raw.data)){
                byte [] buf = new byte [fis.available()];
                fis.read(buf);
                display.setText(new String(buf));
            }catch (Exception e){
                Log.e("리소스 파일 읽기 오류", e.getMessage());
            }
        });
    }
}
