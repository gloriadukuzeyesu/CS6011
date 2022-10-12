package com.example.synthesizer;

import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VolumeWidget extends AudioComponentWidgetBase {

    private Slider slider = new Slider(0,2,1);
    private Label title = new Label();
    public static  Circle VolumeConnector_;

    public VolumeWidget (AudioComponent ac, AnchorPane parent, String name, double xlocation, double ylocation) {
        super(ac, parent, name, xlocation, ylocation);
    }

    public void CreateVolumeWidget() {
        title.setMouseTransparent(true);
        slider.setOnMouseDragged(e-> handleSlider(e));
        title.setText("Volume 1");
        slider.setPadding(new Insets(6));
        centerComponent_.getChildren().add(title);
        centerComponent_.getChildren().add(slider);

        // Circle for the volume widget
        VBox volumebar = new VBox();
//        volumebar.setStyle("-fx-background-color: purple");
        VolumeConnector_ = new Circle(30);
        VolumeConnector_.setFill(Color.DARKORCHID);
        volumebar.getChildren().add(VolumeConnector_);
        baseLayout_.getChildren().add(volumebar);

    }

    public void handleSlider(MouseEvent e) {
        int value = (int) slider.getValue();
        title.setText("Volume " + value + " ");
        Volume volume = new Volume(value);
        volume.connectInput(super.audioComponent_);
    }



}
