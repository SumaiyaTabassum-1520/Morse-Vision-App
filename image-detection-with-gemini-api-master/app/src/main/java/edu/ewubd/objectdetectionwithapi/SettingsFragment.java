package edu.ewubd.objectdetectionwithapi;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import java.util.Locale;


public class SettingsFragment extends PreferenceFragmentCompat {

    private TextToSpeech tts;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the preferences from the XML resource
        setPreferencesFromResource(R.xml.preferences, rootKey);

        SwitchPreferenceCompat morseModeSwitch = findPreference("morse_vibration_mode");
        if (morseModeSwitch != null) {
            morseModeSwitch.setOnPreferenceChangeListener((preference, newValue) -> {
                boolean isEnabled = (boolean) newValue;
                String message = isEnabled
                        ? "Morse mode is enabled."
                        : "Morse mode is disabled.";
                narrateMessage(message);
                return true; // Save the new value
            });
        }
        SwitchPreferenceCompat darkModeSwitch = findPreference("dark_mode");
        if (darkModeSwitch != null) {
            darkModeSwitch.setOnPreferenceChangeListener((preference, newValue) -> {
                boolean isDarkMode = (boolean) newValue;
                if (isDarkMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    narrateMessage("Dark mode enabled.");
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    narrateMessage("Light mode enabled.");
                }
                return true; // Save the new value
            });
        }
    }

    private void narrateMessage(String message) {
        if (!TextUtils.isEmpty(message)) {
            if (tts == null) {
                tts = new TextToSpeech(getContext(), status -> {
                    if (status == TextToSpeech.SUCCESS) {
                        tts.setLanguage(Locale.US);
                        tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                });
            } else {
                tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Clean up TTS resources when the fragment is destroyed
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}