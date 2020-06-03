package com.example.planowestapp2;

public class Level {
    private static String[][] levels = {
                    {
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "                                " +
                    "                                " +
                    "        BBBBBBBBBBBBBBBB        " +
                    "        BBBBBBBBBBBBBBBB        " +
                    "                                " +
                    "                                " +
                    "BBBBBBBB                BBBBBBBB" +
                    "BBBBBBBB                BBBBBBBB" +
                    "BBBBBBBBBBBBBB    BBBBBBBBBBBBBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
                    ,

                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "                                " +
                    "                                " +
                    "        BBBBBB    BBBBBB        " +
                    "        BBBBBBBBBBBBBBBB        " +
                    "                                " +
                    "                                " +
                    "BBBBBBBB                BBBBBBBB" +
                    "BBBBBBBB      BBBB      BBBBBBBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
                    }
            };

    public static String[] getLevel(int level) {
        return levels[level - 1];
    }
}
