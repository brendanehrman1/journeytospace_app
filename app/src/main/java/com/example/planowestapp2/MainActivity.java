package com.example.planowestapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private boolean isLocked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isLocked = getIntent().getBooleanExtra("LOCK", false);
    }

    public void startGame(View v) {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("LOCK", isLocked);
        intent.putExtra("LEVEL", 1);
        startActivity(intent);
    }

    public void goToOptions(View v) {
        Intent intent = new Intent(MainActivity.this, OptionsActivity.class);
        intent.putExtra("LOCK", isLocked);
        startActivity(intent);
    }
}
