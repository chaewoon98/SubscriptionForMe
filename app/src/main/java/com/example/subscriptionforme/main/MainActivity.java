package com.example.subscriptionforme.main;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.subscriptionforme.R;
import com.example.subscriptionforme.SubscriptionModelData;


import com.example.subscriptionforme.collection.FragmentCollectionView;
import com.example.subscriptionforme.home.FragmentHome;
import com.example.subscriptionforme.recommendation.FragmentRecommendation;
import com.example.subscriptionforme.AppUsedTimeData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<SubscriptionModelData> subscriptionModelDataList;

    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();
    // 4개의 메뉴에 들어갈 Fragment들
    private FragmentRecommendation fragmentRecommend = new FragmentRecommendation();
    private FragmentCollectionView fragmentCollectionView = new FragmentCollectionView();
    private FragmentTransaction transaction;
    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backPressCloseHandler = new BackPressCloseHandler(this);
        setContentView(R.layout.activity_main);

        //구독 상품 데이터 셋팅
        initializeSubscriptionModelData();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        // 첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, new FragmentHome()).commitAllowingStateLoss();
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {

                case R.id.home:
                    replaceFragment(new FragmentHome());
                    break;

                case R.id.recommend:
                    replaceFragment(fragmentRecommend);
                    break;

                case R.id.collection:
                    replaceFragment(fragmentCollectionView);
                    break;

            }
            return true;
        }

    }

    public void getPermissionAndAppData(){
        //퍼미션 체크
        boolean granted = false;
        AppOpsManager appOps = (AppOpsManager)getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,android.os.Process.myUid(), getPackageName());

        if (mode == AppOpsManager.MODE_DEFAULT) {
            granted = (checkCallingOrSelfPermission(android.Manifest.permission.PACKAGE_USAGE_STATS) == PackageManager.PERMISSION_GRANTED);
        } else {
            granted = (mode == AppOpsManager.MODE_ALLOWED);
        }

        if (granted == false)
        {
            // 권한이 없을 경우 권한 요구 페이지 이동
            Intent intent = new Intent(android.provider.Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        }

        AppUsedTimeData appUsedTimeData = new AppUsedTimeData();
        Long youtubeUseTime = appUsedTimeData.getAppUsedTime(MainActivity.this,"youtube")/(60*1000);
        Log.d("youtube", String.valueOf(youtubeUseTime));
    }

    //fragment 전환하는 메소드
    public void replaceFragment(Fragment fragment) {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }

    class BackPressCloseHandler {
        private long backKeyPressedTime = 0;
        private Toast toast;
        private Activity activity;
        private MainActivity mainActivity;

        public BackPressCloseHandler(Activity context) {
            this.activity = context;
        }

        public void onBackPressed() {

            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                showGuide();
                return;
            }
            if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                activity.finish();
                toast.cancel();
            }
        }

        public void showGuide() {
            toast = Toast.makeText(activity, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //fragmentHome 업데이트 메소드
    public void refreshInFragmentHome() {
        replaceFragment(new FragmentHome());
    }

    public void initializeSubscriptionModelData() {
        subscriptionModelDataList = new ArrayList<>();

        subscriptionModelDataList.add(new SubscriptionModelData("0", "네이버 플러스 멤버십", "basic", "한 달", "4,900",
                "쇼핑할 때마다 네이버페이 포인트 5%! 다양한 혜택과 서비스", "https://nid.naver.com/nidlogin.login?svctype=1&url=https%3A%2F%2Forder.pay.naver.com%2Fhome", "https://nid.naver.com/nidlogin.login?svctype=1&url=https%3A%2F%2Forder.pay.naver.com%2Fhome", R.drawable.naver_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("1", "스마일 클럽 멤버십", "basic", "한 달", "37,000",
                "G마켓, 옥션, g9 어디서나 최고의 혜택을 받는 최고의 쇼핑 멤버쉽", null, null, R.drawable.smileclub_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("2", "쿠팡 로켓와우 클럽", "basic", "한 달", "2,900",
                "로켓배송상품 100% 무료배송 + 최대 5% 캐시적립. 언제든지 현명하게!", null, null, R.drawable.coupang_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("3", "요기요 슈퍼 클럽", "basic", "한 달", "9,900",
                "주문할 때마다 자동할인, 중복 할인, 매월 받는 정기 할인", null, null, R.drawable.yogiyo_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("4", "버거킹 구독 서비스", "basic", "한 달", "4,900",
                "햄버거를 사랑하는 고객님이라면 누구나! 치킨버거 4개가 한달에 4,900원", null, null, R.drawable.ic_burgerking));

        subscriptionModelDataList.add(new SubscriptionModelData("5", "GS THE POP+", "카페 25", "한 달", "2,500",
                "잔 당 430원씩 절약! 커피를 사랑하는 고객님이라면! 저렴하게!", null, null, R.drawable.gs25_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("6", "GS THE POP+", "도시락&샐러드", "한 달", "3,900",
                "도시락과 샐러드를 어디서나 든든하게! 맛있게! 편하게!", null, null, R.drawable.gs25_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("7", "티몬 슈퍼 세이브", "30일권", "한 달", "10,000",
                "무제한 무료배송+현대M포인트 20% 혜택! 슈퍼세이브 총 12만원 블랙쿠폰 까지!", null, null, R.drawable.tmon_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("8", "티몬 슈퍼 세이브", "90일권", "세 달", "20,000",
                "무제한 무료배송+현대M포인트 20% 혜택! 슈퍼세이브 총 12만원 블랙쿠폰 까지!", null, null, R.drawable.tmon_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("9", "티몬 슈퍼 세이브", "1년권", "일 년", "50,000",
                "무제한 무료배송+현대M포인트 20% 혜택! 슈퍼세이브 총 12만원 블랙쿠폰 까지!", null, null, R.drawable.tmon_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("10", "뚜레쥬르 월간 구독 서비스", "커피 구독", "한 달", "19,900",
                "매일 700원으로 즐기는 커피 구독. 뚜레쥬르가 드리는 최고의 혜택!", null, null, R.drawable.touslesjours_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("11", "뚜레쥬르 월간 구독 서비스", "식빵 구독", "한 달", "7,900",
                "매주 즐기는 프리미엄 식빵 구독. 뚜레쥬르가 드리는 최고의 혜택!", null, null, R.drawable.touslesjours_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("12", "뚜레쥬르 월간 구독 서비스", "모닝세트 구독", "한 달", "49,500",
                "건강한 아침을 챙기는 모닝세트 구독. 뚜레쥬르가 드리는 최고의 혜택!", null, null, R.drawable.touslesjours_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("13", "멜론", "모바일 스트리밍 클럽", "한 달", "6,900",
                "No.1 뮤직플랫폼 멜론! 실시간 차트부터 나를 아는 똑똑한 음악추천까지!", null, null, R.drawable.melon_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("14", "멜론", "스트리밍 플러스", "한 달", "7,900",
                "No.1 뮤직플랫폼 멜론! 실시간 차트부터 나를 아는 똑똑한 음악추천까지!", null, null, R.drawable.melon_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("15", "멜론", "멜론 익스트리밍 플러스", "한 달", "10,700",
                "No.1 뮤직플랫폼 멜론! 실시간 차트부터 나를 아는 똑똑한 음악추천까지!", null, null, R.drawable.melon_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("16", "넷플릭스", "basic", "한 달", "9,500",
                "영화, TV 프로그램을 무제한으로, 다양한 디바이스에서 시청하세요", null, null, R.drawable.nexflix_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("17", "넷플릭스", "standard", "한 달", "12,000",
                "영화, TV 프로그램을 무제한으로, 다양한 디바이스에서 시청하세요", null, null, R.drawable.nexflix_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("18", "넷플릭스", "premium", "한 달", "14,500",
                "영화, TV 프로그램을 무제한으로, 다양한 디바이스에서 시청하세요", null, null, R.drawable.nexflix_logo));

        subscriptionModelDataList.add(new SubscriptionModelData("19", "유튜브 프리미엄", "basic", "한 달", "9,500",
                "프리미엄 유튜브, 편리하고 다양하게 즐기세요.", null, null, R.drawable.logo_yotube));
    }
}