package com.example.subscriptionforme.home.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.subscriptionforme.R;

public class AlarmSettingDialog extends Dialog {

    private TextView alarmSettingTextView;
    private Button oneDayButton,threeDayButton,oneWeekButton,noAlarmButton;

    public AlarmSettingDialog(@NonNull Context context, final TextView alarmSettingTextView) {
        super(context);
        this.alarmSettingTextView = alarmSettingTextView;

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setGravity(Gravity.BOTTOM);

        setContentView(R.layout.dialog_alarm_setting);

        //다이얼로그 크기변경
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, context.getResources().getDisplayMetrics());
        layoutParams.windowAnimations = R.style.CalendarDialogAnimation;
        getWindow().setAttributes(layoutParams);

        oneDayButton = findViewById(R.id.one_day_button_alarm_setting_dialog);
        threeDayButton = findViewById(R.id.three_day_button_alarm_setting_dialog);
        oneWeekButton = findViewById(R.id.one_week_button_alarm_setting_dialog);
        noAlarmButton = findViewById(R.id.no_alarm_button_alarm_setting_dialog);

        oneDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmSettingTextView.setText("1일 전");
                dismiss();
            }
        });

        threeDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmSettingTextView.setText("3일 전");
                dismiss();
            }
        });

        oneWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmSettingTextView.setText("일주일 전");
                dismiss();
            }
        });

        noAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmSettingTextView.setText("설정 안함");
                dismiss();
            }
        });

        show();
    }
}
