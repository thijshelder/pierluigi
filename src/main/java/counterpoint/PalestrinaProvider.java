package counterpoint;

import common.BeatProvider;
import common.Note;
import common.Voice;
import musicalintelligence.theneuronet.NeuroConnector;
import musicalintelligence.theneuronet.fitness.algoritm.GenAlgorithm;
import musicalintelligence.theneuronet.fitness.algoritm.Individual;
import utilities.ArrayUtils;
import utilities.MathUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        return ((Math.abs(presentNoteTenor.getPitch() - newTenor.getPitch())) <= 2);
    }

    public int getNewInterval(Note presentNoteAccomp, Note newTenor) {
        return (presentNoteAccomp.getPitch() - newTenor.getPitch());
    }

    public Note createMelodicStepMovement(Note presentNoteTenor, Note newTenor, Note note, Tonality tonality) {
        if (presentNoteTenor.getPitch() - newTenor.getPitch() <= 0) {
            note = tonality.stepInterval(note, 1);
        } else {
            note = tonality.stepInterval(note, -1);
        }
        return note;
    }

    public int[] createHarmonicFilling()
    {
        double[] input = BeatProvider.getInstance().getVoices().stream().mapToDouble(v->v.getPitch()).toArray();
        Double[] realInput = new ArrayUtils().convertToObjectArray(input);
        GenAlgorithm.getInstance().addIndividual(realInput);
        Individual best =
                GenAlgorithm.getInstance()
                        .engage
                                (Tonality.getInstance(new ArrayList<Note>()).getTonicaPitch());
        int[] reValue = new int[best.getGenome().length];
        int i= 0;
        for(Double d:best.getGenome())
        {
            reValue[i]= d.intValue();
            i++;
        }
        return reValue;
    }


}