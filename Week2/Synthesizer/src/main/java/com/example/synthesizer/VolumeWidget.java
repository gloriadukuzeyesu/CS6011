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

import static com.example.synthesizer.SpeakerWidget.SPEAKER_RADIUS;

public class VolumeWidget extends AudioComponentWidgetBase {

    private Slider slider = new Slider(0,2,1);
    private Label title = new Label();
    private  Circle VolumeConnector_;
    public static Circle VolumeOutput_;
    public static double VOLUME_RADIUS_ = 20;


    private String ComponentName_ = "Volume";


    public VolumeWidget(AudioComponent ac, AnchorPane parent, String name) {
        super(ac, parent, name);
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
        volumebar.setAlignment(Pos.CENTER);

        VolumeConnector_ = new Circle(VOLUME_RADIUS_);
        VolumeConnector_.setFill(Color.DARKORCHID);
        VolumeConnector_.setOnMousePressed(e -> startConnection(e, VolumeConnector_));
        VolumeConnector_.setOnMouseDragged(e -> moveConnection(e, VolumeConnector_));
        VolumeConnector_.setOnMouseReleased(e ->endConnection(e, VolumeConnector_));

        volumebar.getChildren().add(VolumeConnector_);
        rightRightSide_.getChildren().add(volumebar);

        HBox VolumeToSpeaker = new HBox();
        VolumeToSpeaker.setAlignment(Pos.CENTER);
        VolumeOutput_ = new Circle(10, Color.BROWN);
        VolumeToSpeaker.getChildren().add(VolumeOutput_);
        centerComponent_.getChildren().add(VolumeToSpeaker);
    }


/*
    public void endConnection(MouseEvent e, Circle outputCircle) {
        Circle speaker = SpeakerWidget.speaker_;
        Bounds SpeakerBounds = speaker.localToScreen(speaker.getBoundsInLocal());
        double distance = Math.sqrt(Math.pow(SpeakerBounds.getCenterX() - e.getScreenX(), 2.0) +
                Math.pow(SpeakerBounds.getCenterY() - e.getScreenY(), 2.0));

        if (distance < SPEAKER_RADIUS) {
            SpeakerWidget.SpeakerWidgets_.add(this);
//            SynthesizeApplication.widgets_.add(this);
            System.out.println("VolumeWidget connected to Speaker");
        } else {
            parent_.getChildren().remove(line_);
            line_ = null;
            SpeakerWidget.SpeakerWidgets_.remove(this);
            SynthesizeApplication.widgets_.remove(this);
        }
        System.out.println("Cut connection between volume and speaker");
    }
*/
    public void handleSlider(MouseEvent e) {
        double value = (double) slider.getValue();
        double roundedValue = Math.round(value * 100.0) /100.0;
        title.setText("Volume " + roundedValue + " ");
        Volume adjustVolume = new Volume(roundedValue);
        adjustVolume.connectInput(audioComponent_);
    }
    public void handleDrag(MouseEvent e) {
        double mouseDelX = e.getSceneX() - mouseStartDragX_;
        double mouseDelY = e.getSceneY() - mouseStartDragY_;
        this.relocate(widgetStartDragX_ + mouseDelX, widgetStartDragY_ + mouseDelY);

        Bounds parentBounds = parent_.getBoundsInParent();
        Bounds bounds = SpeakerWidget.speaker_.localToScene(SpeakerWidget.speaker_.getBoundsInLocal());

        line_.setStartX(bounds.getCenterX() - parentBounds.getMinX());
        line_.setStartY(bounds.getCenterY() - parentBounds.getMinY());
        System.out.println("connected");


    }
    @Override
    public String getComponentName(){
        return ComponentName_;
    }

}
