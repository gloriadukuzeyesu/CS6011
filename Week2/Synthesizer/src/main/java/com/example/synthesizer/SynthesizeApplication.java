package com.example.synthesizer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.swing.plaf.synth.ColorType;
import java.io.IOException;

import static java.awt.Color.*;

public class SynthesizeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // Parent
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 400);


        /*********************** right panel for the scene ***********************/
        VBox rightPanel = new VBox();
        rightPanel.setPadding(new Insets(5));
        rightPanel.setSpacing(4);
        rightPanel.setStyle("-fx-background-color: oldlace");

        Button sineWaveButton = new Button("SineWave");
        sineWaveButton.setTextFill(Color.BLACK);
//        sineWaveButton.setStyle("-fx-background-color: green");
        sineWaveButton.setOnAction(e-> createComponent("SineWave"));

        Button volumeButton = new Button("Volume");
        volumeButton.setStyle("-fx-background-color: yellow");

        // add children of right panel
        rightPanel.getChildren().add(sineWaveButton);
        rightPanel.getChildren().add(volumeButton);



        // bottom side of the panel
        HBox buttonPanel = new HBox();
        Button buttonPlayButton = new Button("PLAY");
//        buttonPanel.getChildren().add(buttonPanel);


        /*********************** center panel***********************/
        mainCanvas_ = new AnchorPane();
        mainCanvas_.setStyle("-fx-background-color: antiquewhite");

        Circle speakerCircle = new Circle(400,200,15);
        speakerCircle.setFill( Color.PURPLE );

        mainCanvas_.getChildren().add( speakerCircle );









        /// center of the

        /*
        things to make the widget move
        1. click on it
        2. drag it around
        3. keep track of the current location
        3. let it go
         */

        // center portion of widget

;
        /*********************** Put all panels into the parent ***********************/

        // put stuffs into the root container
        root.setRight(rightPanel);
        root.setBottom(buttonPanel);
        root.setCenter(mainCanvas_);
//        root.setBottom(buttonPlayButton);



        stage.setTitle("My Synthesizer");
        stage.setScene(scene);
        stage.show();


    }

    private void createComponent(String sineWave) {
        System.out.println("Create Component");
    }


    public static void main(String[] args) {
        launch();
//        Application.launch(SynthesizeApplication.class); // this will run my JavaFx GUI app, basically it will run the start()
    }

    private AnchorPane mainCanvas_;
    public static Circle speaker;
}