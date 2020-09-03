package com.example.subscriptionforme.home.Listener;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.subscriptionforme.home.Data.UserSubscriptionData;
import com.example.subscriptionforme.home.Dialog.DeleteUserSubscriptionDiaolg;
import com.example.subscriptionforme.home.FragmentHome;
import com.example.subscriptionforme.main.MainActivity;

public class DeleteUserSubscriptionOnClickListener implements View.OnClickListener {

    private Context context;
    private UserSubscriptionData userSubscriptionData;
    private MainActivity mainActivity;

    public DeleteUserSubscriptionOnClickListener(Context context,UserSubscriptionData userSubscriptionData,MainActivity mainActivity) {
        this.context = context;
        this.userSubscriptionData = userSubscriptionData;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        DeleteUserSubscriptionDiaolg deleteUserSubscriptionDiaolg = new DeleteUserSubscriptionDiaolg(context,(MainActivity)context, userSubscriptionData);

        //삭제버튼을 눌렀을시 refresh 될수 있도록
        deleteUserSubscriptionDiaolg.setDeleteUserSubscriptionListener(new DeleteUserSubscriptionListener() {
            @Override
            public void deleteButtonClicked() {
                mainActivity.refreshInFragmentHome();
            }
        });
    }
}
