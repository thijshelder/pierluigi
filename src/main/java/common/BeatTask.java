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
		offset++;
	    for(IBeatListener lister:list) {
			lister.beInformed((Note) list.get(Math.min(list.indexOf(lister) +1, list.size()-1)).informAllOthers());
			lister.contemplateChange(Math.min(list.indexOf(lister), 15));
		}
		
	}

	
	public void addListener(IBeatListener listener)
	{
	  list.add(listener);
	}

}
