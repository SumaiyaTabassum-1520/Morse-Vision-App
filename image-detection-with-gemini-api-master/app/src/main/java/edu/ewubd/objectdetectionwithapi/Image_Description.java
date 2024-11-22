package edu.ewubd.objectdetectionwithapi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.CancellationException;

public class Image_Description extends AppCompatActivity {

    private ImageView img;
    private TextView text;
    private Uri selectedImageUri, cameraImageUri;
    private static final int REQUEST_PICK_IMAGE = 102;
    private static final int REQUEST_IMAGE_CAPTURE = 101;
    private static final int REQUEST_CAMERA_PERMISSION = 123;
    private MainViewModel viewModel;
    private ProgressBar progressBar; // Changed from View to ProgressBar for better control
    private TextToSpeech tts;
    private GestureDetector gestureDetector;
    private Morse_Translator morseTranslator;
    private MorseVibrator vibrate;
    private final GeminiRepository geminiRepository = new GeminiRepository();
    private String morseCode="";
    private String description = "";
    private boolean requestOnGoing = false; //I will ever do this again ever


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_description);

        morseTranslator = new Morse_Translator();

        img = findViewById(R.id.img);
        progressBar = findViewById(R.id.progressBar); // Ensure this matches the correct ID in XML
        text = findViewById(R.id.text);
        vibrate = new MorseVibrator(getApplicationContext());

        // Initialize the ViewModel
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Observe the description text
        viewModel.getDescription().observe(this, description -> {
            text.setText(description);
            narrateText("The description of the image is: " + description);
            // Check if Morse Vibration Mode is enabled
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            boolean isMorseVibrationEnabled = sharedPreferences.getBoolean("morse_vibration_mode", false);

            if (isMorseVibrationEnabled && !description.isEmpty()) {
                morseCode = morseTranslator.morseTranslate(description);

                // Run vibration logic in a background thread
                new Thread(() -> vibrate.vibrateMorseCode(morseCode)).start();
            }
        });
        // Initialize Text-to-Speech
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
                narrateText("Single tap to upload photo, double tap to take a picture");
            }
        });

        img.setOnClickListener(v -> choosePicture());
        img.setOnLongClickListener(v -> {
            narrateText("Please capture a photo by single click.");
            takePicture();
            return true;
        });


//        text.setOnTouchListener((v, event) -> {
//            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                Toast.makeText(this, "Morse Code: " + morseCode, Toast.LENGTH_SHORT).show();
//            }else if (event.getAction() == MotionEvent.ACTION_UP) {
//            }
//            return true;
//        });



        // Observe the loading state to show or hide the progress bar
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });
    }


    private void describePicture() {
        if (selectedImageUri == null) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
        }
        Bitmap bitmap = getBitmapFromUri(selectedImageUri);
        if (bitmap == null) {
            Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show();
        }
        viewModel.setIsloading(true);
        getDescriptionFromGeminiImageApi(bitmap);
    }
    private void handleResponse(boolean isSuccessful,String description){
        viewModel.setIsloading(false);
        if(isSuccessful){
            morseCode = new Morse_Translator().morseTranslate(description);
            viewModel.setDescription(description);
//            if (vibrate != null){
//                vibrate.vibrateMorseCode(morseCode);
//            }
            return;
        }
        viewModel.setDescription(description);
    }

    public void getDescriptionFromGeminiImageApi(Bitmap bitmap) {
        description = "";
        requestOnGoing = true;
        Futures.addCallback(geminiRepository.generateDescription(bitmap), new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                handleResponse(true,result.getText());
            }

            @Override
            public void onFailure(Throwable t) {
                description = "Error: " + t.getMessage();
                handleResponse(false, description);
            }
        }, geminiRepository.getExecutor());
    }


    private void choosePicture() {
        narrateText("Tapped for uploading image");
        narrateText("Please Select an image");
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, REQUEST_PICK_IMAGE);
    }

    private void takePicture() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            } else {
                openCamera();
            }
        } else {
            openCamera();
        }
    }

    private void openCamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a file to store the image
        File photoFile = createImageFile();
        if (photoFile != null) {
            cameraImageUri = FileProvider.getUriForFile(this, "edu.ewubd.objectdetectionwithapi.fileprovider", photoFile);
            takePicture.putExtra(MediaStore.EXTRA_OUTPUT, cameraImageUri);
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
        }
    }
    private File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            return File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera permission is required to use the camera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            displayImage(selectedImageUri);
            describePicture();
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (cameraImageUri != null) {
                selectedImageUri = cameraImageUri;
                displayImage(cameraImageUri);
                describePicture();
            } else {
                Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void displayImage(Uri uri) {
        if (uri != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Uri getImageUri(Bitmap bitmap) {
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "CapturedImage", null);
        return Uri.parse(path);
    }

    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            return MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void narrateText(String message) {
        if (tts != null) {
            tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (vibrate != null) {
            vibrate.stopVibration(); // Stop vibration when activity is paused
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (vibrate != null) {
            vibrate.stopVibration(); // Ensure vibration stops on destroy
        }

        // Cleanup Text-to-Speech resources
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}