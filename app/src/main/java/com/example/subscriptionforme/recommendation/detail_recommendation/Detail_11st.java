package com.example.subscriptionforme.recommendation.detail_recommendation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.setting.SettingActivity;

import java.util.ArrayList;
import java.util.List;

public class Detail_11st extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_11st_recommendation);

        button = findViewById(R.id.service_add_button_ativity_detail);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.auction.co.kr/corners/smileclub")));
            }
        });
    }
}
