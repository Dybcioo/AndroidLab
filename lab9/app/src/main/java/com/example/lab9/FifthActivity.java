package com.example.lab9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FifthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setBackgroundResource(R.drawable.rect);
    }
}
