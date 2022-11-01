package com.example.helloandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public boolean counter = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleClick ( View view){

        if (counter) {
            // print in android. GD is my initial. can help help searching things up
            Log.i("GD:MainActivity", "button was pressed");
            // get in the widget. R helps us gets us to the widget.
            TextView roomNameTV = findViewById(R.id.roomNameText);
            roomNameTV.setText("Enter a Room Name: ");
            counter = false;
        }
        // write a fx to switch to a different activity
        // intent takes two parameter. The current activity and the activity we want to switch to
        Intent switchToSecondActivity = new Intent(this, another.class);
        startActivity(switchToSecondActivity);

    }
//
//









}