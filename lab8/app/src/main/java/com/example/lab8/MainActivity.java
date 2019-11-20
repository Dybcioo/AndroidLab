package com.example.lab8;

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
    public void onAkcelerometrClick(View view){
        Intent intent = new Intent(this,AkcelerometrActivity.class);
        startActivity(intent);
    }
    public void onLightmeterClick(View view){
        Intent intent = new Intent(this,LightmeterActivity.class);
        startActivity(intent);
    }
}
