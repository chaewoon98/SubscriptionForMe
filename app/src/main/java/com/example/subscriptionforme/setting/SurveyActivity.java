package com.example.subscriptionforme.setting;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.subscriptionforme.R;
import com.example.subscriptionforme.home.Data.UserDatabase;
import com.example.subscriptionforme.main.MainActivity;

import java.util.ArrayList;

public class SurveyActivity extends AppCompatActivity {

    private Button submit_button;
    private Context context;
    private RadioGroup ageGroup, jobGroup, familyNumberGroup, babyGroup, mealTpyeGroup;

    private Integer ageID, jobID, familyNumberID, babyID, mealTypeID, isCheckClothes, isCheckRestaurant, isCheckGrocery, isCheckCulture, isCheckLife, isCheckDessert,
            isCheckBook, isCheckVideo, isCheckMusic, isCheckAudioBook, isCheckWebtoon, isCheckMuseum;

    private CheckBox clothCheckBox, retuarantCheckBox, groceryCheckBox, cultureCheckBox, lifeCheckBox, dessertCheckBox, bookCheckBox, videoCheckBox, musicCheckBox, audioBookCheckBox,
            webtoonCheckBox, museumCheckBox;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();
        setContentView(R.layout.activity_survey);

        Log.d("start","start");

        submit_button = findViewById(R.id.submit_survey_button);
        ageGroup = findViewById(R.id.check_age);
        jobGroup = findViewById(R.id.check_job);
        familyNumberGroup = findViewById(R.id.check_family_number);
        babyGroup = findViewById(R.id.check_baby);
        mealTpyeGroup = findViewById(R.id.check_meal_type);

        clothCheckBox = findViewById(R.id.interest_clothes);
        retuarantCheckBox = findViewById(R.id.interest_restaurant);
        groceryCheckBox = findViewById(R.id.interest_grocery);
        cultureCheckBox = findViewById(R.id.interest_culture);
        lifeCheckBox = findViewById(R.id.interest_life);
        dessertCheckBox = findViewById(R.id.interest_dessert);
        bookCheckBox = findViewById(R.id.interest_book);
        videoCheckBox = findViewById(R.id.interest_video);
        musicCheckBox = findViewById(R.id.interest_music);
        audioBookCheckBox = findViewById(R.id.interest_audio_book);
        webtoonCheckBox = findViewById(R.id.interest_webtoon);
        museumCheckBox = findViewById(R.id.interest_museum);

        initializeSurveydata();

