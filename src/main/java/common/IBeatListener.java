package common;

import counterpoint.Note;

public interface IBeatListener
{
	void contemplateChange(int channelNo);
	
	Object informAllOthers();
	
	void beInformed(Note note);
	//do something with the result of your peer
	
}
