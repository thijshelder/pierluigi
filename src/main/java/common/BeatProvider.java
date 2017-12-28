package common;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;




public final class BeatProvider 
{
	Timer timer = new Timer();
	protected BeatTask task = new BeatTask();
	List<Track> tracks = new ArrayList<>();
	
	public void addVoice(Track track)
	{
		tracks.add(track);
	}
	public void createVoicePool()
	{
		for(Track track : tracks)
		{	
			task.addListener(track);
		}
	}
	
	public void makeTick()
	{
		timer.schedule(task, 0, rhythmengine.TimeSignature.determineSmallestTime(Math.max(70,new Random().nextInt(220)),4,16));
	}
	
	public void stopIt()
	{
		timer.purge();
	}
	
}