        //설문 제출하기 버튼 이벤트
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completeUserData();
            }
        });
    }

    public void initializeSurveydata(){
        ArrayList<Integer> surveyArrayList;

        //데이터가 없을 경우
        if(UserDatabase.getInstance(context).getSurveyDataCount(UserDatabase.getInstance(context).getReadableDatabase()) == 0){
            Log.d("return","return");
            return;
        }

        surveyArrayList = UserDatabase.getInstance(context).getUserSurveyData(UserDatabase.getInstance(context).getReadableDatabase());

        RadioButton ageButton = findViewById(surveyArrayList.get(0));
        RadioButton jobButton = findViewById(surveyArrayList.get(1));
        RadioButton familyButton= findViewById(surveyArrayList.get(8));
        RadioButton babyButton= findViewById(surveyArrayList.get(9));
        RadioButton mealTypeButton= findViewById(surveyArrayList.get(10));

        ageButton.performClick();
        jobButton.performClick();
        familyButton.performClick();
        babyButton.performClick();
        mealTypeButton.performClick();
        judgeClick(clothCheckBox,surveyArrayList.get(2));
        judgeClick(retuarantCheckBox,surveyArrayList.get(3));
        judgeClick(groceryCheckBox,surveyArrayList.get(4));
        judgeClick(cultureCheckBox,surveyArrayList.get(5));
        judgeClick(lifeCheckBox,surveyArrayList.get(6));
        judgeClick(lifeCheckBox,surveyArrayList.get(7));
        judgeClick(bookCheckBox,surveyArrayList.get(11));
        judgeClick(videoCheckBox,surveyArrayList.get(12));
        judgeClick(musicCheckBox,surveyArrayList.get(13));
        judgeClick(audioBookCheckBox,surveyArrayList.get(14));
        judgeClick(webtoonCheckBox,surveyArrayList.get(15));
        judgeClick(museumCheckBox,surveyArrayList.get(16));

    }

    public void judgeClick(CheckBox checkBox, int isClicked){

        if(isClicked == 1){
            checkBox.performClick();
        }
    }

    public void completeUserData() {

        ageID = ageGroup.getCheckedRadioButtonId();
        jobID = jobGroup.getCheckedRadioButtonId();
        familyNumberID = familyNumberGroup.getCheckedRadioButtonId();
        babyID = babyGroup.getCheckedRadioButtonId();
        mealTypeID = mealTpyeGroup.getCheckedRadioButtonId();

        isCheckClothes = getIntegerWithBoolean(clothCheckBox.isChecked());
        isCheckRestaurant = getIntegerWithBoolean(retuarantCheckBox.isChecked());
        isCheckGrocery = getIntegerWithBoolean(groceryCheckBox.isChecked());
        isCheckCulture = getIntegerWithBoolean(cultureCheckBox.isChecked());
        isCheckLife = getIntegerWithBoolean(lifeCheckBox.isChecked());
        isCheckDessert = getIntegerWithBoolean(dessertCheckBox.isChecked());
        isCheckBook = getIntegerWithBoolean(bookCheckBox.isChecked());
        isCheckVideo = getIntegerWithBoolean(videoCheckBox.isChecked());
        isCheckMusic = getIntegerWithBoolean(musicCheckBox.isChecked());
        isCheckAudioBook = getIntegerWithBoolean(audioBookCheckBox.isChecked());
        isCheckWebtoon = getIntegerWithBoolean(webtoonCheckBox.isChecked());
        isCheckMuseum = getIntegerWithBoolean(museumCheckBox.isChecked());

        if(ageID == -1){
            Toast.makeText(getApplicationContext(), "나이를 체크해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(jobID == -1){
            Toast.makeText(getApplicationContext(), "직업을 체크해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(familyNumberID == -1){
            Toast.makeText(getApplicationContext(), "가구원 수를 체크해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(babyID == -1){
            Toast.makeText(getApplicationContext(), "자녀 수를 체크해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(mealTypeID == -1){
            Toast.makeText(getApplicationContext(), "식사 성향을 체크해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(isCheckClothes == 0 && isCheckRestaurant == 0 && isCheckGrocery == 0 && isCheckCulture == 0 && isCheckLife == 0 && isCheckDessert == 0){
            Toast.makeText(getApplicationContext(), "관심 항목을 체크해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(isCheckBook == 0 && isCheckVideo == 0 && isCheckMusic == 0 && isCheckAudioBook == 0 && isCheckWebtoon == 0 && isCheckMuseum == 0){
            Toast.makeText(getApplicationContext(), "관심 항목을 체크해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        //데이터 다 삭제하고 다시 insert
        UserDatabase.getInstance(context).deleteUserSurveyData(UserDatabase.getInstance(context).getWritableDatabase());
        UserDatabase.getInstance(context).insertSurveyData(UserDatabase.getInstance(context).getWritableDatabase(), ageID, jobID, isCheckClothes,
                isCheckRestaurant, isCheckGrocery, isCheckCulture, isCheckLife, isCheckDessert, familyNumberID, babyID, mealTypeID, isCheckBook, isCheckVideo, isCheckMusic, isCheckAudioBook,
                isCheckWebtoon, isCheckMuseum);

        ArrayList<Integer> list = UserDatabase.getInstance(context).getUserSurveyData(UserDatabase.getInstance(context).getReadableDatabase());

        for(int i=0;i<17;i++){
            Log.d("eee : ", String.valueOf(i));
            Log.d("eee",list.get(i).toString());

        }

        Toast.makeText(getApplicationContext(), "내 정보 ＆ 관심사 정보가 등록되었습니다.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(context, SettingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public int getIntegerWithBoolean(boolean isCheck) {

        if (isCheck) {
            return 1;
        }
        return 0;

    }

}
