package com.example.subscriptionforme.recommendation;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.home.Data.AccountDatabase;
import com.example.subscriptionforme.home.Data.SubscriptionDatabase;
import com.example.subscriptionforme.recommendation.detail_recommendation.Detail_11st;
import com.example.subscriptionforme.recommendation.detail_recommendation.Detail_BurgerKing;
import com.example.subscriptionforme.recommendation.detail_recommendation.Detail_CoffeePlease;
import com.example.subscriptionforme.recommendation.detail_recommendation.Detail_Coupang;
import com.example.subscriptionforme.recommendation.detail_recommendation.Detail_GS25;
import com.example.subscriptionforme.recommendation.detail_recommendation.Detail_Naver;
import com.example.subscriptionforme.setting.card.AccountGetDataActivity;

import java.util.ArrayList;

public class FragmentRecommendation extends Fragment {

    ArrayList<RecommendationList> recommendationList;
    int dataCount;
    SQLiteDatabase subscriptionDatabase;

    public FragmentRecommendation(){

    }

    public static FragmentRecommendation newInstance() {
        FragmentRecommendation fragment = new FragmentRecommendation();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscriptionDatabase = SubscriptionDatabase.getInstance(getActivity()).getReadableDatabase();
        dataCount = SubscriptionDatabase.getInstance(getActivity()).getDataCount(SubscriptionDatabase.getInstance(getActivity()).getReadableDatabase());

        Log.d("태순숫자",String.valueOf(dataCount));

        initList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommendation,container,false);
        ListView listView = view.findViewById(R.id.listView);


        if(recommendationList != null) {
            final ListAdapter listAdapter = new ListAdapter(getContext(), recommendationList);
            listView.setAdapter(listAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    switch (recommendationList.get(i).getName()){
                        case "스마일 클럽 멤버십":{
                            Intent intent = new Intent(getContext(), Detail_11st.class);
                            startActivity(intent);
                            break;
                        }

                        case "버거킹 정기 구독 서비스":{
                            Intent intent = new Intent(getContext(), Detail_BurgerKing.class);
                            startActivity(intent);
                            break;
                        }

                        case "쿠팡 로켓 와우":{
                            Intent intent = new Intent(getContext(), Detail_Coupang.class);
                            startActivity(intent);
                            break;
                        }

                        case "커피 플리즈":{
                            Intent intent = new Intent(getContext(), Detail_CoffeePlease.class);
                            startActivity(intent);
                            break;
                        }

                        case "네이버 플러스 멤버십":{
                            Intent intent = new Intent(getContext(), Detail_Naver.class);
                            startActivity(intent);
                            break;
                        }

                        case "GS 더 팝 플러스":{
                            Intent intent = new Intent(getContext(), Detail_GS25.class);
                            startActivity(intent);
                            break;
                        }
                    }

//                    Intent intent = new Intent(getContext(), DetailRecommendationActivity.class);
//
//                    intent.putExtra("name", recommendationList.get(i).getName());
//                    intent.putExtra("logo", recommendationList.get(i).getIcon());
//                    intent.putExtra("benefit", recommendationList.get(i).getBenefit());
//                    intent.putExtra("color", recommendationList.get(i).getColor());
//
//                    startActivity(intent);
                }
            });
        }

        return view;
    }


    //임시 추천 리스트
    public void initList(){

        for (int index = 0; index < dataCount; index++) {
            recommendationList.add(SubscriptionDatabase.getInstance(getActivity()).getSubscriptionData(subscriptionDatabase, index));
        }

        //recommendationList = AccountGetDataActivity.recommendationList;
        //Log.d("박태순",String.valueOf(recommendationList));
//        recommendationList.add(new RecommendationList("11번가", "스마일 클럽 멤버십", "5,000", "128,000", "15,360", R.drawable.ic_11st,getResources().getColor(R.color.color11st), R.drawable.benefit_11st));
//        recommendationList.add(new RecommendationList("햄버거", "버거킹 정기 구독 서비스", "4,700", "9,800", "5,200", R.drawable.ic_burgerking, getResources().getColor(R.color.colorBurgerKing),R.drawable.benefit_burgerking));
//        recommendationList.add(new RecommendationList("쿠팡", "쿠팡 로켓 와우", "2,900", "19,000", "2,500", R.drawable.ic_coupang,getResources().getColor(R.color.colorCoupang),R.drawable.benefit_coupang));
//        recommendationList.add(new RecommendationList("커피", "커피 플리즈", "2,900", "19,000", "2,500", R.drawable.ic_coffeeplease,getResources().getColor(R.color.colorCoffeePlease),R.drawable.benefit_coupang));
//        recommendationList.add(new RecommendationList("네이버", "네이버 플러스 멤버십", "2,900", "19,000", "2,500", R.drawable.ic_naver,getResources().getColor(R.color.colorNaver),R.drawable.benefit_coupang));
//        recommendationList.add(new RecommendationList("커피", "GS 더 팝 플러스", "2,900", "19,000", "2,500", R.drawable.ic_gs25,getResources().getColor(R.color.colorGS25),R.drawable.benefit_coupang));

     }
}
