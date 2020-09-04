package com.example.subscriptionforme.home;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.example.subscriptionforme.R;
import com.example.subscriptionforme.home.Data.UserDatabase;
import com.example.subscriptionforme.home.Data.UserSubscriptionAdapter;
import com.example.subscriptionforme.home.Data.UserSubscriptionData;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;


public class FragmentHome extends Fragment {

    private View view;
    private ArrayList<UserSubscriptionData> userSubscriptionDataList;
    private UserSubscriptionAdapter userSubscriptionAdapter;
    private SQLiteDatabase userDatabase;
    private Integer dataCount;
    private ListView listViewInHome;
    private TextView monthTextView,priceInMonthTextView;

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

        listViewInHome = view.findViewById(R.id.listview_fragment_home);
        userSubscriptionAdapter = new UserSubscriptionAdapter(getActivity(),userSubscriptionDataList,this);
        listViewInHome.setAdapter(userSubscriptionAdapter);

        monthTextView = view.findViewById(R.id.month_fragment_home);
        priceInMonthTextView = view.findViewById(R.id.price_in_month_fragment_home);

        //월을 현재 월로 바꾼다.
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat monthDateFormat = new SimpleDateFormat("M월,");
        monthTextView.setText(monthDateFormat.format(date));

        int priceInMonth = getPriceInMonth();
        priceInMonthTextView.setText(moneyFormat(priceInMonth) + " 원");



    }

    public int getPriceInMonth() {

        int priceInMonth = 0;
        Date nowDate = new Date(System.currentTimeMillis());
        SimpleDateFormat monthDateFormat = new SimpleDateFormat("M");

        for (int index = 0; index < dataCount; index++) {

            //당 월에 결제되는 구독상품만 계산하기위해
            String dateString = userSubscriptionDataList.get(index).getSubscriptionPayDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 M월 d일");

            try {
                Date date = dateFormat.parse(dateString);
                if(monthDateFormat.format(nowDate).equals(monthDateFormat.format(date))){
                    String price = userSubscriptionDataList.get(index).getSubscriptionPrice().replaceAll(",","");
                    priceInMonth = priceInMonth + Integer.parseInt(price);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return priceInMonth;
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

    public String moneyFormat(int intputMoney){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        return decimalFormat.format(intputMoney);
    }

}
