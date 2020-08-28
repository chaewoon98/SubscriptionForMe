package com.example.subscriptionforme.collection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_collection);

        Intent secondIntent = getIntent();

        String group = String.valueOf(secondIntent.getStringExtra("group"));
        String childIndex = String.valueOf(secondIntent.getStringExtra("child"));


    }
}