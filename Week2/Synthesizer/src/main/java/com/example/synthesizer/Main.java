package com.example.synthesizer;

import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.util.ArrayList;

public class Main {
    static void testAudioClip() {
        AudioClip clip = new AudioClip();
        for (int i = Short.MIN_VALUE, j = 0; i <= Short.MAX_VALUE; i++, j++) {
            clip.setSample(j, (short) i);
            if (clip.getSample(j) != i) {
                System.out.println("not equal");
            }
            else
                System.out.println("Equal");
        }
    }

    public static  void  main( String [] arg ) throws LineUnavailableException {
        /*
        Testing my clip
         */
        testAudioClip();
        // Get properties from the system about samples rates, etc.
        // AudioSystem is a class from the Java standard library.
        Clip c = AudioSystem.getClip(); // Note, this is different from our AudioClip class.

        /* This is the format that we're following: 44.1 KHz, 16 bits per sample, mono audio,  signed bytes,  littleEndian*/
        AudioFormat format16 =  new AudioFormat(44100, 16, 1, true, false);

        AudioComponent gen = new SineWave(220);
        AudioClip clip = gen.getClip();

        /*  Test to adjust the Volume  */
           Volume MyVolume = new Volume(1.5);
           AudioComponent NewSineWave = new SineWave(220);
           MyVolume.connectInput(NewSineWave);
           AudioClip clip2 = MyVolume.getClip();

       /*  test for mixer, use the already created sine wave, gen and newSineWave */
        Mixer myMixer = new Mixer();
        myMixer.connectInput(NewSineWave);
        myMixer.connectInput(gen);

        MyVolume.connectInput(myMixer);
        AudioClip mixerOut = MyVolume.getClip();


//        c.open(format16, mixerOut.getData(), 0,mixerOut.getData().length);


        /* test for linearRampAudioComponent :
        * create a linearRampAudioComponent obj
        * create a sinewave
        * connect the sinewave to the vol
        * connect the linearRam to the adjustedvolumed sinewave
        * create a clip
        * connect it to the vol
        *
        *  */
        AudioComponent linearRampAudioComponent = new linearRamp(50, 2000);
        AudioComponent linearSineWave = new SineWave(220);
        Volume vol = new Volume (1.5);
        vol.connectInput(linearSineWave);

        linearRampAudioComponent.connectInput(linearSineWave);

        AudioClip linearWaveTestClip = vol.getClip();



        /* Opens the clip, meaning that it should acquire any required system resources and become operational.*/
//        c.open(format16, mixerOut.getData(), 0,mixerOut.getData().length);
        c.open(format16, linearWaveTestClip.getData(), 0,linearWaveTestClip.getData().length);
//        c.open(format16, clip2.getData(), 0,clip2.getData().length);


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
