package com.example.subscriptionforme.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;


import androidx.annotation.Nullable;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.SubsciptionModelData;
import com.example.subscriptionforme.setting.SettingActivity;

import java.util.ArrayList;
import java.util.List;

public class AddSubscriptionActivity extends Activity {

    private ArrayList<SubsciptionModelData> subsciptionModelDataList;
    private ArrayList<String> subsciptionModelNameList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);

        Intent intent = getIntent();

        subsciptionModelDataList = new ArrayList<SubsciptionModelData>();
        subsciptionModelDataList = intent.getParcelableArrayListExtra("subsciptionModelDataList");

        setNameList();

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.auto_textview_ativity_add);
        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,subsciptionModelNameList));

    }

    public void setNameList(){

        subsciptionModelNameList = new ArrayList<>();

        for(int index=0;index<subsciptionModelDataList.size();index++){
            subsciptionModelNameList.add(subsciptionModelDataList.get(index).getName());
        }

    }

    // 설정 페이지 엑티비티 가는 버튼
    void onButtonClick(View v){
        Intent intent  = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

}
