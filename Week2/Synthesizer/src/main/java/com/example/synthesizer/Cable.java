package com.example.synthesizer;

import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.util.ArrayList;

import static com.example.synthesizer.SpeakerWidget.SPEAKER_RADIUS;


public class Cable {
    ArrayList<AudioComponent> audioComponent_;
    protected AnchorPane parent = AudioComponentWidgetBase.parent_;
//    protected Line line = AudioComponentWidgetBase.line_;
    protected AudioComponentWidgetBase audioComponentWidgetBase = new AudioComponentWidgetBase();

    Cable() {}

    // fx to connect all inputs audio components
    public void connectInput(AudioComponent input) {
        audioComponent_.add(input);
    }

    // fx to draw a line between inputs to show connection visually
  /*  public void startConnection(MouseEvent e, Circle outputCircle) {
        // if a line exists (if there is a line connected to someone else)
        if (line != null) {
            // remove that line so that we can create a new connection
            parent.getChildren().remove(line);
            System.out.println("remove the line");
        }
        Bounds parentBounds = parent.getBoundsInParent();
        Bounds bounds = outputCircle.localToScene(outputCircle.getBoundsInLocal());
        line = new Line();
        line.setStrokeWidth(2);
        line.setStartX(bounds.getCenterX() - parentBounds.getMinX());
        line.setStartY(bounds.getCenterY() - parentBounds.getMinY());
        line.setEndX(e.getSceneX());
        line.setEndY(e.getSceneY());
        // add line to the parent so it can be drawn
        parent.getChildren().add(line);
    }

    public  void moveConnection(MouseEvent e, Circle outputCircle) {
        Bounds parentBounds = parent.getBoundsInParent();
        line.setEndX(e.getSceneX() - parentBounds.getMinX());
        line.setEndY(e.getSceneY() - parentBounds.getMinY());
    }

    public void endConnection(MouseEvent e, Circle outputCircle) {
        Circle speaker = SpeakerWidget.speaker_;
        Bounds SpeakerBounds = speaker.localToScreen(speaker.getBoundsInLocal());
        double distance = Math.sqrt(Math.pow(SpeakerBounds.getCenterX() - e.getScreenX(), 2.0) +
                Math.pow(SpeakerBounds.getCenterY() - e.getScreenY(), 2.0));
        System.out.println("DEBUG: on endconnection, distance: " + distance);
        if (distance < SPEAKER_RADIUS) { // if there is a connection add the widget to speaker
            SpeakerWidget.SpeakerWidgets_.add (audioComponentWidgetBase);
        } else {
            parent.getChildren().remove(line);
            line = null;
            SpeakerWidget.SpeakerWidgets_.remove(audioComponentWidgetBase);
        }
    }*/

}
