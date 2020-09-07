package com.example.subscriptionforme.collection;

import android.app.Activity;
import android.os.Bundle;
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

        collectionDescription = findViewById(R.id.collectionDescription);
        collectionName = findViewById(R.id.collectionName);
        collectionPrice = findViewById(R.id.collectionPrice);
        collectionImage = findViewById(R.id.collectionImage);
        collectionButton = findViewById(R.id.collectionButton);


        if(secondIntent.getStringExtra("group").equals("식당")){

            if(secondIntent.getIntExtra("child",0) == 0){
                setDetailActivity("월 4700원에 버거가 4개??",
                        "버거킹",
                        "4,700원","버거킹", R.drawable.ic_burgerking,
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
            if(secondIntent.getIntExtra("child",0) == 0){
                setDetailActivity("밀리의 서재를 구독하고 시간을 가치있게 채워 나가세요",
                        "밀리의 서재 전자책",
                        "9,900원","밀리의 서재", R.drawable.image_millie,
                        "https://www.millie.co.kr/v3/join",
                        "https://www.millie.co.kr/v3/join");
            } else if(secondIntent.getIntExtra("child",1) == 1){
                setDetailActivity("광고 없는 동영상: 수백만 개의 동영상을 광고 없이 시청하세요.",
                        "YouTube Primium",
                        "7,900원","YouTube", R.drawable.youtube_logo,
                        "https://www.youtube.com/premium",
                        "https://www.youtube.com/premium");
            } else if(secondIntent.getIntExtra("child",2) == 2){
                setDetailActivity("영화, TV 프로그램을 무제한으로.",
                        "넷플릭스",
                        "9,900원","nexflix", R.drawable.nexflix_logo,
                        "https://www.netflix.com/kr/",
                        "https://www.netflix.com/kr/");
            } else if(secondIntent.getIntExtra("child",3) == 3){
                setDetailActivity("국내 최다 4000만곡 보유, No.1 뮤직플랫폼 멜론! 실시간 차트부터 나를 아는 똑똑한 음악추천까지!",
                        "멜론",
                        "6,900원","멜론", R.drawable.melon_logo,
                        "https://www.melon.com/commerce/pamphlet/web/sale_listMainView.htm",
                        "https://www.melon.com/commerce/pamphlet/web/sale_listMainView.htm");
            } else if(secondIntent.getIntExtra("child",4) == 4){
                setDetailActivity("국내 최다 음원 보유, FLAC 고음질, 지니차트, 최신음악, 앨범, 뮤직비디오, 오늘의 선곡, TV속음악, 시대별음악, 지니라이프 등 제공.",
                        "지니",
                        "4,700원","지니", R.drawable.genie_logo,
                        "https://www.genie.co.kr/buy/recommend",
                        "https://www.genie.co.kr/buy/recommend");
            }

        } else if(secondIntent.getStringExtra("group").equals("카페, 디저트")){

            if(secondIntent.getIntExtra("child",0) == 0){
                setDetailActivity("건강한 아침을 챙기는 모닝세트 구독",
                        "뚜레쥬르 모닝세트",
                        "49,500원","뚜레쥬르", R.drawable.touslesjours_logo,
                        "https://www.tlj.co.kr:7008/community/news/view.asp?b_idx=%276062",
                        "https://www.tlj.co.kr:7008/community/news/view.asp?b_idx=%276062");
            } else if(secondIntent.getIntExtra("child",1) == 1){
                setDetailActivity(" 매주 로스터가 직접 선정한 신선한 커피를 마셔요..",
                        "커피 리브레",
                        "49,500원","커피 리브레", R.drawable.image_coffeelibre,
                        "http://coffeelibre.kr/shop/item.php?it_id=1505439163",
                        "http://coffeelibre.kr/shop/item.php?it_id=1505439163");
            } else if(secondIntent.getIntExtra("child",2) == 2){
                setDetailActivity(" 매주 로스터가 직접 선정한 신선한 커피를 마셔요..",
                        "커피 플리즈",
                        "3,100원","커피 플리즈", R.drawable.image_coffeeplease,
                        "https://www.coffeeplease.co.kr/subscription.html",
                        "https://www.coffeeplease.co.kr/subscription.html");
            }

        } else if(secondIntent.getStringExtra("group").equals("식품 배송")){

            if(secondIntent.getIntExtra("child",0) == 0){
                setDetailActivity("맛있고 신선한 제철과일을 배달 즐기세요!", "REAL Fruit", "24,000원","REAL Fruit", R.drawable.fruit, "https://realfruit.co.kr/shop/subscription-request","https://realfruit.co.kr/shop/subscription-request");
            }

        } else if(secondIntent.getStringExtra("group").equals("의류")){
            if(secondIntent.getIntExtra("child",0) == 0){
                setDetailActivity("라이프 스타일에 맞는 구독 플랜을 신청하세요!",
                        "스트리밍웨어 옷 구독",
                        "29,900원","스트리밍웨어", R.drawable.image_streamingwear,
                        "https://streamingwear.com/product/streamingwear_basic-plan-mens/",
                        "https://streamingwear.com/product/streamingwear_basic-plan-mens/");
            } else if(secondIntent.getIntExtra("child",1) == 1){
                setDetailActivity("당신의 집 앞으로 매주 새로운 옷을 가져다 드릴게요.",
                        "Trendy 패션 정기구독",
                        "59,000원","TRENDY", R.drawable.image_trendy,
                        "market://details?id=com/store/apps/details?id=co.actionrabbit.tlendy",
                        " https://play.google.com/store/apps/details?id=co.actionrabbit.tlendy");
            }
        } else if(secondIntent.getStringExtra("group").equals("생활")){

            if(secondIntent.getIntExtra("child",0) == 0){
                setDetailActivity("세탁을 맡길 것인지, 집에서 빨것인지...",
                        "LION CLEANERS",
                        "49,900원","LION CLEANERS", R.drawable.lion_clean,
                        "https://www.lioncleaners.com/reserved",
                        "https://www.lioncleaners.com/reserved");
            } else if(secondIntent.getIntExtra("child",1) == 1){
                setDetailActivity("로켓배송상품 100% 무료배송 + 최대 5% 캐시적립",
                        "로켓와우 클럽",
                        "2,900원","로켓와우 클럽", R.drawable.coupang_logo,
                        "https://loyalty.coupang.com/loyalty/sign-up/home",
                        "https://loyalty.coupang.com/loyalty/sign-up/home");
            }

        } else if(secondIntent.getStringExtra("group").equals("기타")){

            if(secondIntent.getIntExtra("child",0) == 0){
                setDetailActivity("쇼핑할 때마다 네이버페이 포인트 5%! 다양한 혜택과 서비스",
                        "네이버 플러스 멤버쉽",
                        "4,900원","NAVER", R.drawable.naver_logo,
                        "https://nid.naver.com/membership/join",
                        "https://nid.naver.com/membership/join");
            } else if(secondIntent.getIntExtra("child",1) == 1){
                setDetailActivity("G마켓, 옥션, G9 어디서나 최고의 혜택을 받는 최고의 쇼핑 멤버쉽",
                        "스마일 클럽",
                        "37,000원","G마켓, 옥션, G9", R.drawable.smileclub_logo,
                        "http://m.auction.co.kr/corners/smileclub",
                        "http://m.auction.co.kr/corners/smileclub");
            } else if(secondIntent.getIntExtra("child",2) == 2){
                setDetailActivity("주문할 때마다 자동할인, 중복 할인, 매월 받는 정기 할인",
                        "요기요 슈퍼 클럽",
                        "9,900원","요기요", R.drawable.yogiyo_logo,
                        "https://www.yogiyo.co.kr/mobile/superclub.html",
                        "https://www.yogiyo.co.kr/mobile/superclub.html");
            } else if(secondIntent.getIntExtra("child",3) == 3){
                setDetailActivity("무제한 무료배송+현대M포인트 20% 혜택! 슈퍼세이브 총 12만원 블랙쿠폰 까지!",
                        "티몬 슈퍼세이브",
                        "2,500원","티몬", R.drawable.tmon_logo,
                        "https://www.tmon.co.kr/deal/927860466",
                        "https://www.tmon.co.kr/deal/927860466");
            }
        }

    }

    public void setDetailActivity(String description, String name, String price, String title, int image, final String url, final String url2)
    {
        collectionDescription.setText(description);
        collectionName.setText(name);
        collectionPrice.setText(price);
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