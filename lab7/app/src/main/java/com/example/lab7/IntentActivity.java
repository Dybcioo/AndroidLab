package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class IntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        Bitmap photo = BitmapFactory.decodeResource(getResources(), R.drawable.piesel);
        ((ImageView)findViewById(R.id.imageView3)).setImageBitmap(photo);
    }
}
