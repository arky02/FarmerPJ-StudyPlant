package com.example.studyplant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    EditText edt_num,edt_quiz;
    SharedPreferences shared;
    SharedPreferences.Editor edit;
    private FirebaseDatabase firebaseDatabase;
    private List<String> userNum = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        firebaseDatabase = FirebaseDatabase.getInstance();

        btn_login = findViewById(R.id.btn_login);
        edt_num = findViewById(R.id.Edt_num);
        edt_quiz = findViewById(R.id.Edt_quiz);
        shared = getApplicationContext().getSharedPreferences("s8_num",MODE_PRIVATE);

        if(shared.contains("num")){
            Intent intent = new Intent(getApplicationContext(),password.class);
            intent.putExtra("isFirst",false);
            startActivity(intent);


        }

        firebaseDatabase.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userNum.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String num = snapshot.getKey();
                    userNum.add(num);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < userNum.size(); i++) {
                    if(!edt_num.getText().equals(userNum.get(i))) {
                        Toast.makeText(getApplicationContext(), "이미 등록된 농부입니다", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(edt_quiz.getText().toString().equals("불파만지파참")){
                            Global.s8_num = Integer.parseInt(edt_num.getText().toString());
                            edit = shared.edit();
                            edit.putInt("num",Global.s8_num);
                            edit.commit();
                            Intent intent = new Intent(getApplicationContext(),password.class);
                            intent.putExtra("isFirst",true);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }
}
