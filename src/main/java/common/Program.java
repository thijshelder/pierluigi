package common;

import counterpoint.Tonality;
import rhythmEngine.PatternLibrary;
import rhythmEngine.RhythmEngine;
import utilities.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Program {

	public static void main(String[] args) throws InterruptedException 
	{	
		BeatProvider provider = new BeatProvider();
		List<Note> list = new ArrayList<Note>();
		list.add(new Note(Integer.parseInt(args[0]),1,0));
		Tonality tonality = new Tonality(list);
		ArrayList<int[]> patternList = new ArrayList<int[]>();

	//	for(int i= 1;i<Integer.parseInt(args[0]);i++)
        for(int i =0;i<3;i++)
        {
            provider.addVoice(new Voice(tonality, PatternLibrary.getPattern(new Random().nextInt(PatternLibrary.getLength())), false));
        }


		/*Voice voice3 = new Voice(tonality,patrn3, false);
		provider.addVoice(voice3)*/;
		//Voice voice= new Voice(48,patrn3,true);
		//provider.addVoice(voice);
		
		//provider.addVoice(voice2);
		//provider.addVoice(voice3);
		provider.createVoicePool();
		provider.makeTick();
		provider.stopIt();
	}

}
