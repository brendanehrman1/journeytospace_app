package com.example.planowestapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LevelActivity extends AppCompatActivity {

    boolean isLocked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        isLocked = getIntent().getBooleanExtra("LOCK", true);
    }

    public void goBack(View v) {
        Intent intent = new Intent(LevelActivity.this, MainActivity.class);
        intent.putExtra("LOCK", isLocked);
        startActivity(intent);
    }

    public void goToLevelOne(View v) { goToLevel(1); }

    public void goToLevelTwo(View v) {
        goToLevel(2);
    }

    public void goToLevelThree(View v) { goToLevel(3); }

    public void goToLevel(int levelNum) {
        Intent intent = new Intent(LevelActivity.this, GameActivity.class);
        intent.putExtra("LOCK", isLocked);
        intent.putExtra("LEVEL", levelNum);
        startActivity(intent);
    }
}
