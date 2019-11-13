package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginResultActivity extends AppCompatActivity {
    TextView textView_get;
    Button longlong, nextpage;
    private WebView mWebView;

    // main(String[] args)라고 생각하면 됨.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //웹뷰 세팅
        mWebView = findViewById(R.id.webView);//xml 자바코드 연결
        mWebView.getSettings().setJavaScriptEnabled(true);//자바스크립트 허용
        mWebView.loadUrl("https://rukaren.cafe24.com/");//웹뷰 실행
        mWebView.setWebChromeClient(new WebChromeClient());//웹뷰에 크롬 사용 허용//이 부분이 없으면 크롬에서 alert가 뜨지 않음
        mWebView.setWebViewClient(new WebViewClientClass());//새창열기 없이 웹뷰 내에서 다시 열기//페이지 이동 원활히 하기위해 사용



        textView_get = findViewById(R.id.textView);


        Intent intent = getIntent();

        Bundle bundle = intent.getExtras(); // bundle  값 반환
        String id = bundle.getString("id");
        String pw = bundle.getString("pw");

        textView_get.setText("환영합니다. "+id+"님!! 비밀번호는 "+ pw+"입니다.");

        longlong = findViewById(R.id.longlong);
        nextpage = findViewById(R.id.nextpage);


        longlong.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("LONG_CLICK", "OnLongClickListener");
                String msg = "Hello!";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                return true; // 다음 이벤트 계속 진행 false, 이벤트 완료 true
            }
        });


        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLICK", "OnClickListener");

                Intent intent_next = new Intent(LoginResultActivity.this, CanvasActivity.class);
                intent_next.putExtra("msg", "next button clicked!");
                startActivity(intent_next);


            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//뒤로가기 버튼 이벤트
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {//웹뷰에서 뒤로가기 버튼을 누르면 뒤로가짐
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {//페이지 이동
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("check URL",url);
            view.loadUrl(url);
            return true;
        }
    }




}
