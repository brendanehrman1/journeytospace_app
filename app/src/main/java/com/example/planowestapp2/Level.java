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
                    "B               BBBBB          B" +
                    "B               BBBBB          B" +
                    "B               BBBBB          B" +
                    "B          BBBBBBBBBB           " +
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

                    "BBBBBBBBBBBBBB    BBBBBBBBBBBBBB" +
                    "B                              B" +
                    "BPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPB" +
                    "B                              B" +
                    "B       BBBBBBB                B" +
                    "B                              B" +
                    "B                 BBBBBBB      B" +
                    "B                              B" +
                    "B       BBBBBBB                B" +
                    "                               B" +
                    "                  BBBBBBB      B" +
                    "B                              B" +
                    "B       BBBBBBB                B" +
                    "B                              B" +
                    "B                              B" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",

                    "BBBBBBBBBBBBBB    BBBBBBBBBBBBBB" +
                    "BBBBBBBBB              BBBBBBBBB" +
                    "BBBBBBBBB              BBBBBBBBB" +
                    "BBBBBBBBB     BBBB     BBBBBBBBB" +
                    "BBBBBBBBB              BBBBBBBBB" +
                    "BBBBBBBBB              BBBBBBBBB" +
                    "BBBBBBBBB     BBBB     BBBBBBBBB" +
                    "BBBBBBBBB              BBBBBBBBB" +
                    "BBBBBBBBB              BBBBBBBBB" +
                    "BBBBBBBBB      BBBB    BBBBBBBBB" +
                    "BBBBBBBBB              BBBBBBBBB" +
                    "BBBBBBBBB              BBBBBBBBB" +
                    "BBBBBBBBB     BBBB     BBBBBBBBB" +
                    "BBBBBBBBB              BBBBBBBBB" +
                    "BBBBBBBBB              BBBBBBBBB" +
                    "BBBBBBBBBBBBBBPPPPBBBBBBBBBBBBBB",

                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "B                              B" +
                    "B                              B" +
                    "B  BB  B   B   BBBB   BBBB   B B" +
                    "B  B B B   B   B      B      B B" +
                    "B  B B B   B   B      BBBB   B B" +
                    "B  B B B   B   B      B        B" +
                    "B  B  BB   B   BBBB   BBBB   B B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                               " +
                    "B                               " +
                    "B                    BBBBBBBBBBB" +
                    "B                    BBBBBBBBBBB" +
                    "BBBBBBBBBBBBBBPPPPBBBBBBBBBBBBBB",

                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "                                " +
                    "                                " +
                    "BBBBBBBBBBBBB      BBBBBBBBBBBBB" +
                    "BBBBBBBBBBBBBB    BBBBBBBBBBBBBB" +
                    "BBBBBBBBBBBBBBSSSSBBBBBBBBBBBBBB",

                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "B                              B" +
                    "B                              B" +
                    "B                               " +
                    "B                               " +
                    "B                              B" +
                    "B                              S" +
                    "B                       BBBB   S" +
                    "B                       BBBB   S" +
                    "B               BBBB    BBBB   S" +
                    "B               BBBB    BBBB   S" +
                    "        BBBB    BBBB    BBBB   S" +
                    "        BBBB    BBBB    BBBB   S" +
                    "BBBB    BBBB    BBBB    BBBB   S" +
                    "BBBB    BBBB    BBBB    BBBB   S" +
                    "BBBBSSSSBBBBSSSSBBBBSSSSBBBBSSSB",

                    "BBBBBBBBBBBBBB    BBBBBBBBBBBBBB" +
                    "B                              B" +
                    "BBBBBB                         B" +
                    "     B         BBBB            B" +
                    "     B                BBBB     B" +
                    "BB                           BBB" +
                    "BB                             B" +
                    "BB                             B" +
                    "BB                      BBBB   B" +
                    "BB        BBBB                 B" +
                    "BB                             B" +
                    "BB                BBBB         B" +
                    "BB  BBBB                       B" +
                    "BB                             B" +
                    "BBSSSSSSSSSSSSSSSSSSSSSSSSSSSSSB" +
                    "BBSSSSSSSSSSSSSSSSSSSSSSSSSSSSSB",

                    "BBBBBBBBBBBBBB    BBBBBBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS    BBBB    SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS    BBBB    SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS    BBBB    SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBB    BBBB    BBBBBBBBBB" +
                    "BBBBBBBBBB            BBBBBBBBBB" +
                    "BBBBBBBBBB            BBBBBBBBBB" +
                    "BBBBBBBBBBBBBBPPPPBBBBBBBBBBBBBB",

                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "B                              B" +
                    "B                              B" +
                    "B              BB              B" +
                    "B             B  B             B" +
                    "B         BBBB    BBBB         B" +
                    "B          B        B          B" +
                    "B           B      B           B" +
                    "B          B BBBBBB B          B" +
                    "B         B B      B B         B" +
                    "B         BB        BB         B" +
                    "B                              B" +
                    "B                              B" +
                    "B                               " +
                    "B                               " +
                    "BBBBBBBBBBBBBBPPPPBBBBBBBBBBBBBB"


                    },
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
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"


            },

            {

                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                   BBBB       B" +
                    "B                   BBBB       B" +
                    "B                   BBBB       B" +
                    "B                   BBBB       B" +
                    "B                   BBBB       B" +
                    "B      CBBC         BBBB       B" +
                    "B      CBBC         BBBB        " +
                    "B      CBBC         BBBB        " +
                    "B      CBBC         BBBBBBBBBBBB" +
                    "B      CBBC         BBBBBBBBBBBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",

                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B              BBBBBB          B" +
                    "B                               " +
                    "B                               " +
                    "B                         BBBBBB" +
                    "B       BBBBBB                 B" +
                    "B                              B" +
                    "                               B" +
                    "                               B" +
                    "BBBBB                          B" +
                    "BBBBB                          B" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",

                    "BBBBBBBBBBBBBB    BBBBBBBBBBBBBB" +
                    "B                              B" +
                    "BPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPB" +
                    "B                              B" +
                    "B                              B" +
                    "BBBBBBBBBBBBBBBBBBS            B" +
                    "     B                         B" +
                    "     B                         B" +
                    "BB                      SSBBBBBB" +
                    "BB                             B" +
                    "BB                             B" +
                    "BB                             B" +
                    "BBBBBB       SBBBBS            B" +
                    "BB                             B" +
                    "BB                             B" +
                    "BBSSSSSSSSSSSSSSSSSSSSSSSSSSSSSB",

                    "BBBBBBBBBBBBBB    BBBBBBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS    BBBB    SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS    BBBB    SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS    BBBB    SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBB            BBBBBBBBBB" +
                    "BBBBBBBBBB            BBBBBBBBBB" +
                    "BBBBBBBBBBBBBBPPPPBBBBBBBBBBBBBB",

                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "B                              B" +
                    "B                              B" +
                    "B  BB  B   B   BBBB   BBBB   B B" +
                    "B  B B B   B   B      B      B B" +
                    "B  B B B   B   B      BBBB   B B" +
                    "B  B B B   B   B      B        B" +
                    "B  B  BB   B   BBBB   BBBB   B B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                               " +
                    "B                               " +
                    "B                    BBBBBBBBBBB" +
                    "B                    BBBBBBBBBBB" +
                    "BBBBBBBBBBBBBBPPPPBBBBBBBBBBBBBB",

                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "B                              B" +
                    "B                              B" +
                    "B        BBBB       BBBB       B" +
                    "B        BBBS       SBBB       B" +
                    "B        BBBS       SBBB       B" +
                    "B        BBBS       SBBB       B" +
                    "B        BBBS       SBBB       B" +
                    "         BBBS       SBBB        " +
                    "         BBBS       SBBB        " +
                    "BBBBBBBBBBBBS       SBBBBBBBBBBB" +
                    "BBBBBBBBBBBBS       SBBBBBBBBBBB" +
                    "BBBBBBBBBBBBBSSSSSSSBBBBBBBBBBBB",

                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                              B" +
                    "B                   BB         B" +
                    "B                              B" +
                    "B       BB                      " +
                    "B                               " +
                    "B                          BBBBB" +
                    "B                              B" +
                    "                               B" +
                    "                               B" +
                    "BBBBB                          B" +
                    "BBBBB                          B" +
                    "BBBBBSSSSSSSSSSSSSSSSSSSSSSSSSSB",

                    "BBBBBBBBBBBBBB    BBBBBBBBBBBBBB" +
                    "BBBBBBBBBBBBBB                 B" +
                    "B            B                 B" +
                    "B            BBBBBBBBBBBBB     B" +
                    "B    B       B           B     B" +
                    "B    B       B           B     B" +
                    "B    B       B     B     B    BB" +
                    "     B       B     B     S    BB" +
                    "     B       B    BB          BB" +
                    "BBBBBB       S    BB          BB" +
                    "BBBBBB            BB          BB" +
                    "BBBBBB            BB     SBBBBBB" +
                    "BBBBBB            BB     SBBBBBB" +
                    "BBBBBB       SBBBBBB     SBBBBBB" +
                    "BBBBBB       SBBBBBB     SBBBBBB" +
                    "BBBBBBSSSSSSSBBBBBBBSSSSSBBBBBBB",

                    "BBBBBBBBBBBBBB    BBBBBBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS    BBBB    SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS    SSSS    SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS    BBBB    SBBBBBBBBB" +
                    "BBBBBBBBBB            BBBBBBBBBB" +
                    "BBBBBBBBBB            BBBBBBBBBB" +
                    "BBBBBBBBBBBBBBPPPPBBBBBBBBBBBBBB",

                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "B                              B" +
                    "B                              B" +
                    "B              BB              B" +
                    "B             B  B             B" +
                    "B         BBBB    BBBB         B" +
                    "B          B        B          B" +
                    "B           B      B           B" +
                    "B          B BBBBBB B          B" +
                    "B         B B      B B         B" +
                    "B         BB        BB         B" +
                    "B                              B" +
                    "B                              B" +
                    "B                               " +
                    "B                               " +
                    "BBBBBBBBBBBBBBPPPPBBBBBBBBBBBBBB"





            }

            };
    private static Point[][] startingLocs = {
                {
                    new Point(2, 1),
                    new Point(2, 2),
                    new Point(16, 1),
                    new Point(16, 1),
                    new Point(2, 1),
                    new Point(2, 3),
                    new Point(1, 3),
                    new Point(2, 11),
                    new Point(16, 1),
                    new Point(16, 1)
                },
                {
                    new Point(2, 1)
                },
                {
                    new Point(2, 1),
                    new Point(2, 3),
                    new Point(1, 8),
                    new Point(16, 1),
                    new Point(2, 1),
                    new Point(2, 3),
                    new Point(2, 3),
                    new Point(2, 7),
                    new Point(16, 1),
                    new Point(16, 1)
                }
            };

    public static String[] getLevel(int level) {
        return levels[level - 1];
    }

    public static Point getStart(int level, int scene) {
        return startingLocs[level - 1][scene];
    }
}
