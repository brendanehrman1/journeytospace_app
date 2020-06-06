package com.example.planowestapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.planowestapp2.MainActivity.PREF_NAME;

public class LevelActivity extends AppCompatActivity {

    private ImageView levelOneComplete;
    private ImageView levelTwoComplete;
    private ImageView levelThreeComplete;
    private ImageView levelFourComplete;
    private TextView levelOneTime;
    private TextView levelTwoTime;
    private TextView levelThreeTime;
    private TextView levelFourTime;
    private Button levelTwoButton;
    private Button levelThreeButton;
    private Button levelFourButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        loadData();
    }

    public void loadData() {
        levelOneComplete = (ImageView) findViewById(R.id.levelOneComplete);
        levelTwoComplete = (ImageView) findViewById(R.id.levelTwoComplete);
        levelThreeComplete = (ImageView) findViewById(R.id.levelThreeComplete);
        levelFourComplete = (ImageView) findViewById(R.id.levelFourComplete);
        levelOneTime = (TextView) findViewById(R.id.levelOneTime);
        levelTwoTime = (TextView) findViewById(R.id.levelTwoTime);
        levelThreeTime = (TextView) findViewById(R.id.levelThreeTime);
        levelFourTime = (TextView) findViewById(R.id.levelFourTime);
        levelTwoButton = (Button) findViewById(R.id.level2Btn);
        levelThreeButton = (Button) findViewById(R.id.level3Btn);
        levelFourButton = (Button) findViewById(R.id.level4Btn);
        levelOneComplete.setVisibility(View.INVISIBLE);
        levelTwoComplete.setVisibility(View.INVISIBLE);
        levelThreeComplete.setVisibility(View.INVISIBLE);
        levelFourComplete.setVisibility(View.INVISIBLE);
        levelOneTime.setVisibility(View.INVISIBLE);
        levelTwoTime.setVisibility(View.INVISIBLE);
        levelThreeTime.setVisibility(View.INVISIBLE);
        levelFourTime.setVisibility(View.INVISIBLE);
        levelTwoButton.setVisibility(View.GONE);
        levelThreeButton.setVisibility(View.GONE);
        levelFourButton.setVisibility(View.GONE);

        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean lvOneComp = sharedPreferences.getBoolean("LEVEL_ONE_COMPLETE", false);
        boolean lvTwoComp = sharedPreferences.getBoolean("LEVEL_TWO_COMPLETE", false);
        boolean lvThreeComp = sharedPreferences.getBoolean("LEVEL_THREE_COMPLETE", false);
        boolean lvFourComp = sharedPreferences.getBoolean("LEVEL_FOUR_COMPLETE", false);
        if (lvOneComp) {
           double time = Double.parseDouble(sharedPreferences.getString("LEVEL_ONE_TIME", null));
           String formatTime = String.format("%02d:%02d:%05.2f", ((int)time / 3600), ((int)time / 60), (time % 60));
           levelOneTime.setText(formatTime);
           levelOneTime.setVisibility(View.VISIBLE);
           levelOneComplete.setVisibility(View.VISIBLE);
           levelTwoButton.setVisibility(View.VISIBLE);
        }
        if (lvTwoComp) {
            double time = Double.parseDouble(sharedPreferences.getString("LEVEL_TWO_TIME", null));
            String formatTime = String.format("%02d:%02d:%05.2f", ((int)time / 3600), ((int)time / 60), (time % 60));
            levelTwoTime.setText(formatTime);
            levelTwoTime.setVisibility(View.VISIBLE);
            levelTwoComplete.setVisibility(View.VISIBLE);
            levelThreeButton.setVisibility(View.VISIBLE);
        }
        if (lvThreeComp) {
            double time = Double.parseDouble(sharedPreferences.getString("LEVEL_THREE_TIME", null));
            String formatTime = String.format("%02d:%02d:%05.2f", ((int)time / 3600), ((int)time / 60), (time % 60));
            levelThreeTime.setText(formatTime);
            levelThreeTime.setVisibility(View.VISIBLE);
            levelThreeComplete.setVisibility(View.VISIBLE);
            levelFourButton.setVisibility(View.VISIBLE);
        }
        if (lvFourComp) {
            double time = Double.parseDouble(sharedPreferences.getString("LEVEL_FOUR_TIME", null));
            String formatTime = String.format("%02d:%02d:%05.2f", ((int)time / 3600), ((int)time / 60), (time % 60));
            levelFourTime.setText(formatTime);
            levelFourTime.setVisibility(View.VISIBLE);
            levelFourComplete.setVisibility(View.VISIBLE);
        }
    }

    public void goBack(View v) {
        Intent intent = new Intent(LevelActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void goToLevelOne(View v) { goToLevel(1); }

    public void goToLevelTwo(View v) { goToLevel(2); }

    public void goToLevelThree(View v) { goToLevel(3); }

    public void goToLevelFour(View v) { goToLevel(4); }

    public void goToLevel(int levelNum) {
        Intent intent = new Intent(LevelActivity.this, GameActivity.class);
        intent.putExtra("LEVEL", levelNum);
        startActivity(intent);
    }
}
