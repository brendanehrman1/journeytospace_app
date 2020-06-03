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
        isLocked = getIntent().getBooleanExtra("LOCK", false);
    }

    public void goToLevelOne(View v) {
        goToLevel(1);
    }

    public void goToLevel(int levelNum) {
        Intent intent = new Intent(LevelActivity.this, GameActivity.class);
        intent.putExtra("LOCK", isLocked);
        intent.putExtra("LEVEL", levelNum);
        startActivity(intent);
    }
}
