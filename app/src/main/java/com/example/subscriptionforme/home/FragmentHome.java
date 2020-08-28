package com.example.subscriptionforme.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.UserSubscriptionAdapter;
import com.example.subscriptionforme.UserSubscriptionData;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    View view;
    List<UserSubscriptionData> userSubscriptionDataList;
    UserSubscriptionAdapter userSubscriptionAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        userSubscriptionDataList = new ArrayList<>(2);

        //예시 데이터 셋팅
        initializeExampleDataSet();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listViewInHome = view.findViewById(R.id.listview_home);
        userSubscriptionAdapter = new UserSubscriptionAdapter(getActivity(),userSubscriptionDataList);
        listViewInHome.setAdapter(userSubscriptionAdapter);

    }

    //예시 데이터 셋팅팅
    public void initializeExampleDataSet() {
        userSubscriptionDataList.add(new UserSubscriptionData("8월 15일", "D-22", "Naver Plus\nMembership", "4,900원", R.drawable.naver_logo,false));
        userSubscriptionDataList.add(new UserSubscriptionData("8월 20일", "D-12", "멜론\n스트리밍 플러스", "10,900원", R.drawable.melon_logo,true));
    }
}
