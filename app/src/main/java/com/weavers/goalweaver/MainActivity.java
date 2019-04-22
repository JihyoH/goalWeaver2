package com.weavers.goalweaver;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    TextInputEditText TextInputEditText_email, TextInputEditText_password;
    RelativeLayout RelativeLayout_login;
    String emailOK = "123@gmail.com";
    String passwordOK = "1234";

    String inputEmail = "";
    String inputPassword = "";

    //main(String[] args) 라고 생각하면 됨
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);  // xml이랑 matching 해주는 이름 activity_main

        TextInputEditText_email = findViewById(R.id.TextInputEditText_email);
        TextInputEditText_password = findViewById(R.id.TextInputEditText_password);
        RelativeLayout_login = findViewById(R.id.RelativeLayout_login);

        // 1. 값을 가져온다 - 검사 (123@gmail.com / 1234)
        // 2. 출력을 감지한다
        // 3. 1번의 값을 다음 액티비티로 넘긴다.

        RelativeLayout_login.setClickable(false);

        TextInputEditText_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { //입력이 변할때마다 값을 다른곳에 전달
                if(s != null){
                    Log.d("SENTI",s.toString()); // s는 현재 보이는 거
                    inputEmail = s.toString();
                    RelativeLayout_login.setEnabled(validation()); // 값을 검사해서 괜찮으면 누를 수 있게 변경
                    if(validation() == true){
                        clickChanger();
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        TextInputEditText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s != null) {
                    Log.d("SENTI",s.toString());
                    inputPassword = s.toString();
                    RelativeLayout_login.setEnabled(validation()); // 값을 검사해서 괜찮으면 누를 수 있게 변경
                    if(validation() == true){
                        clickChanger();
                    }

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*RelativeLayout_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = TextInputEditText_email.getText().toString();
                String password = TextInputEditText_password.getText().toString();

                Intent intent = new Intent(MainActivity.this, LoginResultActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });*/
    }

     public boolean validation(){
         Log.d("SENTI",inputEmail + " / "+inputEmail.equals(emailOK)+ " / "+inputPassword+ " / "+inputPassword.equals(passwordOK));
         return inputEmail.equals(emailOK) && inputPassword.equals(passwordOK);
     }
     public void clickChanger(){
         RelativeLayout_login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String email = TextInputEditText_email.getText().toString();
                 String password = TextInputEditText_password.getText().toString();

                 Intent intent = new Intent(MainActivity.this, LoginResultActivity.class);
                 intent.putExtra("email", email);
                 intent.putExtra("password", password);
                 startActivity(intent);

             }
         });
     }
}
