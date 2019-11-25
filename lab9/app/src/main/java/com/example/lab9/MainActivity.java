package com.example.lab9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onFirstActivityClick(View view){
        Intent intent = new Intent(this, ShowImageActivity.class);
        startActivity(intent);
    }
    public void onSecondActivityClick(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
