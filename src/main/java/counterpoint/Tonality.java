package counterpoint;


import common.Note;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tonality {
    private static final List<Note> scale = new ArrayList<>();

    private Note tonica;
    private int highestNoteValue = 70;

    private static Tonality Instance;

    public static Tonality getInstance(List<Note> list)
    {
        if(Instance == null)
        {
            Instance = new Tonality(list);
        }
        return Instance;
    }
    public static Tonality getInstance()
    {
        if(Instance == null)
        {
            Instance = new Tonality(new ArrayList<>());
        }
        return Instance;
    }

    private Tonality(List<Note> notes)
    {
        if (notes.size() > 1) {
            setScale(notes);
        } else if (notes.size() == 1) {
            createScale(notes.get(0));
        } else if (notes.isEmpty()) {
            createScale(new Note(48, 1, 0));
        }
     }



    private void setScale(List<Note> notes) {
        Iterator itr = notes.iterator();
        //leave this for later
        for (Note note : notes) {
            int p = note.getPitch();
            while (itr.hasNext())
            {
                Note note2 = (Note) itr.next();
                int p2 = note2.getPitch();
                if (p < p2) {
                    notes.set(notes.indexOf(note2) - 1, note);
                }
                break;
            }
        }
    }

    public void setScale(Note note, Mode mode) {
        //make the note tonica
        this.tonica = note;
        tonica.setFunction(1);
        Note dominant = new Note(note.getPitch() + 7, 5, 0);
        Note subDominant = new Note(note.getPitch() + 5, 4, 0);

        switch (mode) {
            case MAJOR:
                //yadeeeyadee

            case MINOR:
                //YeedaaYeeda
        }

    }

    private void createScale(Note tonica) {
        //creates a major scale and no
        this.tonica = tonica;
        int lowestTonica = tonica.getPitch() % 12;
        for (int i = lowestTonica; (i <= 88); i = i + 12) {//yes I know this is crappy
            int pos = 1;

            scale.add(new Note(i, pos, 0));
            scale.add(new Note(i + 2, pos + 1, 0));
            scale.add(new Note(i + 4, pos + 2, 0));
            scale.add(new Note(i + 5, pos + 3, 0));
            pos = 5;
            scale.add(new Note(i + 7, pos, 0));
            scale.add(new Note(i + 9, pos + 1, 0));
            scale.add(new Note(i + 11, pos + 2, 0));
        }

    }

    public List<Note> getScale()
    {
        return scale;
    }

    public Note stepInterval(Note note, int interval) {
        int lowestNoteValue = 30;
        return scale.get(Math.max(lowestNoteValue, Math.min((scale.indexOf(note) + interval), scale.size() - 1)));
    }

    public int getTonicaPitch()
    {
        return tonica.getPitch();
    }

}
