package edu.ewubd.objectdetectionwithapi;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import java.util.Locale;

import PdfExtractor.PdfToVoiceActivity;


public class HomeFragment extends Fragment {

    private TextToSpeech tts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        tts = new TextToSpeech(getContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
                // narrateText("Select the activity");
            }
        });

        // Set an onClickListener for the button for image description
        Button imageDescriptionButton = view.findViewById(R.id.imgDes);
        imageDescriptionButton.setOnClickListener(v -> {
            narrateText("Navigating to Image Description");
            // Intent to navigate to Image Description activity
            Intent intent = new Intent(getActivity(), Image_Description.class);
            startActivity(intent);
        });
        Button textRecognition = view.findViewById(R.id.txt);
        textRecognition.setOnClickListener(v -> {
            narrateText("Navigating to Text Recognition Single tap to capture image");
            // Intent to navigate to Image Description activity
            Intent intent = new Intent(getActivity(), TextRecognition.class);
            startActivity(intent);
        });
        // Set an onClickListener for the button for Morse translation
//        Button morseTranslationButton = view.findViewById(R.id.morse);
//        morseTranslationButton.setOnClickListener(v->{
//            Intent intent=new Intent (getActivity(),Morse_Translator.class);
//            startActivity(intent);
//        });
        Button pdfToVoice = view.findViewById(R.id.buttonSelectPdf);
        pdfToVoice.setOnClickListener(v -> {
            narrateText("Navigating to Documents reader");
            Intent intent = new Intent(getActivity(), PdfToVoiceActivity.class);
            startActivity(intent);
        });
        // Set an onClickListener for the button for Color recognition
        Button colorRecognitionButton = view.findViewById(R.id.color);
        colorRecognitionButton.setOnClickListener(v->{
            narrateText("Navigating to Color Recognition");
            Intent intent = new Intent(getActivity(),Color_Recognition.class);
            startActivity(intent);

        });
        // Set an onClickListener for the button for magnifier
//        Button Magnifier = view.findViewById(R.id.magnifier);
//        Magnifier.setOnClickListener(v->{
//            Intent intent = new Intent(getActivity(),Magnifier.class);
//            startActivity(intent);
//        });
        return view;
    }
    // Helper method to narrate text
    private void narrateText(String message) {
        if (tts != null) {
            tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }
    @Override
    public void onDestroyView() {
        // Shutdown TTS to release resources
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroyView();
    }
}