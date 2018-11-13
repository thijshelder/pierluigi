package counterpoint;

import common.Director;
import common.Note;
import common.Voice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class PunctumContraPunctum {
    static Tonality tonality;
    PalestrinaProvider palestrina;
    List<Voice> voicelist = new ArrayList<>();;

    static int[] consonants = {2, 3, 4, 5};

    public PunctumContraPunctum(Tonality tonality) {
        palestrina = new PalestrinaProvider(tonality);
    }

    public PunctumContraPunctum(List<Voice> voices)
    {

         voicelist.addAll(voices);
         palestrina = new PalestrinaProvider(tonality);
    }

    public void initiatePunctum()
    {

    }

    public List<Note> getHarmonicEnvironment()
    {
        List<Note> myNotes = new ArrayList<>();
        List<Note> inNotes = new ArrayList<>();
        voicelist.forEach(v->inNotes.add(v.getMelos().get(0)));
        Arrays.asList(palestrina.createHarmonicFilling(inNotes)).stream().forEach(i -> myNotes.add(new Note(i[Arrays.asList(palestrina.createHarmonicFilling(inNotes)).indexOf(i)], 0, 0)));
        return myNotes;
    }


    public static void setTonality(Tonality tonality) {
        PunctumContraPunctum.tonality = tonality;
    }

    public static Note makeCounterpoint(Note note) {
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
