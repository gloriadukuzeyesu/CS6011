package com.example.synthesizer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ArrayList;

public class SynthesizeApplication extends Application {
    //member variables
    public static AnchorPane mainCanvas_;
    public static ArrayList<AudioComponentWidgetBase> widgets_ = new ArrayList<>();
    public static ArrayList<AudioComponentWidgetBase> VolumeWidgets_ = new ArrayList<AudioComponentWidgetBase>();


    @Override
    public void start(Stage stage) throws IOException {
        // Parent
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 400);

        /*********************** right panel for the scene ***********************/
        VBox rightPanel = new VBox();
        rightPanel.setPadding(new Insets(5));
        rightPanel.setSpacing(80);
        rightPanel.setAlignment(Pos.CENTER);
        rightPanel.setStyle("-fx-background-color: oldlace");

        Button sineWaveButton = new Button("SineWave");
        sineWaveButton.setTextFill(Color.BLACK);
        sineWaveButton.setOnAction(e -> createSineWaveComponent("SineWave"));


        Button volumeButton = new Button("Volume");
        volumeButton.setTextFill(Color.BLACK);
        volumeButton.setOnAction(e -> createVolumeComponent("Volume"));


        // add children of right panel
        rightPanel.getChildren().add(sineWaveButton);
        rightPanel.getChildren().add(volumeButton);


        /*********************** center panel***********************/
        mainCanvas_ = new AnchorPane();
        mainCanvas_.setStyle("-fx-background-color: antiquewhite");


      /*  speaker_ = new Circle(400, 200, 15);
        speaker_.setFill(Color.BLACK);
        mainCanvas_.getChildren().add(speaker_);*/



        /*********************** bottom panel***********************/
        HBox buttomPanel = new HBox();
        buttomPanel.setAlignment(Pos.BOTTOM_CENTER);
        buttomPanel.setStyle("-fx-background-color: oldlace");
        buttomPanel.setPadding(new Insets(10));
        Button playBtn = new Button("PLAY");
        playBtn.setOnAction(e -> PlayNetwork());
        buttomPanel.getChildren().add(playBtn);

        //*********************** Put all panels into the parent ***********************//
        // put stuffs into the root container
        root.setRight(rightPanel);
        root.setBottom(buttomPanel);
        root.setCenter(mainCanvas_);

        stage.setTitle("My Synthesizer");
        stage.setScene(scene);
        stage.show();
    }

    private void createVolumeComponent(String volume) {
        System.out.println("Create a volume Component");
        AudioComponent vol = new Volume(2);
        VolumeWidget volumeWidget = new VolumeWidget(vol,mainCanvas_,"Volume",50,300);
        volumeWidget.CreateVolumeWidget();
        widgets_.add(volumeWidget); // keep tack of all widgets
    }

    private void createSineWaveComponent(String sineWave) {
        System.out.println("Create sineWave Component");
        AudioComponent SineWave = new SineWave(440);
        SineWaveWidget sine_wave_widget = new SineWaveWidget(SineWave, mainCanvas_, "SineWave", 100,60);
        sine_wave_widget.createSliderAndTitle();
        System.out.println("added the title");
        widgets_.add(sine_wave_widget); // keep tack of all widgets
    }

    private void PlayNetwork() {
        ArrayList<AudioComponentWidgetBase> SpeakerWidgets = SpeakerWidget.SpeakerWidgets_;
        if (SpeakerWidgets.size() == 0 ) {
            System.out.println("widget size is equal to 0");
            return;
        }
        try {
            Clip c = AudioSystem.getClip();
//            AudioListener listener = new AudioListener(c);
            Mixer mixer = new Mixer();
            Volume volume = new Volume(1);
            for (AudioComponentWidgetBase w : widgets_) {
                AudioComponent ac = w.getAudioComponent();
                mixer.connectInput(ac);
                volume.connectInput(ac);
            }
            AudioFormat format = new AudioFormat(44100, 16, 1, true, false);
            byte[] data = mixer.getClip().getData();
            c.open(format, data, 0, data.length);
            c.start();
            c.addLineListener(e -> handleAudioDone(e,c)); // its job is to wait until the event is stopped and then close the clip
        } catch (LineUnavailableException e) {
            System.out.println("failed to open the clip");
        }
    }

    private void handleAudioDone(LineEvent e, Clip c) {
        if ( e.getType() == LineEvent.Type.STOP ) {
            System.out.println("Close clip");
            c.close();
        }
    }

    public static void main(String[] args) {


        //        launch();
        Application.launch(SynthesizeApplication.class); // this will run my JavaFx GUI app, basically it will run the start()
    }




}