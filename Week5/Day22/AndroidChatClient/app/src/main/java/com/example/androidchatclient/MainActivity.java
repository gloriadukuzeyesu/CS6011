package com.example.androidchatclient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static WebSocket ws_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            ws_ = new WebSocketFactory().createSocket("ws://10.0.2.2:8080/endpoint", 1000 );
        }
        catch( IOException e ) {
            Log.e( "Gd:","WS error" );
        }
        ws_.addListener( new myWebSocket());
        ws_.connectAsynchronously();

    }

    public void handleClick ( View view) {
        Log.i("GD : Main activity","login button was pressed" );
        TextInputEditText userName = findViewById(R.id.UserID);
        TextInputEditText roomName = findViewById(R.id.roomID);

        String room_name = roomName.getText().toString();
        String Username = userName.getText().toString();
        Log.i("GD : Main activity",room_name );

        Intent intent = new Intent(MainActivity.this, ChatDashBoard.class);
        intent.putExtra("userName", Username);
        intent.putExtra("roomName", room_name);
        startActivity(intent);

        ws_.sendText("join "+Username + " " + room_name);
    }


}