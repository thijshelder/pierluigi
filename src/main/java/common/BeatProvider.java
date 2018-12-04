package common;


import counterpoint.PunctumContraPunctum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;




public final class BeatProvider 
{
	Timer timer = new Timer();
	protected BeatTask task = new BeatTask();
	List<Voice> voices = new ArrayList<>();
	static BeatProvider Instance;

	public static BeatProvider getInstance()
	{
		if(Instance==null)
		{
		    Instance = new BeatProvider();
		}
    	return Instance;

	}
	
	public void addVoice(Voice voice)
	{
		voices.add(voice);
	}

	public void createVoicePool()
	{
		for(Voice voice:voices) {
			task.addListener(voice);
		}
	}
	
	public void makeTick()
	{
		timer.schedule(task, 0, rhythmengine.TimeSignature.determineSmallestTime(Math.max(70,new Random().nextInt(220)),4,16));
	}

	public List<Voice> getVoices()
	{
		return voices;
	}
	
	public void stopIt()
	{
		timer.purge();
	}
	
}