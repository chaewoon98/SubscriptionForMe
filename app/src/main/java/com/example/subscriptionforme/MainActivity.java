package com.example.subscriptionforme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.subscriptionforme.recommendation.FragmentRecommendation;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FragmentRecommendation fragmentRecommendation = FragmentRecommendation.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragmentRecommendation).commit();

    }
}