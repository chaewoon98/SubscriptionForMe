package com.example.subscriptionforme.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.subscriptionforme.R;

import java.util.List;

public class AlarmButtonOnClickListener implements View.OnClickListener {

    private int position;
    private List<UserSubscriptionData> userSubscriptionDataList;
    private ImageButton alarmButton;
    private Context context;

    public AlarmButtonOnClickListener(int position, List<UserSubscriptionData> userSubscriptionDataList, ImageButton alarmButton, Context context) {
        this.position = position;
        this.userSubscriptionDataList = userSubscriptionDataList;
        this.alarmButton = alarmButton;
        this.context = context;
    }

    @Override
    public void onClick(View view) {

        if(userSubscriptionDataList.get(position).isAlarmOn().equals("true")){
            alarmButton.setImageResource(R.drawable.alarm_cancel_button);
            userSubscriptionDataList.get(position).setAlarmOn("false");
            Toast.makeText(context,"알람이 꺼졌습니다.",Toast.LENGTH_SHORT).show();
        }
        else{
            alarmButton.setImageResource(R.drawable.alarm_button);
            userSubscriptionDataList.get(position).setAlarmOn("true");
            Toast.makeText(context,"알람이 켜졌습니다.",Toast.LENGTH_SHORT).show();
    }
    }
}
