package com.weavers.goalweaver;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoginResultActivity extends AppCompatActivity {

    TextView Textview_get;

    //main(String[] args) 라고 생각하면 됨
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);  // xml이랑 matching 해주는 이름 activity_main

        Textview_get = findViewById(R.id.TextView_get);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras(); // bundle값을 반환해줌
        String email = bundle.getString("email");
        String password = bundle.getString("password");

        Textview_get.setText("환영합니다, "+email + "님 /  " + password);
        /*try{
            Thread.sleep(2500);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this,MainActivity.class));
        finish();*/
    }

    public void onClick(View view){
        Log.d("intent","여기까지는 성공1");
        Intent intent = new Intent(this, NewsActivity.class);
        Log.d("intent","여기까지는 성공2");
        startActivity(intent);
    }

}
