package counterpoint;


import common.Note;

public final class TonalUtilities
{
	private static final String[] noteNames = {"C", "D", "E", "F", "G", "A", "B" };
	private static Tonality tonality;

	private TonalUtilities()
	{}
	
	public static String returnNoteName(int pitch)
	{
		for(Note note:tonality.getScale())
		{
			if(note.getPitch() == pitch)
			{
				return noteNames[note.getFunction()-1];
			}
		}
		return "Z";
	}
	
	public static void setTonality(Tonality tonality)
	{
		TonalUtilities.tonality = tonality;
	}
	
	public static int findFunction(int pitch)
	{
		for(Note note:tonality.getScale())
		{
			if(note.getPitch() == pitch)
			{
				return note.getFunction();
			}
		}
		return 0;
	}

}
