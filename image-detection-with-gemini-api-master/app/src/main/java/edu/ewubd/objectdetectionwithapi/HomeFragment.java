package edu.ewubd.objectdetectionwithapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.camera.core.processing.SurfaceProcessorNode;
import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        // Set an onClickListener for the button for image description
        Button imageDescriptionButton = view.findViewById(R.id.imgDes);
        imageDescriptionButton.setOnClickListener(v -> {
            // Intent to navigate to Image Description activity
            Intent intent = new Intent(getActivity(), Image_Description.class);
            startActivity(intent);
        });
        // Set an onClickListener for the button for Morse translation
        Button morseTranslationButton = view.findViewById(R.id.morse);
        morseTranslationButton.setOnClickListener(v->{
            Intent intent=new Intent (getActivity(),Morse_Translator.class);
            startActivity(intent);
        });
        // Set an onClickListener for the button for Color recognition
        Button colorRecognitionButton = view.findViewById(R.id.color);
        colorRecognitionButton.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(),Color_Recognition.class);
            startActivity(intent);
        });
        // Set an onClickListener for the button for magnifier
        Button Magnifier = view.findViewById(R.id.magnifier);
        Magnifier.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(),Magnifier.class);
            startActivity(intent);
        });

        return view;
    }
}