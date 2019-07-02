package com.tjoen.app0701;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyncTaskActivity extends AppCompatActivity {

    TextView asyncDisp;
    ProgressBar progress;
    Button start, end;
    //진행율을 표시하기 위한 변수
    int value;

    BackgroundTask task;

    class BackgroundTask extends AsyncTask<Integer, Integer, Integer>{
        @Override
        //task 가 시작하면 호출되는 메소드 - 선택
        public void onPreExecute(){
            //프로그래스 바 초기화
            value = 0;
            progress.setProgress(value);
        }
        @Override
        //backgroundThread로 동작하는 메소드 - 필수
        //리턴타입의 자료형이 클래스 생성 시 설정하는 3번째 매개변수 자료형
        //매개변수는 클래스 생성 시 설정하는 2번째 매개변수 자료형
        //...은 파라미터가 몇 개 오던지 관계없음
        public Integer doInBackground(Integer ... values){
            while (isCancelled() == false && value < 100){
                value = value + 1;
                //UI 갱신을 요청
                publishProgress(value);
                //잠시 대기
                try{
                    Thread.sleep(1000);
                }catch (Exception e){

                }
            }
            return value;
        }

        @Override
        //doInBackground 에서 publishProgress를 호출하면 실행되는 메소드 - 선택
        //이 메소드의 매개변수는 클래스 생성 시 설정하는 1번째 자료형
        public void onProgressUpdate(Integer ... values){
            progress.setProgress(values[0]);
            asyncDisp.setText("value:" + values[0]);
        }

        @Override
        //doInBackground가 작업을 종료하면 호출되는 메소드 - 선택
        //매개변수가 doInBackground의 return 값
        public void onPostExecute(Integer result){
            asyncDisp.setText("작업완료");
        }

        @Override
        //스레드가 중지됬을 때 호출되는 메소드
        public void onCancelled(){
            asyncDisp.setText("작업중지");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        asyncDisp = (TextView)findViewById(R.id.asyncdisp);
        progress = (ProgressBar)findViewById(R.id.prgress);
        start = (Button)findViewById(R.id.start);
        end = (Button)findViewById(R.id.end);

        start.setOnClickListener((view) -> {
            //asyncTask 인스턴스를 생성해서 실행
            //BackgroundTask task = new BackgroundTask();
            //end 버튼에서도 사용할 수 있도록 하기 위해 상단에 인스턴스 변수 선언
            task = new BackgroundTask();
            task.execute(100);
        });

        end.setOnClickListener((view) -> {
            task.cancel(true);
        });
    }
}
