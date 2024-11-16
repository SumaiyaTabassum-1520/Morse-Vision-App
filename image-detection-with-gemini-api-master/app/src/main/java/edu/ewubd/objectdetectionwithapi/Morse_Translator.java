package edu.ewubd.objectdetectionwithapi;


import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
public class Morse_Translator extends AppCompatActivity {
    // initialize variables
    EditText etinput, etoutput;
    Button btnEncode, btnDecode, btnclear, btnSound, btnRepeat, btnVibrate;

    private SoundPool soundPool;
    private int dotSound, dashSound;
    private MorseVibrator morseVibrator;
    private String lastAction = "";  // To track last action (sound or vibrate)
    private String morseCode = "";// Store the encoded Morse code
    final String[] AlphaNumeric1 = new String[37];
    final String[] AlphaNumeric = new String[37];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse_translator);

        // Initialize SoundPool and load the sounds
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(2)
                .setAudioAttributes(audioAttributes)
                .build();

        dotSound = soundPool.load(this, R.raw.dot, 1);
        dashSound = soundPool.load(this, R.raw.dash, 1);

        morseVibrator = new MorseVibrator(getApplicationContext());

        // Assign variables
        etinput = findViewById(R.id.etinput);
        // etinput.setText("Start typing from here");
        etinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed here
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    // Restore hint when no text is entered
                    etinput.setHint("Start texting from here");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Clear the hint when user starts typing
                if (s.length() > 0) {
                    etinput.setHint("");
                }
            }
        });
        etoutput = findViewById(R.id.etoutput);
        btnDecode = findViewById(R.id.btndecode);
        btnEncode = findViewById(R.id.btnencode);
        btnclear = findViewById(R.id.btnclear);
        btnVibrate =findViewById(R.id.btnVibrate);
        btnRepeat = findViewById(R.id.btnRepeat);
        btnSound = findViewById(R.id.btnSound);

