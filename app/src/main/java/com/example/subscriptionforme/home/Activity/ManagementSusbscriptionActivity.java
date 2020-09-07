package com.example.subscriptionforme.home.Activity;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.subscriptionforme.AppUsedTimeData;
import com.example.subscriptionforme.R;
import com.example.subscriptionforme.home.Data.AllAccountDatabase;
import com.example.subscriptionforme.home.Data.UserDatabase;
import com.example.subscriptionforme.home.Data.UserSubscriptionData;
import com.example.subscriptionforme.home.Dialog.AlarmSettingDialog;
import com.example.subscriptionforme.home.Dialog.CalendarDialog;
import com.example.subscriptionforme.home.Listener.DeleteUserSubscriptionOnClickListener;
import com.example.subscriptionforme.main.AppTimeCheckDialog;
import com.example.subscriptionforme.main.MainActivity;
import com.example.subscriptionforme.setting.card.AccountVO;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ManagementSusbscriptionActivity extends AppCompatActivity {

    private Context context;
    private UserSubscriptionData userSubscriptionData;
    private ImageView logoImage, warnningImage;
    private TextView name, paymentSystem, beginningDate, payDate, updatePayDate, updateAlarmSetting, deleteSubscriptionTextView, review, recommendation, useStatus;
    private EditText priceEditText, deleteUrlEditText, descriptionEditText;
    private View payDateView, alarmSettingView;
    private LinearLayout useStatusLinearLayout;
    private Button updateButton;
    private ArrayList<AccountVO> accountList;
    private SQLiteDatabase allAccountDatabase;
    private int dataCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_subscription);

        context = getApplicationContext();

        accountList = new ArrayList<>();
        allAccountDatabase = AllAccountDatabase.getInstance(context).getReadableDatabase();
        dataCount = AllAccountDatabase.getInstance(context).getDataCount(allAccountDatabase);

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
        useStatus = findViewById(R.id.use_status_ativity_management_subscription);
        review = findViewById(R.id.review_ativity_management_subscription);
        recommendation = findViewById(R.id.recommendation_ativity_management_subscription);
        warnningImage = findViewById(R.id.warnnig_ativity_management_subscription);
        useStatusLinearLayout = findViewById(R.id.use_status_layout_ativity_management_subscription);

        //이용 현황 셋
        setUserUseStatusData();

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
        String price = moneyFormat(Integer.parseInt(priceEditText.getText().toString().replaceAll(",", "")));

        UserDatabase.getInstance(context).updateSubscruption(UserDatabase.getInstance(context).getWritableDatabase(), userSubscriptionData.getRegisterNumber(),
                price, updatePayDate.getText().toString(), paymentDay, updateAlarmSetting.getText().toString(), isAlarmOn, description, deleteURL);

        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    //계좌정보를 이용해서 이용 현황 set
    public void setUserUseStatusData() {

        String targetDES = "null", targetDES2 = "null";
        recommendation.setClickable(false);

        //데이터가 0일 경우, 즉 계좌 등록이 안됐을 경우.
        if (dataCount == 0) {
            useStatus.setText("-");
            recommendation.setText("-");
            recommendation.setTextColor(Color.BLACK);
            review.setText("계좌가 연동되지 않았습니다. 계좌 연동 진행 시, 해당 서비스에 대한 구독 For Me 만의 총평을 알려드릴게요!");
            warnningImage.setVisibility(View.INVISIBLE);
            return;
        }

        switch (userSubscriptionData.getSubscriptionNumberID()) {

            case "0":
                targetDES = "네이버페이결제";
                judgeRecommendWithPrice(setArrayData(targetDES, targetDES2), 100000);
                break;

            case "1":
                targetDES = "G마켓";
                targetDES2 = "옥션";
                judgeRecommendWithPrice(setArrayData(targetDES, targetDES2), 50000);
                break;

            case "2":
                targetDES = "쿠팡";
                judgeRecommendWithPrice(setArrayData(targetDES, targetDES2), 50000);
                break;

            case "3":
                targetDES = "요기요";
                judgeRecommendWithPrice(setArrayData(targetDES, targetDES2), 50000);
                break;

            case "4":
                makeLayoutInvisible();
                break;

            case "5":
            case "6":
                makeLayoutInvisible();
                break;

            case "7":
            case "8":
            case "9":
                targetDES = "티몬";
                judgeRecommendWithPrice(setArrayData(targetDES, targetDES2), 10000);
                break;

            case "10":
            case "11":
            case "12":
                makeLayoutInvisible();
                break;

            case "13":
            case "14":
            case "15":
                makeLayoutInvisible();
                break;

            case "16":
            case "17":
            case "18":
                judceRecommnedWithTime("netflix");
                break;

            case "19":
                judceRecommnedWithTime("youtube");
                break;
        }

    }

    public void judceRecommnedWithTime(String appName) {

        //권한 체크
        boolean granted = false;
        AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), getPackageName());

        if (mode == AppOpsManager.MODE_DEFAULT) {
            granted = (checkCallingOrSelfPermission(android.Manifest.permission.PACKAGE_USAGE_STATS) == PackageManager.PERMISSION_GRANTED);
        } else {
            granted = (mode == AppOpsManager.MODE_ALLOWED);
        }

        //권한 체크가 false
        if (!granted) {
            useStatus.setText("-");
            recommendation.setText(">>>");
            recommendation.setClickable(true);
            recommendation.setTextColor(Color.BLUE);
            review.setText("어플 사용시간 측정을 위해 사용정보 접근 허용 권한이 필요합니다. 위의 화살표를 누르시면 권한 설정 안내를 해드리겠습니다.");
            warnningImage.setVisibility(View.INVISIBLE);

            recommendation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppTimeCheckDialog appTimeCheckDialog = new AppTimeCheckDialog(ManagementSusbscriptionActivity.this,true);
                }
            });

        } else {
            //권한 체크 된 경우.

            AppUsedTimeData appUsedTimeData = new AppUsedTimeData();

            Long youtubeUseTime = Long.valueOf(0);
            switch (appName){

                case "netflix":
                    youtubeUseTime = appUsedTimeData.getAppUsedTime(context, "netflix") / (30 * 1000);
                    break;

                case "youtube":
                    youtubeUseTime = appUsedTimeData.getAppUsedTime(context, "youtube") / (30 * 1000);
                    break;

            }


            //300분보다 높으면
            if (youtubeUseTime > 300) {
                useStatus.setText(youtubeUseTime + " 분");
                String recommendationMaintainString = "해당 서비스를 충분히 효율적으로 사용하고 있어요! 이용을 유지하는 것을 추천드립니다.";
                recommendation.setText("유지 추천");
                recommendation.setTextColor(Color.GREEN);
                review.setText(recommendationMaintainString);
                warnningImage.setVisibility(View.INVISIBLE);
            } else {
                useStatus.setText(youtubeUseTime + " 분");
                String recommendationCancelString = "최근 2주 서비스 사용 시간을 보니, 이용이 많지 않습니다. 해지를 추천드립니다.";
                recommendation.setText("해지 추천");
                recommendation.setTextColor(Color.RED);
                review.setText(recommendationCancelString);
                warnningImage.setVisibility(View.VISIBLE);
            }
        }
    }

    public int setArrayData(String targetDES, String targetDES2) {
        int useStatusData = 0;
        for (int index = 0; index < dataCount; index++) {
            accountList.add(AllAccountDatabase.getInstance(context).getAccountdata(allAccountDatabase, index));
        }

        for (int index = 0; index < dataCount; index++) {
            accountList.add(AllAccountDatabase.getInstance(context).getAccountdata(allAccountDatabase, index));

            if (accountList.get(index).getResAccountDesc3().contains(targetDES) || accountList.get(index).getResAccountDesc3().contains(targetDES2)) {
                useStatusData += Integer.parseInt(accountList.get(index).getResAccountOut());
            }
        }

        return useStatusData;
    }

    public void makeLayoutInvisible() {
        useStatusLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
    }

    public void judgeRecommendWithPrice(int useStatusData, int price) {
        if (useStatusData > price)
            recommendMaintaination(useStatusData);
        else
            recommendCancel(useStatusData);
    }

    public void recommendMaintaination(int useStatusData) {
        useStatus.setText(moneyFormat(useStatusData) + " 원");
        String recommendationMaintainString = "해당 서비스를 충분히 효율적으로 사용하고 있어요! 이용을 유지하는 것을 추천드립니다.";
        recommendation.setText("유지 추천");
        recommendation.setTextColor(Color.GREEN);
        review.setText(recommendationMaintainString);
        warnningImage.setVisibility(View.INVISIBLE);
    }

    public void recommendCancel(int useStatusData) {
        useStatus.setText(moneyFormat(useStatusData) + " 원");
        String recommendationCancelString = "지난 달 사용액과 비교해보니, 혜택 받는 금액이 더 적을 것 으로 예상됩니다. 해지를 추천드립니다.";
        recommendation.setText("해지 추천");
        recommendation.setTextColor(Color.RED);
        review.setText(recommendationCancelString);
        warnningImage.setVisibility(View.VISIBLE);
    }

    public String moneyFormat(int intputMoney) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        return decimalFormat.format(intputMoney);
    }

    public void backButton(View view) {
        onBackPressed();
    }
}
