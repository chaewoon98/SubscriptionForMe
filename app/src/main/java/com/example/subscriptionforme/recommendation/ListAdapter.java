package com.example.subscriptionforme.recommendation;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.recommendation.detail_recommendation.Detail_11st;
import com.example.subscriptionforme.recommendation.detail_recommendation.Detail_BurgerKing;
import com.example.subscriptionforme.recommendation.detail_recommendation.Detail_CoffeePlease;
import com.example.subscriptionforme.recommendation.detail_recommendation.Detail_Coupang;
import com.example.subscriptionforme.recommendation.detail_recommendation.Detail_GS25;
import com.example.subscriptionforme.recommendation.detail_recommendation.Detail_Millie;
import com.example.subscriptionforme.recommendation.detail_recommendation.Detail_Naver;
import com.example.subscriptionforme.recommendation.detail_recommendation.Detail_Yogiyo;
import com.example.subscriptionforme.recommendation.detail_recommendation.Detail_Youtube;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    View view;
    Context context = null;
    LayoutInflater layoutInflater = null;
    ArrayList<RecommendationList> list;

    public ListAdapter(Context context, ArrayList<RecommendationList> data){
        this.context = context;
        list = data;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        if(list.size() == 0){
            return 1;
        }

        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        if(list.size() == 0){
            view = layoutInflater.inflate(R.layout.recommendation_null_list,viewGroup,false);

            return view;
        }

        view = layoutInflater.inflate(R.layout.recommendation_list_view,null);

        TextView title = (TextView)view.findViewById(R.id.title_text);
        title.setText(list.get(position).getTitle());
        title.setTextColor(list.get(position).getColor());

        TextView name = (TextView)view.findViewById(R.id.name_text);
        name.setText(list.get(position).getName());
        name.setTextColor(list.get(position).getColor());

        ImageView icon = (ImageView)view.findViewById(R.id.icon_logo);
        icon.setImageResource(list.get(position).getIcon());

        TextView consumption = (TextView)view.findViewById(R.id.consumption_text);
        consumption.setText(list.get(position).getConsumption());

        TextView discount = (TextView)view.findViewById(R.id.discount_text);
        discount.setText(list.get(position).getDiscount());

        View viewTouch = view.findViewById(R.id.test_1);
        viewTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (list.get(position).getName()){
                    case "스마일 클럽 멤버십":{
                        Intent intent = new Intent(context, Detail_11st.class);
                        context.startActivity(intent);
                        break;
                    }

                    case "버거킹 정기 구독 서비스":{
                        Intent intent = new Intent(context, Detail_BurgerKing.class);
                        context.startActivity(intent);
                        break;
                    }

                    case "쿠팡 로켓 와우":{
                        Intent intent = new Intent(context, Detail_Coupang.class);
                        context.startActivity(intent);
                        break;
                    }

                    case "커피 플리즈":{
                        Intent intent = new Intent(context, Detail_CoffeePlease.class);
                        context.startActivity(intent);
                        break;
                    }

                    case "네이버 플러스 멤버십":{
                        Intent intent = new Intent(context, Detail_Naver.class);
                        context.startActivity(intent);
                        break;
                    }

                    case "GS 더 팝 플러스":{
                        Intent intent = new Intent(context, Detail_GS25.class);
                        context.startActivity(intent);
                        break;
                    }

                    case "요기요 슈퍼클럽":{
                        Intent intent = new Intent(context, Detail_Yogiyo.class);
                        context.startActivity(intent);
                        break;
                    }

                    case "밀리의 서재":{
                        Intent intent = new Intent(context, Detail_Millie.class);
                        context.startActivity(intent);
                        break;
                    }

                    case "유튜브 프리미엄":{
                        Intent intent = new Intent(context, Detail_Youtube.class);
                        context.startActivity(intent);
                        break;
                    }
                }
            }
        });

        return view;
    }
}
