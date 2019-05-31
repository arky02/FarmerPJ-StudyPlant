package com.example.studyplant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class password extends AppCompatActivity {

    //Button[] btn = new Button[9];
    Button btn_clear,btn_erase;
    EditText edt_result;

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
        btn_clear = findViewById(R.id.btn_clear);
        btn_erase =findViewById(R.id.btn_back);
        edt_result = findViewById(R.id.edt_result);

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
