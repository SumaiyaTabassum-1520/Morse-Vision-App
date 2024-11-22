package edu.ewubd.objectdetectionwithapi;

import android.content.Context;
import android.media.AudioAttributes;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.Locale;

public class LearnMorse extends Fragment {

    private TextToSpeech tts;
    private SoundPool soundPool;
    private int dotSound, dashSound;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learn_morse, container, false);

        ImageButton btnSoundA = view.findViewById(R.id.btnSoundA);
        ImageButton btnVibrateA = view.findViewById(R.id.btnVibrateA);
        ImageButton btnSpeakA = view.findViewById(R.id.btnSpeakA);
        ImageButton btnSoundB = view.findViewById(R.id.btnSoundB);
        ImageButton btnVibrateB = view.findViewById(R.id.btnVibrateB);
        ImageButton btnSpeakB = view.findViewById(R.id.btnSpeakB);
        ImageButton btnSoundC = view.findViewById(R.id.btnSoundC);
        ImageButton btnVibrateC = view.findViewById(R.id.btnVibrateC);
        ImageButton btnSpeakC = view.findViewById(R.id.btnSpeakC);
        ImageButton btnSoundD = view.findViewById(R.id.btnSoundD);
        ImageButton btnVibrateD = view.findViewById(R.id.btnVibrateD);
        ImageButton btnSpeakD = view.findViewById(R.id.btnSpeakD);
        ImageButton btnSoundE = view.findViewById(R.id.btnSoundE);
        ImageButton btnVibrateE = view.findViewById(R.id.btnVibrateE);
        ImageButton btnSpeakE = view.findViewById(R.id.btnSpeakE);
        ImageButton btnSoundF = view.findViewById(R.id.btnSoundF);
        ImageButton btnVibrateF = view.findViewById(R.id.btnVibrateF);
        ImageButton btnSpeakF = view.findViewById(R.id.btnSpeakF);
        ImageButton btnSoundG = view.findViewById(R.id.btnSoundG);
        ImageButton btnVibrateG = view.findViewById(R.id.btnVibrateG);
        ImageButton btnSpeakG = view.findViewById(R.id.btnSpeakG);
        ImageButton btnSoundH = view.findViewById(R.id.btnSoundH);
        ImageButton btnVibrateH = view.findViewById(R.id.btnVibrateH);
        ImageButton btnSpeakH = view.findViewById(R.id.btnSpeakH);
        ImageButton btnSoundI = view.findViewById(R.id.btnSoundI);
        ImageButton btnVibrateI = view.findViewById(R.id.btnVibrateI);
        ImageButton btnSpeakI = view.findViewById(R.id.btnSpeakI);
        ImageButton btnSoundJ = view.findViewById(R.id.btnSoundJ);
        ImageButton btnVibrateJ = view.findViewById(R.id.btnVibrateJ);
        ImageButton btnSpeakJ = view.findViewById(R.id.btnSpeakJ);
        ImageButton btnSoundK = view.findViewById(R.id.btnSoundK);
        ImageButton btnVibrateK = view.findViewById(R.id.btnVibrateK);
        ImageButton btnSpeakK = view.findViewById(R.id.btnSpeakK);
        ImageButton btnSoundL = view.findViewById(R.id.btnSoundL);
        ImageButton btnVibrateL = view.findViewById(R.id.btnVibrateL);
        ImageButton btnSpeakL = view.findViewById(R.id.btnSpeakL);
        ImageButton btnSoundM = view.findViewById(R.id.btnSoundM);
        ImageButton btnVibrateM = view.findViewById(R.id.btnVibrateM);
        ImageButton btnSpeakM = view.findViewById(R.id.btnSpeakM);
        ImageButton btnSoundN = view.findViewById(R.id.btnSoundN);
        ImageButton btnVibrateN = view.findViewById(R.id.btnVibrateN);
        ImageButton btnSpeakN = view.findViewById(R.id.btnSpeakN);
        ImageButton btnSoundO = view.findViewById(R.id.btnSoundO);
        ImageButton btnVibrateO = view.findViewById(R.id.btnVibrateO);
        ImageButton btnSpeakO = view.findViewById(R.id.btnSpeakO);
        ImageButton btnSoundP = view.findViewById(R.id.btnSoundP);
        ImageButton btnVibrateP = view.findViewById(R.id.btnVibrateP);
        ImageButton btnSpeakP = view.findViewById(R.id.btnSpeakP);
        ImageButton btnSoundQ = view.findViewById(R.id.btnSoundQ);
        ImageButton btnVibrateQ = view.findViewById(R.id.btnVibrateQ);
        ImageButton btnSpeakQ = view.findViewById(R.id.btnSpeakQ);
        ImageButton btnSoundR = view.findViewById(R.id.btnSoundR);
        ImageButton btnVibrateR = view.findViewById(R.id.btnVibrateR);
        ImageButton btnSpeakR = view.findViewById(R.id.btnSpeakR);
        ImageButton btnSoundS = view.findViewById(R.id.btnSoundS);
        ImageButton btnVibrateS = view.findViewById(R.id.btnVibrateS);
        ImageButton btnSpeakS = view.findViewById(R.id.btnSpeakS);
        ImageButton btnSoundT = view.findViewById(R.id.btnSoundT);
        ImageButton btnVibrateT = view.findViewById(R.id.btnVibrateT);
        ImageButton btnSpeakT = view.findViewById(R.id.btnSpeakT);
        ImageButton btnSoundU = view.findViewById(R.id.btnSoundU);
        ImageButton btnVibrateU = view.findViewById(R.id.btnVibrateU);
        ImageButton btnSpeakU = view.findViewById(R.id.btnSpeakU);
        ImageButton btnSoundV = view.findViewById(R.id.btnSoundV);
        ImageButton btnVibrateV = view.findViewById(R.id.btnVibrateV);
        ImageButton btnSpeakV = view.findViewById(R.id.btnSpeakV);
        ImageButton btnSoundW = view.findViewById(R.id.btnSoundW);
        ImageButton btnVibrateW = view.findViewById(R.id.btnVibrateW);
        ImageButton btnSpeakW = view.findViewById(R.id.btnSpeakW);
        ImageButton btnSoundX = view.findViewById(R.id.btnSoundX);
        ImageButton btnVibrateX = view.findViewById(R.id.btnVibrateX);
        ImageButton btnSpeakX = view.findViewById(R.id.btnSpeakX);
        ImageButton btnSoundY = view.findViewById(R.id.btnSoundY);
        ImageButton btnVibrateY = view.findViewById(R.id.btnVibrateY);
        ImageButton btnSpeakY = view.findViewById(R.id.btnSpeakY);
        ImageButton btnSoundZ = view.findViewById(R.id.btnSoundZ);
        ImageButton btnVibrateZ = view.findViewById(R.id.btnVibrateZ);
        ImageButton btnSpeakZ = view.findViewById(R.id.btnSpeakZ);
        ImageButton btnSoundZERO = view.findViewById(R.id.btnSoundZERO);
        ImageButton btnVibrateZERO = view.findViewById(R.id.btnVibrateZERO);
        ImageButton btnSpeakZERO = view.findViewById(R.id.btnSpeakZERO);
        ImageButton btnSoundONE = view.findViewById(R.id.btnSoundONE);
        ImageButton btnVibrateONE = view.findViewById(R.id.btnVibrateONE);
        ImageButton btnSpeakONE = view.findViewById(R.id.btnSpeakONE);
        ImageButton btnSoundTWO = view.findViewById(R.id.btnSoundTWO);
        ImageButton btnVibrateTWO = view.findViewById(R.id.btnVibrateTWO);
        ImageButton btnSpeakTWO = view.findViewById(R.id.btnSpeakTWO);
        ImageButton btnSoundTHREE = view.findViewById(R.id.btnSoundTHREE);
        ImageButton btnVibrateTHREE = view.findViewById(R.id.btnVibrateTHREE);
        ImageButton btnSpeakTHREE = view.findViewById(R.id.btnSpeakTHREE);
        ImageButton btnSoundFOUR = view.findViewById(R.id.btnSoundFOUR);
        ImageButton btnVibrateFOUR = view.findViewById(R.id.btnVibrateFOUR);
        ImageButton btnSpeakFOUR = view.findViewById(R.id.btnSpeakFOUR);
        ImageButton btnSoundFIVE = view.findViewById(R.id.btnSoundFIVE);
        ImageButton btnVibrateFIVE = view.findViewById(R.id.btnVibrateFIVE);
        ImageButton btnSpeakFIVE = view.findViewById(R.id.btnSpeakFIVE);
        ImageButton btnSoundSIX = view.findViewById(R.id.btnSoundSIX);
        ImageButton btnVibrateSIX = view.findViewById(R.id.btnVibrateSIX);
        ImageButton btnSpeakSIX = view.findViewById(R.id.btnSpeakSIX);
        ImageButton btnSoundSEVEN = view.findViewById(R.id.btnSoundSEVEN);
        ImageButton btnVibrateSEVEN = view.findViewById(R.id.btnVibrateSEVEN);
        ImageButton btnSpeakSEVEN = view.findViewById(R.id.btnSpeakSEVEN);
        ImageButton btnSoundEIGHT = view.findViewById(R.id.btnSoundEIGHT);
        ImageButton btnVibrateEIGHT = view.findViewById(R.id.btnVibrateEIGHT);
        ImageButton btnSpeakEIGHT = view.findViewById(R.id.btnSpeakEIGHT);
        ImageButton btnSoundNINE = view.findViewById(R.id.btnSoundNINE);
        ImageButton btnVibrateNINE = view.findViewById(R.id.btnVibrateNINE);
        ImageButton btnSpeakNINE = view.findViewById(R.id.btnSpeakNINE);

        tts = new TextToSpeech(getContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
            }
        });
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(2)
                .setAudioAttributes(audioAttributes)
                .build();

        dotSound = soundPool.load(requireContext(), R.raw.dot, 1);
        dashSound = soundPool.load(requireContext(), R.raw.dash, 1);


        btnSpeakA.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for A is Dot Dash", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
        btnSpeakB.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for B is Dash Dot Dot Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
        btnSpeakC.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for C is Dash Dot Dash Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakD.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for D is Dash Dot Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakE.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for E is Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
        btnSpeakF.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for F is Dot Dot Dash Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakG.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for G is Dash Dash Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakH.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for H is Dot Dot Dot Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
        btnSpeakI.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for I is Dot Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakJ.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for J is Dot Dash Dash Dash", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakK.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for K is Dash Dot Dash", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakL.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for L is Dot Dash Dot Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
        btnSpeakM.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for M is Dash Dash", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakN.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for N is Dash Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakO.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for O is Dash Dash Dash", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakP.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for P is Dot Dash Dash Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
        btnSpeakQ.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for Q is Dash Dash Dot Dash", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakR.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for R is Dot Dash Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakS.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for S is Dot Dot Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakT.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for T is Dash", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakU.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for U is Dot Dot Dash", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakV.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for V is Dot Dot Dot Dash", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakW.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for W is Dot Dash Dash", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakX.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for X is Dash Dot Dot Dash", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakY.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for Y is Dash Dot Dash Dash", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakZ.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for Z is Dash Dash Dot Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
        btnSpeakZERO.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for 0 is Dash Dash Dash Dash Dash", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakONE.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for 1 is Dot Dash Dash Dash Dash", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakTWO.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for 2 is Dot Dot Dash Dash Dash", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakTHREE.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for 3 is Dot Dot Dot Dash Dash", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakFOUR.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for 4 is Dot Dot Dot Dot Dash", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakFIVE.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for 5 is Dot Dot Dot Dot Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakSIX.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for 6 is Dash Dot Dot Dot Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakSEVEN.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for 7 is Dash Dash Dot Dot Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakEIGHT.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for 8 is Dash Dash Dash Dot Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

        btnSpeakNINE.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak("Morse code for 9 is Dash Dash Dash Dash Dot", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });



        btnSoundA.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash sound slower to make it longer
            }, 300);
        });
        btnSoundB.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 1000);
        });
        btnSoundC.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 900);
        });

        btnSoundD.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
        });

        btnSoundE.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
        });

        btnSoundF.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 900);
        });

        btnSoundG.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
        });

        btnSoundH.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 900);
        });

        btnSoundI.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
        });

        btnSoundJ.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 900);
        });
        btnSoundK.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
        });

        btnSoundL.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 0.5f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 900);
        });

        btnSoundM.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
        });

        btnSoundN.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
        });

        btnSoundO.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
        });

        btnSoundP.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 900);
        });

        btnSoundQ.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 900);
        });

        btnSoundR.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
        });

        btnSoundS.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
        });

        btnSoundT.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
        });
        btnSoundU.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
        });

        btnSoundV.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 900);
        });

        btnSoundW.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
        });

        btnSoundX.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
        });

        btnSoundY.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
        });

        btnSoundZ.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
        });
        btnSoundZERO.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 900);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f);
            }, 1100);
        });
        btnSoundONE.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            }, 900);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            }, 1100);
        });
        btnSoundTWO.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            }, 900);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            }, 1100);
        });
        btnSoundTHREE.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            }, 900);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            }, 1100);
        });
        btnSoundFOUR.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 900);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            }, 1100);
        });

        btnSoundFIVE.setOnClickListener(v -> {
            soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 900);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 1100);
        });
        btnSoundSIX.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 900);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 1100);
        });
        btnSoundSEVEN.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 900);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 1100);
        });
        btnSoundEIGHT.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 900);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 1100);
        });
        btnSoundNINE.setOnClickListener(v -> {
            soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            }, 500);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            }, 700);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dashSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dash
            }, 900);
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                soundPool.play(dotSound, 1.0f, 1.0f, 1, 0, 1.0f); // Dot
            }, 1100);
        });

        btnVibrateA.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200, 100, 400}; // Dot (200ms), pause (100ms), Dash (400ms)
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateB.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 400, 100, 200, 100, 200, 100, 200};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateC.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 300, 100, 200, 100, 300, 100, 200};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateD.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 300, 100, 200, 100, 200};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateE.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200}; // Single dot
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateF.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200, 100, 200, 100, 300, 100, 200};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateG.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 300, 100, 300, 100, 200};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateH.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200, 100, 200, 100, 200, 100, 200};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateI.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200, 100, 200};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateJ.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200, 100, 300, 100, 300, 100, 300};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateK.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 300, 100, 200, 100, 300};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateL.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200, 100, 300, 100, 200, 100, 200};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateM.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 300, 100, 300};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateN.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 300, 100, 200};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateO.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 300, 100, 300, 100, 300};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateP.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200, 100, 300, 100, 300, 100, 200};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateQ.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 300, 100, 300, 100, 200, 100, 300};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateR.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200, 100, 300, 100, 200};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateS.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200, 100, 200, 100, 200};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateT.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 300}; // Single Dash
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateU.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200, 100, 200, 100, 300};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateV.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200, 100, 200, 100, 200, 100, 300};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateW.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200, 100, 300, 100, 300};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateX.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 300, 100, 200, 100, 200, 100, 300};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateY.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 300, 100, 200, 100, 300, 100, 300};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateZ.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 300, 100, 300, 100, 200, 100, 200};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateZERO.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 300, 100, 300, 100, 300, 100, 300, 100, 300};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateONE.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200, 100, 300, 100, 300, 100, 300, 100, 300};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateTWO.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200, 100, 200, 100, 300, 100, 300, 100, 300};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateTHREE.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200, 100, 200, 100, 200, 100, 300, 100, 300};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateFOUR.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200, 100, 200, 100, 200, 100, 200, 100, 300};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateFIVE.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 200, 100, 200, 100, 200, 100, 200, 100, 200};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateSIX.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 300, 100, 200, 100, 200, 100, 200, 100, 200};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateSEVEN.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 300, 100, 300, 100, 200, 100, 200, 100, 200};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateEIGHT.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 300, 100, 300, 100, 300, 100, 200, 100, 200};
                vibrator.vibrate(pattern, -1); // -1 means no repetition
            }
        });
        btnVibrateNINE.setOnClickListener(v -> {
            Vibrator vibrator = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                long[] pattern = {0, 300, 100, 300, 100, 300, 100, 300, 100, 200};
                vibrator.vibrate(pattern, -1);
            }
        });


        return view;
    }
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}