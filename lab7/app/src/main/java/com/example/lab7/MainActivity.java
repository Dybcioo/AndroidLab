package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
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


    public void onToastClick(View view){
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.AXIS_PULL_BEFORE,-100,-200);

        View toastView = getLayoutInflater().inflate(R.layout.toast_view, null);
        TextView text = (TextView)toastView.findViewById(R.id.toastTextView);
        text.setText("wow much customization!");
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
    public void onAddNotificationClick(View view){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setContentTitle("Very important notification")
                        .setContentText("U must buy eggs")
                        .setTimeoutAfter(1000);
        NotificationManager mNotificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
        System.out.println("elo");
    }
}
