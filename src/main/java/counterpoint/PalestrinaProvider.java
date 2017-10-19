package counterpoint;

import common.Note;
import utilities.MathUtils;

import static counterpoint.PunctumContraPunctum.consonants;

/**
 * Created by Thijs on 28-9-2017.
 */
public class PalestrinaProvider {


    int noOfVOices;
    Note presentNoteTenor;
    Note presentNoteAccomp;
    int presentInterval;
    Note newTenor;
    boolean isPerfect;
    Tonality tonality;

    public PalestrinaProvider(Tonality tonality)
    {
        this.tonality  = tonality;
    }

    public boolean determineInitialConSonance()
    {
       return determineSonance(presentNoteTenor,presentNoteAccomp);
    }

    public boolean determineSonance(Note note1, Note note2 )
    {
        presentInterval = (note1.getPitch() - note2.getPitch())%12;
        if(presentInterval == 0||presentInterval==7||presentInterval==-5)
        {
            isPerfect = true;
        }
        return (MathUtils.isElementOf(presentInterval%12, HarmonicConsonants.consonants));
    }

    public boolean step()
    {
        if((Math.abs(presentNoteTenor.getPitch() - newTenor.getPitch()))>2)
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
       return (presentNoteAccomp.getPitch() - newTenor.getPitch() );
    }

    public Note createMelodicStepMovement(Note note)
    {
        Note note2 = presentNoteAccomp;
        if(presentNoteTenor.getPitch() - newTenor.getPitch() < 0)
        {
            note2 = tonality.stepInterval(note2, 1);
        }
        else
        {
        note2 = tonality.stepInterval(note2, -1);
        }

        return note2;
    }



}