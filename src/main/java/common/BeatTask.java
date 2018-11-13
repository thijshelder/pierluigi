package common;

import java.util.*;

public class BeatTask extends TimerTask {

    int offset = 0;
    List<IBeatListener> list = new ArrayList<>();

    public BeatTask() {
        super();
    }

    public void run() {
        offset++;
        for (IBeatListener lister : list) {
            lister.beInformed((Note) list.get(Math.min(list.indexOf(lister) + 1, list.size() - 1)).informAllOthers());
            lister.contemplateChange(Math.min(list.indexOf(lister), 15));
        }
        System.out.println("ticks passed : " + offset);

    }


    public void addListener(IBeatListener listener) {
        list.add(listener);
    }

}
