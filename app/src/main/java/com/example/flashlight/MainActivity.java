package com.example.flashlight;

import android.content.Context;
import android.graphics.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private CameraDevice cameraDevice;
    boolean isOff = true;
    String[] cameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button torch = (Button) findViewById(R.id.button);
        torch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFlash();
            }
        });
    }

    protected void toggleFlash() {
        CameraManager manager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try {
                cameraId = manager.getCameraIdList();
                //CameraCharacteristics chars = manager.getCameraCharacteristics(cameraId[0]);
                // Do something with the characteristics
        } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        if (isOff) {
            try {
                manager.setTorchMode(cameraId[0], true);
                isOff = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                manager.setTorchMode(cameraId[0], false);
                isOff = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
