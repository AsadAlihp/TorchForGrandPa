package com.asadalihp.torchforgrandpa;

/**
 * Created by asad on 5/21/16.
 */
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;

public class torchservice extends Service implements Shaker.OnShakeListener {


    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Shaker mShakeDetector;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new Shaker();

        super.onCreate();

    }





   public int onStartCommand(Intent intent, int flags, int startId) {
      Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
       vibrator.vibrate(2000);

       return super.onStartCommand(intent, flags, startId);



    }

    @Override
    public void onShake(int count) {

    }
}