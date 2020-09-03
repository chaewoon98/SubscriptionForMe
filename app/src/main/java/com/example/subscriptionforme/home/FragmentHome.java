package com.example.subscriptionforme.home;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.home.Data.UserSubscriptionAdapter;
import com.example.subscriptionforme.home.Data.UserSubscriptionData;
import com.example.subscriptionforme.main.MainActivity;
import com.example.subscriptionforme.main.UserDatabase;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;


public class FragmentHome extends Fragment {

    private View view;
    private ArrayList<UserSubscriptionData> userSubscriptionDataList;
    private UserSubscriptionAdapter userSubscriptionAdapter;
    private SQLiteDatabase userDatabase;
    private Integer dataCount;
    private ListView listViewInHome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        userDatabase = UserDatabase.getInstance(getActivity()).getReadableDatabase();
        dataCount = UserDatabase.getInstance(getActivity()).getDataCount(userDatabase);

        userSubscriptionDataList = new ArrayList<>();
        //예시 데이터 셋팅
        initializeExampleDataSet();

        listViewInHome = view.findViewById(R.id.listview_home);
        userSubscriptionAdapter = new UserSubscriptionAdapter(getActivity(),userSubscriptionDataList,this);
        listViewInHome.setAdapter(userSubscriptionAdapter);
        
    }

    //예시 데이터 셋팅팅
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void initializeExampleDataSet() {

        for (int index = 0; index < dataCount; index++) {
            userSubscriptionDataList.add(UserDatabase.getInstance(getActivity()).getUserData(userDatabase, index));
        }

        //날짜 순 정렬
        userSubscriptionDataList.sort(new Comparator<UserSubscriptionData>() {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
            @Override
            public int compare(UserSubscriptionData userSubscriptionData, UserSubscriptionData userSubscriptionData1) {
                try {
                    return dateFormat.parse(userSubscriptionData.getSubscriptionPayDate()).compareTo(dateFormat.parse(userSubscriptionData1.getSubscriptionPayDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return -1;
            }
        });
    }

}
