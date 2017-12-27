package common;

import counterpoint.Tonality;
import rhythmEngine.PatternLibrary;
import rhythmEngine.RhythmEngine;
import utilities.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Program {

	public static void main(String[] args) throws InterruptedException 
	{	Director.initOrchestra(6,51);
		Director pierluigi  = new Director();
		pierluigi.startSong(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
	}

}
