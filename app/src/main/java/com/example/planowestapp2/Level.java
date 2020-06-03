package com.example.planowestapp2;

import android.graphics.Point;

public class Level {
    private static String[][] levels = {
                    {
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "C                              B" +
                    "C                              B" +
                    "C               BB              " +
                    "C               BB              " +
                    "B         BBBBBBBB              " +
                    "B     BBBBBBBBBBBB       BBBBBBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
                    ,

                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                     BBBBB    B" +
                    "B                     BBBBB    B" +
                    "B            BBBBB    BBBBB     " +
                    "B            BBBBB    BBBBB     " +
                    "B   BBBBB    BBBBB    BBBBBBBBBB" +
                    "                             BBB" +
                    "                             BBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",

                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B       BBBBBBSSSSBBBBBB       B" +
                    "B       BBBBBBBBBBBBBBBB       B" +
                    "B                              B" +
                    "B                              B" +
                    "B                       BBBBBBBB" +
                    "B             BBBB      BBBBBBBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
                    }
            };
    private static Point[][] startingLocs = {{new Point(2, 1), new Point(2, 2)}};

    public static String[] getLevel(int level) {
        return levels[level - 1];
    }

    public static Point getStart(int level, int scene) {
        return startingLocs[level - 1][scene];
    }
}
