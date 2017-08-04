package main.java;

public class Note 
{
	//lets for now assume that we will use midi. Even if not, any program based on western tone systems is unlikely to use more than 88 different pitches
	int pitch; //0 to 88, if
	//velocity idem. we might want to use a function here, so that it is able to vary over time, but for now we're ok.
	int function;
	int duration;
	
	public Note(int pitch, int function, int duration)// de noemer van de nootwaarde (halve noot wordt dus 2)
	{
		this.pitch = pitch;
		this.function = function;
		this.duration = duration;
		//anything rhytmic should be handled elsewhere 
	}
	
	public int getPitch()
	{
		return pitch;
	}
	
	public int getFunction()
	{
		return function;
	}
	
	public int getDuration()
	{
	return duration;
	}
	
	public void setDuration(int duration)
	{
		this.duration = duration;
	}
	
	public void setPitch(int pitch)
	{
		this.pitch = pitch;
	}
}