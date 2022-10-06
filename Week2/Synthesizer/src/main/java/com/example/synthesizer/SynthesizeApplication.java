package com.example.synthesizer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SynthesizeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /*
        1. one parent object that contains child nodes
        2. Display one scene
         */

        AnchorPane root = new AnchorPane();

        Scene scene = new Scene( root, 440, 300);

        //creat an audion component  as verticle box

        VBox componentWidget = new VBox();
        componentWidget.setStyle ("-fx-background-color: purple");

        Label title = new Label();
        title.setText("I am red for the cool music.Sine wave(440HZ)");

        componentWidget.getChildren().add(title);

        // how to set the widget on different location on the screen
        componentWidget.relocate(50, 100);


        // ability to move the box and move around this around the screen
        componentWidget.setOnMousePressed( e -> handleMOusePressed (e) );

        // add a slider that can be moved around
        Slider slider = new Slider(220,880,4440 );
        componentWidget.getChildren().add(slider);

//        slider.setOnMouseDragged(e -> handleSlider (e) );

        //1. get the children and add
        root.getChildren().add(componentWidget);

        // write once and odo not worry about it
        stage.setScene(scene);
        stage.show();




/*
        // TODO how to create GUI
        Button submit_b = new Button("submit ");
        Button cancel_B = new Button("Cancel");

        GridPane g1 = new GridPane();
        // add our object to the layout
        g1.add(submit_b,1, 1 ); // the first argument is the colum
        g1.add(cancel_B, 1, 2);

        Scene s1 = new Scene(g1, 500, 500);

        // add the scene to the stage
        stage.setScene(s1);
        stage.show();


        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

 */
/*
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                cancel_B.setText("text updated");
            }

            // add some cool feature like dragging
        };

 */



    }



    private void handleSlider(MouseEvent e, Slider slider, Label Title ) {
       int value = (int) slider.getValue();
       // TODO check on the title
//       title.setText("Sine wave" + value + "HZ");

    }

    private void handleMOusePressed(MouseEvent e) {
        System.out.println("Mouse was pressed " );
    }

    public static void main(String[] args) {
        Application.launch(SynthesizeApplication.class); // this will run my JavaFx GUI app, basically it will run the start()
    }
}