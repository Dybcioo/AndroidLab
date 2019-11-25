package com.example.lab9;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowImageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        Bitmap photo = BitmapFactory.decodeResource(getResources(), R.drawable.piesel);
        ((ImageView)findViewById(R.id.imageView)).setImageBitmap(photo);
    }
}
