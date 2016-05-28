package com.asadalihp.torchforgrandpa;

/**
 * Created by asad on 5/22/16.
 */

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;

public class FlashManager {

    public LightPower currentPower;
    //being used in others may be
    private Camera camera;
    private Parameters parameters;

    public static boolean isFlashAvailable(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    public void release() {
        Log.e("----RELEASE---", "---------STATUS---" + (currentPower == LightPower.OFF));
        for (int i = 0; i < 10; i++) {
            try {
                setPower(LightPower.OFF);

            } catch (Exception e) {
                Log.e("----CAM-TFON-", "---------switching of by releasing");
                e.printStackTrace();
            }
        }
    }

    public void turnFlashON() {

        for (int i = 0; i < 30; i++) {
            try {
                camera = Camera.open();
            } catch (Exception e) {
                Log.e("----CAM-TFON-", "------------camera.open()");
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 30; i++) {
            try {
                parameters = camera.getParameters();
            } catch (Exception e) {
                Log.e("----CAM-TFON-", "------------camera.getParameters()");
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 30; i++) {
            try {
                parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);

            } catch (Exception e) {
                Log.e("----CAM-TFON-", "------------FLASH_MODE_TORCH");
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 30; i++) {
            try {
                camera.setParameters(parameters);

            } catch (Exception e) {
                Log.e("----CAM-TFON-", "------------SET PARAMETERS");
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 30; i++) {
            try {
                camera.startPreview();
            } catch (Exception e) {
                Log.e("----CAM- TFON-", "-----------START PREVIEW");
                e.printStackTrace();
            }
        }
        try {
            currentPower = LightPower.ON;
        } catch (Exception e) {
            Log.e("----CAM- TFON-", "---------setting status");
            e.printStackTrace();
        }


    }


    public void turnFlashOFF() {

        if (camera != null) {
            for (int i = 0; i < 30; i++) {
                try {
                    camera.stopPreview();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("000000000", "=========================================can't stop Preview!!!===" + i);
                }
            }

            for (int i = 0; i < 30; i++) {
                try {
                    camera.release();
                } catch (Exception e) {
                    Log.e("000000000", "=========================================can't release camera!!!===" + i);
                }
            }
            currentPower = LightPower.OFF;


        }
    }

    public LightPower getPower() {
        if (currentPower == null) {
            return LightPower.OFF;
        }
        return currentPower;
    }

    public void setPower(LightPower value) {
        switch (value) {
            case ON:
                if (getPower() == LightPower.OFF) {
                    Log.e("---in CASE---", "--IS OFF case" + (getPower() == LightPower.OFF));
                    turnFlashON();
                }
                break;
            case OFF:
                if (getPower() == LightPower.ON) {
                    Log.e("---in CASE---", "-- iS on case" + (getPower() == LightPower.ON));
                    turnFlashOFF();//being turned offed
                }
                break;
            default:
                break;
        }
    }

    public enum LightPower {
        OFF, ON;
    }

}