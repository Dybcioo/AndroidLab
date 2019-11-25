package com.example.lab9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout rl = new RelativeLayout(this);
        ImageView image = new ImageView(this);

        ContextCompat.getDrawable(getApplicationContext(), R.drawable.piesel);
        int height = (int)getResources().getDimension(R.dimen.jelly_height);
        int width = (int)getResources().getDimension(R.dimen.jelly_width);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(width,height);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        image.setLayoutParams(params);

        Bitmap photo = BitmapFactory.decodeResource(getResources(), R.drawable.piesel);
        image.setImageBitmap(photo);

        setContentView(rl);

    }
}
