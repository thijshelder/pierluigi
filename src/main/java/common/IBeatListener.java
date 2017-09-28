package common;

public interface IBeatListener 
{
	int contemplateChange(int no_of_channel);
	
	Object informAllOthers();
	
	void beInformed(Note note);
	//do something with the result of your peer
	
}
