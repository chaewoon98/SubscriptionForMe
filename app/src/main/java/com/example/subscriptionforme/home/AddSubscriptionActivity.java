package com.example.subscriptionforme.home;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.SubscriptionModelData;
import com.example.subscriptionforme.main.UserDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddSubscriptionActivity extends Activity {

    private ArrayList<SubscriptionModelData> subscriptionModelDataList;
    private ArrayList<String> subsciptionModelNameList;

    private AutoCompleteTextView autoCompleteTextView;
    private EditText priceEditText, paymentSystemEditText;
    private TextView payDateTextView, alarmSettingTextView;
    private View payDateView, alarmSettingView;
    private Context context;
    private String selectedSubscriptionNumber;
    private int selectedSubscriptionImageID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);

        context = getApplicationContext();

        Intent intent = getIntent();

        //구독 모델 리스트 받아오기
        subscriptionModelDataList = new ArrayList<SubscriptionModelData>();
        subscriptionModelDataList = intent.getParcelableArrayListExtra("subsciptionModelDataList");

        //이름 검색어 설정
        autoCompleteTextView = findViewById(R.id.auto_textview_ativity_add);
        SubscriptionAutoCompleteAdapter adapter = new SubscriptionAutoCompleteAdapter(this, subscriptionModelDataList);
        autoCompleteTextView.setAdapter(adapter);

        priceEditText = findViewById(R.id.price_ativity_add_subscription);
        paymentSystemEditText = findViewById(R.id.payment_system_ativity_add_subscription);
        payDateView = findViewById(R.id.pay_date_touch_view_ativity_add_subscription);
        payDateTextView = findViewById(R.id.pay_date_ativity_add_subscription);
        alarmSettingView = findViewById(R.id.alarm_setting_touch_view_ativity_add_subscription);
        alarmSettingTextView = findViewById(R.id.alarm_setting_ativity_add_subscription);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SubscriptionModelData subscriptionModelData = new SubscriptionModelData();
                subscriptionModelData = (SubscriptionModelData) adapterView.getItemAtPosition(position);

                selectedSubscriptionImageID = subscriptionModelData.getImageID();
                selectedSubscriptionNumber = subscriptionModelData.getNumberID();
                priceEditText.setText(subscriptionModelData.getPrice());
                paymentSystemEditText.setText(subscriptionModelData.getPaymentSystem());
            }
        });

        payDateView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                CalendarDialog calendarDialog = new CalendarDialog(AddSubscriptionActivity.this, payDateTextView);
            }
        });

        alarmSettingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmSettingDialog alarmSettingDialog = new AlarmSettingDialog(AddSubscriptionActivity.this, alarmSettingTextView);
            }
        });

    }

    //서비스 추가 버튼 리스너
    public void addServiceButton(View view) {

        String isAlarmOn;

        if (autoCompleteTextView.getText().toString().equals("")) {
            Toast.makeText(context, "서비스 이름을 설정하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (paymentSystemEditText.getText().toString().equals("")) {
            Toast.makeText(context, "멤버십 종류를 설정하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (priceEditText.getText().toString().equals("")) {
            Toast.makeText(context, "서비스 금액을 설정하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (payDateTextView.getText().toString().equals("설정하기")) {
            Toast.makeText(context, "최초 결제일을 설정하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (alarmSettingTextView.getText().toString().equals("설정하기")) {
            Toast.makeText(context, "결제전 알람을 설정하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (alarmSettingTextView.getText().toString()) {

            case "1일 전":
            case "3일 전":
            case "일주일 전":
                isAlarmOn = "true";
                break;

            case "설정 안함":
                isAlarmOn = "false";
                break;

            default:
                isAlarmOn = "false";
                break;
        }


        //DB 등록 번호 설정
        int registerNumber = UserDatabase.getInstance(context).getDataCount(UserDatabase.getInstance(context).getReadableDatabase());

        //아이템이 select 되지 않아서 설정값이 null로 들어갈경우
        if (selectedSubscriptionNumber.equals("null")) {

            Log.d("123","123");
        } else {
            UserDatabase.getInstance(context).insertSubcriptionData(UserDatabase.getInstance(context).getWritableDatabase(), Integer.toString(registerNumber), selectedSubscriptionNumber,
                    autoCompleteTextView.getText().toString(), paymentSystemEditText.getText().toString(), priceEditText.getText().toString(), payDateTextView.getText().toString(),
                    alarmSettingTextView.getText().toString(),isAlarmOn,selectedSubscriptionImageID);

            onBackPressed();
        }

    }

    public void backButton(View view) {
        onBackPressed();
    }
}
