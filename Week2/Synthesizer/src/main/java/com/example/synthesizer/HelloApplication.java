package com.example.synthesizer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);


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

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                cancel_B.setText("text updated");
            }

            // add some cool feature like dragging
        };



    }

    public static void main(String[] args) {
        launch();
    }
}