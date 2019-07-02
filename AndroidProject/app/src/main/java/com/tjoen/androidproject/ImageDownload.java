package com.tjoen.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.BreakIterator;

public class ImageDownload extends AppCompatActivity {

    ImageView imageView;

    //Bitmap 데이터를 받아서 imageView에 출력하는 핸들러
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            Bitmap image = (Bitmap)msg.obj;
            imageView.setImageBitmap(image);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_download);

        imageView = (ImageView)findViewById(R.id.imageview);
        Button imagedisp = (Button)findViewById(R.id.imagedisp);
        Button imagedownload = (Button)findViewById(R.id.imagedownload);

        //imagedisp 버튼을 눌렀을 때 이미지를 바로 출력하기
        imagedisp.setOnClickListener((view) -> {
            Thread th = new Thread(){
                public void run(){
                    try{
                        //이미지 파일의 Url 만들기
                        URL url = new URL(
                                "http://images.christiandaily.co.kr/data/images/full/55691/135774_w_700-png.png?w=600"
                        );
                        //스트림 만들기
                        InputStream is = url.openStream();
                        //비트맵 만들기
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        //만든 비트맵을 메시지에 담아서 핸들러에게 전송
                        Message msg = new Message();
                        msg.obj = bitmap;
                        handler.sendMessage(msg);

                        is.close();

                    }catch (Exception e){
                        Log.e("이미지 출력 실패", e.getMessage());
                    }
                }
            };
            th.start();
        });
        //이미지가 앱 안에 존재하면 앱 안의 이미지를 출력하고
        //이미지가 없으면 앱 안에 파일로 저장하고 출력하기
        imagedownload.setOnClickListener((view) -> {
            //이미지 파일의 경로
            String addr = "http://www.entermedia.co.kr/photo/2019/05/26/1558846643_1.jpg";
            //파일 이름 만들기 - 마지막/다음의 문자열
            int idx = addr.lastIndexOf("/");
            String imageName = addr.substring(idx + 1);
            //위의 파일이 앱에 존재하는지 확인
            //앱 내의 파일 경로 만들기
            String path = Environment.getDataDirectory().getAbsolutePath();
            path +=
                    "/data/com.tjoen.androidproject/files/" + imageName;

            Log.e("path", path);
            Log.e("imagename", imageName);

            //파일의 경로를 가지고 File 객체 생성
            //파일의 존재 엽루 확인을 위해서
            File file = new File(path);
            if(file.exists()){

                Log.e("exists", "true");

                Toast.makeText(ImageDownload.this,
                        "파일이 존재합니다.", Toast.LENGTH_LONG).show();
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                Message msg = new Message();
                msg.obj = bitmap;
                handler.sendMessage(msg);

            }else{

                Log.e("exists", "false");

                Toast.makeText(ImageDownload.this,
                        "파일이 없습니다.", Toast.LENGTH_LONG).show();
                //스레드를 이용해서 다운로드 받은 후 파일을 만들고 출력
                Thread th = new Thread(){
                    @Override
                    public void run(){
                        try{
                            //이미지 파일의 경로
                            String addr = "http://www.entermedia.co.kr/photo/2019/05/26/1558846643_1.jpg";
                            Log.e("addr", addr);
                            //파일 이름 만들기 - 마지막/다음의 문자열
                            int idx = addr.lastIndexOf("/");
                            String imageName = addr.substring(idx + 1);
                            Log.e("ImageName", imageName);
                            //위의 파일이 앱에 존재하는지 확인
                            //앱 내의 파일 경로 만들기
                            String path = Environment.getDataDirectory().getAbsolutePath();
                            path +=
                                    "/data/com.tjoen.androidproject/files/" + imageName;
                            Log.e("path", path);
                            URL url = new URL(addr);
                            Log.e("url", url.toString());
                            HttpURLConnection con = (HttpURLConnection)url.openConnection();
                            Log.e("con", con.toString());
                            //디운로드 받는 크기
                            int len = con.getContentLength();
                            Log.e("len", len + "");
                            //저장할 바이트 배열 만들기
                            byte [] laster = new byte[len];
                            //이미지를 읽을 스트림과 파일에 기록할 스트림을 생성
                            InputStream is = con.getInputStream();
                            Log.e("is status", is.toString());
                            FileOutputStream fos = openFileOutput(imageName, 0);
                            //is로 읽은 내용을 fos에 기록
                            while (true) {
                                int read = is.read(laster);
                                if (read < 0)
                                    break;
                                fos.write(laster, 0, read);
                            }
                                is.close();
                                fos.close();
                                con.disconnect();

                                Message msg = new Message();
                                msg.obj = BitmapFactory.decodeFile(path);
                                handler.sendMessage(msg);

                        }catch (Exception e){
                            Log.e("다운로드 예외", e.getMessage());
                        }
                    }
                };
                th.start();
            }
        });

    }
}
