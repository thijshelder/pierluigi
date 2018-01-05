package common;

import java.util.List;
import java.util.Random;

import counterpoint.*;
import player.MidiHandler;
import rhythmengine.PatternLibrary;
import utilities.MathUtils;

public class Track implements IBeatListener {

    private Voice voice;

    private Note noteNowPlaying;
    private int duration = 1;

    private int numberOfPulse = 1;
    private int numberOfChanges = 1;
    private int[] pattern; //pattern should definitively be its own class

    private int channelNo;
    String name;
    private static int noOfVoices;

    private boolean tacet  = false;


    public Track(Tonality tonality, int[] pattern, boolean counterpoint) {

        this.pattern = pattern;

        this.channelNo = Math.min(Track.noOfVoices,15);

        Track.setNoOfTracks();
        name = "voice" + Track.noOfVoices;

    }

    private static void setNoOfTracks()
    {
        noOfVoices++;
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

            if (!counterpoint)
            {
                noteNowPlaying = MelodicOperation.randomMelodic(note, mytonality);
            }
            else
            {
                noteNowPlaying = punctum.createCounterpoint(melos.get(Math.max(numberOfChanges - 2, 0)), note, punctumContra);
            }

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

    public void changePattern(int selector) {
        pattern = PatternLibrary.getPattern(selector);
    }

    public Note getTonica() {
        return null;
    }

    public int getPitch() {
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
