package counterpoint;
import common.Note;
import common.Voice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class PunctumContraPunctum {
    static Tonality tonality;
    PalestrinaProvider palestrina;

    static int[] consonants = {2, 3, 4, 5};

    public PunctumContraPunctum(Tonality tonality)
    {
        palestrina = new PalestrinaProvider(tonality);
    }

    public PunctumContraPunctum(List<Voice> voices) {
        List<Note> noteList = new ArrayList<>();
        for (Voice voice : voices) {
            noteList.add(voice.getMelos().get(0));
        }
        palestrina = new PalestrinaProvider(tonality);
    }

    public static void setTonality(Tonality tonality) {
        PunctumContraPunctum.tonality = tonality;
    }

    public void makeAlwaysConsonant() {
        boolean upOrDown;
    }

    public static Note makeCounterpoint(Note note) {
        //System.out.println(tonality.getScale().get(tonality.getScale().indexOf(note.getFunction() + Math.round(note.getPitch()/7))));
        return tonality.getScale().get(Math.min(Math.max(0, note.getFunction() + ((note.getPitch() / 12) * 7) - consonants[new Random().nextInt(3)] - 1), 55));
    }

    public  Note createCounterpoint(Note beforenote, Note afternote, Note accompNote)
    {
        Note note = accompNote;

        if (beforenote.equals(afternote))
        {
            note = MelodicOperation.randomMelodic(accompNote, tonality);
        }
        else if (palestrina.determineSonance(beforenote, accompNote)) {
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
