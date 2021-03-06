package common;

import java.util.*;

public class BeatTask extends TimerTask {

    private int offset = 0;
    private final List<IBeatListener> list = new ArrayList<>();

    public BeatTask() {
        super();
    }

    public void run() {
        offset++;
        for (IBeatListener lister : list) {
            lister.beInformed((Note) list.get(Math.min(list.indexOf(lister) + 1, list.size() - 1)).informAllOthers());
            lister.contemplateChange(Math.min(list.indexOf(lister), 15));
        }
     }


    public void addListener(IBeatListener listener) {
        list.add(listener);
    }

}
