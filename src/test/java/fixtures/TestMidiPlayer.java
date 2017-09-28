package fixtures;

import org.junit.Test;

import player.MidiHandler;

public class TestMidiPlayer {

	@Test
	public void test() 
	{
		MidiHandler.openMidiHandler();
		MidiHandler.viewInstruments();
		
	}

}
