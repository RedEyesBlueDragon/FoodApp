package com.example.navigate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.sarnava.textwriter.TextWriter;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextWriter textWriter;
        Intent login = new Intent(Splash.this, MainActivity2.class);

        getSupportActionBar().hide();


        textWriter = findViewById(R.id.textWriter);
        textWriter
                .setWidth(12)
                .setDelay(30)
                .setColor(Color.BLACK)
                .setConfig(TextWriter.Configuration.INTERMEDIATE)
                .setSizeFactor(30f)
                .setLetterSpacing(25f)
                .setText("FOOD APP")
                .setListener(new TextWriter.Listener() {
                    @Override
                    public void WritingFinished() {

                        startActivity(login);
                    }
                })
                .startAnimation();
    }
}