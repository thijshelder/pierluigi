package player;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;

import static javax.sound.sampled.AudioSystem.getMixerInfo;


public class SoundHandler
{
public void displayMixerInfo()
{

    Mixer.Info[] mixInfo = AudioSystem.getMixerInfo();
    for(Mixer.Info info: mixInfo)
    {
     System.out.println(info)   ;
    }

}


}