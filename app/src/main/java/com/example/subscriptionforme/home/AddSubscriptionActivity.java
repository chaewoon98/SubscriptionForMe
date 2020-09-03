package com.example.subscriptionforme.home;

import android.app.Activity;
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

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.SubscriptionModelData;
import com.example.subscriptionforme.home.Data.SubscriptionAutoCompleteAdapter;
import com.example.subscriptionforme.home.Dialog.AlarmSettingDialog;
import com.example.subscriptionforme.home.Dialog.CalendarDialog;
import com.example.subscriptionforme.main.UserDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddSubscriptionActivity extends Activity {

    private ArrayList<SubscriptionModelData> subscriptionModelDataList;
    private ArrayList<String> subsciptionModelNameList;

    private AutoCompleteTextView autoCompleteTextView;
    private EditText priceEditText, paymentSystemEditText;
    private TextView payDateTextView, alarmSettingTextView;
    private View payDateView, alarmSettingView;
    private Context context;
    private String selectedSubscriptionNumber,selectedSubscriptionDescription;
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
        payDateView = findViewById(R.id.pay_date_touch_view_ativity_add_subscription);
        payDateTextView = findViewById(R.id.pay_date_ativity_add_subscription);
        alarmSettingView = findViewById(R.id.alarm_setting_touch_view_ativity_add_subscription);
        alarmSettingTextView = findViewById(R.id.alarm_setting_ativity_add_subscription);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SubscriptionModelData subscriptionModelData = new SubscriptionModelData();
                subscriptionModelData = (SubscriptionModelData) adapterView.getItemAtPosition(position);

                selectedSubscriptionDescription = subscriptionModelData.getDescrpition();
                selectedSubscriptionImageID = subscriptionModelData.getImageID();
                selectedSubscriptionNumber = subscriptionModelData.getNumberID();
                priceEditText.setText(subscriptionModelData.getPrice());
                paymentSystemEditText.setText(subscriptionModelData.getPaymentSystem());

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


        //DB 등록 번호 설정
        // registerNumber = UserDatabase.getInstance(context).getDataCount(UserDatabase.getInstance(context).getReadableDatabase());

        //아이템이 select 되지 않아서 설정값이 null로 들어갈경우
        if (selectedSubscriptionNumber.equals("null")) {

            //수동으로 직접 등록 할 경우 예외처리 들어가야함.
            Log.d("123","123");
        } else {
            UserDatabase.getInstance(context).insertSubcriptionData(UserDatabase.getInstance(context).getWritableDatabase(), selectedSubscriptionNumber,
                    autoCompleteTextView.getText().toString(), paymentSystemEditText.getText().toString(), priceEditText.getText().toString(), payDateTextView.getText().toString()
                    ,getPayDate(payDateTextView.getText().toString()),alarmSettingTextView.getText().toString(),isAlarmOn,selectedSubscriptionDescription,selectedSubscriptionImageID);

            onBackPressed();
        }

    }

    public void backButton(View view) {
        onBackPressed();
    }

    /**실제로 결제할 날짜를 get하늕 메소드
     * 최초 결제일 2020년 8월 11일 -> 현재 월 + 최초결제일의 일
     * 최초 결제일이 현재 보다 늦을 경우 그대로 들어가게끔.
     */
    public String getPayDate(String subscriptionPayDateString) {

        //현지 시간과 비교
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 M월 d일");

        SimpleDateFormat yearDateFormat = new SimpleDateFormat("yyyy년");
        SimpleDateFormat monthDateFormat = new SimpleDateFormat("M월");
        SimpleDateFormat dayDateFormat = new SimpleDateFormat("d일");

        try {
            Date nowDate = dateFormat.parse(dateFormat.format(date));
            Date subscriptionPayDate = dateFormat.parse(subscriptionPayDateString);

            //현재 시간보다 추가하는 구독 서비스의 최초결제일이 빠를 때
            if (nowDate.after(subscriptionPayDate)) {

                String paymentYearString = yearDateFormat.format(date);
                String paymentMonthString = monthDateFormat.format(date);
                String paymentDayString = dayDateFormat.format(subscriptionPayDate);

                if (paymentDayString.equals("31일"))
                    paymentDayString = "30일";

                return paymentYearString + " " + paymentMonthString + " " + paymentDayString;
            }

            String paymentYearString = yearDateFormat.format(subscriptionPayDate);
            String paymentMonthString = monthDateFormat.format(subscriptionPayDate);
            String paymentDayString = dayDateFormat.format(subscriptionPayDate);
            return paymentYearString + " " + paymentMonthString + " " + paymentDayString;

        } catch (Exception e) {
        }
        return "";
    }
}
