package com.example.subscriptionforme.home.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.subscriptionforme.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;

public class CalendarDialog extends Dialog {

    private Context context;
    private MaterialCalendarView materialCalendarView;
    private String payDate;
    private Button okaybutton;
    private TextView payDateTextView;

    public CalendarDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CalendarDialog(@NonNull final Context context, TextView textView) {
        super(context);

        this.context = context;
        this.payDateTextView = textView;
        payDate = "";

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setGravity(Gravity.BOTTOM);

        setContentView(R.layout.dialog_calendar);

        //다이얼로그 크기변경
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 480, context.getResources().getDisplayMetrics());
        layoutParams.windowAnimations = R.style.CalendarDialogAnimation;
        getWindow().setAttributes(layoutParams);

        materialCalendarView = findViewById(R.id.calender_dialog);
        materialCalendarView.setWeekDayTextAppearance(R.color.mainColor);
        setCalender();

        okaybutton = findViewById(R.id.button_ok_calender_dialog);
        okaybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!payDate.isEmpty()) {
                    payDateTextView.setText(payDate);
                    dismiss();
                }
                else
                    Toast.makeText(context,"최초 결제일을 선택하세요.",Toast.LENGTH_SHORT).show();
            }
        });

        show();

    }

    //달력 기본 설정
    public void setCalender() {

        materialCalendarView.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                return day.getCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new ForegroundColorSpan(
                        ContextCompat.getColor(getContext(), R.color.red)));
            }
        });

        materialCalendarView.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                return day.getCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new ForegroundColorSpan(
                        ContextCompat.getColor(getContext(), R.color.blue)));
            }
        });

        materialCalendarView.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                CalendarDay date = CalendarDay.today();
                return date != null && day.equals(date);
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new StyleSpan(Typeface.BOLD));
                view.addSpan(new RelativeSizeSpan(1.5f));
            }
        });

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                payDate = date.getYear() + "년 " + (date.getMonth()+1) + "월 " + date.getDay() + "일";
            }
        });
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
