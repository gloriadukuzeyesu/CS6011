package com.example.synthesizer;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class SpeakerWidget implements BaseWidgetAndSpeaker {
    //fields
    public AudioComponent audioComponent_;
    public static Circle speaker_;
    public static double SPEAKER_RADIUS = 20;
    public static ArrayList<AudioComponentWidgetBase> SpeakerWidgets_ = new ArrayList<>();
    public static ArrayList<AudioComponent> SpeakerAudioComponents_ = new ArrayList<>(); // maybe I might need it later

    public SpeakerWidget () {
    }

    public void CreateSpeakerWidget () {
        speaker_ = new Circle(650, 400, SPEAKER_RADIUS, Color.BLACK);
        AnchorPane mainWindow =  SynthesizeApplication.mainCanvas_;
        mainWindow.getChildren().add(speaker_);
    }

    public void connectInput(AudioComponent input) {
        SpeakerAudioComponents_.add(input);
    }

    @Override
    public AudioComponent getAudioComponent() {
        return audioComponent_;
    }

}
