package com.example.subscriptionforme.main;

import android.app.AppOpsManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.subscriptionforme.AppUsedTimeData;
import com.example.subscriptionforme.R;
import com.example.subscriptionforme.home.Activity.ManagementSusbscriptionActivity;

public class AppTimeCheckDialog extends Dialog {

    private Button cancelButton, okayButton;
    private Context context;

    public AppTimeCheckDialog(Context context){
        super(context);

        this.context = context;

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_check_app_time_permission);

        cancelButton = findViewById(R.id.cancel_button_check_app_time_permission);
        okayButton = findViewById(R.id.ok_button_check_app_time_permission);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        okayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void getPermissionAndAppData(){
        //퍼미션 체크
        boolean granted = false;
        AppOpsManager appOps = (AppOpsManager)context.getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,android.os.Process.myUid(), context.getPackageName());

        if (mode == AppOpsManager.MODE_DEFAULT) {
            granted = (context.checkCallingOrSelfPermission(android.Manifest.permission.PACKAGE_USAGE_STATS) == PackageManager.PERMISSION_GRANTED);
        } else {
            granted = (mode == AppOpsManager.MODE_ALLOWED);
        }

        if (granted == false)
        {
            // 권한이 없을 경우 권한 요구 페이지 이동
            Intent intent = new Intent(android.provider.Settings.ACTION_USAGE_ACCESS_SETTINGS);
            context.startActivity(intent);
        }

        AppUsedTimeData appUsedTimeData = new AppUsedTimeData();
        Long youtubeUseTime = appUsedTimeData.getAppUsedTime(context,"youtube")/(60*1000);
        Log.d("youtube", String.valueOf(youtubeUseTime));
    }

}
