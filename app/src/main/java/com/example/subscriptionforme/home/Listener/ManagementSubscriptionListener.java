package com.example.subscriptionforme.home.Listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.subscriptionforme.home.Activity.ManagementSusbscriptionActivity;
import com.example.subscriptionforme.home.Data.UserSubscriptionData;
import com.example.subscriptionforme.home.Dialog.CheckDeleteUserSubscriptionDialog;
import com.example.subscriptionforme.main.MainActivity;

public class ManagementSubscriptionListener implements View.OnClickListener {

    private Context context;
    private UserSubscriptionData userSubscriptionData;
    private MainActivity mainActivity;

    public ManagementSubscriptionListener(Context context, UserSubscriptionData userSubscriptionData, MainActivity mainActivity) {
        this.context = context;
        this.userSubscriptionData = userSubscriptionData;
        this.mainActivity = mainActivity;
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, ManagementSusbscriptionActivity.class);
        intent.putExtra("userSubscriptionData",userSubscriptionData);
        mainActivity.startActivity(intent);
    }
}
