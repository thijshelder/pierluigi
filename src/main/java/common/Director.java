package common;

import counterpoint.Note;
import counterpoint.Tonality;
import player.MidiHandler;
import rhythmengine.PatternLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Director {

    BeatProvider provider = new BeatProvider();
    List<Note> list = new ArrayList<>();

    Tonality tonality = new Tonality(list);
    ArrayList<int[]> patternList = new ArrayList<>();

    public void initOrchestra(int numberOfVoices, int instrument)
    {
        MidiHandler.openMidiHandler();
        for(int i=0;i<=numberOfVoices;i++)
        {
            MidiHandler.chProgramChange(instrument,i);
        }
    }

    public void startSong(int startPitch, int numberOfVoices)
    {
        list.add(new Note(startPitch, 1, 0));
        for (int i = 0; i < numberOfVoices; i++)
        {
            boolean acc = false;
            if (i > 0)
            {
                acc = true;
            }
            provider.addVoice(new Track(tonality, PatternLibrary.getPattern(new Random().nextInt(PatternLibrary.getLength())), acc));
        }
        provider.createVoicePool();
        provider.makeTick();
    }

    public void stopItNow()
    {
        provider.stopIt();
    }
}

