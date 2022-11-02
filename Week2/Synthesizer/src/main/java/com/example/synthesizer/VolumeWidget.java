package com.example.synthesizer;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VolumeWidget extends AudioComponentWidgetBase {

    private final Slider slider = new Slider(0,2,1);
    private final Label title = new Label();
    private  Circle VolumeConnector_;
    public static Circle VolumeOutput_;
    public static double VOLUME_RADIUS_ = 20;
    protected static Volume AdjustVolume_;

    public VolumeWidget(Volume ac, AnchorPane parent, String name) {
        super(ac, parent, name);
        AdjustVolume_ = ac;
    }

    public void CreateVolumeWidget() {
        title.setMouseTransparent(true);
        slider.setOnMouseDragged(e-> handleSlider(e));
        title.setText("Volume 1");
        slider.setPadding(new Insets(6));
        centerComponent_.getChildren().add(title);
        centerComponent_.getChildren().add(slider);

        // Circle for the volume widget
        VBox volumeBox = new VBox();
        volumeBox.setAlignment(Pos.CENTER);

        VolumeConnector_ = new Circle(VOLUME_RADIUS_);
        VolumeConnector_.setFill(Color.DARKORCHID);
        VolumeConnector_.setOnMousePressed(e -> startConnection(e, VolumeConnector_));
        VolumeConnector_.setOnMouseDragged(e -> moveConnection(e, VolumeConnector_));
        VolumeConnector_.setOnMouseReleased(e ->endConnection(e, VolumeConnector_));

        volumeBox.getChildren().add(VolumeConnector_);
        rightRightSide_.getChildren().add(volumeBox);

        HBox VolumeToSpeaker = new HBox();
        VolumeToSpeaker.setAlignment(Pos.CENTER);
        VolumeOutput_ = new Circle(10, Color.BROWN);
        VolumeToSpeaker.getChildren().add(VolumeOutput_);
        centerComponent_.getChildren().add(VolumeToSpeaker);
    }

    /* this fx enables the slider to move while adjusting the volume of the input audio component*/
    public void handleSlider(MouseEvent e) {
        double value = slider.getValue();
        double roundedValue = Math.round(value * 100.0) /100.0;
        title.setText("Volume " + roundedValue + " ");
        AdjustVolume_.setVolumeScale(roundedValue);
    }

    /*this fx enables the volume widget to move around the canvas. It finds the widget x and y location and the x and y locations
     of the  mouse on scene.*/
    @Override
    public void handleDrag(MouseEvent e) {
        double mouseDelX = e.getSceneX() - mouseStartDragX_;
        double mouseDelY = e.getSceneY() - mouseStartDragY_;
        this.relocate(widgetStartDragX_ + mouseDelX, widgetStartDragY_ + mouseDelY); // the widget relocated to this Updated new location

        Bounds parentBounds = parent_.getBoundsInParent();
        Bounds bounds = VolumeConnector_.localToScene(VolumeConnector_.getBoundsInLocal());

        if ( line_ != null ) {
            line_.setStartX(bounds.getCenterX() - parentBounds.getMinX());
            line_.setStartY(bounds.getCenterY() - parentBounds.getMinY());
            System.out.println("connected");
        }



    }

    public AudioComponent getAudioComponent() {
        return AdjustVolume_;
    }

}
