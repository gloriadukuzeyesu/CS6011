package com.example.synthesizer;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import static com.example.synthesizer.SpeakerWidget.SPEAKER_RADIUS;
import static com.example.synthesizer.VolumeWidget.VOLUME_RADIUS_;

public class AudioComponentWidgetBase extends Pane implements BaseWidgetAndSpeaker {
    // fields
    protected AudioComponent audioComponent_;
    public static AnchorPane parent_;
    public static HBox baseLayout_;
    protected VBox centerComponent_;
    protected double mouseStartDragX_, mouseStartDragY_, widgetStartDragX_, widgetStartDragY_;
    protected Line line_ = new Line();
    protected VBox rightRightSide_;

    AudioComponentWidgetBase() {}

    AudioComponentWidgetBase(AudioComponent ac, AnchorPane parent, String name) {
        audioComponent_ = ac;
        parent_ = parent;
        baseLayout_ = new HBox();
        baseLayout_.setStyle("-fx-border-color: black; -fx-border-image-width: 10; -fx-background-color: seashell");

        /* Right side of the widget */
        rightRightSide_ = new VBox();
        Button closeButton = new Button();
        closeButton.setText("X");
        closeButton.setOnAction(e -> closeWidget());
        closeButton.setTextFill(Color.RED);

        rightRightSide_.getChildren().add(closeButton);
        rightRightSide_.setAlignment(Pos.CENTER);
        rightRightSide_.setPadding(new Insets(6));
        rightRightSide_.setSpacing(5);
        ;

        /***** center portion of the widget *****/
        centerComponent_ = new VBox();
        centerComponent_.setStyle("-fx-background-color: lavender");
        centerComponent_.setAlignment(Pos.CENTER);

        centerComponent_.setOnMousePressed(e -> startDrag(e));
        centerComponent_.setOnMouseDragged(e -> handleDrag(e));

        baseLayout_.getChildren().add(centerComponent_);

        baseLayout_.getChildren().add(rightRightSide_);
        // add the baseLayout to the pane. baseLayout is the object of Pane
        this.getChildren().add(baseLayout_);
        parent_.getChildren().add(this);
    }

    public void handleDrag(MouseEvent e ) {

    }
    /*public void handleDrag(MouseEvent e) {
        double mouseDelX = e.getSceneX() - mouseStartDragX_;
        double mouseDelY = e.getSceneY() - mouseStartDragY_;
        this.relocate(widgetStartDragX_ + mouseDelX, widgetStartDragY_ + mouseDelY);

        Bounds parentBounds = parent_.getBoundsInParent();
        Bounds bounds = SineWaveWidget.OutputCircle_.localToScene(SineWaveWidget.OutputCircle_.getBoundsInLocal());

        if( line_ != null ) {
            line_.setStartX(bounds.getCenterX() - parentBounds.getMinX());
            line_.setStartY(bounds.getCenterY() - parentBounds.getMinY());
            System.out.println("dragging the widget and the line");
        }


    }*/

    public void startDrag(MouseEvent e) {
        mouseStartDragX_ = e.getSceneX();
        mouseStartDragY_ = e.getSceneY();
        widgetStartDragX_ = this.getLayoutX();
        widgetStartDragY_ = this.getLayoutY();
    }

  /* this fx will remove the widget from the parent, and remove it from all connected widgets, remove  the audio components if its sinewave connected to the
    volume widget */
    public void closeWidget() {
        parent_.getChildren().remove(this);
        System.out.println("widget has been removed");
        if (line_ != null) {
            parent_.getChildren().remove(line_);
            System.out.println("remove the line"); // remove that line so that we can create a new connection
        }
        SynthesizeApplication.widgets_.remove(this); // spaghetti code.
        SpeakerWidget.SpeakerWidgets_.remove(this);
        VolumeWidget.AdjustVolume_.removeInput(audioComponent_);
    }

    public void startConnection(MouseEvent e, Circle outputCircle) {
        if (line_ != null) {
            // remove that line so that we can create a new connection
            parent_.getChildren().remove(line_);
            SpeakerWidget.SpeakerWidgets_.remove(this);
            SynthesizeApplication.widgets_.remove(this);
            System.out.println("remove the line");
        }
        Bounds parentBounds = parent_.getBoundsInParent();
        Bounds bounds = outputCircle.localToScene(outputCircle.getBoundsInLocal());
        line_ = new Line();
        line_.setStrokeWidth(4);
        line_.setStartX(bounds.getCenterX() - parentBounds.getMinX());
        line_.setStartY(bounds.getCenterY() - parentBounds.getMinY());
        line_.setEndX(e.getSceneX());
        line_.setEndY(e.getSceneY());
        parent_.getChildren().add(line_);
        System.out.println("start connection");
    }

    public void moveConnection(MouseEvent e, Circle outputCircle) {
        Bounds parentBounds = parent_.getBoundsInParent();
        line_.setEndX(e.getSceneX() - parentBounds.getMinX());
        line_.setEndY(e.getSceneY() - parentBounds.getMinY());
        System.out.println("move connection");
    }

    public void endConnection(MouseEvent e, Circle outputCircle) {

        Circle speaker = SpeakerWidget.speaker_;
        Bounds SpeakerBounds = speaker.localToScreen(speaker.getBoundsInLocal());
        double distanceToSpeaker = Math.sqrt(Math.pow(SpeakerBounds.getCenterX() - e.getScreenX(), 2.0) +
                Math.pow(SpeakerBounds.getCenterY() - e.getScreenY(), 2.0));

        Circle volumeReceiver = VolumeWidget.VolumeOutput_;
        Bounds Volume_Receiver_Bounds = volumeReceiver.localToScreen(volumeReceiver.getBoundsInLocal());
        double distanceToVolume = Math.sqrt(Math.pow(Volume_Receiver_Bounds.getCenterX() - e.getScreenX(), 2.0) +
                Math.pow(Volume_Receiver_Bounds.getCenterY() - e.getScreenY(), 2.0));

        if (distanceToSpeaker < SPEAKER_RADIUS) {
            SpeakerWidget.SpeakerWidgets_.add(this);
            System.out.println("sineWave widget connected to Speaker");
        } else if (distanceToVolume < VOLUME_RADIUS_) {
            VolumeWidget.AdjustVolume_.connectInput(audioComponent_);
            System.out.println("SinewaveWidget connected to VolumeWidget");
        } else {
            parent_.getChildren().remove(line_);
            line_ = null;
            SpeakerWidget.SpeakerWidgets_.remove(this);
            SynthesizeApplication.widgets_.remove(this);
            VolumeWidget.AdjustVolume_.removeInput(audioComponent_);

        }
    }

    public AudioComponent getAudioComponent() {
        return audioComponent_;
    }

}
