package com.example.subscriptionforme.setting;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.setting.card.CardActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SettingActivity extends AppCompatActivity {

    Button cardDataButton;    // 카드 정보 읽기 버튼
    Button cardCsvDataButton; // 카드 csv 정보 받기 버튼
    Button subscriptionDataButton; // 내 구독 관리, 추천 csv 정보 받기 버튼
    Button surveyButton; //설문조사 버튼

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_setting);
    }

    @Override
    public void onStart() {

        super.onStart();
        cardDataButton = findViewById(R.id.buttonCardData);
        cardCsvDataButton = findViewById(R.id.buttonCardDataCSV);
        subscriptionDataButton = findViewById(R.id.buttonDataCSV);
        surveyButton = findViewById(R.id.buttonSurvey);

        cardDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(view.getContext(), CardActivity.class);
                startActivity(intent);
            }
        });

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

        subscriptionDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder data = new StringBuilder();
                data.append("startDate,endDate,resMemberStoreName,resPaymentAmt,useMoney,maxSale,manageMent,recommend");
                for(int i=0;i<1;i++){
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
