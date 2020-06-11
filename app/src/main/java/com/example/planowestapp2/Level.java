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
                    "B                               " +
                    "B                               " +
                    "B                            CBB" +
                    "B                            CBB" +
                    "B                            CBB" +
                    "B                            CBB" +
                    "B                            CBB" +
                    "B                CBBB        CBB" +
                    "B                CBBB        CBB" +
                    "B                CBBB        CBB" +
                    "B     BBBB       CBBB        CBB" +
                    "B     BBBB       CBBB        CBB" +
                    "B     BBBB       CBBB        CBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",

                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "B      BC             B        B" +
                    "       BC             B        B" +
                    "       BC             B        B" +
                    "BBBB   BC             B        B" +
                    "BBBB   BC             B        B" +
                    "BBBB   BC  BBBBBBC    B        B" +
                    "BBBB   BC  BBBBBBC  BBB        B" +
                    "BBBB   BC  BBBBBBC             B" +
                    "BBBB   BC  BBBBBBC             B" +
                    "BBBB   BC  BBBBBBC              " +
                    "BBBB   BC  BBBBBBC              " +
                    "BBBB   BC  BBBBBBC  BBBBBBBBBBBB" +
                    "BBBB       BBBBBBC  BBBBBBBBBBBB" +
                    "BBBB       BBBBBBC  BBBBBBBBBBBB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",

                    "BBBBBBBB    BBBBBBBBBBBBBBBBBBBB" +
                    "B                              B" +
                    "B                              B" +
                    "B       BB                     B" +
                    "B       BB                     B" +
                    "B       BB                     B" +
                    "B       BBBBC      BBBB        B" +
                    "B                          BBBBB" +
                    "B                              B" +
                    "B                              B" +
                    "                        CBBBBBBB" +
                    "                CBBB           B" +
                    "BB              C              B" +
                    "BB    BBBB      C              B" +
                    "BB                             B" +
                    "BSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSB",

                    "BBBBBBBBBBBBBB    BBBBBBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS    CBBC    SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS    CBBC    SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS    CBBC    SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBB              BBBBBBBBBB" +
                    "BBBBBBBB              BBBBBBBBBB" +
                    "BBBBBBBBPPPPBBBBBBBBBBBBBBBBBBBB",

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
                    "B           BS                 B" +
                    "B           BS                  " +
                    "B           BS                  " +
                    "B           BS     CBBBBBBBBBBBB" +
                    "B           BS     CB           " +
                    "B           BS     CB           " +
                    "B           BC     SB           " +
                    "B           BC     SB           " +
                    "B           BC     SB           " +
                    "B           BS     CB           " +
                    "            BS     CB           " +
                    "            BS     CB           " +
                    "BBBB               CB           " +
                    "BBBB               CB           " +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",

                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                    "B      SB         SB         SBB" +
                    "       SB         SB         SBB" +
                    "       SB   CBC   SB         SBB" +
                    "BBBC   SB   CBC   SB         SBB" +
                    "BBBC   SB   CBC   SB         SBB" +
                    "BBBC   SB   CBC        CBC   SBB" +
                    "BBBC   SB   CBC        CBC   SBB" +
                    "BBBC   SB   CBC        CBC      " +
                    "BBBC        CBC        CBC      " +
                    "BBBC        CBC   SBBBBBBC      " +
                    "BBBC        CBC   SBBBBBBC      " +
                    "BBBC        CBC   SBBBBBBC   SBB" +
                    "BBBC   SBBBBBBC   SBBBBBBC   SBB" +
                    "BBBBSSSSBBBBBBBSSSSBBBBBBBSSSSSB" +
                    "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",

                    "BBBBBBBB    BBBBBBBBBBBBBBBBBBBB" +
                    "B                              B" +
                    "B                              B" +
                    "B       BBB                    B" +
                    "B                              B" +
                    "B               BC     S       B" +
                    "B      BBBC     BC     S       B" +
                    "B                      C       B" +
                    "                       C       B" +
                    "                       C       B" +
                    "               CBB     C       B" +
                    "               C       S       B" +
                    "BB             S       S       B" +
                    "BB    SBBBB                    B" +
                    "BB                             B" +
                    "BBSSSSSSSSSSSSSSSSSSSSSSSSSSSSSB",

                    "BBBBBBBBBBBBBB    BBBBBBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS    CBBC    SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS    SSSS    SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS    CSSC    SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS    SSSS    SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBBBS    CSSC    SBBBBBBBBB" +
                    "BBBBBBBBBS            SBBBBBBBBB" +
                    "BBBBBBBB              BBBBBBBBBB" +
                    "BBBBBBBB              BBBBBBBBBB" +
                    "BBBBBBBBPPPPBBBBBBBBBBBBBBBBBBBB",

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





            },

            {

                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                "B                              B" +
                "B                              B" +
                "B       SSPPPP        BBBBSSSSSB" +
                "B       SS                     B" +
                "B       SS     SS              B" +
                "B       SS     SS              B" +
                "B       BB     SS     SSSS     B" +
                "C              SS              B" +
                "C              SS              B" +
                "C  BBBBBBB     SS               " +
                "C              SS               " +
                "C              SS     BBBB     B" +
                "C              SS              B" +
                "C              SS              B" +
                "BBBBBBSSSSSSSSSSSSSSSSSSSSSSSSSS",

                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                "BBB      BS                    B" +
                "BBB      BS                    B" +
                "BBB      BS                    B" +
                "BBB      BS     SBB            B" +
                "BBB      BC     SBB            B" +
                "BBB      BC     SBBSS    SSSSSSB" +
                "BBB      BC     SBB            B" +
                "BBB      BS     SBB          BBB" +
                "BBB      BS     SBB          BBB" +
                "         BS     SBB             " +
                "         BS     SBB             " +
                "BBB             CBB         BBBB" +
                "BBB             CBB         SBBB" +
                "BBB             CBB         SBBB" +
                "BBBSSSSSSSSSSSSSSSSSSSSSSSSSSSSB",

                "BBBBBBBBBBBBBBBBBBBBBBBBBBC    C" +
                "B     SBSSSSSSSSSSSSSSSSSBC    C" +
                "B     SB                 BC    C" +
                "B     SB                 BS    C" +
                "B     SB   C         C   BS    C" +
                "B     SB   CSSSSSSSSSC   BS    C" +
                "B     SB   SBBBBBBBBBS   BC    S" +
                "B     SB   SBBBBBBBBBS   BC    S" +
                "B     SB   SBBBBBBBBBS   BC    S" +
                "B     SB   SBBBBBBBBBS   BC    S" +
                "      SB   SBBBBBBBBBS         C" +
                "           SBBBBBBBBBS         C" +
                "BBB        CBBBBBBBBBC         C" +
                "BBB        CBBBBBBBBBC         C" +
                "BBB        BBBBBBBBBBB         B" +
                "BBBSSSSSSSSSSSSSSSSSSSSSSSSSSSSB",

                "BBBBBBBBBBBBBB    BBBBBBBBBBBBBB" +
                "B                              B" +
                "B                              B" +
                "B                              B" +
                "B                              B" +
                "B           CBBBBBBC           B" +
                "B                              B" +
                "B                              B" +
                "B                              B" +
                "B            SSSSSS            B" +
                "B                              B" +
                "B                              B" +
                "B                              B" +
                "B             CBBB             B" +
                "B                              B" +
                "BSSSSSSSSSSSSSSSSSSSSSSSSSBPPPPB",

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

                "BBBBBBBBBBBBBBBSSSSSSSSSSSSSSSSB" +
                "BBBBBBBBBBBBBBBS                " +
                "BBBBBBBBBBBBBBBS                " +
                "BBBBBBBBBBBBBBBS               B" +
                "BBBBBBBBBBBBBBBS     C  SSSSSSSB" +
                "BBBBBBBBBBBSSSSS     C  SBBBBBBB" +
                "BBBBBBBBBBBS            SBBBBBBB" +
                "BBBBBBBBBBBS        SSSSSBBBBBBB" +
                "BSSSSSSSSSSS        SBBBBBBBBBBB" +
                "B              S    SBBBBBBBBBBB" +
                "B              S    SBBBBBBBBBBB" +
                "             BBS    SBBBBBBBBBBB" +
                "                 BB SBBBBBBBBBBB" +
                "BBB          SSSSSSSSBBBBBBBBBBB" +
                "BBB          SBBBBBBBBBBBBBBBBBB" +
                "BBBSSSSSSSSSSSSSSSSSSSSSSSSSSSSB",

                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                "      SSSSSSSSSSSSSSSSBBBBBBBBBB" +
                "      SS             SBBBBBBBBBB" +
                "BB    SS             SBBBBBBBBBB" +
                "BB     S             SBBBBBBBBBB" +
                "BB     S       S     SSSBBBBBBBB" +
                "BB     S       S       SSBBBBBBB" +
                "BB     S   CC  S        SBBBBBBB" +
                "BB             S        SBBBBBBB" +
                "BB                      SSSSSSSS" +
                "BB               SS             " +
                "BB               SS             " +
                "BBSSSSSSSSSSSSSSSSS           BB" +
                "BBBBBBBBBBBBBBBBBBS           BB" +
                "BBBBBBBBBBBBBBBBBBS    BBB    BB" +
                "BBBBBBBBBBBBBBBBBBSSSSSBBBSSSSBB",

                "BBBBBBBBBBBBBB    BBBBBBBBBBBBBB" +
                "BBBBBBBBBBBBS      SBBBBBBBBBBBB" +
                "BBBBBBBBBBBBS      SBBBBBBBBBBBB" +
                "BBBBBBBBBBBBS      SBBBBBBBBBBBB" +
                "BBBBBBBBBBBBS  C   SBBBBBBBBBBBB" +
                "BBBBBBBBBBBBS  C   SBBBBBBBBBBBB" +
                "BBBBBBBBBBBBS      SBBBBBBBBBBBB" +
                "BSSSSSSSSSSSSSSSS  SBBBBBBBBBBBB" +
                "B             SS   SBBBBBBBBBBBB" +
                "B             SS   SBBBBBBBBBBBB" +
                "                   SBBBBBBBBBBBB" +
                "                   SSSBBBBBBBBBB" +
                "BB            CB     SBBBBBBBBBB" +
                "BB                   SBBBBBBBBBB" +
                "BB                   SBBBBBBBBBB" +
                "BBSSSSSSSSSSSSSSSSSSSSBBBBBBBBBB",

                "BBBBBBBBBBBBBB    BBBBBBBBBBBBBB" +
                "B                              B" +
                "B   S                          B" +
                "B              CC          S   B" +
                "B     S       S  S             B" +
                "B         CSSS    SSSC      S  B" +
                "B          S        S          B" +
                "B    S      S      S     S     B" +
                "B          S SSSSSS S          B" +
                "BS        S S      S S         B" +
                "B         SS        SS        SB" +
                "B   S                     S    B" +
                "B                              B" +
                "B                              B" +
                "B  S                        S  B" +
                "BBBBBBBBBBBBBBPPPPBBBBBBBBBBBBBB",

                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
                "B                              B" +
                "B                              B" +
                "B              BB              B" +
                "B             B  B             B" +
                "B         BBBB    BBBB         B" +
                "B          B        B          B" +
                "B   BB      B      B     BB    B" +
                "B          B BBBBBB B          B" +
                "B         B B      B B         B" +
                "B         BB        BB         B" +
                "B                              B" +
                "B                              B" +
                "B      BB              BB       " +
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
                    new Point(2, 1),
                    new Point(2, 12),
                    new Point(1, 4),
                    new Point(16, 1),
                    new Point(2, 3),
                    new Point(1, 3),
                    new Point(1, 12),
                    new Point(1, 4),
                    new Point(16, 1),
                    new Point(16, 1)
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
                },
                {
                    new Point(2, 1),
                    new Point(1, 4),
                    new Point(1, 4),
                    new Point(28, 1),
                    new Point(16, 1),
                    new Point(2, 3),
                    new Point(1, 13),
                    new Point(1, 4),
                    new Point(16, 1),
                    new Point(16, 1)
                }
            };

    public static String[] getLevel(int level) {
        return levels[level - 1];
    }

    public static Point getStart(int level, int scene) {
        if (scene >= startingLocs[level - 1].length)
            return startingLocs[level - 1][startingLocs[level - 1].length - 1];
        return startingLocs[level - 1][scene];
    }
}