//        AlphaNumeric[0] = "A";
//        AlphaNumeric[1] = "B";
//        AlphaNumeric[2] = "C";
//        AlphaNumeric[3] = "D";
//        AlphaNumeric[4] = "E";
//        AlphaNumeric[5] = "F";
//        AlphaNumeric[6] = "G";
//        AlphaNumeric[7] = "H";
//        AlphaNumeric[8] = "I";
//        AlphaNumeric[9] = "J";
//        AlphaNumeric[10] = "K";
//        AlphaNumeric[11] = "L";
//        AlphaNumeric[12] = "M";
//        AlphaNumeric[13] = "N";
//        AlphaNumeric[14] = "O";
//        AlphaNumeric[15] = "P";
//        AlphaNumeric[16] = "Q";
//        AlphaNumeric[17] = "R";
//        AlphaNumeric[18] = "S";
//        AlphaNumeric[19] = "T";
//        AlphaNumeric[20] = "U";
//        AlphaNumeric[21] = "V";
//        AlphaNumeric[22] = "W";
//        AlphaNumeric[23] = "X";
//        AlphaNumeric[24] = "Y";
//        AlphaNumeric[25] = "Z";
//        AlphaNumeric[26] = "0";
//        AlphaNumeric[27] = "1";
//        AlphaNumeric[28] = "2";
//        AlphaNumeric[29] = "3";
//        AlphaNumeric[30] = "4";
//        AlphaNumeric[31] = "5";
//        AlphaNumeric[32] = "6";
//        AlphaNumeric[33] = "7";
//        AlphaNumeric[34] = "8";
//        AlphaNumeric[35] = "9";
//        AlphaNumeric[36] = " ";


        AlphaNumeric1[0] = ".-";
        AlphaNumeric1[1] = "-...";
        AlphaNumeric1[2] = "-.-.";
        AlphaNumeric1[3] = "-..";
        AlphaNumeric1[4] = ".";
        AlphaNumeric1[5] = "..-.";
        AlphaNumeric1[6] = "--.";
        AlphaNumeric1[7] = "....";
        AlphaNumeric1[8] = "..";
        AlphaNumeric1[9] = ".---";
        AlphaNumeric1[10] = "-.-";
        AlphaNumeric1[11] = ".-..";
        AlphaNumeric1[12] = "--";
        AlphaNumeric1[13] = "-.";
        AlphaNumeric1[14] = "---";
        AlphaNumeric1[15] = ".--.";
        AlphaNumeric1[16] = "--.-";
        AlphaNumeric1[17] = ".-.";
        AlphaNumeric1[18] = "...";
        AlphaNumeric1[19] = "-";
        AlphaNumeric1[20] = "..-";
        AlphaNumeric1[21] = "...-";
        AlphaNumeric1[22] = ".--";
        AlphaNumeric1[23] = "-..-";
        AlphaNumeric1[24] = "-.--";
        AlphaNumeric1[25] = "--..";
        AlphaNumeric1[26] = "-----";
        AlphaNumeric1[27] = ".----";
        AlphaNumeric1[28] = "..---";
        AlphaNumeric1[29] = "...--";
        AlphaNumeric1[30] = "....-";
        AlphaNumeric1[31] = ".....";
        AlphaNumeric1[32] = "-....";
        AlphaNumeric1[33] = "--...";
        AlphaNumeric1[34] = "---..";
        AlphaNumeric1[35] = "----.";
        AlphaNumeric1[36] = "/";


        btnEncode.setOnClickListener(v -> {
            String morseCode = morseTranslate();
            displayOutput(morseCode);
        });
        btnSound.setOnClickListener(v -> {
            String input = etinput.getText().toString().toUpperCase();
            StringBuilder output = new StringBuilder();
            morseCode = "";  // Clear previous Morse code

            // Encoding logic (same as before)
            for (int i = 0; i < input.length(); i++) {
                String ch = input.substring(i, i + 1);
                for (int j = 0; j < AlphaNumeric.length; j++) {
                    if (ch.equals(AlphaNumeric[j])) {
                        output.append(AlphaNumeric1[j]).append(" ");
                        break;
                    }
                }
            }

            morseCode = output.toString();  // Store the encoded Morse code
            etoutput.setText(morseCode);    // Display in EditText

            // Play sound in parallel with writing
            if (!morseCode.isEmpty()) {
                playMorseCodeSound(morseCode);
                lastAction = "sound";  // Mark sound as the last action
            }
        });



        // Play sound for each Morse code symbol

        btnVibrate.setOnClickListener(v -> {
            // Encode the input text into Morse code
            String input = etinput.getText().toString().toUpperCase();
            StringBuilder output = new StringBuilder();
            morseCode = "";  // Clear previous Morse code

            // Encoding logic
            for (int i = 0; i < input.length(); i++) {
                String ch = input.substring(i, i + 1);
                for (int j = 0; j < AlphaNumeric.length; j++) {
                    if (ch.equals(AlphaNumeric[j])) {
                        output.append(AlphaNumeric1[j]).append(" ");
                        break;
                    }
                }
            }
            morseCode = output.toString();  // Store the encoded Morse code
            etoutput.setText(morseCode);    // Display in EditText

            // Vibrate according to the Morse code
            if (!morseCode.isEmpty()) {
                morseVibrator.vibrateMorseCode(morseCode);
                lastAction = "vibrate";  // Mark vibration as the last action
            }
        });


        btnRepeat.setOnClickListener(v -> {
            if (!morseCode.isEmpty()) {
                if (lastAction.equals("sound")) {
                    playMorseCodeSound(morseCode);  // Repeat sound
                } else if (lastAction.equals("vibrate")) {
                    morseVibrator.vibrateMorseCode(morseCode);  // Repeat vibration
                }
            }
        });


        btnclear.setOnClickListener(v -> {
            // When button clear is clicked then the
            // following lines inside this curly
            // braces will be executed

            // to clear the etinput
            etinput.setText("");

            // to clear etoutput
            etoutput.setText("");
        });
        btnDecode.setOnClickListener(v -> {
            // When button decode is clicked then the
            // following lines inside this curly
            // braces will be executed

            // to get the input given by the user as string
            String input1 = etinput.getText().toString();

            // to add space to the end of the string
            // because of the logic used in decoding
            String input = input1.concat(" ");

            // to get the length of the input string
            int l = input.length();

            // i and j are integer variables used in loops.
            // Variable p is used as the end index of
            // substring() function
            int i, j, p;

            // variable used as a starting
            // index of substring() function
            int pos = 0;

            // to store the extracted morse code
            // for each Alphabet,number or space
            String letter = "";

            // a to store the output in it
            String output = "";

            for (i = 0; i < l; i++) {

                // a variable used to trigger the j loop only
                // when the complete morse code of a letter
                // or number is extracted
                int flag = 0;

                // to extract each token at a time
                String ch = input.substring(i, i + 1);

                // if the extracted token is a space
                if (ch.equalsIgnoreCase(" ")) {

                    // to store the value of i in p
                    p = i;

                    // to extract the morse code for each letter or number
                    letter = input.substring(pos, p);

                    // to update the value of pos so that next
                    // time the morse code for the next letter
                    // or digit is extracted
                    pos = p + 1;

                    flag = 1;
                }
                String letter1 = letter.trim();
                // to delete extra whitespaces at
                // both ends in case there are any
                if (flag == 1) {
                    for (j = 0; j <= 36; j++) {
                        if (letter1.equalsIgnoreCase(AlphaNumeric1[j])) {
                            output = output.concat(AlphaNumeric[j]);
                            break;
                        }
                    }
                }
            }
            // to display the output
            etoutput.setText(output);
        });
    }

    private String getInput() {
        return etinput.getText().toString().toUpperCase();
    }
    public String morseTranslate(){
         String text = getInput();
        StringBuilder output = new StringBuilder();
        morseCode = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') {
                output.append(AlphaNumeric1[text.charAt(i) - 'A']);
            }
        }
        morseCode = output.toString();
        return text;
    }
    private void displayOutput(String morseCode) {
        etoutput.setText(morseCode);
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
                    Thread.sleep(500); // Adjust timing based on dot/dash duration
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}