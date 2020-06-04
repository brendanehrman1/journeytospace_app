package com.example.planowestapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class OptionsActivity extends AppCompatActivity {

    private Switch lockControls;
    private boolean isLocked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        lockControls = findViewById(R.id.lockControls);
        isLocked = getIntent().getBooleanExtra("LOCK", false);
        lockControls.setChecked(isLocked);
    }

    public void goBack(View v) {
        Intent intent = new Intent(OptionsActivity.this, MainActivity.class);
        intent.putExtra("LOCK", lockControls.isChecked());
        startActivity(intent);
    }
}
