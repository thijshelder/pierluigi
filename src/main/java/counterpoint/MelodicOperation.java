package counterpoint;

import common.Note;
import utilities.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class MelodicOperation {
    public List<Note> createRetrogade(List<Note> melos) {
        List<Note> solem = new ArrayList<Note>();
        for (int i = 0; i < melos.size(); i++) {
            solem.add(melos.get(melos.size() - (i + 1)));
        }
        return solem;
    }

    public List<Note> createMirror(List<Note> melos) {
        List<Note> wejoz = new ArrayList<Note>();
        wejoz.add(melos.get(0));
        for (int i = 0; i < melos.size() - 1; i++)
        {
            int diff = (
                    melos.get(i).getPitch() -
                            melos.get(i + 1).getPitch()
            );
            diff = -diff;
            wejoz.add(new Note(melos.get(i).getPitch() + diff, 0, 0));
        }
        return wejoz;
    }

    public static Note randomMelodic(Note note, Tonality tonality) {
        Note noteUit = new Note(0, 0, 0);
        switch (new Random().nextInt(12)) {
            case 0:
            case 8:
                noteUit = note;
                break;
            case 1:
            case 5:
            case 9:
                noteUit = tonality.stepInterval(note, 1);
                break;
            case 2:
            case 6:
            case 10:
                noteUit = tonality.stepInterval(note, -1);
                break;
            case 3:
            case 7:
                noteUit = tonality.stepInterval(note, -4);
                break;
            case 4:
                noteUit = tonality.stepInterval(note, 5);
                break;
            case 11:
                noteUit = tonality.stepInterval(note, -5);
                break;
            default:
                noteUit = tonality.stepInterval(note, MathUtils.getRandomPosOrNeg(-6, 7));
        }

        return noteUit;
    }
}

