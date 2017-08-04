package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.time.StopWatch;

import counterpoint.Tonality;

public class Program {

	public static void main(String[] args) throws InterruptedException 
	{	
		BeatProvider provider = new BeatProvider();
		List<Note> list = new ArrayList<Note>();
		list.add(new Note(Integer.parseInt(args[1]),1,0));
		Tonality tonality = new Tonality(list);
		ArrayList<int[]> patternList = new ArrayList<int[]>();
		
		int[] patrn1 = {4,4,2,2,2,2,8};
		patternList.add(patrn1);
		int[] patrn2 = {4,4,8,6,2,4};
		patternList.add(patrn2);
		int[] patrn3 = {2,2,4,2,2,4,4,4};
		patternList.add(patrn3);
		int[] patrn4 = {8,8};
		patternList.add(patrn4);
		int[] patrn5 = RhythmEngine.RhythmEngine.createRandomPattern();
		patternList.add(patrn5);
	//	for(int i= 1;i<Integer.parseInt(args[0]);i++)
		Voice voice1 = new Voice(tonality,patrn1,false);
		provider.addVoice(voice1);
		Voice voice2 = new Voice(tonality,patrn2,true);
		provider.addVoice(voice2);
		Voice voice3 = new Voice(tonality,patrn3, false);
		provider.addVoice(voice3);
		//Voice voice= new Voice(48,patrn3,true);
		//provider.addVoice(voice);
		
		//provider.addVoice(voice2);
		//provider.addVoice(voice3);
		provider.createVoicePool();
		provider.makeTick();
		provider.stopIt();
	}

}
