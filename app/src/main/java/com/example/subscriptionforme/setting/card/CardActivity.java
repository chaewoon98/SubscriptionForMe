package com.example.subscriptionforme.setting.card;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.subscriptionforme.R;

import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {

    GridView gridView;
    GridAdapter adapter;
    ArrayList<Card> cards = new ArrayList<>();

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_card_company);
        cards.add(new Card("신한카드",R.drawable.sinhan_logo));
        cards.add(new Card("현대카드",R.drawable.hyundae_logo));
        cards.add(new Card("삼성카드",R.drawable.sansung_logo));
        cards.add(new Card("KB국민카드",R.drawable.kb_logo));
        cards.add(new Card("롯데카드",R.drawable.lotte_logo));
        cards.add(new Card("하나카드",R.drawable.hana_logo));
        cards.add(new Card("우리카드",R.drawable.uri_logo));
        cards.add(new Card("NH농협카드",R.drawable.nh_logo));
        cards.add(new Card("BC카드",R.drawable.bc_logo));
        adapter = new GridAdapter(this, cards);
        gridView = (GridView)findViewById(R.id.cardGrid);
        gridView.setAdapter(adapter);
    }

    @Override
    public void onStart() {

        super.onStart();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), AgreementActivity.class);
                startActivity(intent);
            }

        });


    }

}
