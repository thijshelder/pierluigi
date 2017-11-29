package fixtures;

import org.junit.Test;
import player.SoundHandler;

public class TestSoundHandler
{
    @Test
    public void testMixerInfo()
    {
      SoundHandler handler = new SoundHandler();
      handler.displayMixerInfo();
    }
}
