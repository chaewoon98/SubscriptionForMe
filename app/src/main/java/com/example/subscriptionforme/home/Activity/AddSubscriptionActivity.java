package com.example.subscriptionforme.home.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.SubscriptionModelData;
import com.example.subscriptionforme.home.Data.SubscriptionAutoCompleteAdapter;
import com.example.subscriptionforme.home.Dialog.AlarmSettingDialog;
import com.example.subscriptionforme.home.Dialog.CalendarDialog;
import com.example.subscriptionforme.home.Data.UserDatabase;
import com.example.subscriptionforme.main.MainActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddSubscriptionActivity extends AppCompatActivity {

    private ArrayList<SubscriptionModelData> subscriptionModelDataList;
    private ArrayList<String> subsciptionModelNameList;

    private AutoCompleteTextView autoCompleteTextView;
    private EditText priceEditText, paymentSystemEditText,descriptionEditText;
    private TextView payDateTextView, alarmSettingTextView;
    private View payDateView, alarmSettingView;
    private Context context;
    private String selectedSubscriptionNumber,selectedSubscriptionDescription,selectedSubscriptionDeleteURL;
    private int selectedSubscriptionImageID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);

        //autoCompleteTextView 선택 번호를 null로 초기화하고 시작.
        selectedSubscriptionNumber= "null";

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
        descriptionEditText = findViewById(R.id.description_ativity_add_subscription);
        payDateView = findViewById(R.id.pay_date_touch_view_ativity_add_subscription);
        payDateTextView = findViewById(R.id.pay_date_ativity_add_subscription);
        alarmSettingView = findViewById(R.id.alarm_setting_touch_view_ativity_add_subscription);
        alarmSettingTextView = findViewById(R.id.alarm_setting_ativity_add_subscription);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SubscriptionModelData subscriptionModelData = new SubscriptionModelData();
                subscriptionModelData = (SubscriptionModelData) adapterView.getItemAtPosition(position);

                selectedSubscriptionDeleteURL = subscriptionModelData.getCancellationUrl();
                selectedSubscriptionDescription = subscriptionModelData.getDescrpition();
                selectedSubscriptionImageID = subscriptionModelData.getImageID();
                selectedSubscriptionNumber = subscriptionModelData.getNumberID();
                priceEditText.setText(subscriptionModelData.getPrice());
                paymentSystemEditText.setText(subscriptionModelData.getPaymentSystem());
                descriptionEditText.setText(selectedSubscriptionDescription);

                //키보드 내리기
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(autoCompleteTextView.getWindowToken(),0);
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

        //구독 설명&메모 부분 설정.
        if(descriptionEditText.getText().toString() == null || descriptionEditText.getText().toString().equals("")){
            selectedSubscriptionDescription = "구독 서비스의 설명 & 메모가 없습니다.";
        }
        else
            selectedSubscriptionDescription = descriptionEditText.getText().toString();

        if(selectedSubscriptionDeleteURL == null || selectedSubscriptionDeleteURL.equals("")){
            selectedSubscriptionDeleteURL = "null";
        }

        CalendarDialog calendarDialog = new CalendarDialog(context);
        String paymentDay = calendarDialog.getPayDate(payDateTextView.getText().toString());
        String price =moneyFormat(Integer.parseInt(priceEditText.getText().toString().replaceAll(",","")));

        //autoCompleteTextView를 선택하지 않은경우, 즉 수동으로 등록한 경우
        if (selectedSubscriptionNumber.equals("null")) {
            UserDatabase.getInstance(context).insertSubcriptionData(UserDatabase.getInstance(context).getWritableDatabase(), selectedSubscriptionNumber,
                    autoCompleteTextView.getText().toString(), paymentSystemEditText.getText().toString(), price, payDateTextView.getText().toString()
                    ,paymentDay,alarmSettingTextView.getText().toString(),isAlarmOn,selectedSubscriptionDescription,"null",R.drawable.no_image);
        } else {
            UserDatabase.getInstance(context).insertSubcriptionData(UserDatabase.getInstance(context).getWritableDatabase(), selectedSubscriptionNumber,
                    autoCompleteTextView.getText().toString(), paymentSystemEditText.getText().toString(), price, payDateTextView.getText().toString()
                    ,paymentDay,alarmSettingTextView.getText().toString(),isAlarmOn,selectedSubscriptionDescription,selectedSubscriptionDeleteURL,selectedSubscriptionImageID);
        }

        Intent intent = new Intent(context, CompleteAddingSubscriptionActivity.class);
        intent.putExtra("name",autoCompleteTextView.getText().toString());
        intent.putExtra("price",price);
        intent.putExtra("payDate",paymentDay);
        intent.putExtra("alarmSetting",alarmSettingTextView.getText().toString());
        startActivity(intent);

    }

    public void backButton(View view) {
        onBackPressed();
    }

    public String moneyFormat(int intputMoney){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        return decimalFormat.format(intputMoney);
    }
}
