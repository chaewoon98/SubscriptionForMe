package com.example.subscriptionforme.recommendation;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.subscriptionforme.R;
import com.example.subscriptionforme.home.Data.AccountDatabase;
import com.example.subscriptionforme.home.Data.SubscriptionDatabase;
import com.example.subscriptionforme.setting.SettingActivity;


import java.util.ArrayList;

public class FragmentRecommendation extends Fragment {

    ArrayList<RecommendationList> recommendationList;
    int dataCount;
    SQLiteDatabase subscriptionDatabase;
    private ImageButton imageButton;

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
        //if(dataCount != 0) {
            final ListAdapter listAdapter = new ListAdapter(getContext(), recommendationList);
            listView.setAdapter(listAdapter);
       // }

        return view;
    }

    //임시 추천 리스트
    public void initList(){

        for(int i=0;i<dataCount;i++)
        {
            recommendationList.add(SubscriptionDatabase.getInstance(getActivity()).getSubscriptionData(subscriptionDatabase, i));
        }
     }

    @Override
    public void onStart() {
        super.onStart();

        imageButton = getActivity().findViewById(R.id.setting_fragment_recommendatrion);

        // 설정 페이지 엑티비티 가는 버튼
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent intent  = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);

            }
        });
    }

}
