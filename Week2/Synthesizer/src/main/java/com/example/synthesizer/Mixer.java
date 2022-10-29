package com.example.synthesizer;

import java.util.ArrayList;

public class Mixer implements AudioComponent {
    ArrayList<AudioComponent> inputs;

    Mixer () {
        inputs = new ArrayList<>();
    }

    @Override
    public AudioClip getClip() {
        AudioClip OutPutClip = new AudioClip();
//        AudioClip clip = new AudioClip();

        for (AudioComponent input : inputs) {
            AudioClip clip = input.getClip();
            for (int j = 0; j < AudioClip.TotalSamples; j++) {
                OutPutClip.setSample(j, (short) (OutPutClip.getSample(j) + clip.getSample(j)));
            }
        }
        return OutPutClip; // TODO call the clamping as here
    }

    @Override
    public boolean hasInput() {
        return true;
    }

    @Override
    public void connectInput(AudioComponent input) {
        inputs.add(input);
    }
}

