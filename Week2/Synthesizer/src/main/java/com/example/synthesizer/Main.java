package com.example.synthesizer;

import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Main {

    public static  void  main( String [] arg ) throws LineUnavailableException {
        // Get properties from the system about samples rates, etc.
        // AudioSystem is a class from the Java standard library.
        Clip c = AudioSystem.getClip(); // Note, this is different from our AudioClip class.

        /* This is the format that we're following: 44.1 KHz, 16 bits per sample, mono audio,  signed bytes,  littleEndian*/
        AudioFormat format16 =  new AudioFormat(44100, 16, 1, true, false);

        AudioComponent gen = new SineWave(220);
        AudioClip clip = gen.getClip();

        /*  Test to adjust the Volume  */
           Volume MyVolume = new Volume(.9);
           AudioComponent NewSineWave = new SineWave(220);
           MyVolume.connectInput(NewSineWave);
           AudioClip clip2 = MyVolume.getClip();


        /* Opens the clip, meaning that it should acquire any required system resources and become operational.*/
        c.open(format16, clip2.getData(), 0,clip2.getData().length);

        System.out.println("About to play ");
        c.start(); // Plays it.
        c.loop(0); // Plays it 2 more times if desired, so 6 seconds total
        while ( c.getFramePosition() < AudioClip.TotalSamples || c.isActive() || c.isRunning() ) {
            // Do nothing while we wait for the note to play.
        }

        System.out.println("Done");
        c.close();


    }
}
