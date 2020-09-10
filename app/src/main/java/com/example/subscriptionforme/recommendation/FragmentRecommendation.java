package com.example.subscriptionforme.recommendation;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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

import com.example.subscriptionforme.AppUsedTimeData;
import com.example.subscriptionforme.R;
import com.example.subscriptionforme.home.Data.AccountDatabase;
import com.example.subscriptionforme.home.Data.SubscriptionDatabase;
import com.example.subscriptionforme.home.Data.UserDatabase;
import com.example.subscriptionforme.setting.SettingActivity;


import java.util.ArrayList;

public class FragmentRecommendation extends Fragment {

    ArrayList<RecommendationList> recommendationList;
    ArrayList<RecommendationUserVO> recommendationUserList;
    int dataCount, userDataCount;
    SQLiteDatabase subscriptionDatabase;
    private ImageButton imageButton;
    private View view;
    private Context context;
    private Long youtubeUseTime;

    public FragmentRecommendation() {

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
        context = getActivity();

        updateRecommendation();

        subscriptionDatabase = SubscriptionDatabase.getInstance(getActivity()).getReadableDatabase();
        dataCount = SubscriptionDatabase.getInstance(getActivity()).getDataCount(SubscriptionDatabase.getInstance(getActivity()).getReadableDatabase());
        userDataCount = SubscriptionDatabase.getInstance(getActivity()).getUserDataCount(SubscriptionDatabase.getInstance(getActivity()).getReadableDatabase());


        initList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recommendation, container, false);
        ListView listView = view.findViewById(R.id.listView);

        final ListAdapter listAdapter = new ListAdapter(getContext(), recommendationList, recommendationUserList, youtubeUseTime);
        listView.setAdapter(listAdapter);


        return view;
    }

    //임시 추천 리스트
    public void initList() {

        recommendationList = new ArrayList<RecommendationList>();
        recommendationUserList = new ArrayList<RecommendationUserVO>();

        for (int i = 0; i < dataCount; i++) {
            recommendationList.add(SubscriptionDatabase.getInstance(getActivity()).getSubscriptionData(subscriptionDatabase, i));
        }

        for (int i = 0; i < userDataCount; i++) {
            recommendationUserList.add(SubscriptionDatabase.getInstance(getActivity()).getUserSubscriptionData(subscriptionDatabase, i));
        }

        Log.d("qwe", String.valueOf(recommendationUserList.size()));

    }

    @Override
    public void onStart() {
        super.onStart();
        imageButton = view.findViewById(R.id.setting_fragment_recommendatrion);

        // 설정 페이지 엑티비티 가는 버튼
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);

            }
        });
    }

    public void updateRecommendation() {

        youtubeUseTime = 0l;
        SubscriptionDatabase.getInstance(context).deleteUserSubscription(SubscriptionDatabase.getInstance(context).getWritableDatabase());

        //책읽기를 설문조사에서 한 경우
        if (UserDatabase.getInstance(context).isBookCheck(UserDatabase.getInstance(context).getReadableDatabase()) == 1) {
            SubscriptionDatabase.getInstance(context).insertUserSubscriptionData(SubscriptionDatabase.getInstance(context).getWritableDatabase(),
                    "책 읽기", "밀리의 서재", "독서와 무제한 친해지리", R.drawable.ic_millie, Color.parseColor("#9900CC"));
        }

        //권한 체크가 허용된 경우에만,
        boolean granted = false;
        AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), context.getPackageName());

        if (mode == AppOpsManager.MODE_DEFAULT) {
            granted = (context.checkCallingOrSelfPermission(android.Manifest.permission.PACKAGE_USAGE_STATS) == PackageManager.PERMISSION_GRANTED);
        } else {
            granted = (mode == AppOpsManager.MODE_ALLOWED);
        }

        //권한 체크가 true 인 경우,유튜브 앱 사용 시간 2주 300분 이상인경우.
        if (granted) {
            AppUsedTimeData appUsedTimeData = new AppUsedTimeData();
            youtubeUseTime = appUsedTimeData.getAppUsedTime(context, "youtube") / (30 * 1000);

            if (youtubeUseTime > 300) {
                SubscriptionDatabase.getInstance(context).insertUserSubscriptionData(SubscriptionDatabase.getInstance(context).getWritableDatabase(),
                        "유튜브 시청", "유튜브 프리미엄", "유튜브 100% 즐기기", R.drawable.ic_youtube, Color.RED);
            }

        }

    }

}
