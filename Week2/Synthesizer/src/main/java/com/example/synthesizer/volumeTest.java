package com.example.synthesizer;

import org.junit.jupiter.api.Test;

class volumeTest {
    @Test
    public void runAllTest() {

        SineWave sineWave = new SineWave( 44100);
        Volume MyVolume = new Volume(0.5);
        MyVolume.connectInput(sineWave);



        /*
        if the volumeScale is less than 1 , the sound is quieter and greater than 1 the scalevolume is max
         */



        //  Connect the sine wave as the input for your volume object,
        //  then get the audio from your volume object and play it.










    }

}