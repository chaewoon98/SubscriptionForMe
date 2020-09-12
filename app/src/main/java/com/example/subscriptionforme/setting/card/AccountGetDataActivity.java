package com.example.subscriptionforme.setting.card;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.subscriptionforme.AppUsedTimeData;
import com.example.subscriptionforme.R;
import com.example.subscriptionforme.home.Data.AccountDatabase;
import com.example.subscriptionforme.home.Data.AllAccountDatabase;
import com.example.subscriptionforme.home.Data.SubscriptionDatabase;
import com.example.subscriptionforme.home.Data.UserDatabase;
import com.example.subscriptionforme.home.FragmentHome;
import com.example.subscriptionforme.main.MainActivity;
import com.example.subscriptionforme.recommendation.RecommendationList;

import java.util.ArrayList;
import java.util.List;

import com.example.subscriptionforme.home.FragmentHome;
import com.example.subscriptionforme.main.MainActivity;

import java.util.regex.Pattern;

public class AccountGetDataActivity extends AppCompatActivity {

    private Context context;
    private List<AccountVO> accountList; // 전체 계좌
    private List<AccountVO> accountRecommendationList; // 추천에 쓰인 계좌
    private int naverAccount;
    private int gsAccount;
    private int burgerkingAccount;
    private int cupangAccount;
    private int yokiyoAccount;
    private int smailAccount;
    private int coffeeAccount;
    private int[] naverAccountIndex; // 네이버 추천인덱스
    private int[] gsAccountIndex;
    private int[] burgerkingAccountIndex;
    private int[] yokiyoAccountIndex;
    private int yokiyoCount;
    private int coffeeCount;
    private int[] cupangAccountIndex;
    private int[] coffeeAccountIndex;
    private int[] smailAccountIndex;
    public static ArrayList<RecommendationList> recommendationList;
    ImageButton back_btn;

