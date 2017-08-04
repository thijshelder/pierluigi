package main.java;


import java.util.*;

import RhythmEngine.RhythmEngine;
import RhythmEngine.TimeSignature;



public final class BeatProvider 
{
	//observer pattern and we know it
	
	Timer timer = new Timer();
	protected BeatTask task = new BeatTask();
	List<Voice> voices = new ArrayList<Voice>();
	
	public void addVoice(Voice voice)
	{
		voices.add(voice);
	}
	public void createVoicePool()
	{
		for(Voice voice:voices)
		{	
			task.addListener(voice);
		}
	}
	
	public void makeTick()
	{
		//code to periodically send a pulse of some sort. An event would be awesome
		
		{
		timer.schedule(task, 0, TimeSignature.determineSmallestTime(120,4,16));
		}
	
	}
	
	public void stopIt()
	{
		timer.purge();
	}
	
}