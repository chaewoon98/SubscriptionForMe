package com.example.subscriptionforme.collection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.subscriptionforme.R;

public class ChildActivity extends AppCompatActivity {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_collection);

        Intent secondIntent = getIntent();

        TextView collectionTitle = findViewById(R.id.collectionTitle);
        TextView collectionDescription = findViewById(R.id.collectionDescription);
        TextView collectionName = findViewById(R.id.collectionName);
        TextView collectionPrice = findViewById(R.id.collectionPrice);
        ImageView collectionImage = findViewById(R.id.collectionImage);

        if(secondIntent.getStringExtra("group").equals("식당")){

        } else if(secondIntent.getStringExtra("group").equals("문화")){

        } else if(secondIntent.getStringExtra("group").equals("카페, 디저트")){

        } else if(secondIntent.getStringExtra("group").equals("식품 배송")){
            Log.d("박태순","hi");
            if(secondIntent.getIntExtra("child",0) == 0){
                collectionDescription.setText("맛있고 신선한 제철과일을 배달 즐기세요!");
                collectionPrice.setText("24,000원");
                collectionTitle.setText("REAL Fruit");
                collectionImage.setImageResource(R.drawable.fruit);
                Log.d("박태순","hi");
            }

        } else if(secondIntent.getStringExtra("group").equals("의류")){

        } else if(secondIntent.getStringExtra("group").equals("생활")){

        } else if(secondIntent.getStringExtra("group").equals("기타")){

        }

    }
}