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

        recommendationList = new ArrayList<RecommendationList>();

        Log.d("태순숫자",String.valueOf(dataCount)); // 2

        initList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommendation,container,false);
        ListView listView = view.findViewById(R.id.listView);

        if(dataCount != 0) {
            final ListAdapter listAdapter = new ListAdapter(getContext(), recommendationList);
            listView.setAdapter(listAdapter);
        }

        return view;
    }

    //임시 추천 리스트
    public void initList(){

        for(int i=0;i<dataCount;i++)
        {
            recommendationList.add(SubscriptionDatabase.getInstance(getActivity()).getSubscriptionData(subscriptionDatabase, i));
        }

     }

}
