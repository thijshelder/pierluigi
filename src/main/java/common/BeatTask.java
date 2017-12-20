package common;

import java.util.*;

public class BeatTask extends TimerTask {
	
	//private List<IBeatListener> listeners = new ArrayList<IBeatListener>();
	
	int offset = 0;
	//controls rhythm. an note duration can be n; so after n ticks the note will have to change and offset is reset to 0;
	List<IBeatListener> list = new ArrayList<IBeatListener>();
	public BeatTask()
	{
		super();
	}
	
	public void run()
	{
		
		//System.out.println("pulse");
		offset++;
		
		for(IBeatListener lister:list)
		{
		//boolean change = (offset%(2*list.indexOf(lister)+1)==0);
		lister.beInformed((Note)list.get(list.indexOf(list)+1).informAllOthers());
		//System.out.println("succes finding note to counterpoint to");
		lister.contemplateChange(Math.min(list.indexOf(lister),15));
		//int b  = lister.contemplateChange(9);
		}
		
	}

	
	public void addListener(IBeatListener listener)
	{
	  list.add(listener);
	}

}
