package rhythmengine;

/**
 * Created by Thijs on 27-9-2017.
 */
public  class PatternLibrary
{
        private PatternLibrary()
        {}

        private static final int[] jofel = {4,4,2,2,2,2,4,8,8,2,2,16};
        private static final int[] kwarten = {16, 16, 16, 16};
        private static final int[] huppel = {12, 4, 12, 4, 12, 4, 12, 4};
        private static final int[] omgekeerdeHuppel = {4, 12, 16, 4, 12, 16};
        private static final int[] achtel = {8, 8, 8, 8, 8, 8, 8, 8};
        private static final int[] pompompom = {8, 8, 16, 8, 8, 16};
        private static final int[] poompompompoom = {16, 8, 8, 16, 8, 8};
        private static final int[] pomhuppel = {16, 12, 4, 8, 8, 12, 4};
        private static final int[][] patternList = {jofel,kwarten, huppel, omgekeerdeHuppel,achtel, pompompom, poompompompoom, pomhuppel} ;

        public static int[] getPattern(int selector)
        {
            return patternList[selector];
        }

        public static int getLength()
        {
            return patternList.length-1;
        }
 }