    Button button; // 데이터 얻는 버튼

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_get_data);
        context = getApplicationContext();

        accountList = new ArrayList<AccountVO>();
        accountRecommendationList = new ArrayList<AccountVO>();
        recommendationList = new ArrayList<RecommendationList>();
        naverAccount = 0;
        gsAccount = 0;
        yokiyoCount = 0;
        burgerkingAccount = 0;
        cupangAccount = 0;
        smailAccount = 0;
        coffeeAccount = 0;
        coffeeCount = 0;
        yokiyoAccount = 0;
        naverAccountIndex = new int[50];
        gsAccountIndex = new int[50];
        coffeeAccountIndex = new int[50];
        yokiyoAccountIndex = new int[50];
        burgerkingAccountIndex = new int[50];
        cupangAccountIndex = new int[50];
        smailAccountIndex = new int[50];
        button = findViewById(R.id.account_data_get_button);
        back_btn = findViewById(R.id.get_data_back_btn);
    }

    @Override
    public void onStart() {

        super.onStart();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                accountList.clear(); // 전에 있는 전체 내용 삭제
                AllAccountDatabase.getInstance(getApplicationContext()).deleteAllAccount(AllAccountDatabase.getInstance(getApplicationContext()).getWritableDatabase());
                AccountDatabase.getInstance(getApplicationContext()).deleteAccount(AccountDatabase.getInstance(getApplicationContext()).getWritableDatabase());
                SubscriptionDatabase.getInstance(getApplicationContext()).deleteSubscription(SubscriptionDatabase.getInstance(getApplicationContext()).getWritableDatabase());
                SubscriptionDatabase.getInstance(getApplicationContext()).deleteUserSubscription(SubscriptionDatabase.getInstance(getApplicationContext()).getWritableDatabase());

                if (recommendationList.size() != 0) {
                    recommendationList.clear();
                }

                accountList = new ArrayList<AccountVO>();
                recommendationList = new ArrayList<RecommendationList>();
                if (AgreementActivity.BANK == 0) {
                    setAccountCoffeeList();
                } else if (AgreementActivity.BANK == 1) {
                    setAccountGS25List();
                } else {
                    setAccountYogiyoList();
                }


                setSubscriptionList();

                //설문 데이터 초기화.
                UserDatabase.getInstance(context).deleteUserSurveyData(UserDatabase.getInstance(context).getWritableDatabase());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void setAccountYogiyoList() {
        AccountVO accountVO1 = new AccountVO("20200801", "042103", "19000", "0", "박태순", "", "회덕 농협", "1000000");
        accountList.add(accountVO1);
        AccountVO accountVO2 = new AccountVO("20200802", "205211", "12540", "0", "박태순", "", "쿠팡", "987460");
        accountList.add(accountVO2);
        AccountVO accountVO3 = new AccountVO("20200803", "043141", "1000", "0", "박태순", "", "네이버페이결제", "986460");
        accountList.add(accountVO3);
        AccountVO accountVO4 = new AccountVO("20200804", "205211", "1000", "0", "박태순", "", "네이버페이결제", "985460");
        accountList.add(accountVO4);
        AccountVO accountVO5 = new AccountVO("20200805", "042358", "19000", "0", "박태순", "", "우아한형제들", "966460");
        accountList.add(accountVO5);
        AccountVO accountVO6 = new AccountVO("20200806", "011237", "600", "0", "박태순", "", "CU세종대학생회관", "965860");
        accountList.add(accountVO6);
        AccountVO accountVO7 = new AccountVO("20200807", "185045", "3000", "0", "박태순", "", "CU세종대학생회관", "962860");
        accountList.add(accountVO7);
        AccountVO accountVO8 = new AccountVO("20200808", "025253", "25000", "0", "박태순", "", "쿠팡", "937860");
        accountList.add(accountVO8);
        AccountVO accountVO9 = new AccountVO("20200809", "025306", "2000", "0", "박태순", "", "몰빵", "935860");
        accountList.add(accountVO9);
        AccountVO accountV10 = new AccountVO("20200810", "232111", "2000", "0", "박태순", "", "몰빵", "933860");
        accountList.add(accountV10);
        AccountVO accountV11 = new AccountVO("20200811", "232119", "27000", "0", "박태순", "", "회덕 농협", "906860");
        accountList.add(accountV11);
        AccountVO accountV12 = new AccountVO("20200812", "174257", "2000", "0", "박태순", "", "몰빵", "902860");
        accountList.add(accountV12);
        AccountVO accountV13 = new AccountVO("20200813", "174312", "19000", "0", "박태순", "", "쿠팡", "883860");
        accountList.add(accountV13);
        AccountVO accountV14 = new AccountVO("20200814", "200904", "2000", "0", "박태순", "", "GS25오토바이거리", "881860");
        accountList.add(accountV14);
        AccountVO accountV15 = new AccountVO("20200815", "233931", "2000", "0", "박태순", "", "GS25오토바이거리", "879860");
        accountList.add(accountV15);
        AccountVO accountV16 = new AccountVO("20200816", "001231", "18000", "0", "박태순", "", "회덕 농협", "861860");
        accountList.add(accountV16);
        AccountVO accountV17 = new AccountVO("20200817", "195516", "2500", "0", "박태순", "", "명량핫도그", "859360");
        accountList.add(accountV17);
        AccountVO accountV18 = new AccountVO("20200818", "131244", "2500", "0", "박태순", "", "명량핫도그", "856860");
        accountList.add(accountV18);
        AccountVO accountV19 = new AccountVO("20200819", "181134", "18000", "0", "박태순", "", "회덕농협", "838860");
        accountList.add(accountV19);
        AccountVO accountV20 = new AccountVO("20200820", "030137", "1500", "0", "박태순", "", "회덕농협", "837360");
        accountList.add(accountV20);
        AccountVO accountV21 = new AccountVO("20200821", "084617", "13400", "0", "박태순", "", "쿠팡", "823960");
        accountList.add(accountV21);
        AccountVO accountV22 = new AccountVO("20200822", "190229", "1500", "0", "박태순", "", "회덕농협", "822460");
        accountList.add(accountV22);
        AccountVO accountV23 = new AccountVO("20200823", "191120", "16000", "0", "박태순", "", "회덕농협", "806460");
        accountList.add(accountV23);
        AccountVO accountV24 = new AccountVO("20200824", "140049", "7000", "0", "박태순", "", "쿠팡", "799460");
        accountList.add(accountV24);
        AccountVO accountV25 = new AccountVO("20200825", "140109", "8900", "0", "박태순", "", "쿠팡", "790560");
        accountList.add(accountV25);
        AccountVO accountV27 = new AccountVO("20200827", "235656", "25000", "0", "박태순", "", "요기요", "765560");
        accountList.add(accountV27);
        AccountVO accountV28 = new AccountVO("20200828", "132151", "1500", "0", "박태순", "", "농협", "764060");
        accountList.add(accountV28);
    }

    //대학생 박태순(네이버 쇼핑을 애용하는! 커피 ,인스턴트 중독자 대학생 박태순)
    public void setAccountCoffeeList() {
        AccountVO accountVO1 = new AccountVO("20200801", "042103", "19000", "0", "박태순", "", "우아한형제들", "1000000");
        accountList.add(accountVO1);
        AccountVO accountVO2 = new AccountVO("20200802", "205211", "6800", "0", "박태순", "", "버거킹", "993200");
        accountList.add(accountVO2);
        AccountVO accountVO3 = new AccountVO("20200803", "043141", "35000", "0", "박태순", "", "네이버페이결제", "958200");
        accountList.add(accountVO3);
        AccountVO accountVO4 = new AccountVO("20200804", "205211", "50000", "0", "박태순", "", "네이버페이결제", "908200");
        accountList.add(accountVO4);
        AccountVO accountVO5 = new AccountVO("20200805", "042358", "19000", "0", "박태순", "", "우아한형제들", "889200");
        accountList.add(accountVO5);
        AccountVO accountVO6 = new AccountVO("20200806", "011237", "4100", "0", "박태순", "", "스타벅스 군자점", "885100");
        accountList.add(accountVO6);
        AccountVO accountVO7 = new AccountVO("20200807", "185045", "3000", "0", "박태순", "", "CU세종대학생회관", "882100");
        accountList.add(accountVO7);
        AccountVO accountVO8 = new AccountVO("20200808", "025253", "25000", "0", "박태순", "", "쿠팡", "857100");
        accountList.add(accountVO8);
        AccountVO accountVO9 = new AccountVO("20200809", "025306", "4100", "0", "박태순", "", "스타벅스 군자점", "853000");
        accountList.add(accountVO9);
        AccountVO accountV10 = new AccountVO("20200810", "232111", "4100", "0", "박태순", "", "스타벅스 군자점", "848900");
        accountList.add(accountV10);
        AccountVO accountV11 = new AccountVO("20200811", "232119", "27000", "0", "박태순", "", "우아한형제들", "821900");
        accountList.add(accountV11);
        AccountVO accountV12 = new AccountVO("20200812", "174257", "2000", "0", "박태순", "", "몰빵", "819900");
        accountList.add(accountV12);
        AccountVO accountV13 = new AccountVO("20200813", "174312", "4100", "0", "박태순", "", "투썸플레이스", "815800");
        accountList.add(accountV13);
        AccountVO accountV14 = new AccountVO("20200814", "200904", "10000", "0", "박태순", "", "롯데리아", "805800");
        accountList.add(accountV14);
        AccountVO accountV15 = new AccountVO("20200815", "233931", "2000", "0", "박태순", "", "GS25오토바이거리", "803800");
        accountList.add(accountV15);
        AccountVO accountV16 = new AccountVO("20200816", "001231", "4100", "0", "박태순", "", "투썸플레이스", "799700");
        accountList.add(accountV16);
        AccountVO accountV17 = new AccountVO("20200817", "195516", "18000", "0", "박태순", "", "우아한형제들", "781700");
        accountList.add(accountV17);
        AccountVO accountV18 = new AccountVO("20200818", "131244", "4100", "0", "박태순", "", "스타벅스 군자점", "777600");
        accountList.add(accountV18);
        AccountVO accountV19 = new AccountVO("20200819", "181134", "4100", "0", "박태순", "", "투썸플레이스", "773500");
        accountList.add(accountV19);
        AccountVO accountV20 = new AccountVO("20200820", "030137", "18000", "0", "박태순", "", "요기요", "755500");
        accountList.add(accountV20);
        AccountVO accountV21 = new AccountVO("20200821", "084617", "1500", "0", "박태순", "", "회덕농협", "754000");
        accountList.add(accountV21);
        AccountVO accountV22 = new AccountVO("20200822", "190229", "9800", "0", "박태순", "", "버거킹", "744200");
        accountList.add(accountV22);
        AccountVO accountV23 = new AccountVO("20200823", "191120", "1500", "0", "박태순", "", "회덕농협", "742700");
        accountList.add(accountV23);
        AccountVO accountV24 = new AccountVO("20200824", "140049", "16000", "0", "박태순", "", "요기요", "726700");
        accountList.add(accountV24);
        AccountVO accountV25 = new AccountVO("20200825", "140109", "6800", "0", "박태순", "", "버거킹", "719900");
        accountList.add(accountV25);
        AccountVO accountV26 = new AccountVO("20200826", "140109", "8000", "0", "박태순", "", "버거킹", "711900");
        accountList.add(accountV26);
        AccountVO accountV27 = new AccountVO("20200827", "235656", "25000", "0", "박태순", "", "요기요", "686900");
        accountList.add(accountV27);
        AccountVO accountV28 = new AccountVO("20200828", "132151", "85000", "0", "박태순", "", "네이버페이결제", "601900");
        accountList.add(accountV28);
        AccountVO accountV29 = new AccountVO("20200828", "131820", "4100", "0", "박태순", "", "스타벅스 군자점", "597800");
        accountList.add(accountV29);
        AccountVO accountV30 = new AccountVO("20200830", "152251", "4100", "0", "박태순", "", "스타벅스 군자점", "593700");
        accountList.add(accountV30);
    }

    //직장인 박태순(출퇴근 시간에 유튜브를 자주보는! 아침을 gs로 해결함)
    public void setAccountGS25List() {
        AccountVO accountVO1 = new AccountVO("20200801", "042103", "19000", "0", "박태순", "", "우아한형제들", "1000000");
        accountList.add(accountVO1);
        AccountVO accountVO2 = new AccountVO("20200802", "205211", "26000", "0", "박태순", "", "옥션", "974000");
        accountList.add(accountVO2);
        AccountVO accountVO3 = new AccountVO("20200803", "043141", "4000", "0", "박태순", "", "GS25세종대점", "970000");
        accountList.add(accountVO3);
        AccountVO accountVO4 = new AccountVO("20200804", "205211", "4200", "0", "박태순", "", "GS25세종대점", "965800");
        accountList.add(accountVO4);
        AccountVO accountVO5 = new AccountVO("20200805", "042358", "4300", "0", "박태순", "", "GS25세종대점", "961500");
        accountList.add(accountVO5);
        AccountVO accountVO6 = new AccountVO("20200806", "011237", "4100", "0", "박태순", "", "GS25세종대점", "957400");
        accountList.add(accountVO6);
        AccountVO accountVO7 = new AccountVO("20200807", "185045", "4250", "0", "박태순", "", "GS25세종대점", "953150");
        accountList.add(accountVO7);
        AccountVO accountVO8 = new AccountVO("20200808", "025253", "28000", "0", "박태순", "", "G마켓", "925150");
        accountList.add(accountVO8);
        AccountVO accountVO9 = new AccountVO("20200809", "025306", "71000", "0", "박태순", "", "옥션", "854150");
        accountList.add(accountVO9);
        AccountVO accountV10 = new AccountVO("20200810", "232111", "4370", "0", "박태순", "", "GS25세종대점", "849780");
        accountList.add(accountV10);
        AccountVO accountV11 = new AccountVO("20200811", "232119", "4410", "0", "박태순", "", "GS25세종대점", "845370");
        accountList.add(accountV11);
        AccountVO accountV12 = new AccountVO("20200812", "174257", "4450", "0", "박태순", "", "GS25세종대점", "840920");
        accountList.add(accountV12);
        AccountVO accountV13 = new AccountVO("20200813", "174312", "4490", "0", "박태순", "", "GS25세종대점", "836430");
        accountList.add(accountV13);
        AccountVO accountV14 = new AccountVO("20200814", "200904", "4530", "0", "박태순", "", "GS25세종대점", "831900");
        accountList.add(accountV14);
        AccountVO accountV15 = new AccountVO("20200815", "233931", "30000", "0", "박태순", "", "G마켓", "801900");
        accountList.add(accountV15);
        AccountVO accountV16 = new AccountVO("20200816", "001231", "24000", "0", "박태순", "", "G마켓", "777900");
        accountList.add(accountV16);
        AccountVO accountV17 = new AccountVO("20200817", "195516", "4690", "0", "박태순", "", "GS25세종대점", "773210");
        accountList.add(accountV17);
        AccountVO accountV18 = new AccountVO("20200818", "131244", "4690", "0", "박태순", "", "GS25세종대점", "768520");
        accountList.add(accountV18);
        AccountVO accountV19 = new AccountVO("20200819", "181134", "4730", "0", "박태순", "", "GS25세종대점", "763790");
        accountList.add(accountV19);
        AccountVO accountV20 = new AccountVO("20200820", "030137", "4770", "0", "박태순", "", "GS25세종대점", "759020");
        accountList.add(accountV20);
        AccountVO accountV21 = new AccountVO("20200821", "084617", "1500", "0", "박태순", "", "회덕농협", "757520");
        accountList.add(accountV21);
        AccountVO accountV22 = new AccountVO("20200822", "190229", "9800", "0", "박태순", "", "박태순", "747720");
        accountList.add(accountV22);
        AccountVO accountV23 = new AccountVO("20200823", "191120", "1500", "0", "박태순", "", "회덕농협", "746220");
        accountList.add(accountV23);
        AccountVO accountV24 = new AccountVO("20200824", "140049", "16000", "0", "박태순", "", "박태순", "730220");
        accountList.add(accountV24);
        AccountVO accountV25 = new AccountVO("20200825", "140109", "6800", "0", "박태순", "", "박태순", "723420");
        accountList.add(accountV25);
        AccountVO accountV26 = new AccountVO("20200826", "140109", "8000", "0", "박태순", "", "박태순", "715420");
        accountList.add(accountV26);
        AccountVO accountV27 = new AccountVO("20200827", "235656", "25000", "0", "박태순", "", "요기요", "690420");
        accountList.add(accountV27);
        AccountVO accountV28 = new AccountVO("20200828", "132151", "85000", "0", "박태순", "", "옥션", "605420");
        accountList.add(accountV28);

    }


    public void setSubscriptionList() {

        for (int i = 0; i < accountList.size(); i++) {

            // 전체 계좌 저장
            AllAccountDatabase.getInstance(getApplicationContext()).insertAllAccountData(AllAccountDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                    accountList.get(i).getResAccountTrDate(), accountList.get(i).getResAccountTrTime(), accountList.get(i).getResAccountOut(), accountList.get(i).getResAccountIn(), accountList.get(i).getResAccountDesc1(), accountList.get(i).getResAccountDesc2()
                    , accountList.get(i).getResAccountDesc3(), accountList.get(i).getResAfterTranBalance());

            if (accountList.get(i).getResAccountDesc3().contains("네이버")) {
                naverAccount += Integer.valueOf(accountList.get(i).getResAccountOut());
                naverAccountIndex[i] = 1;
            }

            if (accountList.get(i).getResAccountDesc3().contains("버거킹") || accountList.get(i).getResAccountDesc3().contains("롯데리아") || accountList.get(i).getResAccountDesc3().contains("맥도날드") || accountList.get(i).getResAccountDesc3().contains("롯데리아")) {
                burgerkingAccount += Integer.valueOf(accountList.get(i).getResAccountOut());
                burgerkingAccountIndex[i] = 1;
            }

            if (accountList.get(i).getResAccountDesc3().contains("쿠팡")) {
                cupangAccount += Integer.valueOf(accountList.get(i).getResAccountOut());
                cupangAccountIndex[i] = 1;
            }

            if (accountList.get(i).getResAccountDesc3().contains("GS")) {
                gsAccount += Integer.valueOf(accountList.get(i).getResAccountOut());
                gsAccountIndex[i] = 1;
            }

            if (accountList.get(i).getResAccountDesc3().contains("우아한") || accountList.get(i).getResAccountDesc3().contains("요기요")) {
                yokiyoAccount += Integer.valueOf(accountList.get(i).getResAccountOut());
                yokiyoCount++;
                yokiyoAccountIndex[i] = 1;
            }

            if (accountList.get(i).getResAccountDesc3().contains("옥션") || accountList.get(i).getResAccountDesc3().contains("G마켓")) {
                smailAccount += Integer.valueOf(accountList.get(i).getResAccountOut());
                smailAccountIndex[i] = 1;
            }

            if (accountList.get(i).getResAccountDesc3().contains("스타벅스") || accountList.get(i).getResAccountDesc3().contains("투썸")) {
                coffeeAccount += Integer.valueOf(accountList.get(i).getResAccountOut());
                coffeeAccountIndex[i] = 1;
            }

        }

        if (naverAccount > 5000) {

            recommendationList.add(new RecommendationList("네이버", "네이버 플러스 멤버십", "4,900", "170,000", "8,500", R.drawable.ic_naver,getResources().getColor(R.color.colorNaver),R.drawable.benefit_coupang));

            SubscriptionDatabase.getInstance(getApplicationContext()).insertSubscriptionData(SubscriptionDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                    "네이버", "네이버 플러스 멤버십", "4,900", "170,000", "8,500", R.drawable.ic_naver, getResources().getColor(R.color.colorNaver), R.drawable.benefit_coupang
            );

            Log.d("태순숫자", "안녕");

            for (int i = 0; i < 50; i++) {
                if (naverAccountIndex[i] == 1) {
                    AccountDatabase.getInstance(getApplicationContext()).insertAccountData(AccountDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                            accountList.get(i).getResAccountTrDate(), accountList.get(i).getResAccountTrTime(), accountList.get(i).getResAccountOut(), accountList.get(i).getResAccountIn(), accountList.get(i).getResAccountDesc1(), accountList.get(i).getResAccountDesc2()
                            , accountList.get(i).getResAccountDesc3(), accountList.get(i).getResAfterTranBalance());
                }
            }
        }

        if (burgerkingAccount > 5000) {

            SubscriptionDatabase.getInstance(getApplicationContext()).insertSubscriptionData(SubscriptionDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                    "햄버거", "버거킹 정기 구독 서비스", "4,700", "41,400", "26,700", R.drawable.ic_burgerking, getResources().getColor(R.color.colorBurgerKing), R.drawable.benefit_burgerking
            );

            Log.d("태순숫자", "안녕1");

            for (int i = 0; i < 50; i++) {
                if (burgerkingAccountIndex[i] == 1) {
                    AccountDatabase.getInstance(getApplicationContext()).insertAccountData(AccountDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                            accountList.get(i).getResAccountTrDate(), accountList.get(i).getResAccountTrTime(), accountList.get(i).getResAccountOut(), accountList.get(i).getResAccountIn(), accountList.get(i).getResAccountDesc1(), accountList.get(i).getResAccountDesc2()
                            , accountList.get(i).getResAccountDesc3(), accountList.get(i).getResAfterTranBalance());
                }
            }
        }

        if (cupangAccount > 30000) {

            recommendationList.add(new RecommendationList("쿠팡", "쿠팡 로켓 와우", "2,900", "19,000", "2,500", R.drawable.ic_coupang, getResources().getColor(R.color.colorCoupang), R.drawable.benefit_coupang));

            SubscriptionDatabase.getInstance(getApplicationContext()).insertSubscriptionData(SubscriptionDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                    "쿠팡", "쿠팡 로켓 와우", "2,900", "83,300", "12,500", R.drawable.ic_coupang, getResources().getColor(R.color.colorCoupang), R.drawable.benefit_coupang
            );

            Log.d("태순숫자", "안녕2");

            for (int i = 0; i < 50; i++) {
                if (cupangAccountIndex[i] == 1) {
                    AccountDatabase.getInstance(getApplicationContext()).insertAccountData(AccountDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                            accountList.get(i).getResAccountTrDate(), accountList.get(i).getResAccountTrTime(), accountList.get(i).getResAccountOut(), accountList.get(i).getResAccountIn(), accountList.get(i).getResAccountDesc1(), accountList.get(i).getResAccountDesc2()
                            , accountList.get(i).getResAccountDesc3(), accountList.get(i).getResAfterTranBalance());
                }
            }
        }

        if (gsAccount > 5000) {
            recommendationList.add(new RecommendationList("커피", "GS 더 팝 플러스", "2,900", "19,000", "2,500", R.drawable.ic_gs25, getResources().getColor(R.color.colorGS25), R.drawable.benefit_coupang));

            SubscriptionDatabase.getInstance(getApplicationContext()).insertSubscriptionData(SubscriptionDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                    "커피", "GS 더 팝 플러스", "4,900", "202,500", "약 50,700", R.drawable.ic_gs25, getResources().getColor(R.color.colorGS25), R.drawable.benefit_coupang
            );

            Log.d("태순숫자", "안녕3");

            for (int i = 0; i < 50; i++) {
                if (gsAccountIndex[i] == 1) {
                    AccountDatabase.getInstance(getApplicationContext()).insertAccountData(AccountDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                            accountList.get(i).getResAccountTrDate(), accountList.get(i).getResAccountTrTime(), accountList.get(i).getResAccountOut(), accountList.get(i).getResAccountIn(), accountList.get(i).getResAccountDesc1(), accountList.get(i).getResAccountDesc2()
                            , accountList.get(i).getResAccountDesc3(), accountList.get(i).getResAfterTranBalance());
                }
            }
        }

        // 바꿈 카운트부분!!
        if (yokiyoCount > 3) {
            recommendationList.add(new RecommendationList("배달 음식", "요기요 슈퍼클럽", "2,900", "19,000", "2,500", R.drawable.ic_gs25, getResources().getColor(R.color.colorGS25), R.drawable.benefit_coupang));

            SubscriptionDatabase.getInstance(getApplicationContext()).insertSubscriptionData(SubscriptionDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                    "배달 음식", "요기요 슈퍼클럽", "9,900","142,000", "9,000", R.drawable.ic_yogiyo, getResources().getColor(R.color.colorYogiyo), R.drawable.benefit_coupang

            ); // 여기도 바꿈 내용은 추가바람.


            Log.d("태순숫자", "안녕3");

            for (int i = 0; i < 50; i++) {
                if (gsAccountIndex[i] == 1) {
                    AccountDatabase.getInstance(getApplicationContext()).insertAccountData(AccountDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                            accountList.get(i).getResAccountTrDate(), accountList.get(i).getResAccountTrTime(), accountList.get(i).getResAccountOut(), accountList.get(i).getResAccountIn(), accountList.get(i).getResAccountDesc1(), accountList.get(i).getResAccountDesc2()
                            , accountList.get(i).getResAccountDesc3(), accountList.get(i).getResAfterTranBalance());
                }
            }
        }

        if (smailAccount > 37000) {

            recommendationList.add(new RecommendationList("스마일 클럽", "스마일 클럽", "3,000", "19,000", "2,500", R.drawable.ic_smile_club, getResources().getColor(R.color.color11st), R.drawable.benefit_coupang));

            SubscriptionDatabase.getInstance(getApplicationContext()).insertSubscriptionData(SubscriptionDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                    "G마켓, 옥션", "스마일 클럽 멤버십", "3,000", "127,000", "44,500", R.drawable.ic_smile_club, getResources().getColor(R.color.color11st), R.drawable.benefit_coupang

            ); // 여기도 바꿈 내용은 추가바람.

            for (int i = 0; i < 50; i++) {
                if (gsAccountIndex[i] == 1) {
                    AccountDatabase.getInstance(getApplicationContext()).insertAccountData(AccountDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                            accountList.get(i).getResAccountTrDate(), accountList.get(i).getResAccountTrTime(), accountList.get(i).getResAccountOut(), accountList.get(i).getResAccountIn(), accountList.get(i).getResAccountDesc1(), accountList.get(i).getResAccountDesc2()
                            , accountList.get(i).getResAccountDesc3(), accountList.get(i).getResAfterTranBalance());
                }
            }
        }

        if (coffeeAccount > 31000) {

            //recommendationList.add(new RecommendationList("스마일 클럽", "스마일 클럽", "3,000", "19,000", "2,500", R.drawable.ic_smile_club, getResources().getColor(R.color.color11st), R.drawable.benefit_coupang));

            SubscriptionDatabase.getInstance(getApplicationContext()).insertSubscriptionData(SubscriptionDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                    "커피", "커피 플리즈", "30,800", "36,900", "6,100", R.drawable.ic_coffeeplease, getResources().getColor(R.color.colorCoffeePlease), R.drawable.benefit_coupang
            ); // 여기도 바꿈 내용은 추가바람.

            for (int i = 0; i < 50; i++) {
                if (coffeeAccountIndex[i] == 1) {
                    AccountDatabase.getInstance(getApplicationContext()).insertAccountData(AccountDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                            accountList.get(i).getResAccountTrDate(), accountList.get(i).getResAccountTrTime(), accountList.get(i).getResAccountOut(), accountList.get(i).getResAccountIn(), accountList.get(i).getResAccountDesc1(), accountList.get(i).getResAccountDesc2()
                            , accountList.get(i).getResAccountDesc3(), accountList.get(i).getResAfterTranBalance());
                }
            }
        }

    }

    public List<AccountVO> getAccountList() {
        return accountList;
    }

    public ArrayList<RecommendationList> getRecommendationList() {
        return recommendationList;
    }

}