package com.example.planowestapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import static com.example.planowestapp2.MainActivity.PREF_NAME;

public class OptionsActivity extends AppCompatActivity {

    private Switch lockControls;
    private boolean isLocked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        lockControls = findViewById(R.id.lockControls);
        isLocked = getSharedPreferences(PREF_NAME, MODE_PRIVATE).getBoolean("LOCK", true);
        lockControls.setChecked(isLocked);
    }

    public void goBack(View v) {
        Intent intent = new Intent(OptionsActivity.this, MainActivity.class);
        SharedPreferences.Editor edit = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
        edit.putBoolean("LOCK", lockControls.isChecked());
        edit.apply();
        startActivity(intent);
    }
}
