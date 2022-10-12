package com.example.synthesizer;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class SpeakerWidget implements BaseWidgetAndSpeaker{
    //fields
    public AudioComponent audioComponent_;
    public static Circle speaker_;
    public static ArrayList<AudioComponentWidgetBase> SpeakerWidgets_ = new ArrayList<>();

    public SpeakerWidget () {
    }

    public void CreateSpeakerWidget () {
        speaker_ = new Circle(400, 200, 15);
        speaker_.setFill(Color.BLACK);
        AnchorPane mainWindow =  SynthesizeApplication.mainCanvas_;
        mainWindow.getChildren().add(mainWindow);
    }


    @Override
    public AudioComponent getAudioComponent() {
        return audioComponent_;
    }

}
