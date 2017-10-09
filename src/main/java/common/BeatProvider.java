package common;


import java.util.*;

import rhythmEngine.RhythmEngine;




public final class BeatProvider 
{
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
		timer.schedule(task, 0, rhythmEngine.TimeSignature.determineSmallestTime(120,4,16));
	}
	
	public void stopIt()
	{
		timer.purge();
	}
	
}