package counterpoint;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.java.*;
public class Tonality 
{
	static List<Note> scale = new ArrayList<Note>();
	
	Note tonica;
	Note dominant;
	Note subDominant;
	Mode tonality;
	int lowest_note_Value= 30;
	
	public Tonality(List<Note> notes)
	{
		//calculate interval, decide upon a tonality and calculate dominant, subDominant
		//other intervals to be calculated in a separate method. 
		//
		if(notes.size()>1)
		{
		setScale(notes,tonality);
		}
		else if (notes.size()==1)
		{
			createScale(notes.get(0));
		}
		else if (notes.size()==0)
		{
			System.out.println("no note given, default scale" );
			createScale(new Note(48, 1,0));
		}
		
	}
	
	public void setScale(List<Note> notes, Mode tonality)
	{
		Iterator itr = notes.iterator();
		//leave this for later
		for(Note note:notes)
		{
			int p = note.getPitch();
			while(itr.hasNext())
			{
				Note note2 =(Note) itr.next();
				int p2 = note2.getPitch();
				if(p<p2)
				{
					notes.set(notes.indexOf(note2)-1, note);
					
				}
				break;
			}
		}
		
	}
	
	public void setScale(Note note, Mode tonality)
	{
		//make the note tonica
		this.tonica = note;
		this.dominant = new Note(note.getPitch()+7,5,0);
		this.subDominant = new Note(note.getPitch()+5, 4,0);
		
		switch(tonality)
		{
			case Major: 
				//yadeeeyadee
				
			case Minor:
				//YeedaaYeeda
		}
	
	}
	
	public void createScale(Note tonica)
	{
		//creates a major scale and no 
		int lowest_tonica = tonica.getPitch()%12;
		
		
		for(int i = lowest_tonica;(i<=88);i=i+12)
		{//yes I know this is crappy
			int pos = 1;
		
				scale.add(new Note(i, pos,0));
			
				scale.add(new Note(i+2,pos+1,0));
				
				scale.add(new Note(i+4,pos+2,0));
				
				scale.add(new Note(i+5,pos+3,0));
			
					
			pos = 5;
			
				scale.add(new Note(i+7,pos,0));
		
				scale.add(new Note(i+9,pos+1,0));
			
				scale.add(new Note(i+11,pos+2,0));
						
		}
	
}
	
	public List<Note> getScale()
	
	{
		return scale;
	}
	
	public Note stepInterval(Note note, int interval )
	{
		
		return scale.get(Math.max(lowest_note_Value,Math.min((scale.indexOf(note)+interval),scale.size()-1)));
		
	}
}
