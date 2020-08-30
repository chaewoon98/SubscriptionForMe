package com.example.subscriptionforme.recommendation;

import android.content.Intent;
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

import java.util.ArrayList;

public class FragmentRecommendation extends Fragment {

    ArrayList<RecommendationList> recommendationList;

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

                    Intent intent = new Intent(getContext(), DetailRecommendationActivity.class);

                    intent.putExtra("name", recommendationList.get(i).getName());
                    intent.putExtra("logo", recommendationList.get(i).getIcon());
                    intent.putExtra("benefit", recommendationList.get(i).getBenefit());
                    intent.putExtra("color", recommendationList.get(i).getColor());

                    startActivity(intent);
                }
            });
        }

        return view;
    }

    public void initList(){

        recommendationList = new ArrayList<RecommendationList>();
        recommendationList.add(new RecommendationList("11번가", "Smile Club Membership", "5,000", "128,000", "15,360", R.drawable.ic_11st,getResources().getColor(R.color.color11st), R.drawable.benefit_11st));
        recommendationList.add(new RecommendationList("햄버거", "버거킹 정기 구독 서비스", "4,700", "9,800", "5,200", R.drawable.ic_burgerking, getResources().getColor(R.color.colorBurgerKing),R.drawable.benefit_burgerking));
        recommendationList.add(new RecommendationList("쿠팡", "쿠팡 로켓 와우", "2,900", "19,000", "2,500", R.drawable.ic_coupang,getResources().getColor(R.color.colorCoupang),R.drawable.benefit_coupang));
     }
}
