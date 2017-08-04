package main.test.fixtures;

import static org.junit.Assert.*;

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
