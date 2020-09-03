package com.example.subscriptionforme.setting.card;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.subscriptionforme.R;

import java.util.ArrayList;

public class AgreementActivity extends AppCompatActivity {

    public static int MAX_COUNT = 15; // 계좌 최대 입력 갯수
    public static int MIN_COUNT = 10; // 계좌 최소 입력 갯수
    Button button; // 동의 버튼
    TextView bankName; // 은행 이름
    TextView warningText; // 경고 문고
    EditText accountNumberEditText;
    boolean canNextButton;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        button = findViewById(R.id.agreement_button);
        bankName = findViewById(R.id.bankText);
        accountNumberEditText = findViewById(R.id.accountNumberEdit);
        warningText = findViewById(R.id.warningText);
        warningText.setText("");

        setCardName(); // 카드 이름 세팅
    }

    @Override
    public void onStart() {

        super.onStart();


        accountNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // 입력 글자 수 제한
                if(charSequence.length() > MAX_COUNT){
                    accountNumberEditText.setText(accountNumberEditText.getText().toString().substring(0,MAX_COUNT));
                    accountNumberEditText.setSelection(accountNumberEditText.length());
                }

                if(charSequence.length() < MIN_COUNT && charSequence.length() > 0){
                    warningText.setText("계좌번호 자릿 수를 확인 바랍니다.");
                    canNextButton = false;
                } else{
                    warningText.setText("");
                    canNextButton = true;
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
                    Log.d("태순","넘어감");
                }
            }
        });
    }

    public void setCardName(){

        Intent secondIntent = getIntent();

        if(secondIntent.getIntExtra("card",0) == 0){
            bankName.setText("NH농협은행");
        } else if(secondIntent.getIntExtra("card",1) == 1){
            bankName.setText("우리은행");
        } else if(secondIntent.getIntExtra("card",2) == 2){
            bankName.setText("신한은행");
        } else if(secondIntent.getIntExtra("card",3) == 3){
            bankName.setText("KB국민은행");
        } else if(secondIntent.getIntExtra("card",4) == 4){
            bankName.setText("하나은행");
        } else if(secondIntent.getIntExtra("card",5) == 5){
            bankName.setText("씨티은행");
        } else if(secondIntent.getIntExtra("card",6) == 6){
            bankName.setText("IBK기업은행");
        } else if(secondIntent.getIntExtra("card",7) == 7){
            bankName.setText("케이뱅크");
        } else if(secondIntent.getIntExtra("card",8) == 8){
            bankName.setText("카카오뱅크");
        }

    }

}
