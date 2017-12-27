package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import counterpoint.*;
import player.MidiHandler;
import rhythmEngine.PatternLibrary;
import utilities.MathUtils;

public class Voice implements IBeatListener {
    private List<Note> melos = new ArrayList<Note>();
    private Note note_now_playing;
    private boolean changable;
    private int pitch;
    private int velocity = 75;
    private int duration = 1;
    private Tonality mytonality;
    private int numberOfPulse = 1;
    private int numberOfChanges = 1;
    private int[] pattern; //pattern should definitively be its own class
    private boolean accompagnement;
    private Note punctumContra = null;
    private int channelNo;
    private String name;
    private static int noOfVoices;
    PunctumContraPunctum punctum = new PunctumContraPunctum(mytonality);
    private boolean tacet  = false;


    public Voice(Tonality tonality, int[] pattern, boolean accompagnement) {
        mytonality = tonality;
        this.pattern = pattern;
        this.channelNo = Math.min(Voice.noOfVoices,15);
        PunctumContraPunctum.setTonality(mytonality);
        TonalUtilities.setTonality(mytonality);
        firstNote();
        Voice.setNoOfVoices();
        name = "voice" + Voice.noOfVoices;

    }

    private static void setNoOfVoices()
    {
        noOfVoices++;
    }

    public void setContraPunctum(Note note) {
        punctumContra = note;
    }

    public void firstNote()
    {
        note_now_playing = new Note(pitch, TonalUtilities.findFunction(pitch), 0);
        melos.add(note_now_playing);
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
            if(tacet)
            {
                velocity = 0;
            }
            numberOfPulse = 0;
            numberOfChanges = 0;

            changePattern(new Random().nextInt(PatternLibrary.getLength()));
        }
    }

    private void setNoteValue()
    {
        int notevalue = pattern[(Math.min(Math.max(0, numberOfChanges), pattern.length - 1))];
        note_now_playing.setDuration(notevalue);
    }

    private void handleNoteChange()
    {
        if (note_now_playing.getDuration() <= duration)
        {
            MidiHandler.muteNoteOnChannel(channelNo, note_now_playing);
            Note note = note_now_playing;

            if (!accompagnement)
            {
                note_now_playing = MelodicOperation.randomMelodic(note, mytonality);
            }
            else
            {
                note_now_playing = punctum.createCounterpoint(melos.get(Math.max(numberOfChanges - 1, 0)), note, punctumContra);
            }

            MidiHandler.playNoteOnChannel(channelNo, note_now_playing);
            melos.add(note_now_playing);
            numberOfChanges++;

            duration = 1;
        }
        else
        {
            duration++;
        }

    }

    public void changePattern(int selector) {
        pattern = PatternLibrary.getPattern(selector);
    }

    public Note getTonica() {
        return null;
    }

    public int getPitch() {
        return note_now_playing.getPitch();
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
        return note_now_playing;
    }

    public boolean getAccompagnement() {
        return accompagnement;
    }

    public void beInformed(Note note) {
        punctumContra = note;
    }

}
