package edu.ewubd.morsevision;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.util.SparseArray;
import android.view.TextureView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;
import java.util.Locale;

public class TextRecognition extends AppCompatActivity implements TextureView.SurfaceTextureListener  {

    private static final int CAMERA_REQUEST_CODE = 100;
    private TextureView textureView;
    private Camera camera;
    private TextToSpeech textToSpeech;
    private TextView textViewResult;
    private Bitmap capturedBitmap;
    private TextRecognizer textRecognizer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_recognition);

        textureView = findViewById(R.id.texture_view);
        textViewResult = findViewById(R.id.text_view_result);
        textureView.setSurfaceTextureListener(this);

        textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            Log.e("TextRecognizer", "Detector dependencies are not yet available.");
            Toast.makeText(this, "Text recognition not available", Toast.LENGTH_SHORT).show();
            return;
        }
//        tts = new TextToSpeech(this, status -> {
//            if (status == TextToSpeech.SUCCESS) {
//                tts.setLanguage(Locale.US);
//
//            }
//        });
// Initialize Text to Speech
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.US);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language not supported");
                }

            } else {
                Log.e("TTS", "Initialization failed");
            }
        });

        // Set tap listener to capture image
        textureView.setOnClickListener(v -> {
            // Reset the TextView message to prompt user for action
            captureImage();
        });
    }


    private void startCamera() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
        } else {
            camera = Camera.open();
            camera.setDisplayOrientation(90);

            // Set camera parameters for better clarity
            Camera.Parameters params = camera.getParameters();
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE); // Auto-focus mode
            params.setPictureSize(1280, 720); // Set picture size for better quality
            camera.setParameters(params);

            try {
                camera.setPreviewTexture(textureView.getSurfaceTexture());
                camera.startPreview();
                narrateText("Single tap to capture image"); // Speak prompt when the camera starts
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void captureImage() {

        if (camera != null) {
            camera.takePicture(null, null, (data, camera) -> {
                capturedBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                if (capturedBitmap != null) {
                    recognizeTextAndSpeak(); // Process text recognition and speech immediately
                }
                camera.stopPreview(); // Stop preview after capture
            });
        }
    }


    private void recognizeTextAndSpeak() {
        if (capturedBitmap != null) {
            TextRecognizer recognizer = new TextRecognizer.Builder(getApplicationContext()).build();
            Frame frame = new Frame.Builder().setBitmap(capturedBitmap).build();
            SparseArray<TextBlock> textBlocks = recognizer.detect(frame);

            StringBuilder recognizedText = new StringBuilder();
            for (int index = 0; index < textBlocks.size(); index++) {
                TextBlock textBlock = textBlocks.valueAt(index);
                recognizedText.append(textBlock.getValue()).append("\n");
            }

            String resultText = recognizedText.toString();
            textViewResult.setText(resultText);

            if (!resultText.isEmpty()) {
                textViewResult.setText(resultText);
                textToSpeech.speak(resultText, TextToSpeech.QUEUE_FLUSH, null, null);
            } else {
                narrateText("No text found. Tap to capture again.");
                startCamera();
            }
        } else {
            narrateText("No image capture");
        }
    }
    private void narrateText(String text) {
        if (textToSpeech != null && !textToSpeech.isSpeaking()) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    public void onDestroy() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
//        if (tts != null) {
//            tts.stop();
//            tts.shutdown();
//        }
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera();
            }
        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        startCamera();
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
        return true;
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }
}