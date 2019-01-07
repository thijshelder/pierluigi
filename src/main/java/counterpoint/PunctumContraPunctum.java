package counterpoint;

import common.Note;
import common.Voice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class PunctumContraPunctum {
    private Tonality tonality = Tonality.getInstance();
    private final PalestrinaProvider palestrina;
    List<Voice> voicelist = new ArrayList<>();

    private static final int[] consonants = {2, 3, 4, 5};

    public PunctumContraPunctum(Tonality tonality) {
        palestrina = new PalestrinaProvider(tonality);
    }

    public PunctumContraPunctum()
    {
        palestrina = new PalestrinaProvider(tonality);
    }

    public void initiatePunctum()
    {

    }

    public List<Note> getHarmonicEnvironment()
    {
        List<Note> myNotes = new ArrayList<>();

        for(int i:palestrina.createHarmonicFilling())
        {
            myNotes.add(new Note( i,  0,0));
        }


        return myNotes;
    }



    public void setTonality(Tonality tonality)
    {
     this.tonality = tonality;
    }

    public  Note makeCounterpoint(Note note) {
        return tonality.getScale().get(Math.min(Math.max(0, note.getFunction() + ((note.getPitch() / 12) * 7) - consonants[new Random().nextInt(3)] - 1), 55));
    }

    public Note createCounterpoint(Note beforenote, Note afternote, Note accompNote) {
        Note note = accompNote;

        if (beforenote.equals(afternote)) {
            note = MelodicOperation.randomMelodic(accompNote, tonality);
        } else if (palestrina.determineSonance(beforenote, accompNote)) {
            if (palestrina.step(beforenote, afternote)) {
                note = palestrina.createMelodicStepMovement(beforenote, afternote, accompNote, tonality);
            } else if (palestrina.isPerfect) {
                note = MelodicOperation.randomMelodic(accompNote, tonality);
            } else {
                note = palestrina.createMelodicStepMovement(beforenote, afternote, accompNote, tonality);
            }
        }
        return note;
    }
}
