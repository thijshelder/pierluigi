package player;

import common.Note;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;



public final class MidiHandler 
{
	static MidiDevice synth = null;
	static MidiChannel[] channels = null;
	
	public static void openMidiHandler()
	
	{	
		try
		{
			MidiDevice.Info[] infos =  MidiSystem.getMidiDeviceInfo();
			for(Info info:infos)
					{
						System.out.println(info.getDescription());
					}
			synth = MidiSystem.getMidiDevice(infos[0]);
			
			synth.getDeviceInfo();

			System.out.println(synth.getDeviceInfo());
			if(synth instanceof Synthesizer)
			{
			channels = ((Synthesizer) synth).getChannels();
			}
			else
			{
				System.out.println("that is not a synthesizer");
				System.exit(1);
			}
			synth.open();
		}
		catch(MidiUnavailableException e)
		{
			System.out.println("midi unavailable on this system, please check");
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	
	
	public static void viewInstruments()
	{
		Instrument[] instruments = ((Synthesizer) synth).getAvailableInstruments();
		int i = 0;
		for(Instrument instrument:instruments)
		{
			System.out.println(instrument.getName() + " "+ instrument.hashCode() + " "+ i);
			i++;
		}
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
