package musicalintelligence.theneuronet.fitness.fitnesstools;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ScoreUtil
{
    public double totalCloseToValue(double adouble, double target)
    {
        return -(Math.pow((adouble -target),2.0));
    }

    public double mayRepresentAValidChord(int[] tones)
    {
        double score = 0.0;
        if(IntStream.of( tones).sum()>tones[0]*12)
        {
            score += 2.0;
        }
        for(int i=1;i<tones.length;i++)
        {
            if(i%12-tones[i-1]%12<3)
            {
                score+=4.0;
            }
        }
        for(int i=0;i<tones.length;i++)
        {
            if (tones[i] == tones[Math.max(0,i-1)]||tones[i]==tones[Math.min(i+1,tones.length-1)])
            {
               score += 0.2;
            }
        }
        for(int i:tones)
        {
            if(i==0)
            {
                score += 3.0;
            }
            for(int j:tones)
            {
                if ((i - j) % 12 == 0) {
                    score -= 0.5;
                }
                if ((i - j) % 7 == 0) {
                    score -= 0.5;

                }
                if ((i - j) % 12 == 4 || (i - j) % 12 == 3 || (i - j) % 12 == 8 || (i - j) % 12 == 9) {
                    score -= 0.5;
                }

            }
        }
        return -(Math.pow(score,2.0));
    }

    public double isEqualMode(int tonica, int[] tones)
    {
        //force major
        double score = 0.0;
        int actual = tonica%12;
        for (int i:tones)
        {
            int t = Math.abs(actual - i%12);
            if(Arrays.binarySearch(new int[]{1,3,6,8,10}, t)==1) {
                score += 2.0;
            }
        }
        return score;
    }


}
