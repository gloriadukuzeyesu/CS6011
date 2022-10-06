package com.example.synthesizer;
import static java.lang.Math.PI;
import static java.lang.Math.sin;


public class linearRamp implements AudioComponent {
    int Start = 50;
    int Stop = 2000;
    AudioClip inputClip = new AudioClip();
    AudioClip OutPutClip = new AudioClip();

    linearRamp( int start, int stop) {
        this.Start = start;
        this.Stop = stop;
    }

    @Override
    public AudioClip getClip() {
        int phase = 0;
        for ( int i =0; i< AudioClip.TotalSamples; i++){
            phase+=2 * PI * inputClip.getSample(i) / AudioClip.sampleRate;
            OutPutClip.setSample(i, (short) (Short.MAX_VALUE * sin(phase)));
        }
        return OutPutClip;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {
        inputClip = input.getClip();
    }
}

