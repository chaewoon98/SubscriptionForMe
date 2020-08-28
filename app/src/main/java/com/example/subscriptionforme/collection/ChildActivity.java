package com.example.subscriptionforme.collection;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.subscriptionforme.R;

public class ChildActivity extends AppCompatActivity {

    TextView collectionTitle;
    TextView collectionDescription;
    TextView collectionName;
    TextView collectionPrice;
    ImageView collectionImage;
    Button collectionButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        final String appPackageName = getPackageName();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_collection);
        Intent secondIntent = getIntent();

        collectionTitle = findViewById(R.id.collectionTitle);
        collectionDescription = findViewById(R.id.collectionDescription);
        collectionName = findViewById(R.id.collectionName);
        collectionPrice = findViewById(R.id.collectionPrice);
        collectionImage = findViewById(R.id.collectionImage);
        collectionButton = findViewById(R.id.collectionButton);


        if(secondIntent.getStringExtra("group").equals("식당")){

            if(secondIntent.getIntExtra("child",0) == 0){
                setDetailActivity("월 4700원에 버거가 4개??",
                        "버거킹",
                        "4,700원","버거킹", R.drawable.burgerking,
                        "market://details?id=com.skmc.okcashbag.home_google",
                        "https://play.google.com/store/apps/details?id=com.skmc.okcashbag.home_google");
            } else if(secondIntent.getIntExtra("child",1) == 1){
                setDetailActivity("월 2500원만 내면! 아메리카노 한 잔 1200원",
                        "GS25 더팝Plus",
                        "2,500원","GS25", R.drawable.gs25,
                        "market://details?id=com.gsretail.android.thepop",
                        "https://play.google.com/store/apps/details?id=com.gsretail.android.thepop");
            }

        } else if(secondIntent.getStringExtra("group").equals("문화")){

        } else if(secondIntent.getStringExtra("group").equals("카페, 디저트")){

        } else if(secondIntent.getStringExtra("group").equals("식품 배송")){

            if(secondIntent.getIntExtra("child",0) == 0){
                setDetailActivity("맛있고 신선한 제철과일을 배달 즐기세요!", "REAL Fruit", "24,000원","REAL Fruit", R.drawable.fruit, "market://details?id=com.skmc.okcashbag.home_google","https://play.google.com/store/apps/details?id=com.skmc.okcashbag.home_google");
            }

        } else if(secondIntent.getStringExtra("group").equals("의류")){

        } else if(secondIntent.getStringExtra("group").equals("생활")){

            if(secondIntent.getIntExtra("child",0) == 0){
                setDetailActivity("세탁을 맡길 것인지, 집에서 빨것인지...",
                        "LION CLEANERS",
                        "49,900원","LION CLEANERS", R.drawable.lion_clean,
                        "https://www.lioncleaners.com/reserved",
                        "https://www.lioncleaners.com/reserved");
            }

        } else if(secondIntent.getStringExtra("group").equals("기타")){

        }

    }

    public void setDetailActivity(String description, String name, String price, String title, int image, final String url, final String url2)
    {
        collectionDescription.setText(description);
        collectionName.setText(name);
        collectionPrice.setText(price);
        collectionTitle.setText(title);
        collectionImage.setImageResource(image);
        collectionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url2)));
                }
            }
        });
    }
}