package edu.ewubd.objectdetectionwithapi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import android.view.GestureDetector;
import android.view.MotionEvent;

import java.io.IOException;
import java.util.Locale;

public class Image_Description extends AppCompatActivity {

    private ImageView img;
    private TextView text;
    private Uri selectedImageUri;
    private static final int REQUEST_PICK_IMAGE = 102;
    private static final int REQUEST_IMAGE_CAPTURE = 101;
    private static final int REQUEST_CAMERA_PERMISSION = 123;
    private MainViewModel viewModel;
    private ProgressBar progressBar; // Changed from View to ProgressBar for better control
    private TextToSpeech tts;
    private GestureDetector gestureDetector;
    private Morse_Translator morseTranslator;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_description);

        morseTranslator = new Morse_Translator();

        img = findViewById(R.id.img);
        progressBar = findViewById(R.id.progressBar); // Ensure this matches the correct ID in XML
        text = findViewById(R.id.text);


        // Initialize the ViewModel
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Observe the description text
        viewModel.getDescription().observe(this, description -> {
            text.setText(description);
            narrateText("The description of the image is: " + description);
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

//       
        progressBar.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // Start the translation when the user taps and holds
                String morseCode = morseTranslator.morseTranslate();
                // Display the Morse code, for example, using Toast or Vibrator
                Toast.makeText(this, "Morse Code: " + morseCode, Toast.LENGTH_SHORT).show();
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                // End the translation when the user releases
            }
            return true;
        });



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
            return;
        }
        Bitmap bitmap = getBitmapFromUri(selectedImageUri);
        if (bitmap != null) {
            viewModel.describePicture(bitmap);
        }
    }


    private void choosePicture() {
        narrateText("Tapped for uploading image");
        narrateText("Please Select an image");
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, REQUEST_PICK_IMAGE);
    }

    private void takePicture() {
        // Request camera permission if needed
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
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
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
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            // Handle camera capture
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            if (imageBitmap != null) {
                img.setImageBitmap(imageBitmap);
                selectedImageUri = getImageUri(imageBitmap); // Convert bitmap to Uri for further processing
                describePicture();
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

    // Method to narrate instructions
    private void narrateText(String message) {
        if (tts != null) {
            tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    protected void onDestroy() {
        // Cleanup Text-to-Speech resources
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}