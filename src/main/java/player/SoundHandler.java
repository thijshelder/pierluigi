package player;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;


public class SoundHandler {
    public void displayMixerInfo() {
        Mixer.Info[] mixInfo = AudioSystem.getMixerInfo();
        for (Mixer.Info info : mixInfo) {
            System.out.println(info);
        }
    }
}