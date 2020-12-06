package com.example.navigate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private EditText username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setTitle("Food App");



    }

    public void doGo(View view) {
        username = (EditText) findViewById(R.id.editTextTextEmailAddress);
        String user_name = username.getText().toString();

        if (user_name.equals("admin")){

            Intent Intent_go = new Intent(MainActivity2.this, AddData.class);
            startActivity(Intent_go);
            overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
        }
        else{
            Intent Intent_go = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(Intent_go);
            overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
        }
    }


    public void doRegister(View view) {
        Intent Intent_go = new Intent(MainActivity2.this, Register.class);
        startActivity(Intent_go);
        overridePendingTransition( R.anim.slide_left, R.anim.slide_right );
    }
}