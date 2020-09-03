package com.example.subscriptionforme.setting.card;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.home.FragmentHome;
import com.example.subscriptionforme.main.MainActivity;

import java.util.regex.Pattern;

public class AccountGetDataActivity extends AppCompatActivity {

    Button button; // 데이터 얻는 버튼

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_get_data);

        button = findViewById(R.id.account_data_get_button);
    }

    @Override
    public void onStart() {

        super.onStart();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }

}
