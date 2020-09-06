package com.example.subscriptionforme.setting.card;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.subscriptionforme.R;

import java.util.regex.Pattern;

public class AccountPasswordActivity extends AppCompatActivity {

    public int MAX_COUNT; // 계좌 비밀번호 갯수
    EditText passwordEditText;
    TextView warningText;
    boolean canNextButton;
    ImageButton back_btn;
    Button button; // 동의 버튼
    int index;
    Intent secondIntent;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_password);
        MAX_COUNT = 4;
        warningText = findViewById(R.id.warningPasswordText);
        passwordEditText = findViewById(R.id.accountPasswordEdit);
        button = findViewById(R.id.account_data_button);
        index = 0;
        back_btn = findViewById(R.id.account_password_back_btn);

    }

    @Override
    public void onStart() {

        super.onStart();

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                // 입력 글자 수 제한

                if(charSequence.length() > MAX_COUNT){
                    passwordEditText.setText(passwordEditText.getText().toString().substring(0,MAX_COUNT));
                    passwordEditText.setSelection(passwordEditText.length());
                }

                Pattern ps = Pattern.compile("^[0-9]{0,4}+$");

                if (!ps.matcher(charSequence).matches()) {
                    warningText.setText("비밀번호를 확인 바랍니다.");
                    canNextButton = false;
                }
                else{
                    warningText.setText("");
                    canNextButton = true;
                }

                if(charSequence.length() == 0){
                    warningText.setText("");
                    canNextButton = false;
                }

            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(canNextButton == true){
                    Intent intent = new Intent(getApplicationContext(), AccountGetDataActivity.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

}
