package edu.ewubd.objectdetectionwithapi;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class MorseVibrator {
    // Define the vibration durations for dot, dash, and space in milliseconds
    private static final long DOT_DURATION = 200;  // Short vibration for dot
    private static final long DASH_DURATION = 500; // Longer vibration for dash
    private static final long SPACE_DURATION = 300; // Pause between parts of the same letter
    private static final long LETTER_SPACE_DURATION = 800; // Pause between letters

    private Vibrator vibrator;

    // Constructor to initialize the Vibrator service
    public MorseVibrator(Context context) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }
    // Method to vibrate Morse code based on input text
    public void vibrateMorseCode(String morseCode) {
        for (char symbol : morseCode.toCharArray()) {
            switch (symbol) {
                case '.': // Dot
                    vibrate(DOT_DURATION);
                    break;
                case '-': // Dash
                    vibrate(DASH_DURATION);
                    break;
                case ' ': // Space between letters
                    try {
                        Thread.sleep(LETTER_SPACE_DURATION);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            // Short pause between symbols of the same letter
            try {
                Thread.sleep(SPACE_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // Vibrate for the specified duration
    private void vibrate(long duration) {
        if (vibrator != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(duration);
            }
        }
    }
}
