package com.example.synthesizer;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;


import java.awt.*;

public class AudioComponentWidget extends Pane {
    //pane is a widget that can draw it self

    AudioComponent audioComponent_;
    AnchorPane parent_;
    String name_;
    HBox baseLayout;

    AudioComponentWidget(AudioComponent ac, AnchorPane parent, String name )
    {
        audioComponent_ = ac;
        parent_ = parent;
        name_ = name;

        baseLayout = new HBox();

        /* Right side of the widget */
        VBox rightRightSide = new VBox();
        Button closeButton = new Button("X");
        Circle OutputCircle = new Circle(10);
        OutputCircle.setFill(Color.CORNSILK);
        rightRightSide.getChildren().add(closeButton);
        rightRightSide.getChildren().add(OutputCircle);

        baseLayout.getChildren().add( rightRightSide );
        // add the baseLayout to the pane. baseLayout is the object of Pane
        this.getChildren().add( baseLayout );

        this.setLayoutX( 50 );
        this.setLayoutY( 100 );









    }


}
