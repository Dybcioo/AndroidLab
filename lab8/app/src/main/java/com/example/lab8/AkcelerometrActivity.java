package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class AkcelerometrActivity extends AppCompatActivity implements SensorEventListener {

    private TextView XValueView;
    private TextView YValueView;
    private TextView ZValueView;
    private TextView XGravity;
    private TextView YGravity;
    private TextView ZGravity;
    private TextView XAccel;
    private TextView YAccel;
    private TextView ZAccel;
    private SensorManager mSensorManager;

    private float[] mGravity = new float[3];
    private float[] mAccel = new float[3];
    float mAlpha = 0.8f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akcelerometr);

        XValueView = (TextView) findViewById(R.id.textView);
        YValueView = (TextView) findViewById(R.id.textView2);
        ZValueView = (TextView) findViewById(R.id.textView3);

        XGravity = (TextView) findViewById(R.id.textView4);
        YGravity = (TextView) findViewById(R.id.textView5);
        ZGravity = (TextView) findViewById(R.id.textView6);

        XAccel = (TextView) findViewById(R.id.textView7);
        YAccel = (TextView) findViewById(R.id.textView8);
        ZAccel = (TextView) findViewById(R.id.textView9);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float rawX= event.values[0], rawY = event.values[1], rawZ =
                    event.values[2];
            mGravity[0] = lowPass(rawX, mGravity[0]);
            mGravity[1] = lowPass(rawY, mGravity[1]);
            mGravity[2] = lowPass(rawZ, mGravity[2]);
            mAccel[0] = highPass(rawX, mGravity[0]);
            mAccel[1] = highPass(rawY, mGravity[1]);
            mAccel[2] = highPass(rawZ, mGravity[2]);
            XValueView.setText("X: "+String.valueOf(rawX));
            YValueView.setText("Y: "+String.valueOf(rawY));
            ZValueView.setText("Z: "+String.valueOf(rawZ));
            XGravity.setText("XG: "+String.valueOf(mGravity[0]));
            YGravity.setText("YG: "+String.valueOf(mGravity[1]));
            ZGravity.setText("ZG: "+String.valueOf(mGravity[2]));
            XAccel.setText("XA: "+String.valueOf(mAccel[0]));
            YAccel.setText("YA: "+String.valueOf(mAccel[1]));
            ZAccel.setText("ZA: "+String.valueOf(mAccel[2]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private float lowPass(float current, float gravity){
        return gravity * mAlpha + current * (1-mAlpha);
    }
    private float highPass(float current, float gravity){
        return current - gravity;
    }

    @Override
    protected void onResume() {
        super.onResume();
         mSensorManager = (SensorManager)
                getSystemService(SENSOR_SERVICE);

        Sensor mAccelerometer =
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer,
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
