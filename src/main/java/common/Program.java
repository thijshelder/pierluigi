package common;

import counterpoint.Tonality;
import player.MidiHandler;
import rhythmEngine.PatternLibrary;
import rhythmEngine.RhythmEngine;
import utilities.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Program {

	public static void main(String[] args) throws InterruptedException 
	{
		Director pierluigi  = new Director();
        pierluigi.initOrchestra(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
		pierluigi.startSong(Integer.parseInt(args[0]),Integer.parseInt(args[1]));

	}

}
