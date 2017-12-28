package player;

import counterpoint.Note;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;



public final class MidiHandler
{
	static MidiDevice synth = null;
	static MidiChannel[] channels = null;

	private MidiHandler()
	{}
	
	public static void openMidiHandler()
	
	{	
		try
		{
			MidiDevice.Info[] infos =  MidiSystem.getMidiDeviceInfo();
			synth = MidiSystem.getMidiDevice(infos[0]);
			synth.getDeviceInfo();

			if(synth instanceof Synthesizer)
			{
			    channels = ((Synthesizer) synth).getChannels();
			}
			else
			{
				System.exit(1);
			}
			synth.open();
		}
		catch(MidiUnavailableException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	
	
	public static void viewInstruments()
	{
		Instrument[] instruments = ((Synthesizer) synth).getAvailableInstruments();
	}
	
	public static void chProgramChange(int inst, int channelno)
	{
		channels[channelno].programChange(1,inst);
	}
	
	
	public static void playNoteOnChannel(int channelno, Note note)
	{
		channels[channelno].noteOn(note.getPitch(), 60);//we'll fix velocity later
	}
	
	public static void muteNoteOnChannel(int channelno, Note note)
	{
		channels[channelno].noteOff(note.getPitch());
	}
	
}
