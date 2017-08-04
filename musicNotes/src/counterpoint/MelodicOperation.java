package counterpoint;

import main.java.Note;

import java.util.ArrayList;
import java.util.List;

public final class MelodicOperation 
{
	public List<Note> createRetrogade(List<Note> melos)
	{
		
		List<Note> solem = new ArrayList<Note>();
		for(int i = 0;i<melos.size();i++)
		{
			solem.add(melos.get(melos.size()-(i+1)));
		}
		return solem;
	}

	public List<Note> createMirror(List<Note> melos)
	{
		List<Note> wejoz = new ArrayList<Note>();
		wejoz.add(melos.get(0));
		for(int i = 0;i<melos.size()-1;i++)
		{
			int diff = (
					melos.get(i).getPitch() - 
					melos.get(i+1).getPitch()
					);
			diff = -diff;
			wejoz.add(new Note(melos.get(i).getPitch()+diff,0,0));
		}
		return wejoz;
	}
}

