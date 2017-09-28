package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import counterpoint.PunctumContraPunctum;
import counterpoint.TonalUtilities;
import counterpoint.Tonality;
import player.MidiHandler;
import rhythmEngine.PatternLibrary;
import utilities.MathUtils;

public class Voice implements IBeatListener
{
	List<Note> melos = new ArrayList<Note>();
	Note note_now_playing;
	boolean changable;
	int pitch; 
	int velocity = 100;
	int duration = 1;
	Tonality mytonality;
	int numberOfPulse = 1;
	int numberOfChanges = 1;
	int[] pattern;
	boolean accompagnement;
	Note punctumContra = null;
	String name;
	static int noOfVoices;


	
	public Voice(Tonality tonality, int[] pattern, boolean accompagnement)
	{
		mytonality = tonality;
		this.pattern = pattern;
		MidiHandler.openMidiHandler();
		MidiHandler.chProgramChange(1, 1);
		MidiHandler.chProgramChange(1, 0);
		MidiHandler.chProgramChange(1, 2);
		PunctumContraPunctum.setTonality(mytonality);
		TonalUtilities.setTonality(mytonality);
		firstNote();
		Voice.setNoOfVoices();
		name = "voice" +Voice.noOfVoices;

	}

    private static void setNoOfVoices()
    {
        noOfVoices++;
    }
	
	public Voice(int pitch,boolean accompagnement)
	{
		this.pitch = pitch;
		mytonality = new Tonality(melos);
		mytonality.createScale(new Note(pitch, 0,0));
		MidiHandler.openMidiHandler();
		MidiHandler.chProgramChange(221, 1);
		PunctumContraPunctum.setTonality(mytonality);
		TonalUtilities.setTonality(mytonality);
		firstNote();
		this.accompagnement = accompagnement;

	}

	public void setContraPunctum(Note note)
	{
	punctumContra = note;
	}
	public void firstNote()
	{		
		note_now_playing = new Note(pitch, TonalUtilities.findFunction(pitch), 0);
		melos.add(note_now_playing);
	}



	public int contemplateChange(int channelno)

	{
	    int sumOfPattern = MathUtils.getSumOfArray(pattern);
		if (numberOfPulse==sumOfPattern)
		{   numberOfPulse = 0;
		    numberOfChanges = 0;
		    int decider  =new Random().nextInt(PatternLibrary.getLength());
		    changePattern(decider);
		    System.out.println("patternchange for voice " + this.name + " naar pattern "+ decider );
		}
		
		int  notevalue = pattern[(Math.min(Math.max(0, numberOfChanges),pattern.length-1))];
		//System.out.println(" present  notevalue is  " + " "+ notevalue + "voor " + name);
		note_now_playing.setDuration(notevalue);
		boolean change = (note_now_playing.getDuration()==duration);
		if(change)
			{
			System.out.println(numberOfChanges);
			MidiHandler.muteNoteOnChannel(channelno, note_now_playing);
			Note note = note_now_playing;
			
				if(!accompagnement)
					{switch(new Random().nextInt(3) )
					{
					case 0: note_now_playing = note;
							break;
					case 1: note_now_playing = mytonality.stepInterval(note, 1);
							break;
					case 2: note_now_playing = mytonality.stepInterval(note, -1);
							break;
							
					case 3: note_now_playing = mytonality.stepInterval(note, -4);
							break;
					case 4: note_now_playing = mytonality.stepInterval(note, 5);
							break;
					default: note_now_playing  = mytonality.stepInterval(note,MathUtils.getRandomPosOrNeg(-6, 7));}
					 }
					
				else
					{
					note_now_playing = PunctumContraPunctum.makeCounterpoint(punctumContra);
					}

				//System.out.println("pitch is " + TonalUtilities.returnNoteName(note_now_playing.getPitch())+ " voor " + name );
				
				//System.out.println("pitchvalue = " +  note_now_playing.getPitch() + " voor " + name);
				MidiHandler.playNoteOnChannel(channelno, note_now_playing);
				
				melos.add(note_now_playing);
				numberOfChanges++;
				
			    duration =1;
			}
		else
			{
				duration++;
			}
		numberOfPulse++;
		System.out.println("numberOf " +numberOfChanges + " voor " + name);
		//System.out.println("number of pulses "+ numberOfPulse);
		return duration;
	//yeah, that works!
	}
	
	/*public int getNumberOf()
	{
		return numberOf;
	}*/

	public void changePattern(int selector)
	{
		pattern = PatternLibrary.getPattern(selector);
	}

	public Note getTonica()
	{
		return null;
	}
	
	public int getPitch()
	{
		return note_now_playing.getPitch();
	}
	
	public List<Note> getMelos()
	{
		return melos;
	}
	
	public void setPitch(int pitch)
	{
		this.pitch = pitch;
	}
	
	public Object informAllOthers()
	{
		return note_now_playing;
	}
	
	public boolean getAccompagnement()
	{
		return accompagnement;
	}
	
	public void beInformed(Note note)
	{
	punctumContra = note;
	}

}
