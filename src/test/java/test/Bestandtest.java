package test;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class Bestandtest {

    @Test
    public void kijkRondOpjeBestandssysteem()
    {
        try {
            URL url = new URL("file:///C:/Japps/Pierluigi/MyEmptyPage.html");
        }

        catch(MalformedURLException e)
        {
            Logger log =Logger.getGlobal();
            e.printStackTrace();
        }
    }
}
