package com.tjoen.logindb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class SubActivityXMLparsing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_xmlparsing);

        Button btn = (Button)findViewById(R.id.backbtn);
        //backbtn을 누르면 이전 페이지로 이동
        btn.setOnClickListener((view) -> {
            finish();
        });
        WebView webView = (WebView)findViewById(R.id.webview);
        //리다이렉트되는 URL을 웹 뷰가 처리하도록 하는 설정
        webView.setWebViewClient(new WebViewClient());
        //웹 뷰에서 자바스크립트를 사용하고 확대 축 아이콘이 보이도록 설정
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        //넘겨준 데이터를 읽어서 출력
        String url = getIntent().getStringExtra("link");
        webView.loadUrl(url);
    }
}
