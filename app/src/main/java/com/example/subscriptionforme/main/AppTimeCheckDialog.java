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
import android.widget.Toast;

import com.example.subscriptionforme.AppUsedTimeData;
import com.example.subscriptionforme.R;
import com.example.subscriptionforme.home.Activity.AddSubscriptionActivity;
import com.example.subscriptionforme.home.Activity.ManagementSusbscriptionActivity;

public class AppTimeCheckDialog extends Dialog {

    private Button cancelButton, okayButton;
    private Context context;
    private boolean isInManagement;

    public AppTimeCheckDialog(Context context, boolean isInManagement) {
        super(context);

        this.context = context;
        this.isInManagement = isInManagement;

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_check_app_time_permission);

        cancelButton = findViewById(R.id.cancel_button_check_app_time_permission);
        okayButton = findViewById(R.id.ok_button_check_app_time_permission);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                Toast.makeText(context, "권한 요청 취소로 인해 앱 사용 시간 분석이 불가능해집니다.", Toast.LENGTH_SHORT).show();
            }
        });

        okayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPermissionAndAppData();
                dismiss();


            }
        });

        show();

    }

    public void getPermissionAndAppData() {

        if (isInManagement) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
        // 권한이 없을 경우 권한 요구 페이지 이동
        Intent intent = new Intent(android.provider.Settings.ACTION_USAGE_ACCESS_SETTINGS);
        context.startActivity(intent);

    }

}
