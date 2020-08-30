package com.example.subscriptionforme.home;

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
import com.example.subscriptionforme.codef.Codef;
import com.example.subscriptionforme.main.MainActivity;

import java.util.ArrayList;

import java.util.List;

public class UserSubscriptionAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<UserSubscriptionData> userSubscriptionDataList;

    public UserSubscriptionAdapter(Context context, ArrayList<UserSubscriptionData> userSubscriptionDataList) {

        this.userSubscriptionDataList = userSubscriptionDataList;
        this.context = context;

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

        /* View view;*/
        TextView startingDate, dDayDate, nameOfSubscription, priceOfSubscription;
        ImageButton alarmButton, addSubscrpitionButton;
        ImageView imageOfSubscription;

        ImageView imageOfSetting; // 세팅 매뉴

        Button manageButton, cancleButton;

        //추가하기 버튼 view
        if (position == userSubscriptionDataList.size()) {
            view = layoutInflater.inflate(R.layout.item_add_subscription_home, viewGroup, false);
            addSubscrpitionButton = view.findViewById(R.id.add_button_subscription_home);

            //추가하기 버튼 처리
            addSubscrpitionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity mainActivity = (MainActivity) context;
                    Intent intent = new Intent(context, AddSubscriptionActivity.class);

                    intent.putParcelableArrayListExtra("subsciptionModelDataList", mainActivity.subsciptionModelDataList);
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
        priceOfSubscription = view.findViewById(R.id.price_subsciption_home);
        imageOfSubscription = view.findViewById(R.id.image_subsciption_home);
        alarmButton = view.findViewById(R.id.alarm_button_subsciption_home);
        manageButton = view.findViewById(R.id.manage_button_subscription_home);
        cancleButton = view.findViewById(R.id.cancle_button_subscription_home);
        imageOfSetting = view.findViewById(R.id.setting);

        startingDate.setText(userSubscriptionDataList.get(position).getStartingDate());
        dDayDate.setText(userSubscriptionDataList.get(position).getDDayDate());
        nameOfSubscription.setText(userSubscriptionDataList.get(position).getSubscriptionName());
        priceOfSubscription.setText(userSubscriptionDataList.get(position).getSubscriptionPrice());
        imageOfSubscription.setImageResource(userSubscriptionDataList.get(position).getSubscriptionImageID());


        if (userSubscriptionDataList.get(position).isAlarmOn())
            alarmButton.setImageResource(R.drawable.alarm_button);
        else {
            alarmButton.setImageResource(R.drawable.alarm_cancel_button);
        }

        //알람버튼 온클릭리스너
        alarmButton.setOnClickListener(new AlarmButtonOnClickListener(position, userSubscriptionDataList, alarmButton, context));

        //관리버튼 온클릭리스너
        manageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //해지버튼 온클릭리스너
        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // new Codef().getCodef();
            }
        });

        //설정 버튼

//        imageOfSetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        return view;
    }



}
