package RhythmEngine;

public class TimeSignature 

{
	int tempo;
	static int counter;
	static int denominator;
	static int smallestNoteValue;
	
	
	public static int determineSmallestTime(int tempo, int denominator, int smallestNoteValue)// a method we might use to find out what the timer timeout must be.
	{//lots of  intelligent code here
		setSmallestNoteValue(smallestNoteValue);
		int smallestTime = 60000/((tempo/denominator)*smallestNoteValue);
		return smallestTime;
	}
	
	public static int getTotalPulsesPerMeasures()
	{
		return counter*denominator*smallestNoteValue;
	}
	
	public int getSmallestNoteValue()
	{
		return smallestNoteValue;
	}
	
	public static void setSmallestNoteValue(int i)
	{
		smallestNoteValue = i;
	}
}
