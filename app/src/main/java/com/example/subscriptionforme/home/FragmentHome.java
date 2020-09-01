package com.example.subscriptionforme.home;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.subscriptionforme.R;
import com.example.subscriptionforme.main.UserDatabase;


import java.util.ArrayList;


public class FragmentHome extends Fragment {

    private View view;
    private ArrayList<UserSubscriptionData> userSubscriptionDataList;
    private UserSubscriptionAdapter userSubscriptionAdapter;
    private SQLiteDatabase userDatabase;
    private Integer dataCount;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        userDatabase = UserDatabase.getInstance(getActivity()).getReadableDatabase();
        dataCount = UserDatabase.getInstance(getActivity()).getDataCount(userDatabase);

        userSubscriptionDataList = new ArrayList<>();
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

        for (int index = 0; index < dataCount; index++) {
            userSubscriptionDataList.add(UserDatabase.getInstance(getActivity()).getUserData(userDatabase,index));
        }

        //userSubscriptionDataList.add(new UserSubscriptionData("1","8월 15일", "D-22", "네이버 플러스\n멤버쉽", "4,900원", R.drawable.naver_logo,false));
        //userSubscriptionDataList.add(new UserSubscriptionData("2","8월 20일", "D-12", "멜론\n스트리밍 플러스", "10,900원", R.drawable.melon_logo,true));
    }
}
