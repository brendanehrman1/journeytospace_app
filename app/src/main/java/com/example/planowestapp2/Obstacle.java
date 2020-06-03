package com.example.planowestapp2;

import android.graphics.Rect;

public class Obstacle {
    private Rect dimensions;
    private String type;

    public Obstacle(Rect d, String t) {
        dimensions = d;
        type = t;
    }

    public String getType() {
        return type;
    }

    public Rect getRect() {
        return dimensions;
    }
}
