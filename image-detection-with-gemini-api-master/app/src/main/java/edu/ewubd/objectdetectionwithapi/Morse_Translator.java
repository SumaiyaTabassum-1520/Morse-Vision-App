package edu.ewubd.objectdetectionwithapi;


import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
public class Morse_Translator{
    // initialize variables

    private SoundPool soundPool;
    private int dotSound, dashSound;
    private MorseVibrator morseVibrator;
    private String lastAction = "";  // To track last action (sound or vibrate)
    private String morseCode = "";// Store the encoded Morse code
    final String[] AlphaNumeric1 = new String[37];
    public Morse_Translator() {
        AlphaNumeric1[0] = ".-";//a
        AlphaNumeric1[1] = "-...";//b
        AlphaNumeric1[2] = "-.-.";//c
        AlphaNumeric1[3] = "-..";//d
        AlphaNumeric1[4] = ".";//e
        AlphaNumeric1[5] = "..-.";//f
        AlphaNumeric1[6] = "--.";//g
        AlphaNumeric1[7] = "....";//h
        AlphaNumeric1[8] = "..";//i
        AlphaNumeric1[9] = ".---";//j
        AlphaNumeric1[10] = "-.-";//k
        AlphaNumeric1[11] = ".-..";//l
        AlphaNumeric1[12] = "--";//m
        AlphaNumeric1[13] = "-.";//n
        AlphaNumeric1[14] = "---";//o
        AlphaNumeric1[15] = ".--.";//P
        AlphaNumeric1[16] = "--.-";//Q
        AlphaNumeric1[17] = ".-.";//R
        AlphaNumeric1[18] = "...";//S
        AlphaNumeric1[19] = "-";//T
        AlphaNumeric1[20] = "..-";//U
        AlphaNumeric1[21] = "...-";//V
        AlphaNumeric1[22] = ".--";//W
        AlphaNumeric1[23] = "-..-";//X
        AlphaNumeric1[24] = "-.--";//Y
        AlphaNumeric1[25] = "--..";//Z
        AlphaNumeric1[26] = "-----";//0
        AlphaNumeric1[27] = ".----";//1
        AlphaNumeric1[28] = "..---";//2
        AlphaNumeric1[29] = "...--";//3
        AlphaNumeric1[30] = "....-";//4
        AlphaNumeric1[31] = ".....";//5
        AlphaNumeric1[32] = "-....";//6
        AlphaNumeric1[33] = "--...";//7
        AlphaNumeric1[34] = "---..";//8
        AlphaNumeric1[35] = "----.";//9
        AlphaNumeric1[36] = "/";
    }
    public String morseTranslate(String inputText){
        inputText=inputText.toUpperCase();
        StringBuilder output = new StringBuilder();
        morseCode = "";
        for (int i = 0; i < inputText.length(); i++) {
            if (inputText.charAt(i) >= 'A' && inputText.charAt(i) <= 'Z') {
                int idx = inputText.charAt(i) - 'A';
                String morseLetter = AlphaNumeric1[idx];
                output.append(morseLetter);
            }
            else if(inputText.charAt(i)>='0' && inputText.charAt(i)<='9'){
                int idx = (inputText.charAt(i)-'0') + 26;
                String morseNumber=AlphaNumeric1[idx];
                output.append(morseNumber);
            }
            else if(inputText.charAt(i)==' '){
                output.append(' ');
            }
        }
        morseCode = output.toString();
        return morseCode;
    }


    private void playMorseCodeSound(String morseCode) {
        new Thread(() -> {
            for (char symbol : morseCode.toCharArray()) {
                if (symbol == '.') {
                    soundPool.play(dotSound, 1, 1, 0, 0, 1);
                } else if (symbol == '-') {
                    soundPool.play(dashSound, 1, 1, 0, 0, 1);
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}