package com.asadalihp.torchforgrandpa;

/**
 * Created by asad on 5/21/16.
 */

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;

public class torchservice extends Service implements Shaker.OnShakeListener {

    //flag to detect flash is on or off
    public static final String SERVICE_RUNNING = torchservice.class.getName() + ".PREF_SERVICE_RUNNING";
    public static final String ACTION_SERVICE_STOPPED = torchservice.class.getName() + ".ACTION_SERVICE_STOPPED";
    private static final String ACTION_STOP_REQUEST = torchservice.class.getName() + ".ACTION_STOP_REQUEST";
    private static final int SERVICE_ID = 79290;
    public FlashManager flfl;
    /*Declaration for flash light down*/
    private int o = 0;
    private int a = 0;
    private boolean on = false;
    private boolean repeating = false;
    private boolean noFlash = false;
    private FlashManager mFlashManager = new FlashManager();
    private Camera camera;
    private boolean isFlashOn;
    private boolean hasFlash;
    private Parameters params;
    /*declaration for shake detection down*/

    private Shaker mShaker;
    private SensorManager mSensorManager;


    private Sensor mAccelerometer;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {

        super.onCreate();
        this.mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        this.mAccelerometer = this.mSensorManager.getDefaultSensor(1);
        mShaker = new Shaker(this);
        mShaker.setOnShakeListener(this);
    }

    @Override
    public void onShake() {
        Log.e("--------", "value of a = " + o);
        flfl = new FlashManager();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //----------------
        //FLASH LIGHT-------------------------------------------BELOW

        if (flfl.getPower() == FlashManager.LightPower.ON) {
            v.vibrate(200);// Vibrate for 400 milliseconds
            //for (int i = 0; i < 10; i++) {
            try {
                flfl.turnFlashOFF();
            } catch (Exception e) {
                e.printStackTrace();
                }

            // }
            Log.e("----SERVICE---", "SWITCHING OFF.......");
        }
        if (flfl.getPower() == FlashManager.LightPower.OFF) {
            v.vibrate(200);// Vibrate for 400 milliseconds
            //for (int i = 0; i < 10; i++) {
            try {
                flfl.turnFlashON();
            } catch (Exception e) {
                e.printStackTrace();
                }
            //}
        }
    }

    //-------------------------------------------FLASH LIGHT ABOVE
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);


        return START_STICKY;


    }
}