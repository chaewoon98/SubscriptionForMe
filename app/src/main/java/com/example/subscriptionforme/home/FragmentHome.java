package com.example.subscriptionforme.home;

import android.content.Intent;
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
import com.example.subscriptionforme.setting.SettingActivity;


import java.util.ArrayList;


public class FragmentHome extends Fragment {

    private View view;
    private ArrayList<UserSubscriptionData> userSubscriptionDataList;
    private UserSubscriptionAdapter userSubscriptionAdapter;
    private ImageButton imageButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        userSubscriptionDataList = new ArrayList<>(2);

        //예시 데이터 셋팅
        initializeExampleDataSet();
        // 이미지 버튼 id 값 부여
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listViewInHome = view.findViewById(R.id.listview_home);
        userSubscriptionAdapter = new UserSubscriptionAdapter(getActivity(),userSubscriptionDataList);
        listViewInHome.setAdapter(userSubscriptionAdapter);



    }

    @Override
    public void onStart() {
        super.onStart();

        imageButton = getActivity().findViewById(R.id.setting);

        // 설정 페이지 엑티비티 가는 버튼
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent intent  = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);

            }
        });
    }

    //예시 데이터 셋팅팅
    public void initializeExampleDataSet() {
        userSubscriptionDataList.add(new UserSubscriptionData("8월 15일", "D-22", "Naver Plus\nMembership", "4,900원", R.drawable.naver_logo,false));
        userSubscriptionDataList.add(new UserSubscriptionData("8월 20일", "D-12", "멜론\n스트리밍 플러스", "10,900원", R.drawable.melon_logo,true));
    }



}
