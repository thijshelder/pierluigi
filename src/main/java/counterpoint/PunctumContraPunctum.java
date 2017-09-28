package counterpoint;
import common.Note;
import common.Voice;

import java.util.List;
import java.util.Random;

public final class PunctumContraPunctum 
{
	static Tonality tonality;
	
	static int[] consonants = {2,3,4,5};
	
	
	
	public PunctumContraPunctum(List<Voice> voices)
	{
	List<Note> noteList = null; //I might consider finding some default value here.
	for(Voice voice:voices)
	{
		noteList.add(voice.getMelos().get(0));
	}
	tonality = new Tonality(noteList);
	}
	
	public static void setTonality(Tonality tonality)
	{
		PunctumContraPunctum.tonality = tonality;
	}
	
	public void makeAlwaysConsonant()
	{
		boolean upOrDown;
	}
		
	public static Note makeCounterpoint(Note note)
	{
	//System.out.println(tonality.getScale().get(tonality.getScale().indexOf(note.getFunction() + Math.round(note.getPitch()/7))));
		return tonality.getScale().get(Math.min(Math.max(0, note.getFunction() + Math.round((note.getPitch()/12)*7)-consonants[new Random().nextInt(3)]-1), 55));
	}
	
	
}
