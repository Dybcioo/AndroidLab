package com.example.lab7;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Intent mNotificationIntent;
    private PendingIntent mContentIntent;
    private Uri soundURI =
            Uri.parse("android.resource://com.example.lab7/" +
                    R.raw.woof);
    private long[] vibrationPattern = {0, 200, 200, 300};


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotificationIntent = new Intent(getApplicationContext(),
                IntentActivity.class);
        mContentIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                mNotificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
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


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onAddNotificationClick(View view){
        int notifyID = 1;
        String CHANNEL_ID = "my_channel_01";
        CharSequence name = getString(R.string.channel_name);
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setContentTitle("Very important notification")
                        .setContentText("U must buy eggs")
                        .setTimeoutAfter(1000);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(mChannel);
        mNotificationManager.notify(notifyID, mBuilder.build());
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onAddSpecialNotificationClick(View view){

        RemoteViews mContentView = new RemoteViews("com.example.lab7", R.layout.custom_notification);

        mContentView.setTextViewText(R.id.textView, "U must buy eggs");
        Bitmap photo = BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.piesel);
        mContentView.setImageViewBitmap(R.id.imageView2, photo);

        int notifyID = 2;
        String CHANNEL_ID = "my_channel_02";
        CharSequence name = getString(R.string.channel_namee);
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setAutoCancel(true)
                .setContentIntent(mContentIntent)
                .setSound(soundURI)
                .setVibrate(vibrationPattern)
                .setContent(mContentView);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(mChannel);
        mNotificationManager.notify(notifyID, mBuilder.build());
    }
}
