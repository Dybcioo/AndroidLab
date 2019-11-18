package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @SuppressLint("ResourceAsColor")
    public void onToastClick(View view){
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.AXIS_PULL_BEFORE,-100,-200);

        View toastView = getLayoutInflater().inflate(R.layout.toast_view, null);
        TextView text = (TextView)toastView.findViewById(R.id.toastTextView);
        text.setText("wow much customization!");
        text.setBackgroundColor(R.color.black);
        text.setTextColor(R.color.white);
        ImageView image = (ImageView) toastView.findViewById(R.id.imageView);
        Bitmap photo = BitmapFactory.decodeResource(getResources(),
                R.drawable.piesel);
        image.setImageBitmap(photo);
        toast.setView(toastView);

        toast.show();
    }
    public void onDialogClick(View view){
        ExampleDialog dialog = new ExampleDialog();
        dialog.show(getSupportFragmentManager(),"dialog");
    }
}
