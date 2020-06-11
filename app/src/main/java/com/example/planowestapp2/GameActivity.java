package com.example.planowestapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SurfaceView surfaceView = new Game(this, getIntent());
        surfaceView.setZOrderOnTop(true);
        surfaceView.getHolder().setFormat(PixelFormat.TRANSPARENT);
        ImageView bgImagePanel = new ImageView(this);
        int levelNum = getIntent().getIntExtra("LEVEL", 1);
        if (levelNum == 1)
            bgImagePanel.setBackgroundResource(R.drawable.level_one_background);
        else if (levelNum == 2)
            bgImagePanel.setBackgroundResource(R.drawable.level_two_background);
        else if (levelNum == 3)
            bgImagePanel.setBackgroundResource(R.drawable.level_three_background);
        else
            bgImagePanel.setBackgroundResource(R.drawable.level_four_background);
        RelativeLayout.LayoutParams fillParentLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
        RelativeLayout rootPanel = new RelativeLayout(this);
        rootPanel.setLayoutParams(fillParentLayout);
        rootPanel.addView(surfaceView, fillParentLayout);
        rootPanel.addView(bgImagePanel, fillParentLayout);
        this.setContentView(rootPanel);
    }
}
