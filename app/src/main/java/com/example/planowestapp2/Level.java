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
                    "C               BBBBB          B" +
                    "C               BBBBB          B" +
                    "C               BBBBB           " +
                    "C          BBBBBBBBBB           " +
                    "B          BBBBBBBBBB           " +
                    "B     BBBBBBBBBBBBBBB      BBBBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
                    ,

                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "B                              B" +
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

                    "BBBBBBBBBBBBB    BBBBBBBBBBBBBBB" +
                    "B                              B" +
                    "BPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPB" +
                    "B                              B" +
                    "B       BBBBBBB                B" +
                    "B                              B" +
                    "B                  BBBBBBB     B" +
                    "B                              B" +
                    "B       BBBBBBB                B" +
                    "                               B" +
                    "                   BBBBBBB     B" +
                    "B                              B" +
                    "B       BBBBBBB                B" +
                    "B                              B" +
                    "B                              B" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",

                    "BBBBBBBBBBBBB    BBBBBBBBBBBBBBB" +
                    "B                              B" +
                    "BPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPB" +
                    "B                              B" +
                    "B       BBBBBBB                B" +
                    "B                              B" +
                    "B                  BBBBBBB     B" +
                    "B                              B" +
                    "B       BBBBBBB                B" +
                    "B                              B" +
                    "B                  BBBBBBB     B" +
                    "B                              B" +
                    "B       BBBBBBB                B" +
                    "B                              B" +
                    "B                              B" +
                    "BBBBBBBBBBBBBPPPPBBBBBBBBBBBBBBB"


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
