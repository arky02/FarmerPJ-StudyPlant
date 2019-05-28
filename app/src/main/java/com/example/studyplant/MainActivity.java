package com.example.studyplant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int level = 1, i, sum = 0,total=0;
    LinearLayout linear_timetable;
    TextView txt_level,txt_total,txt_current;
    Button btn_study, btn_input;
    boolean clicked = false;

    EditText edt_inputTime;
    ImageView[] timeArr = new ImageView[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_study = findViewById(R.id.btn_study);
        txt_level = findViewById(R.id.txt_level);
        linear_timetable = findViewById(R.id.linear_timetable);
        edt_inputTime = findViewById(R.id.edt_timeInput);
        btn_input = findViewById(R.id.btn_input);
        txt_current = findViewById(R.id.txt_current);
        txt_total = findViewById(R.id.txt_total);
        timeArr[0] = findViewById(R.id.time1);
        timeArr[1] = findViewById(R.id.time2);
        timeArr[2] = findViewById(R.id.time3);
        timeArr[3] = findViewById(R.id.time4);
        timeArr[4] = findViewById(R.id.time5);
        timeArr[5] = findViewById(R.id.time6);
        timeArr[6] = findViewById(R.id.time7);
        timeArr[7] = findViewById(R.id.time8);
        timeArr[8] = findViewById(R.id.time9);
        timeArr[9] = findViewById(R.id.time10);
        timeArr[10] = findViewById(R.id.time11);
        timeArr[11] = findViewById(R.id.time12);

        linear_timetable.setVisibility(View.INVISIBLE);

        btn_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(clicked){
                    clicked = false;
                    linear_timetable.setVisibility(View.VISIBLE);

                }else{
                    clicked = true;
                    linear_timetable.setVisibility(View.INVISIBLE);
                }




            }
        });
        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum = sum + Integer.parseInt(edt_inputTime.getText().toString());
                total = total + Integer.parseInt(edt_inputTime.getText().toString());

                txt_total.setText(Integer.toString(total));

                if (sum >= 12) {
                    level += (sum/12);
                    sum %= 12;
                    txt_level.setText("Level "+level);
                    for (i = 1; i <= 12; i++) {
                        timeArr[i - 1].setBackgroundResource(R.drawable.off);
                    }
                    for (i = 1; i <= sum; i++) {
                        timeArr[i - 1].setBackgroundResource(R.drawable.on);
                    }

                }else{
                    for (i = 1; i <= sum; i++) {
                        timeArr[i - 1].setBackgroundResource(R.drawable.on);
                    }
                }
                edt_inputTime.setText("");
                txt_current.setText(Integer.toString(sum)+"/12,");

            }




        });
    }
}
