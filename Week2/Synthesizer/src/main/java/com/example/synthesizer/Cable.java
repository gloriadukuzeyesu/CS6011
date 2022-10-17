package com.example.synthesizer;

import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Cable{
    ArrayList<AudioComponent> audioComponent_;

    // field
    protected Line line_;
    protected Line LineBetweenSineVolume_;
    protected AnchorPane parent_;


    Cable() {}

    // fx to connect all inputs audio components
    public void connectInput(AudioComponent input) {
        audioComponent_.add(input);
    }

    // fx to draw a line between inputs to show connection visually


    private void moveConnectionBetweenVolumeCircles(MouseEvent e, Circle volumeCircle_) {
        Bounds parentBounds = parent_.getBoundsInParent();
        LineBetweenSineVolume_.setEndX(e.getSceneX() - parentBounds.getMinX());
        LineBetweenSineVolume_.setEndY(e.getSceneY() - parentBounds.getMinY());
    }

    private void startBetweenVolumeCircles(MouseEvent e, Circle volumeCircle_) {
        // if a line exists (if there is a line connected to someone else)
        if (LineBetweenSineVolume_ != null) {
            // remove that line so that we can create a new connection
            parent_.getChildren().remove(LineBetweenSineVolume_);
            System.out.println("remove the line");
        }

        Bounds parentBounds = parent_.getBoundsInParent();
        Bounds bounds = volumeCircle_.localToScene(volumeCircle_.getBoundsInLocal());
        LineBetweenSineVolume_ = new Line();
        LineBetweenSineVolume_.setStrokeWidth(1);
        LineBetweenSineVolume_.setStartX(bounds.getCenterX() - parentBounds.getMinX());
        LineBetweenSineVolume_.setStartY(bounds.getCenterY() - parentBounds.getMinY());
        LineBetweenSineVolume_.setEndX(e.getSceneX());
        LineBetweenSineVolume_.setEndY(e.getSceneY());
        // add line to the parent so it can be drawn
        parent_.getChildren().add(LineBetweenSineVolume_);

    }

    private void endConnectionbetweenVolumeCircles(MouseEvent e, Circle volumeCircle) {
        Circle volumeConnector = VolumeWidget.VolumeConnector_;
        Bounds VolumeBounds = volumeConnector.localToScreen(volumeConnector.getBoundsInLocal());
        double distance = Math.sqrt(Math.pow(VolumeBounds.getCenterX() - e.getScreenX(), 2.0) +
                Math.pow(VolumeBounds.getCenterY() - e.getScreenY(), 2.0));

        if (distance < 10) {
            //handle actual connection to speaker
//            SynthesizeApplication.widgets_.remove( this );
        } else {
            parent_.getChildren().remove(line_);
            line_ = null;
        }
//        SynthesizeApplication.VolumeWidgets_.add(this);
    }


}
