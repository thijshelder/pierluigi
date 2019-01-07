package common;

import counterpoint.PunctumContraPunctum;
import counterpoint.Tonality;
import player.MidiHandler;
import rhythmengine.PatternLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Director {

    private final BeatProvider provider =BeatProvider.getInstance();
    private final List<Note> list = new ArrayList<>();

    private final Tonality tonality = Tonality.getInstance(list);
    ArrayList<int[]> patternList = new ArrayList<>();
    private PunctumContraPunctum punctum;

    public void initOrchestra(int numberOfVoices, int instrument)
    {
        MidiHandler.openMidiHandler();
        for(int i=0;i<=numberOfVoices;i++)
        {
            MidiHandler.chProgramChange(new Random().nextInt(127),
                   i);// i);
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
            provider.addVoice(new Voice(tonality, PatternLibrary.getPattern(new Random().nextInt(PatternLibrary.getLength())), acc));
        }
        provider.createVoicePool();
        provider.makeTick();
        punctum = new PunctumContraPunctum();

     }

     public List<Note> getNotes()
     {
         List<Note> notes = new ArrayList<>();
         for(Voice voice:provider.voices)
         {
             voice.getMelos().get(voice.getMelos().size());
         }
         return notes;
     }

    public void stopItNow()
    {
        provider.stopIt();
    }
}

