package com.example.subscriptionforme.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.subscriptionforme.R;

public class SurveyActivity extends AppCompatActivity {

    Button submit_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_survey);

        submit_button =findViewById(R.id.submit_survey_button);


        //추가할것!
        //버튼 누르면 설문데이터 저장되어야함


        //설문 제출하기 버튼 이벤트
        submit_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "내 정보 ＆ 관심사 정보가 등록되었습니다.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(view.getContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
