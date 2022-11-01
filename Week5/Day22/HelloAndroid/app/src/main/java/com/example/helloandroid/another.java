package com.example.helloandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class another extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
    }


    public void handleBack(View view) {
        Intent switchBackToMain = new Intent(this, MainActivity.class);
        startActivity(switchBackToMain);


    }
}