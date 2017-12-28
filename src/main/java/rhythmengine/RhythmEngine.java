package rhythmengine;

import java.util.Random;

public final class RhythmEngine

{
	private RhythmEngine()
	{

	}

	public static boolean createRandomNoteValue()
	{
		return new Random().nextBoolean();
	}
	
	public static boolean createFixedNoteValue(int noteLength, int someOtherParameterIAmLikelytoneed)
	{
		return (noteLength == someOtherParameterIAmLikelytoneed);
	}
	
	public static int[] createRandomPattern()
	{
		int length = new Random().nextInt(19);
		int[] pattern = new int[length];
			
					for(int i = 0;i<length;i++)
					{pattern[i] =		
					new Random().nextInt(8);
					}
		
		return pattern;
	}
	
	


	
}
