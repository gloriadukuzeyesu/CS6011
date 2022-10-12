package com.example.synthesizer;

import java.util.ArrayList;

public class Cable {
    ArrayList<AudioComponent> audioComponent_;

    Cable() {}

    // fx to connect all inputs audio components
    public void ConnectAudioComponent (AudioComponent inputs ) {
        audioComponent_.add(inputs);
    }


    // fx to draw a line between inputs to show connection visually





}
