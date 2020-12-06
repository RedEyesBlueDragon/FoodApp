package com.example.navigate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Food App");

    }


    public void RegisterGo(View view) {
        Intent Intent_go = new Intent(Register.this, MainActivity.class);
        startActivity(Intent_go);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition( R.anim.left_slide_in,R.anim.left_slide_out );

        return true;
    }
}
