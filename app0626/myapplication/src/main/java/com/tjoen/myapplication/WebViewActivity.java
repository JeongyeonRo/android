package com.tjoen.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebViewActivity extends AppCompatActivity {


    Button google, local;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        google = (Button)findViewById(R.id.google);
        local = (Button)findViewById(R.id.local);
        webView = (WebView)findViewById(R.id.webview);

        //WebView의 옵션 설정
        //리다이렉트 되는 페이지의 이동을 WebView가 처리하도록 설정
        webView.setWebViewClient(new WebViewClient());
        //자바스크립트와 화면 크기 변화를 가능하도록 옵션
        //WebView의 옵션 설정
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);

        //WebView의 URL load
        webView.loadUrl("https://www.google.com");

        google.setOnClickListener((view) -> {
            webView.loadUrl("https://www.google.com");
        });

        local.setOnClickListener((view) -> {
            webView.loadUrl("file://android_asset/test.html");
        });
    }
}
