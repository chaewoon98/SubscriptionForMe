package com.example.subscriptionforme.setting.card;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.subscriptionforme.R;

import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {

    GridView gridView;
    GridAdapter adapter;
    ImageButton back_btn;
    ArrayList<Card> cards = new ArrayList<>();

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_card_company);
        cards.add(new Card("NH농협",R.drawable.nh_logo));
        cards.add(new Card("우리",R.drawable.uri_logo));
        cards.add(new Card("신한",R.drawable.sinhan_logo));
        cards.add(new Card("KB국민",R.drawable.kb_logo));
        cards.add(new Card("하나",R.drawable.hana_logo));
        cards.add(new Card("씨티",R.drawable.city_logo));
        cards.add(new Card("IBK기업",R.drawable.ibk_logo));
        cards.add(new Card("케이뱅크",R.drawable.kbank_logo));
        cards.add(new Card("카카오뱅크",R.drawable.kakaobank_logo));

        adapter = new GridAdapter(this, cards);
        gridView = (GridView)findViewById(R.id.cardGrid);
        gridView.setAdapter(adapter);
        back_btn = findViewById(R.id.select_card_back_btn);
    }

    @Override
    public void onStart() {

        super.onStart();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), AgreementActivity.class);
                intent.putExtra("card", i);
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

}
