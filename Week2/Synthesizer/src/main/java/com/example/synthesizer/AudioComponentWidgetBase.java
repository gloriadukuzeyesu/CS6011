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

public class AudioComponentWidgetBase extends Pane implements BaseWidgetAndSpeaker {
    // fields
    protected AudioComponent audioComponent_;
    protected AnchorPane parent_;
//    protected String name_;
    public static HBox baseLayout_;
    protected VBox centerComponent_;
//    protected Line line_;
    protected double mouseStartDragX_, mouseStartDragY_, widgetStartDragX_, widgetStartDragY_;
    public static VBox leftSide_;
    public Circle volumeCircle_;

//    protected Line LineBetweenSineVolume_;

    AudioComponentWidgetBase  () {}

    //constuctor
    AudioComponentWidgetBase(AudioComponent ac, AnchorPane parent, String name, double xlocation, double ylocation) {
        audioComponent_ = ac;
        parent_ = parent;
        baseLayout_ = new HBox();
        baseLayout_.setStyle("-fx-border-color: black; -fx-border-image-width: 10; -fx-background-color: seashell");
        baseLayout_.relocate(xlocation, ylocation);

        /* Right side of the widget */
        VBox rightRightSide = new VBox();
        Button closeButton = new Button();
        closeButton.setText("X");
        closeButton.setOnAction(e -> closeWidget());
        closeButton.setTextFill(Color.RED);

        Circle OutputCircle = new Circle(10);
        OutputCircle.setFill(Color.GREEN);
        OutputCircle.setOnMousePressed(e -> startConnection(e, OutputCircle));
        OutputCircle.setOnMouseDragged(e -> moveConnection(e, OutputCircle));
        OutputCircle.setOnMouseReleased(e -> endConnection(e, OutputCircle));

        rightRightSide.getChildren().add(closeButton);
        rightRightSide.getChildren().add(OutputCircle);
        rightRightSide.setAlignment(Pos.CENTER);
        rightRightSide.setPadding(new Insets(6));
        rightRightSide.setSpacing(5);

        // Circle for the volume widget
        leftSide_ = new VBox();
        VBox.setVgrow(leftSide_, Priority.ALWAYS);
        volumeCircle_ = new Circle(10);
        volumeCircle_.setFill(Color.RED);
        volumeCircle_.setOnMousePressed(e -> startBetweenVolumeCircles(e, volumeCircle_));
        volumeCircle_.setOnMouseDragged(e -> moveConnectionBetweenVolumeCircles(e, volumeCircle_));
        volumeCircle_.setOnMouseReleased(e -> endConnectionbetweenVolumeCircles(e, volumeCircle_));

        leftSide_.setAlignment(Pos.CENTER);
        leftSide_.getChildren().add(volumeCircle_);




        /***** center portion of the widget *****/
        centerComponent_ = new VBox();
        centerComponent_.setStyle("-fx-background-color: lavender");
        centerComponent_.setAlignment(Pos.CENTER);

        centerComponent_.setOnMousePressed(e -> startDrag(e));
        centerComponent_.setOnMouseDragged(e -> handleDrag(e));

        baseLayout_.getChildren().add(centerComponent_);
        baseLayout_.getChildren().add(rightRightSide);
        // add the baseLayout to the pane. baseLayout is the object of Pane
        this.getChildren().add(baseLayout_);
        parent_.getChildren().add(this);
    }
/*****************************************************************************************************************************/
/*    private void moveConnectionBetweenVolumeCircles(MouseEvent e, Circle volumeCircle_) {
        Bounds parentBounds = parent_.getBoundsInParent();
        line_.setEndX(e.getSceneX() - parentBounds.getMinX());
        line_.setEndY(e.getSceneY() - parentBounds.getMinY());
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
    }*/

    /*****************************************************************************************************************************/

    public void startConnection(MouseEvent e, Circle outputCircle) {
        // if a line exists (if there is a line connected to someone else)
        if (line_ != null ) {
            // remove that line so that we can create a new connection
            parent_.getChildren().remove(line_);
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
        // add line to the parent so it can be drawn
        parent_.getChildren().add(line_);
    }

    public void moveConnection(MouseEvent e, Circle outputCircle) {
        Bounds parentBounds = parent_.getBoundsInParent();
        line_.setEndX(e.getSceneX() - parentBounds.getMinX());
        line_.setEndY(e.getSceneY() - parentBounds.getMinY());
    }

    public void endConnection(MouseEvent e, Circle outputCircle) {
        Circle speaker = SpeakerWidget.speaker_;
        Bounds speakerBounds = speaker.localToScreen(speaker.getBoundsInLocal());
        double distance = Math.sqrt(Math.pow(speakerBounds.getCenterX() - e.getScreenX(), 2.0) +
                Math.pow(speakerBounds.getCenterY() - e.getScreenY(), 2.0));
        if (distance < 10) {
            //handle actual connection to speaker
//            SynthesizeApplication.widgets_.remove( this );
        } else {
            parent_.getChildren().remove(line_);
            line_ = null;
        }
        SpeakerWidget.SpeakerWidgets_.add(this);
    }

    /*****************************************************************************************************************************/

    public void handleDrag(MouseEvent e) {
        double mouseDelX = e.getSceneX() - mouseStartDragX_;
        double mouseDelY = e.getSceneY() - mouseStartDragY_;
        this.relocate(widgetStartDragX_ + mouseDelX, widgetStartDragY_ + mouseDelY);
    }

    public void startDrag(MouseEvent e) {
        mouseStartDragX_ = e.getSceneX();
        mouseStartDragY_ = e.getSceneY();
        widgetStartDragX_ = this.getLayoutX();
        widgetStartDragY_ = this.getLayoutY();
    }

    public void closeWidget() {
        parent_.getChildren().remove(this);
        System.out.println("widget has been removed");

        SynthesizeApplication.widgets_.remove(this); // spaghetti code.
//        SynthesizeApplication.VolumeWidgets_.remove(this);
        SpeakerWidget.SpeakerWidgets_.remove(this);

        if (line_ != null) {
            parent_.getChildren().remove(line_);
        }

        if (LineBetweenSineVolume_ != null) {
            parent_.getChildren().remove(LineBetweenSineVolume_);
        }



    }
    
    public AudioComponent getAudioComponent() {
        return audioComponent_;
    }


}
