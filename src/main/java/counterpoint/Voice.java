package counterpoint;

import java.util.ArrayList;
import java.util.List;

public class Voice {

    private int pitch;
    private int velocity = 75;
    private List<Note> melos = new ArrayList<>();
    private Tonality mytonality;
    private boolean counterpoint;
    private Note punctumContra = null;
    PunctumContraPunctum punctum = new PunctumContraPunctum(mytonality);


    public Voice(Tonality mytonality)
    {
        PunctumContraPunctum.setTonality(mytonality);
        TonalUtilities.setTonality(mytonality);
        firstNote();
    }

    public void firstNote()
    {
        noteNowPlaying = new Note(pitch, TonalUtilities.findFunction(pitch), 0);
        melos.add(noteNowPlaying);
    }

    public void setContraPunctum(Note note) {
        punctumContra = note;
    }

}
