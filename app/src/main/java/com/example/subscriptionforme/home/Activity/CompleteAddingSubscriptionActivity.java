package com.example.subscriptionforme.home.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.main.MainActivity;

public class CompleteAddingSubscriptionActivity extends AppCompatActivity {

    private Button completeButton;
    private TextView priceTextview,nameTextview,payDateTextview,alarmSettingTextview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_add_subscription);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        String price = intent.getExtras().getString("price");
        String payDate = intent.getExtras().getString("payDate");
        String alarmSetting = intent.getExtras().getString("alarmSetting");

        nameTextview = findViewById(R.id.name_ativity_compelete_add_subscription);
        priceTextview = findViewById(R.id.price_ativity_compelete_add_subscription);
        payDateTextview = findViewById(R.id.pay_date_ativity_compelete_add_subscription);
        alarmSettingTextview = findViewById(R.id.alarm_setting_ativity_compelete_add_subscription);
        completeButton = findViewById(R.id.compelte_button_ativity_compelete_add_subscription);

        nameTextview.setText(name);
        priceTextview.setText(price);
        payDateTextview.setText(payDate);
        alarmSettingTextview.setText(alarmSetting);

        completeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompleteAddingSubscriptionActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}
