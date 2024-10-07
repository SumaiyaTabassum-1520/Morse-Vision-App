package edu.ewubd.objectdetectionwithapi;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

import java.io.IOException;

public class Magnifier extends AppCompatActivity implements SurfaceHolder.Callback {
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Camera camera;
    private SeekBar zoomSeekBar;
    private Button focusButton;
    private Button flashlightButton;
    private static final int REQUEST_CAMERA_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnifier);

        surfaceView = findViewById(R.id.surface_view);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

        zoomSeekBar = findViewById(R.id.zoom_seek_bar);
        zoomSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser ) {
                Camera.Parameters parameters = camera.getParameters();
                parameters.setZoom(progress);
                camera.setParameters(parameters);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Magnifier.this, "Adjusting zoom", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Magnifier.this, "Zoom adjusted to " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();

            }

        });

        focusButton = findViewById(R.id.focus_button);
        focusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Camera.Parameters parameters = camera.getParameters();
                if (parameters.getFocusMode().equals(Camera.Parameters.FOCUS_MODE_AUTO)) {
                    parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_MACRO);
                    focusButton.setText("Manual Focus");
                } else {
                    parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                    focusButton.setText("Autofocus");
                }
                camera.setParameters(parameters);
            }
        });

        flashlightButton = findViewById(R.id.flashlight_button);
        flashlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Camera.Parameters parameters = camera.getParameters();
                if (parameters.getFlashMode().equals(Camera.Parameters.FLASH_MODE_OFF)) {
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    flashlightButton.setText("Flashlight On");
                } else {
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    flashlightButton.setText("Flashlight Off");
                }
                camera.setParameters(parameters);
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
            } else {
                // Permission denied
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();
        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        camera.startPreview();
        Camera.Parameters parameters = camera.getParameters();
        parameters.setZoom(10); // set zoom level to 10
        parameters.set("orientation", "portrait");
        camera.setDisplayOrientation(90);
        camera.setParameters(parameters);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
    }
}