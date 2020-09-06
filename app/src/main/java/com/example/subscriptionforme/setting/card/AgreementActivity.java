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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.subscriptionforme.R;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AgreementActivity extends AppCompatActivity {

    public int MAX_COUNT; // 계좌 최대 입력 갯수
    public static int BANK;
    Button button; // 동의 버튼
    TextView bankName; // 은행 이름
    TextView warningText; // 경고 문고
    EditText accountNumberEditText;
    boolean canNextButton;
    ImageView back_btn;
    int index;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        MAX_COUNT = 15;
        button = findViewById(R.id.agreement_button);
        bankName = findViewById(R.id.bankText);
        accountNumberEditText = findViewById(R.id.accountNumberEdit);
        back_btn = findViewById(R.id.agreement_back_btn);
        warningText = findViewById(R.id.warningText);
        warningText.setText("");
        BANK = 0;
        setCardName(); // 카드 이름 세팅
        Intent secondIntent = getIntent();
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

                    Pattern ps = Pattern.compile("^[0-9]{11,15}|''+$");

                    if (!ps.matcher(charSequence).matches()) {
                        warningText.setText("계좌번호를 확인 바랍니다.");
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
                    Intent intent = new Intent(getApplicationContext(), AccountPasswordActivity.class);
                    intent.putExtra("card", index);
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

    public void setCardName(){

        Intent secondIntent = getIntent();

        if(secondIntent.getIntExtra("card",0) == 0){
            BANK = 0;
            bankName.setText("NH농협은행");
        } else if(secondIntent.getIntExtra("card",1) == 1){
            BANK = 1;
            bankName.setText("우리은행");
        } else if(secondIntent.getIntExtra("card",2) == 2){
            BANK = 2;
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
