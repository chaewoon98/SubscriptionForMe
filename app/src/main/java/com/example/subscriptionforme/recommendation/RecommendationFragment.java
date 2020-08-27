package com.example.subscriptionforme.recommendation;

import android.content.Intent;
import android.os.Bundle;
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

public class RecommendationFragment extends Fragment {

    ArrayList<RecommendationList> recommendationList;

    private RecommendationFragment(){

    }

    public static RecommendationFragment newInstance() {
        RecommendationFragment fragment = new RecommendationFragment();
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

//                    ((MainActivity)getActivity()).replaceFragment(DetailRecommendationFragment.newInstance());

//                    Intent intent = DetailRecommendationActivity
                    Intent intent = new Intent(getContext(), DetailRecommendationActivity.class);
                    startActivity(intent);
                }
            });
        }

        return view;
    }

    public void initList(){

        recommendationList = new ArrayList<RecommendationList>();
        recommendationList.add(new RecommendationList("햄버거", "버거킹", "4700", "9800원", "5200원", R.drawable.ic_burgerking, getResources().getColor(R.color.colorBurgerKing)));
        recommendationList.add(new RecommendationList("11번가", "11번가", "5000", "128000원", "15360원", R.drawable.ic_11st,getResources().getColor(R.color.color11st)));
        recommendationList.add(new RecommendationList("햄버거", "버거킹3", "4700", "9800원", "5200원", R.drawable.ic_burgerking,getResources().getColor(R.color.colorBurgerKing)));
        recommendationList.add(new RecommendationList("햄버거", "버거킹3", "4700", "9800원", "5200원", R.drawable.ic_burgerking,getResources().getColor(R.color.colorBurgerKing)));
     }
}
