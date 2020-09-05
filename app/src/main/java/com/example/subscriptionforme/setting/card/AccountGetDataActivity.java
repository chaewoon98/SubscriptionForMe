package com.example.subscriptionforme.setting.card;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.home.Data.AccountDatabase;
import com.example.subscriptionforme.home.FragmentHome;
import com.example.subscriptionforme.main.MainActivity;
import com.example.subscriptionforme.recommendation.RecommendationList;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AccountGetDataActivity extends AppCompatActivity {

    private List<AccountVO> accountList; // 전체 계좌
    private List<AccountVO> accountRecommendationList; // 추천에 쓰인 계좌
    private int naverAccount;
    private int gsAccount;
    private int burgerkingAccount;
    private int cupangAccount;
    private int smailAccount;
    private int[] naverAccountIndex; // 네이버 추천인덱스
    private int[] gsAccountIndex;
    private int[] burgerkingAccountIndex;
    private int[] cupangAccountIndex;
    private int[] smailAccountIndex;
    public static ArrayList<RecommendationList> recommendationList;

    Button button; // 데이터 얻는 버튼


    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_get_data);
        accountList = new ArrayList<AccountVO>();
        accountRecommendationList = new ArrayList<AccountVO>();
        recommendationList = new ArrayList<RecommendationList>();
        naverAccount = 0;
        gsAccount = 0;
        burgerkingAccount = 0;
        cupangAccount = 0;
        smailAccount = 0;
        naverAccountIndex = new int[50];
        gsAccountIndex = new int[50];
        burgerkingAccountIndex = new int[50];
        cupangAccountIndex = new int[50];
        smailAccountIndex = new int[50];

        button = findViewById(R.id.account_data_get_button);
    }

    @Override
    public void onStart() {

        super.onStart();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(accountList.size() != 0)
                {
                    accountList.clear(); // 전에 있는 내용 삭제
                }

                if(recommendationList.size() != 0)
                {
                    recommendationList.clear();
                }

                accountList = new ArrayList<AccountVO>();
                recommendationList = new ArrayList<RecommendationList>();

                setAccountNaverList();
                setSubscriptionList();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Log.d("태순", String.valueOf(AccountDatabase.getInstance(getApplicationContext()).getDataCount(AccountDatabase.getInstance(getApplicationContext()).getReadableDatabase())));
                startActivity(intent);
            }
        });

    }

    // 계좌 데이터
    public void setAccountNaverList(){
            AccountVO accountVO1 = new AccountVO("20200801","042103","889","0","박태순","","네이버페이결제","11");
            accountList.add(accountVO1);
            AccountVO accountVO2 = new AccountVO("20200802","043056","0","1000","박태순","","박태순","1011");
            accountList.add(accountVO2);
            AccountVO accountVO3 = new AccountVO("20200802","043141","1000","0","박태순","","네이버페이결제","11");
            accountList.add(accountVO3);
            AccountVO accountVO4 = new AccountVO("20200802","205211","0","3000","박태순","","박태순","3011");
            accountList.add(accountVO4);
            AccountVO accountVO5 = new AccountVO("20200803","042358","1000","0","박태순","","네이버페이결제","2011");
            accountList.add(accountVO5);
            AccountVO accountVO6 = new AccountVO("20200804","011237","8000","0","박태순","","버거킹","1011");
            accountList.add(accountVO6);
            AccountVO accountVO7 = new AccountVO("20200805","185045","400","0","박태순","","KB카드출금","611");
            accountList.add(accountVO7);
            AccountVO accountVO8 = new AccountVO("20200806","025253","400","0","박태순","","박태순","1011");
            accountList.add(accountVO8);
            AccountVO accountVO9 = new AccountVO("20200806","025306","1000","0","박태순","","네이버페이결제","11");
            accountList.add(accountVO9);
            AccountVO accountV10 = new AccountVO("20200807","232111","0","10000","박태순","","박태순","10011");
            accountList.add(accountV10);
            AccountVO accountV11 = new AccountVO("20200807","232119","600","0","박태순","","씨유세종대학생회관","9411");
            accountList.add(accountV11);
            AccountVO accountV12 = new AccountVO("20200808","174257","0","20000","박태순","","박태순","20000");
            accountList.add(accountV12);
            AccountVO accountV13 = new AccountVO("20200808","174312","18000","0","박태순","","채움","18000");
            accountList.add(accountV13);
            AccountVO accountV14 = new AccountVO("20200808","200904","0","21135","박태순","","박태순","32546");
            accountList.add(accountV14);
            AccountVO accountV15 = new AccountVO("20200808","233931","1000","0","박태순","","네이버페이결제","31546");
            accountList.add(accountV15);
            AccountVO accountV16 = new AccountVO("20200810","001231","100","0","박태순","","네이버페이결제","31446");
            accountList.add(accountV16);
            AccountVO accountV17 = new AccountVO("20200810","195516","7990","0","박태순","","현대해08011","23456");
            accountList.add(accountV17);
            AccountVO accountV18 = new AccountVO("20200811","131244","0","100000","박태순","","엄마","123456");
            accountList.add(accountV18);
            AccountVO accountV19 = new AccountVO("20200812","181134","50000","0","박태순","","토스__박병우","66156");
            accountList.add(accountV19);
            AccountVO accountV20 = new AccountVO("20200815","030137","0","242","박태순","","이자세금:30원","66398");
            accountList.add(accountV20);
            AccountVO accountV21 = new AccountVO("20200817","084617","500","0","박태순","","GS25복합터미널1호점","64898");
            accountList.add(accountV21);
            AccountVO accountV22 = new AccountVO("20200818","190229","18000","0","박태순","","춘천골닭갈비","46898");
            accountList.add(accountV22);
            AccountVO accountV23 = new AccountVO("20200818","191120","400","0","박태순","","공차어린이대공원역","46498");
            accountList.add(accountV23);
            AccountVO accountV24 = new AccountVO("20200819","140049","0","100000","박태순","","엄마","146498");
            accountList.add(accountV24);
            AccountVO accountV25 = new AccountVO("20200819","140109","0","12000","박태순","","박태순","158498");
            accountList.add(accountV25);
            AccountVO accountV26 = new AccountVO("20200821","224525","20000","0","박태순","","카카오페이","138498");
            accountList.add(accountV26);
            AccountVO accountV27 = new AccountVO("20200821","235656","7050","0","박태순","","씨유잠실리센츠점","131448");
            accountList.add(accountV27);
            AccountVO accountV28 = new AccountVO("20200822","132151","2000","0","박태순","","코이라멘 (신천점)","129448");
            accountList.add(accountV28);
            AccountVO accountV29 = new AccountVO("20200822","134659","10000","0","박태순","","카카오페이","119448");
            accountList.add(accountV29);
            AccountVO accountV30 = new AccountVO("20200824","145427","21000","0","박태순","","중국대반점","98448");
            accountList.add(accountV30);
            AccountVO accountV31 = new AccountVO("20200824","161909","14592","0","박태순","","카카오페이","83856");
            accountList.add(accountV31);
            AccountVO accountV32 = new AccountVO("20200824","204451","10000","0","박태순","","카카오페이","73856");
            accountList.add(accountV32);
            AccountVO accountV33 = new AccountVO("20200826","022815","30000","0","박태순","","농민순대","43856");
            accountList.add(accountV33);
            AccountVO accountV34 = new AccountVO("20200826","121723","1000","0","박태순","","네이버페이결제","42856");
            accountList.add(accountV34);
    }


    public void setSubscriptionList(){

        for(int i=0; i<accountList.size(); i++){

            if(accountList.get(i).getResAccountDesc3().contains("네이버")){
                naverAccount += Integer.valueOf(accountList.get(i).getResAccountOut());
                naverAccountIndex[i] = 1;
            }

            if(accountList.get(i).getResAccountDesc3().contains("버거킹") || accountList.get(i).getResAccountDesc3().contains("롯데리아") || accountList.get(i).getResAccountDesc3().contains("맥도날드") || accountList.get(i).getResAccountDesc3().contains("롯데리아") ){
                burgerkingAccount += Integer.valueOf(accountList.get(i).getResAccountOut());
                burgerkingAccountIndex[i] = 1;
            }

            if(accountList.get(i).getResAccountDesc3().contains("쿠팡")){
                cupangAccount += Integer.valueOf(accountList.get(i).getResAccountOut());
                cupangAccountIndex[i] = 1;
            }

            if(accountList.get(i).getResAccountDesc3().contains("GS")){
                gsAccount += Integer.valueOf(accountList.get(i).getResAccountOut());
                gsAccountIndex[i] = 1;
            }

        }

        if(naverAccount > 2000){
            recommendationList.add(new RecommendationList("네이버", "네이버 플러스 멤버십", "2,900", "19,000", "2,500", R.drawable.ic_naver,getResources().getColor(R.color.colorNaver),R.drawable.benefit_coupang));

            for(int i=0;i<50;i++){
                if(naverAccountIndex[i] == 1){
                    AccountDatabase.getInstance(getApplicationContext()).insertAccountData(AccountDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                            accountList.get(i).getResAccountTrDate(), accountList.get(i).getResAccountTrTime(), accountList.get(i).getResAccountOut(), accountList.get(i).getResAccountIn(), accountList.get(i).getResAccountDesc1(), accountList.get(i).getResAccountDesc2()
                            ,accountList.get(i).getResAccountDesc3(),accountList.get(i).getResAfterTranBalance());
                }
            }
        }

        if(burgerkingAccount > 5000){
            recommendationList.add(new RecommendationList("햄버거", "버거킹 정기 구독 서비스", "4,700", "9,800", "5,200", R.drawable.ic_burgerking, getResources().getColor(R.color.colorBurgerKing),R.drawable.benefit_burgerking));

            for(int i=0;i<50;i++){
                if(burgerkingAccountIndex[i] == 1){
                    AccountDatabase.getInstance(getApplicationContext()).insertAccountData(AccountDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                            accountList.get(i).getResAccountTrDate(), accountList.get(i).getResAccountTrTime(), accountList.get(i).getResAccountOut(), accountList.get(i).getResAccountIn(), accountList.get(i).getResAccountDesc1(), accountList.get(i).getResAccountDesc2()
                            ,accountList.get(i).getResAccountDesc3(),accountList.get(i).getResAfterTranBalance());
                }
            }
        }

        if(cupangAccount > 5000){
            recommendationList.add(new RecommendationList("쿠팡", "쿠팡 로켓 와우", "2,900", "19,000", "2,500", R.drawable.ic_coupang,getResources().getColor(R.color.colorCoupang),R.drawable.benefit_coupang));

            for(int i=0;i<50;i++){
                if(cupangAccountIndex[i] == 1){
                    AccountDatabase.getInstance(getApplicationContext()).insertAccountData(AccountDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                            accountList.get(i).getResAccountTrDate(), accountList.get(i).getResAccountTrTime(), accountList.get(i).getResAccountOut(), accountList.get(i).getResAccountIn(), accountList.get(i).getResAccountDesc1(), accountList.get(i).getResAccountDesc2()
                            ,accountList.get(i).getResAccountDesc3(),accountList.get(i).getResAfterTranBalance());
                }
            }
        }

        if(gsAccount > 5000){
            recommendationList.add(new RecommendationList("커피", "GS 더 팝 플러스", "2,900", "19,000", "2,500", R.drawable.ic_gs25,getResources().getColor(R.color.colorGS25),R.drawable.benefit_coupang));

            for(int i=0;i<50;i++){
                if(gsAccountIndex[i] == 1){
                    AccountDatabase.getInstance(getApplicationContext()).insertAccountData(AccountDatabase.getInstance(getApplicationContext()).getWritableDatabase(),
                            accountList.get(i).getResAccountTrDate(), accountList.get(i).getResAccountTrTime(), accountList.get(i).getResAccountOut(), accountList.get(i).getResAccountIn(), accountList.get(i).getResAccountDesc1(), accountList.get(i).getResAccountDesc2()
                            ,accountList.get(i).getResAccountDesc3(),accountList.get(i).getResAfterTranBalance());
                }
            }
        }
    }

    public List<AccountVO> getAccountList() {
        return accountList;
    }

    public ArrayList<RecommendationList> getRecommendationList(){
        return recommendationList;
    }

}
