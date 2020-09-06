package com.example.subscriptionforme.setting;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.home.Data.SubscriptionDatabase;
import com.example.subscriptionforme.recommendation.RecommendationList;
import com.example.subscriptionforme.setting.card.CardActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    int dataCount;
    LinearLayout cardRegisterButton; // 카드 정보 읽기 버튼
    LinearLayout cardCsvDataButton; // 카드 csv 정보 받기 버튼
    LinearLayout recommendationCsvDataButton; // 내 구독 관리, 추천 csv 정보 받기 버튼
    LinearLayout surveyButton; //설문조사 버튼


    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataCount = 0;
        setContentView(R.layout.activity_detail_setting);
    }

    @Override
    public void onStart() {
        super.onStart();

        cardRegisterButton = findViewById(R.id.card_register_layout);
        recommendationCsvDataButton = findViewById(R.id.recommendation_data_csv_layout);
        cardCsvDataButton = findViewById(R.id.card_data_csv_layout);
        surveyButton = findViewById(R.id.survey_layout);

        //카드 등록 하기 버튼 이벤트
        cardRegisterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(view.getContext(), CardActivity.class);
                startActivity(intent);
            }
        });

        //카드 사용 내역 CSV 내려받기 버튼 이벤트
        cardCsvDataButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                StringBuilder data = new StringBuilder();
                data.append("name,resUsedDate,resMemberStoreName,resPaymentAmt");

                for(int i=0;i<1;i++){
                    data.append("\n"+"스마일 클럽"+","+"2020-08-13"+","+"11번가"+","+"50000");
                }

                try {
                    FileOutputStream out = openFileOutput("carddata.csv", Context.MODE_PRIVATE);
                    out.write((data.toString()).getBytes());
                    out.close();

                    Context context = getApplicationContext();
                    File filelocation = new File(getFilesDir(), "carddata.csv");
                    Uri path = FileProvider.getUriForFile(context, "com.example.subscriptionforme.setting.fileprovider",filelocation);
                    Intent fileIntent =  new Intent(Intent.ACTION_SEND);
                    fileIntent.setType("text/csv");
                    fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
                    fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                    startActivity(Intent.createChooser(fileIntent, "Send mail"));

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        //서비스 추천 CSV 데이터 버튼 이벤트
        recommendationCsvDataButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                StringBuilder data = new StringBuilder();
                ArrayList<RecommendationList> list = new ArrayList<RecommendationList>();
                SQLiteDatabase subscriptionDatabase = SubscriptionDatabase.getInstance(getApplicationContext()).getReadableDatabase();;
                dataCount = SubscriptionDatabase.getInstance(getApplicationContext()).getDataCount(SubscriptionDatabase.getInstance(getApplicationContext()).getReadableDatabase());

                for(int i =0; i<dataCount;i++){
                    list.add(SubscriptionDatabase.getInstance(getApplicationContext()).getSubscriptionData(subscriptionDatabase, i));
                }

                data.append("startDate,endDate,resMemberStoreName,resPaymentAmt,useMoney,maxSale,manageMent,recommend");

                for(int i=0;i<dataCount;i++){
                    data.append("\n"+"2020-06-15"+","+"2020-09-12"+","+"유튜브"+","+"12000"+","+"0"+","+"0"+","+"1"+","+"0");
                }

                try {
                    FileOutputStream out = openFileOutput("subcriptionData.csv", Context.MODE_PRIVATE);
                    out.write((data.toString()).getBytes());
                    out.close();

                    Context context = getApplicationContext();
                    File filelocation = new File(getFilesDir(), "subcriptionData.csv");
                    Uri path = FileProvider.getUriForFile(context, "com.example.subscriptionforme.setting.fileprovider",filelocation);
                    Intent fileIntent =  new Intent(Intent.ACTION_SEND);
                    fileIntent.setType("text/csv");
                    fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
                    fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                    startActivity(Intent.createChooser(fileIntent, "Send mail"));

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        surveyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), SurveyActivity.class);
                startActivity(intent);
            }
        });

    }
}
