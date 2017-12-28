package common;

public class Program {

	public static void main(String[] args)
	{
		Director pierluigi  = new Director();
        pierluigi.initOrchestra(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
		pierluigi.startSong(Integer.parseInt(args[0]),Integer.parseInt(args[1]));

	}

}
