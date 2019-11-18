package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressBar pr = (ProgressBar) findViewById(R.id.progressBar);
        pr.setVisibility(View.INVISIBLE);
    }
    public void onToastClick(View view){
        Toast.makeText(this, getText(R.string.login_data_save_error),
                Toast.LENGTH_SHORT).show();
    }
    public void onDownloadTextClick(View view){
        LoadJsonTask task = new LoadJsonTask(new MyJsonResponseListener() {
            @Override
            public void onJsonResponseChange(String string) {
                ((TextView)(findViewById(R.id.textView))).setText(string);
            }
        });
        task.execute("http://apps.ii.uph.edu.pl:88/MSK/MSK/Hello/");
    }
    public void onDownloadImageClick(View view){
        ImageView im = (ImageView) findViewById(R.id.imageView);
        ProgressBar pr = (ProgressBar) findViewById(R.id.progressBar);
        pr.setVisibility(View.VISIBLE);
        LoadImageTask task = new LoadImageTask(pr,im);
        task.execute("http://apps.ii.uph.edu.pl:88/MSK/MSK/AndroidImage/");
    }
    public void OnLoginActivityClick(View view){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

}
