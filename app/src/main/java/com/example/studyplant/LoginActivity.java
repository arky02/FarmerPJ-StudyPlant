package com.example.studyplant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    EditText edt_num,edt_quiz;
    SharedPreferences shared;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btn_login = findViewById(R.id.btn_login);
        edt_num = findViewById(R.id.Edt_num);
        edt_quiz = findViewById(R.id.Edt_quiz);
        shared = getApplicationContext().getSharedPreferences("s8_num",MODE_PRIVATE);

        if(shared.contains("num")){
            Intent intent = new Intent(getApplicationContext(),password.class);
            startActivity(intent);
            intent.putExtra("isFirst",false);

        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_quiz.getText().toString().equals("불파만지파참")){
                    Global.s8_num = Integer.parseInt(edt_num.getText().toString());
                    edit = shared.edit();
                    edit.putInt("num",Global.s8_num);
                    edit.commit();
                    Intent intent = new Intent(getApplicationContext(),password.class);
                    startActivity(intent);
                    intent.putExtra("isFirst",true);
                }
            }
        });


    }
}
