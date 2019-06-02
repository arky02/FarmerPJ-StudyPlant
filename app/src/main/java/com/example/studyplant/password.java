package com.example.studyplant;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class password extends AppCompatActivity {

    //Button[] btn = new Button[9];
    Button btn_clear,btn_erase,btn_login;
    EditText edt_result;
    TextView txt_password,txt_forget;
    SharedPreferences sp_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        /*
        btn[0] = findViewById(R.id.btn_0);
        btn[1] = findViewById(R.id.btn_1);
        btn[2] = findViewById(R.id.btn_2);
        btn[3] = findViewById(R.id.btn_3);
        btn[4] = findViewById(R.id.btn_4);
        btn[5] = findViewById(R.id.btn_5);
        btn[6] = findViewById(R.id.btn_6);
        btn[7] = findViewById(R.id.btn_7);
        btn[8] = findViewById(R.id.btn_8);
        btn[9] = findViewById(R.id.btn_9); */

        txt_password = findViewById(R.id.txt_password);
        btn_clear = findViewById(R.id.btn_clear);
        btn_erase =findViewById(R.id.btn_back);
        edt_result = findViewById(R.id.edt_result);
        btn_login = findViewById(R.id.btn_login2);
        txt_forget = findViewById(R.id.txt_forget);

        Intent intent1 = getIntent();
        final Boolean isFirst = intent1.getExtras().getBoolean("isFirst");
        sp_password = getApplicationContext().getSharedPreferences("password", MODE_PRIVATE);

        if(isFirst){
            txt_password.setText("비밀번호를 설정하세요");
            btn_login.setText("설정");
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_result.getText().toString().length()>=4) {
                    if (isFirst) {
                        SharedPreferences.Editor edit = sp_password.edit();
                        edit.putString("password", edt_result.getText().toString());
                        edit.commit();
                        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent1);
                    }else{
                        if(edt_result.getText().toString().equals(sp_password.getString("password",null))){
                            Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent1);
                        }else{
                            Toast.makeText(getApplicationContext(),"비밀번호가 틀립니다. 다시 입력해주세요",Toast.LENGTH_SHORT).show();
                            edt_result.setText("");
                        }
                    }
                }
            }
        });

        txt_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

                builder.setMessage("개발진한테 문의(페메)하세요! 비밀번호를 알려드립니다 ^_^");
                builder.setCancelable(false);
                builder.setTitle("아이고...설마, 비밀번호를 잊어버렸나요");
                builder.setPositiveButton("네! 물어볼께요ㅜㅜ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),
                                "잘 생각해봐요.. 기억이 날수도?",Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("핑요없어!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when click the negative button
                        Toast.makeText(getApplicationContext(),
                                "장난으로 눌러보다니!! 이런..",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
    public void onClick (View v)
    {
        switch(v.getId()){
            case R.id.btn_0 : edt_result.setText(edt_result.getText().toString() + 0); break;
            case R.id.btn_1 : edt_result.setText(edt_result.getText().toString() + 1); break;
            case R.id.btn_2 : edt_result.setText(edt_result.getText().toString() + 2); break;
            case R.id.btn_3 : edt_result.setText(edt_result.getText().toString() + 3); break;
            case R.id.btn_4 : edt_result.setText(edt_result.getText().toString() + 4); break;
            case R.id.btn_5 : edt_result.setText(edt_result.getText().toString() + 5); break;
            case R.id.btn_6 : edt_result.setText(edt_result.getText().toString() + 6); break;
            case R.id.btn_7 : edt_result.setText(edt_result.getText().toString() + 7); break;
            case R.id.btn_8 : edt_result.setText(edt_result.getText().toString() + 8); break;
            case R.id.btn_9 : edt_result.setText(edt_result.getText().toString() + 9); break;
        }
    }
}


