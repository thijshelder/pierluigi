package melodieur;

import java.util.*;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class Melodieur {
	
	int tonica;
    private static boolean DEBUG = true;
    boolean carry; 
 
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }

    public final int Toninica() {        
        int octaver;
        Random r = new Random();
        Random p = new Random();
        octaver = (p.nextInt(4) * 10) + 36;
        tonica = r.nextInt(12) + octaver;
        return tonica;
        
    }
    
    public int NextNote() {
        
        int uitnote;
        int trap[];
        trap = new int[8];
        int tree;
        
        trap[0] = tonica;
        trap[1] = tonica + 2;
        trap[2] = tonica + 4;
        trap[3] = tonica + 5;
        trap[4] = tonica + 7;
        trap[5] = tonica + 9;
        trap[6] = tonica + 11;
        trap[7] = tonica + 12;
        
        Random l = new Random();
        tree = l.nextInt(7);
        uitnote = trap[tree];
        
        return uitnote;
    }

    public int Contrapunctum(int innote) {
        
        int comex = 0;
        int dux;
        int dorem;
        boolean octaver;        
        
        dux = innote;
        Random r = new Random();
        dorem = r.nextInt(3);        
        octaver = r.nextBoolean();
        
        if (octaver = true) {
            dux = dux - 12;
        }
        if (dux == tonica || dux == tonica + 5) {
            comex = dux + 4;
        } else if (dux == tonica + 5) {
            comex = dux + 3;
        } else {
            comex = dux + 7;
        }        
        System.out.println(comex + ", " + octaver + ", " + dux+", "+carry);
        return comex;
    }
    
    public void Playa(int nput1) {
        int nextnote;        
        int nChannelNumber;        
        int duur1;
        int duur2;
        int i;
        int contra;
        
        
        Thread t1 = new Thread();
        Thread t2 = new Thread();
        
       
        nChannelNumber = nput1 - 1;
        nChannelNumber = Math.min(15, Math.max(0, nChannelNumber));
        
        Synthesizer synth = null;
        try {
            synth = MidiSystem.getSynthesizer();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
         
        }


        /*
         *	Of course, we have to open the synthesizer to
         *	produce any sound for us.
         */
        try {
            synth.open();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        }

        /*
         *	Turn the note on on MIDI channel 1.
         *	(Index zero means MIDI channel 1)
         */
        t1.run();
        nextnote = Toninica();
        
        
        while(carry = true) {
            MidiChannel[] channels = synth.getChannels();
            MidiChannel channel = channels[nChannelNumber];
            
            Random r = new Random();
            duur1 = (r.nextInt(8) * 200);
              
            try {
                
                channel.noteOn(nextnote, 127);
                Thread.sleep(duur1);
            } catch (InterruptedException e) {
            }
            channel.noteOff(nextnote);
            nextnote = NextNote();
        
        t2.run();
        contra = Contrapunctum(nextnote);
        
        
    
            
            Random s = new Random();
            duur2 = (s.nextInt(8) * 200);
        try {
               
                channel.noteOn(contra, 127);
                t2.sleep(duur2);
            }
            catch (InterruptedException e){}
            
            channel.noteOff(contra);
        }    
        synth.close();  
        }
        
                     
       



            /*
             *	Wait for the specified amount of time
             *	(the duration of the note).
             */
            

            /*
             *	Turn the note off.
             */
      public void sjostoppah(){
          carry = false;
           }      

}
