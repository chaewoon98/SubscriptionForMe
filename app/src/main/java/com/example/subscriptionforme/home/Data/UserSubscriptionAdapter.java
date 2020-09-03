package com.example.subscriptionforme.home.Data;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.home.Activity.AddSubscriptionActivity;
import com.example.subscriptionforme.home.FragmentHome;
import com.example.subscriptionforme.home.Listener.AlarmButtonOnClickListener;
import com.example.subscriptionforme.home.Listener.DeleteUserSubscriptionOnClickListener;
import com.example.subscriptionforme.home.Activity.ManagementSusbscriptionActivity;
import com.example.subscriptionforme.home.Listener.ManagementSubscriptionListener;
import com.example.subscriptionforme.main.MainActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserSubscriptionAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<UserSubscriptionData> userSubscriptionDataList;
    private FragmentHome fragmentHome;
    public MainActivity mainActivity;

    public UserSubscriptionAdapter(Context context, ArrayList<UserSubscriptionData> userSubscriptionDataList, FragmentHome fragmentHome) {

        this.fragmentHome = fragmentHome;
        this.userSubscriptionDataList = userSubscriptionDataList;
        this.context = context;
        mainActivity = (MainActivity) this.context;

        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return userSubscriptionDataList.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return userSubscriptionDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        TextView startingDate, dDayDate, nameOfSubscription, priceOfSubscription, paymentSystemSubscription;
        ImageButton alarmButton, addSubscrpitionButton;
        ImageView imageOfSubscription;
        Button manageButton, cancleButton;

        //추가하기 버튼 view 일 경우
        if (position == userSubscriptionDataList.size()) {
            view = layoutInflater.inflate(R.layout.item_add_subscription_home, viewGroup, false);
            addSubscrpitionButton = view.findViewById(R.id.add_button_subscription_home);

            //추가하기 버튼 처리
            addSubscrpitionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity mainActivity = (MainActivity) context;
                    Intent intent = new Intent(context, AddSubscriptionActivity.class);
                    intent.putParcelableArrayListExtra("subsciptionModelDataList", mainActivity.subscriptionModelDataList);
                    mainActivity.startActivity(intent);
                }
            });
            return view;
        }

        //초기화
        view = layoutInflater.inflate(R.layout.item_subscription_home, viewGroup, false);
        startingDate = view.findViewById(R.id.starting_date_subsciption_home);
        dDayDate = view.findViewById(R.id.d_day_date_subsciption_home);
        nameOfSubscription = view.findViewById(R.id.name_subsciption_home);
        paymentSystemSubscription = view.findViewById(R.id.payment_system_subsciption_home);
        priceOfSubscription = view.findViewById(R.id.price_subsciption_home);
        imageOfSubscription = view.findViewById(R.id.image_subsciption_home);
        alarmButton = view.findViewById(R.id.alarm_button_subsciption_home);
        manageButton = view.findViewById(R.id.manage_button_subscription_home);
        cancleButton = view.findViewById(R.id.cancle_button_subscription_home);

        startingDate.setText(userSubscriptionDataList.get(position).getSubscriptionPayDate().substring(2));
        dDayDate.setText(getDDay(position));
        nameOfSubscription.setText(userSubscriptionDataList.get(position).getSubscriptionName());
        paymentSystemSubscription.setText(userSubscriptionDataList.get(position).getSubscriptionPaymentSystem());
        priceOfSubscription.setText(userSubscriptionDataList.get(position).getSubscriptionPrice());
        imageOfSubscription.setImageResource(userSubscriptionDataList.get(position).getSubscriptionImageID());

        if (userSubscriptionDataList.get(position).isAlarmOn().equals("true"))
            alarmButton.setImageResource(R.drawable.alarm_button);
        else {
            alarmButton.setImageResource(R.drawable.alarm_cancel_button);
        }

        //알람버튼 온클릭리스너
        alarmButton.setOnClickListener(new AlarmButtonOnClickListener(position, userSubscriptionDataList, alarmButton, context));

        //관리버튼 온클릭리스너
        manageButton.setOnClickListener(new ManagementSubscriptionListener(context,userSubscriptionDataList.get(position),mainActivity));

        //해지버튼 온클릭리스너
        cancleButton.setOnClickListener(new DeleteUserSubscriptionOnClickListener(context, userSubscriptionDataList.get(position), mainActivity));

        return view;
    }

    public String getDDay(int position) {

        Date nowDate = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        Date subscriptionPayDate = null;

        try {
            subscriptionPayDate = dateFormat.parse(userSubscriptionDataList.get(position).getSubscriptionPayDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //현재 시간과 구독 서비스 결제 시간을 비교하여 d를 구함
        //현재 시간보다 구독 결제일이 더 빠르면 d-0 출력.
        if (nowDate.after(subscriptionPayDate))
            return "D-0";

        //구독 결제일이 시간이 남았을 경우 디데이를 계산
        long dDay = subscriptionPayDate.getTime() - nowDate.getTime();
        dDay = dDay / (24 * 60 * 60 * 1000);
        dDay = Math.abs(dDay);

        return "D-" + String.valueOf(dDay);


    }

}
