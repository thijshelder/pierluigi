package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import counterpoint.*;
import player.MidiHandler;
import rhythmengine.PatternLibrary;
import utilities.MathUtils;

public class Voice implements IBeatListener {
    private final List<Note> melos = new ArrayList<>();
    private Note noteNowPlaying;
    private int pitch;
    private int duration = 1;
    private Tonality mytonality;
    private int numberOfPulse = 1;
    private int numberOfChanges = 1;
    private int[] pattern; //pattern should definitively be its own class
    private final boolean counterpoint;
    private Note punctumContra = null;
    private int channelNo;
    private final String name;
    private static int noOfVoices;
    private final PunctumContraPunctum punctum = new PunctumContraPunctum(mytonality);
    private final int voiceno;


    public Voice(Tonality tonality, int[] pattern, boolean counterpoint)
    {
        mytonality = tonality;
        this.pattern = pattern;
        this.counterpoint = counterpoint;
        this.channelNo = Math.min(Voice.noOfVoices,15);
        TonalUtilities.setTonality(mytonality);
        firstNote();
        Voice.setNoOfVoices();
        this.channelNo = noOfVoices;
        name = "voice" + Voice.noOfVoices;
        voiceno = noOfVoices;

    }

    private static void setNoOfVoices()
    {
        noOfVoices++;
    }

    public void setContraPunctum(Note note) {
        punctumContra = note;
    }

    private void firstNote()
    {
        noteNowPlaying = new Note(pitch, TonalUtilities.findFunction(pitch), 0);
        melos.add(noteNowPlaying);
    }


    public synchronized void contemplateChange(int channelno)
    {
        resetPattern();
        setNoteValue();
        handleNoteChange();
        numberOfPulse++;
    }

    private boolean patternEnded()
    {
        int sumOfPattern = MathUtils.getSumOfArray(pattern);
        return(numberOfPulse == sumOfPattern);
    }

    private void resetPattern()
    {
        if (patternEnded())
        {
            boolean tacet = false;
            if(tacet)
            {
                int velocity = 0;
            }
            numberOfPulse = 0;
            numberOfChanges = 0;

            changePattern(new Random().nextInt(PatternLibrary.getLength()));
        }
    }

    private void setNoteValue()
    {
        int notevalue = pattern[(Math.min(Math.max(0, numberOfChanges), pattern.length - 1))];
        noteNowPlaying.setDuration(notevalue);
    }

    private void handleNoteChange()
    {
        if (noteNowPlaying.getDuration() <= duration)
        {
            MidiHandler.muteNoteOnChannel(channelNo, noteNowPlaying);
            Note note = noteNowPlaying;

/*            if (!counterpoint)
            {
                noteNowPlaying = MelodicOperation.randomMelodic(note, mytonality);
            }
            else
            {*/
                noteNowPlaying = punctum.getHarmonicEnvironment().get(Math.min(voiceno-1, noOfVoices));
            //}

            MidiHandler.playNoteOnChannel(channelNo, noteNowPlaying);
            melos.add(noteNowPlaying);
            numberOfChanges++;

            duration = 1;
        }
        else
        {
            duration++;
        }

    }

    private void changePattern(int selector) {
        pattern = PatternLibrary.getPattern(selector);
    }

    public Note getTonica() {
        return null;
    }

    public Integer getPitch() {
        return noteNowPlaying.getPitch();
    }

    public List<Note> getMelos() {
        return melos;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    public void setProgramChange(int instrumentNo) {
        MidiHandler.chProgramChange(instrumentNo, channelNo);
    }

    public Object informAllOthers() {
        return noteNowPlaying;
    }

    public boolean getAccompagnement() {
        return counterpoint;
    }

    public void beInformed(Note note) {
        punctumContra = note;
    }

}
