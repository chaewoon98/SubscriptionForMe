package com.example.subscriptionforme.home.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.home.Data.UserDatabase;
import com.example.subscriptionforme.home.Data.UserSubscriptionData;
import com.example.subscriptionforme.home.Dialog.AlarmSettingDialog;
import com.example.subscriptionforme.home.Dialog.CalendarDialog;
import com.example.subscriptionforme.home.Listener.DeleteUserSubscriptionOnClickListener;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManagementSusbscriptionActivity extends AppCompatActivity {

    private Context context;
    private UserSubscriptionData userSubscriptionData;
    private ImageView logoImage;
    private TextView name, paymentSystem, beginningDate, payDate, updatePayDate, updateAlarmSetting, deleteSubscriptionTextView;
    private EditText priceEditText, deleteUrlEditText, descriptionEditText;
    private View payDateView, alarmSettingView;
    private Button updateButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_subscription);

        context = getApplicationContext();

        Intent intent = getIntent();
        userSubscriptionData = (UserSubscriptionData) intent.getSerializableExtra("userSubscriptionData");

        logoImage = findViewById(R.id.logo_ativity_management_subscription);
        beginningDate = findViewById(R.id.beginnig_date_ativity_management_subscription);
        payDate = findViewById(R.id.pay_date_ativity_management_subscription);
        updatePayDate = findViewById(R.id.update_pay_date_ativity_management_subscription);
        updateAlarmSetting = findViewById(R.id.alarm_setting_ativity_management_subscription);
        priceEditText = findViewById(R.id.price_ativity_management_subscription);
        deleteUrlEditText = findViewById(R.id.delete_url_ativity_management_subscription);
        descriptionEditText = findViewById(R.id.description_ativity_management_subscription);
        payDateView = findViewById(R.id.pay_date_touch_view_ativity_management_subscription);
        alarmSettingView = findViewById(R.id.alarm_setting_touch_view_ativity_management_subscription);
        updateButton = findViewById(R.id.service_update_button_ativity_management_subscription);
        name = findViewById(R.id.name_ativity_management_subscription);
        paymentSystem = findViewById(R.id.payment_system_ativity_management_subscription);
        deleteSubscriptionTextView = findViewById(R.id.delete_subscription_ativity_management_subscription);

        logoImage.setImageResource(userSubscriptionData.getSubscriptionImageID());
        beginningDate.setText(userSubscriptionData.getBeginningPayDate());
        payDate.setText(userSubscriptionData.getSubscriptionPayDate());
        updatePayDate.setText(userSubscriptionData.getSubscriptionPayDate());
        updateAlarmSetting.setText(userSubscriptionData.getAlarmSetting());
        priceEditText.setText(userSubscriptionData.getSubscriptionPrice());
        name.setText(userSubscriptionData.getSubscriptionName());
        paymentSystem.setText(userSubscriptionData.getSubscriptionPaymentSystem());

        //deleteURL이 있는 경우에만
        if (!userSubscriptionData.getSubscriptionDeleteURL().equals("") && userSubscriptionData.getSubscriptionDeleteURL() != null && !userSubscriptionData.getSubscriptionDeleteURL().equals("null"))
            deleteUrlEditText.setText(userSubscriptionData.getSubscriptionDeleteURL());

        //description이 있는 경우에만
        if (!userSubscriptionData.getSubscriptionDescription().equals("") && userSubscriptionData.getSubscriptionDescription() != null
                && !userSubscriptionData.getSubscriptionDescription().equals("구독 서비스의 설명 & 메모가 없습니다."))
            descriptionEditText.setText(userSubscriptionData.getSubscriptionDescription());

        payDateView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                CalendarDialog calendarDialog = new CalendarDialog(ManagementSusbscriptionActivity.this, updatePayDate);
            }
        });

        alarmSettingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmSettingDialog alarmSettingDialog = new AlarmSettingDialog(ManagementSusbscriptionActivity.this, updateAlarmSetting);
            }
        });

        deleteSubscriptionTextView.setOnClickListener(new DeleteUserSubscriptionOnClickListener(ManagementSusbscriptionActivity.this, userSubscriptionData, this));

    }

    public void updateSubscriptionData(View view) {

        String isAlarmOn, description, deleteURL;

        if (priceEditText.getText().toString().equals("")) {
            Toast.makeText(context, "서비스 금액을 설정하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (updateAlarmSetting.getText().toString()) {

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
        if (descriptionEditText.getText().toString() == null || descriptionEditText.getText().toString().equals("")) {
            description = "구독 서비스의 설명 & 메모가 없습니다.";
        } else
            description = descriptionEditText.getText().toString();

        //url 부분
        if (deleteUrlEditText.getText().toString() == null || deleteUrlEditText.getText().toString().equals("")) {
            deleteURL = "null";
        } else
            deleteURL = deleteUrlEditText.getText().toString();

        CalendarDialog calendarDialog = new CalendarDialog(context);
        String paymentDay = calendarDialog.getPayDate(updatePayDate.getText().toString());
        String price =moneyFormat(Integer.parseInt(priceEditText.getText().toString().replaceAll(",","")));

        UserDatabase.getInstance(context).updateSubscruption(UserDatabase.getInstance(context).getWritableDatabase(), userSubscriptionData.getRegisterNumber(),
                price,updatePayDate.getText().toString(),paymentDay,updateAlarmSetting.getText().toString(),isAlarmOn,description,deleteURL);

        onBackPressed();

    }

    public String moneyFormat(int intputMoney){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        return decimalFormat.format(intputMoney);
    }

    public void backButton(View view) {
        onBackPressed();
    }
}
