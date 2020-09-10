package com.example.subscriptionforme.recommendation.detail_recommendation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.subscriptionforme.R;

public class Detail_Youtube extends AppCompatActivity {

    private Button button;
    private Long youtubeUseTime;
    private TextView youtubeTimeTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_recommendation);

        Intent intent = getIntent();
        youtubeUseTime = intent.getExtras().getLong("youtubeTime");

        youtubeTimeTextView = findViewById(R.id.youtube_time_recommendation);
        button = findViewById(R.id.service_add_button_ativity_detail);

        youtubeTimeTextView.setText(youtubeUseTime.toString() + "분");

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/premium")));
            }
        });
    }

}
