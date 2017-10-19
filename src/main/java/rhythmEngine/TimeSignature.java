package rhythmEngine;

public class TimeSignature 

{
	int tempo;
	static int counter;
	static int denominator;
	static int smallestNoteValue;
	
	public static int determineSmallestTime(int tempo, int denominator, int smallestNoteValue)// a method we might use to find out what the timer timeout must be.
	{//lots of  intelligent code here
		setSmallestNoteValue(smallestNoteValue);
	    return 60000/((tempo/denominator)*smallestNoteValue);
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
