package counterpoint;

import utilities.MathUtils;

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

    public PalestrinaProvider(Tonality tonality) {
        this.tonality = tonality;
    }

    public boolean determineInitialConSonance() {
        return determineSonance(presentNoteTenor, presentNoteAccomp);
    }

    public boolean determineSonance(Note note1, Note note2) {
        presentInterval = (note1.getPitch() - note2.getPitch()) % 12;
        if (presentInterval == 0 || presentInterval == 7 || presentInterval == -5) {
            isPerfect = true;
        }
        return (MathUtils.isElementOf(Math.abs(presentInterval % 12), HarmonicConsonants.consonants));
    }

    public boolean step(Note presentNoteTenor, Note newTenor) {
        return ((Math.abs(presentNoteTenor.getPitch() - newTenor.getPitch())) <= 2) ;
    }

    public int getNewInterval(Note presentNoteAccomp, Note newTenor) {
        return (presentNoteAccomp.getPitch() - newTenor.getPitch());
    }

    public Note createMelodicStepMovement(Note presentNoteTenor, Note newTenor, Note note, Tonality tonality) {
        if (presentNoteTenor.getPitch() - newTenor.getPitch() < 0) {
            note = tonality.stepInterval(note, 1);
        } else {
            note = tonality.stepInterval(note, -1);
        }
        return note;
    }

    public Note createHarmonicFilling(Note tenor, Note altus)
    {
        Note bassus;
        if(determineSonance(tenor, altus))
        {   //determine highest
            //determine interval after solution
            //find appropiate note(s) to add

        }
    return null;
    }

}