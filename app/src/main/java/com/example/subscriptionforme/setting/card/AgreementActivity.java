package com.example.subscriptionforme.setting.card;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.subscriptionforme.R;

import java.util.ArrayList;

public class AgreementActivity extends AppCompatActivity {

    Button button; // 동의 버튼

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        button = findViewById(R.id.agreement_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}
