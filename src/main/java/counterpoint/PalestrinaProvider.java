package counterpoint;

import utilities.MathUtils;

import static counterpoint.PunctumContraPunctum.consonants;

/**
 * Created by Thijs on 28-9-2017.
 */
public class PalestrinaProvider {

    int noOfVOices;
    int presentPitchTenor;
    int presentPitchAccomp;
    int presentInterval;
    int newPitchTenor;
    int newPitchAccomp;
    boolean isPerfect;


    public boolean determineInitialSonance()
    {
       return determineSonance(presentPitchTenor,presentPitchAccomp);
    }
    public boolean determineSonance(int pitch1, int pitch2 )
    {
        presentInterval = (pitch1 - pitch2)%12;
        if(presentInterval == 0||presentInterval==7||presentInterval==-5)
        {
            isPerfect = true;
        }
        return (MathUtils.isElementOf(presentInterval, consonants));
    }

    public boolean step()
    {
        if((Math.abs(presentPitchTenor - newPitchTenor))>2)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public int getNewInterval()
    {
       return (presentPitchAccomp - newPitchTenor );
    }


}