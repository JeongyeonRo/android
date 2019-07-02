package com.tjoen.socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocketActivity extends AppCompatActivity {

    EditText msg;
    Button send;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_socket);

        msg = (EditText)findViewById(R.id.msg);
        send = (Button)findViewById(R.id.send);
        display = (TextView)findViewById(R.id.display);

        send.setOnClickListener((view) -> {
            //네트워크 작업은 스레드를 이용
            Thread th = new Thread(){
                public void run(){
                    try{
                        /*
                        //연결할 주소 생성
                        InetAddress ia = InetAddress.getByName("192.168.0.232");
                        //소켓만들기
                        Socket socket = new Socket(
                                ia, 9999
                        );
                        //입력한 문자열을 전송
                        PrintWriter pw = new PrintWriter(socket.getOutputStream());
                        pw.println(msg.getText().toString());
                        pw.flush();

                        //전송받은 문자열 읽기
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(socket.getInputStream())
                        );
                        String content = br.readLine();
                        display.setText(content);

                        pw.close();
                        br.close();
                        socket.close();
                        */

                        //데이터를 전송하는 데이터 그램 소켓 생성
                        DatagramSocket ds = new DatagramSocket();
                        //보낼 주소 생성
                        InetAddress ia = InetAddress.getByName("192.168.0.107");
                        //보낼 데이터 생성
                        String data = msg.getText().toString();
                        DatagramPacket dp = new DatagramPacket(
                                data.getBytes(), data.getBytes().length,
                                ia, 8888
                        );
                        ds.send(dp);
                        display.setText("메시지 전송 성공");

                    }catch (Exception e){
                        Log.e("에러", e.getMessage());

                    }
                }
            };
            th.start();
        });
    }
}
