package com.example.subscriptionforme.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


import androidx.annotation.Nullable;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.SubscriptionModelData;

import java.util.ArrayList;

public class AddSubscriptionActivity extends Activity {

    private ArrayList<SubscriptionModelData> subscriptionModelDataList;
    private ArrayList<String> subsciptionModelNameList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);

        Intent intent = getIntent();

        subscriptionModelDataList = new ArrayList<SubscriptionModelData>();
        subscriptionModelDataList = intent.getParcelableArrayListExtra("subsciptionModelDataList");

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.auto_textview_ativity_add);
        SubscriptionAutoCompleteAdapter adapter = new SubscriptionAutoCompleteAdapter(this,subscriptionModelDataList);
        autoCompleteTextView.setAdapter(adapter);

    }

}
