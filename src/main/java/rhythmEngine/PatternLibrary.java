package rhythmEngine;

/**
 * Created by Thijs on 27-9-2017.
 */
public  class PatternLibrary
{
        static int[] jofel = {4,4,2,2,2,2,4,8,8,2,2,16};
        static int[] kwarten = {16, 16, 16, 16};
        static int[] huppel = {12, 4, 12, 4, 12, 4, 12, 4};
        static int[] omgekeerdeHuppel = {4, 12, 16, 4, 12, 16};
        static int[] achtel = {8, 8, 8, 8, 8, 8, 8, 8};
        static int[] pompompom = {8, 8, 16, 8, 8, 16};
        static int[] poompompompoom = {16, 8, 8, 16, 8, 8};
        static int[] pomhuppel = {16, 12, 4, 8, 8, 12, 4};
        static int[][] patternList = {jofel,kwarten, huppel, omgekeerdeHuppel,achtel, pompompom, poompompompoom, pomhuppel} ;

        public static int[] getPattern(int selector)
        {
            return patternList[selector];


        }

        public static int getLength()
        {
            return patternList.length-1;
        }
 }
