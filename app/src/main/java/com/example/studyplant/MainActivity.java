package com.example.studyplant;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int level = 1, i, sum = 0, total = 0;
    LinearLayout linear_timetable;
    TextView txt_level, txt_total, txt_current;
    Button btn_study, btn_input;
    ImageView image;
    boolean clicked = false;

    EditText edt_inputTime;
    ImageView[] timeArr = new ImageView[12];
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sf = getSharedPreferences("sFile", MODE_PRIVATE);
        int time = sf.getInt("time", 0);
        int time_current = sf.getInt("time_current",0);

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
        image = findViewById(R.id.image);

        linear_timetable.setVisibility(View.INVISIBLE);
        studyTimeData studytime = (studyTimeData)getApplication();
        studytime.setTime(time_current);
        reSetting(time);

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
                String time = edt_inputTime.getText().toString();
                int time_num = Integer.parseInt(edt_inputTime.getText().toString());
                studyTimeData studytime = (studyTimeData)getApplication();
                studytime.setTime(Integer.parseInt(time));
                if(!time.isEmpty()){
                    if(time_num > 8 || studytime.getTime() > 8) {
                        showAlertDialog();
                        studytime.backTime(Integer.parseInt(time));
                    }
                    else {
                        sum = sum + Integer.parseInt(edt_inputTime.getText().toString());
                        total = total + Integer.parseInt(edt_inputTime.getText().toString());

                        txt_total.setText(Integer.toString(total)+"시간");

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
                        txt_current.setText(Integer.toString(sum)+"/12");
                        setImage();
                    }
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        studyTimeData studytime = (studyTimeData)getApplication();
        int time = studytime.getTime();
        SharedPreferences sharedPreferences = getSharedPreferences("sFile",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("time", total);
        editor.putInt("time_current", time);
        editor.commit();

    }

    private void setImage() {
        switch (level) {
            case 1 :
                image.setImageResource(R.drawable.image_1);
                break;

            case 2 :
                image.setImageResource(R.drawable.image_2);
                break;

            case 3 :
                image.setImageResource(R.drawable.image_3);
                break;

            case 4 :
                image.setImageResource(R.drawable.image_4);
                break;

            case 5 :
                image.setImageResource(R.drawable.image_5);
                break;

            case 6 :
                image.setImageResource(R.drawable.image_6);
                break;

            default:
                image.setImageResource(R.drawable.image_6);
                break;
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setTitle("경고!!")
                .setMessage("산꼴나셨네요~ 어떻게 하루에 8시간 넘게함?")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void reSetting(int time) {
        sum = sum + time;
        total = total + time;

        txt_total.setText(Integer.toString(total)+"시간");

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
        txt_current.setText(Integer.toString(sum)+"/12");
        setImage();
    }

    private void addData(String userName, int time) {
        databaseReference.child(userName).push().setValue(time);
    }
}
