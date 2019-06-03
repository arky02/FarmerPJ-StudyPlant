package com.example.studyplant;
//TODO 되는지 탭 연결해서 체크

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    int level = 1, i, sum = 0, total = 0,a=0;
    LinearLayout linear_timetable;
    TextView txt_level, txt_total, txt_current,txt_name;
    Button btn_study, btn_input,btn_minus;
    ImageView image;
    boolean clicked = true;
    SharedPreferences shared,sf,sp_password;

    EditText edt_inputTime;
    ImageView[] timeArr = new ImageView[12];
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shared = getSharedPreferences("s8_num",MODE_PRIVATE);
        Global.s8_num = shared.getInt("num",-1); //여기서 최초 로그인할때 입력한 번호 sharedpreference에 저장한거 가져온거를 Global.s8_num에 넣음
        //근데 GLobal보면 내가 이걸 번호별로 인덱스 해서 이름배열 정리했단 말야 그래서 애들 이름은  Global.student[Global.s8_num-1] 이거야
        Toast.makeText(getApplicationContext(),Global.student[Global.s8_num-1]+"농부님 환영합니다",Toast.LENGTH_SHORT).show();
        sp_password = getApplicationContext().getSharedPreferences("password", MODE_PRIVATE); //비밀번호 가져오기
        sf = getSharedPreferences("sFile", MODE_PRIVATE);//시간 가져오기

        int time = sf.getInt("time", 0);
        int time_current = sf.getInt("time_current",0);

        btn_study = findViewById(R.id.btn_study);
        txt_name =findViewById(R.id.farmer_name);
        txt_level = findViewById(R.id.txt_level);
        linear_timetable = findViewById(R.id.linear_timetable);
        edt_inputTime = findViewById(R.id.edt_timeInput);
        btn_input = findViewById(R.id.btn_input);
        txt_current = findViewById(R.id.txt_current);
        txt_total = findViewById(R.id.txt_total);
        btn_minus = findViewById(R.id.btn_minus);
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

        txt_name.setText("StudyPlant의 부지런한 "+ Global.student[Global.s8_num-1]+"농부");


        linear_timetable.setVisibility(View.INVISIBLE);
        studyTimeData studytime = (studyTimeData)getApplication();
        studytime.setTime(time_current);
        reSetting(time);

        btn_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=1;//면학시간 추가

                if(clicked){
                    linear_timetable.setVisibility(View.VISIBLE);
                    clicked = false;

                }else{
                    linear_timetable.setVisibility(View.INVISIBLE);
                    clicked = true;
                }
                btn_input.setText("추가");

            }

        });
        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = edt_inputTime.getText().toString();
                int time_num = Integer.parseInt(edt_inputTime.getText().toString());
                studyTimeData studytime = (studyTimeData)getApplication();

                if(a==1){
                    studytime.setTime(Integer.parseInt(time));
                }else if(a==2){
                    studytime.backTime(Integer.parseInt(time));
                    reSetting(sum);
                }

                if(!time.isEmpty()){
                    if(time_num > 8 || studytime.getTime() > 8) {
                       showAlertDialog();
                       studytime.backTime(Integer.parseInt(time));
                     }
                   else {
                        if(a==1) {
                            sum = sum + Integer.parseInt(edt_inputTime.getText().toString());
                            total = total + Integer.parseInt(edt_inputTime.getText().toString());

                            txt_total.setText("\" 총 "+total+"시간 \"");

                            if (sum >= 12) {
                                level += (sum / 12);
                                sum %= 12;
                                txt_level.setText("Level " + level);
                                for (i = 1; i <= 12; i++) {
                                    timeArr[i - 1].setBackgroundResource(R.drawable.off);
                                }
                                for (i = 1; i <= sum; i++) {
                                    timeArr[i - 1].setBackgroundResource(R.drawable.on);
                                }

                            } else {
                                for (i = 1; i <= sum; i++) {
                                    timeArr[i - 1].setBackgroundResource(R.drawable.on);
                                }
                            }
                            edt_inputTime.setText("");
                            txt_current.setText(sum + "/12 시간");
                            setImage();
                        }else if(a==2){
                            sum = sum - Integer.parseInt(edt_inputTime.getText().toString());
                            total = total - Integer.parseInt(edt_inputTime.getText().toString());

                            txt_total.setText("\" 총 "+total+"시간 \"");
                            txt_total.setText("\" 총 "+total+"시간 \"");
                            if (sum <=0 ) {

                                if(total != 0) {
                                    level += (sum / 12);
                                    sum = -sum;
                                    sum %= 12;
                                    sum = 12 - sum;
                                    txt_level.setText("Level " + level);
                                    for (i = 1; i <= 12; i++) {
                                        timeArr[i - 1].setBackgroundResource(R.drawable.off);
                                    }
                                    for (i = 1; i <= sum; i++) {
                                        timeArr[i - 1].setBackgroundResource(R.drawable.on);
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(),"삭제할 시간이 없습니다",Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                for (i = 1; i <=  sum; i++) {
                                    timeArr[i - 1].setBackgroundResource(R.drawable.on);
                                }
                            }
                            edt_inputTime.setText("");
                            txt_current.setText(sum + "/12 시간");
                            setImage();
                        }
                  }
                }
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_input.setText("삭제");
                a=2; //면학시간 삭제

                if(clicked){
                    linear_timetable.setVisibility(View.VISIBLE);
                    clicked = false;
                }else{
                    linear_timetable.setVisibility(View.INVISIBLE);
                    clicked = true;
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
        sp_password = getApplicationContext().getSharedPreferences("password", MODE_PRIVATE); //비밀번호 가져오기
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("time", total);
        editor.putInt("time_current", time);
        editor.commit();

        String pw = sp_password.getString("password","");

        addData(Global.student[Global.s8_num-1], total, Global.s8_num, pw); //비밀번호 추가

    }

    private void setImage() {
        switch (level) {
            case 1 : image.setImageResource(R.drawable.image_1); break;
            case 2 : image.setImageResource(R.drawable.image_2); break;
            case 3 : image.setImageResource(R.drawable.image_3); break;
            case 4 : image.setImageResource(R.drawable.image_4); break;
            case 5 : image.setImageResource(R.drawable.image_5); break;
            case 6 : image.setImageResource(R.drawable.image_6); break;
            default: image.setImageResource(R.drawable.image_6); break;
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

        txt_total.setText("\" 총 "+total+"시간 \"");

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
        txt_current.setText(sum+"/12 시간");
        setImage();
    }

    private void addData(String userName, int time, int userNumber, String pw) {
        Map<String,Object>taskmap = new HashMap<>();
        taskmap.put(userNumber + "/" + "Name" , userName);
        taskmap.put(userNumber + "/" + "Password" , pw);
        taskmap.put(userNumber + "/" + "Time" , time);

        databaseReference.updateChildren(taskmap);

    }
}
