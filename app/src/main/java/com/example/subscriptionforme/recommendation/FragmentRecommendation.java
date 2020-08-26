package com.example.subscriptionforme.recommendation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.subscriptionforme.R;

import java.util.ArrayList;

public class FragmentRecommendation extends Fragment {

    ArrayList<RecommendationList> recommendationList;

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
        }

        return view;
    }

    public void initList(){

        recommendationList = new ArrayList<RecommendationList>();
        recommendationList.add(new RecommendationList("버거킹1", "4700", "9800원", "5200원", R.drawable.burgerking));
        recommendationList.add(new RecommendationList("버거킹2", "4700", "9800원", "5200원", R.drawable.burgerking));
        recommendationList.add(new RecommendationList("버거킹3", "4700", "9800원", "5200원", R.drawable.burgerking));
        recommendationList.add(new RecommendationList("버거킹3", "4700", "9800원", "5200원", R.drawable.burgerking));
        recommendationList.add(new RecommendationList("버거킹3", "4700", "9800원", "5200원", R.drawable.burgerking));
        recommendationList.add(new RecommendationList("버거킹3", "4700", "9800원", "5200원", R.drawable.burgerking));
    }
}
