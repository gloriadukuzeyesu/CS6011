package com.example.synthesizer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

public class SineWaveWidget extends AudioComponentWidgetBase {
    Slider slider = new Slider(220, 880, 440);
    Label title = new Label();


    public SineWaveWidget (AudioComponent ac, AnchorPane parent, String name, double xlocation, double ylocation) {
        super(ac, parent, name, xlocation, ylocation);
    }

    public SineWaveWidget( AnchorPane parent){

    }

    public void createSliderAndTitle() {
        title.setMouseTransparent(true);
        title.setText("SineWave");
        slider.setOnMouseDragged(e-> handleSlider(e));
        title.setText("SineWave 440Hz");
        slider.setPadding(new Insets(6));
        centerComponent_.getChildren().add(title);
        centerComponent_.getChildren().add(slider);

        baseLayout_.setPrefSize(200,60);
        baseLayout_.setAlignment(Pos.CENTER_LEFT);
        baseLayout_.getChildren().add(leftSide_);
        baseLayout_.setSpacing(13);
    }

    public void handleSlider(MouseEvent e) {
        int value = (int) slider.getValue();
        title.setText("SineWave " + value + "Hz");
        audioComponent_ = new SineWave(value);
    }

}
