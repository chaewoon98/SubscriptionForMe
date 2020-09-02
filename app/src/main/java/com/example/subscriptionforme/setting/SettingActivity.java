package com.example.subscriptionforme.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.subscriptionforme.R;
import com.example.subscriptionforme.setting.card.CardActivity;

public class SettingActivity extends AppCompatActivity {

    Button cardDataButton;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_setting);
    }

    @Override
    public void onStart() {

        super.onStart();
        cardDataButton = findViewById(R.id.buttonCardData);
        cardDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(view.getContext(), CardActivity.class);
                startActivity(intent);
            }
        });


    }
}
