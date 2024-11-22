package FaceRecognition;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

import edu.ewubd.objectdetectionwithapi.R;

public class AddFriend extends AppCompatActivity {
    Button register, recongize;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_friend);


        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int langResult = textToSpeech.setLanguage(Locale.getDefault());
                    if (langResult == TextToSpeech.LANG_MISSING_DATA
                            || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(AddFriend.this, "Language not supported", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddFriend.this, "TTS initialization failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register = findViewById(R.id.register_button);

        recongize = findViewById(R.id.recognize_button);

        register.setOnClickListener(v -> {
            speak("Registering new face");
            startActivity(new Intent(AddFriend.this, RegisterActivity.class));
        });

        recongize.setOnClickListener(v -> {
            speak("Recognizing face");
            startActivity(new Intent(AddFriend.this, RecognitionActivity.class));
        });

    }

    private void speak(String text) {
        if (textToSpeech != null) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            super.onDestroy();
            if (textToSpeech != null) {
                textToSpeech.stop();
                textToSpeech.shutdown();
            }
        }
    }
}