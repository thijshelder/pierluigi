package common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.Timer;

public class BeatEvent extends EventObject{
	
	public BeatEvent(Object source, int min_beat_duration)
	{
		super(source);
		ActionListener listener = new ActionListener()
				{public void actionPerformed(ActionEvent event)
				{//System.out.println("Event fired" );
					}
				};
		Timer timer = new Timer(min_beat_duration, listener);
		timer.start();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
