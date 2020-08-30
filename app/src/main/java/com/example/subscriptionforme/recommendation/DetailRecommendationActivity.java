package com.example.subscriptionforme.recommendation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.subscriptionforme.R;

public class DetailRecommendationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recommendation);

        TextView name = findViewById(R.id.name_text);
        ImageView logo = findViewById(R.id.icon_logo);
        ImageView benefit = findViewById(R.id.benefit_image);

        Intent intent = getIntent();

        String name_text = intent.getExtras().getString("name");
        int logo_image = intent.getExtras().getInt("logo");
        int benefit_image = intent.getExtras().getInt("benefit");
        int color = intent.getExtras().getInt("color");

        name.setText(name_text);
        name.setTextColor(color);
        logo.setImageResource(logo_image);
        benefit.setImageResource(benefit_image);
    }
}
