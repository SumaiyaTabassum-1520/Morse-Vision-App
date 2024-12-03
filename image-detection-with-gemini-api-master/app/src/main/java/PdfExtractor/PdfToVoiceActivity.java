package PdfExtractor;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.OpenableColumns;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.ewubd.morsevision.R;

public class PdfToVoiceActivity extends AppCompatActivity {

    private final int CHOOSE_PDF_FROM_DEVICE = 1001;
    private static final String Tag ="PdfToVoiceActivity";
    private float x1,x2,y1,y2;

    private Button chooseBtn, previousBtn, nextBtn;
    private ImageButton voiceConverter;
    private TextView pdfTv, filePathTv, pageNumber;
    private TextToSpeech textToSpeech;
    private Book book;
    private int currentPage = 0;
    private static int MIN_DISTANCE = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button press
                finish();  // Close the current activity
            }
        });

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pdf_to_voice);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        chooseBtn = findViewById(R.id.ChooseBtn);

        pdfTv = findViewById(R.id.pdfTv);
        filePathTv = findViewById(R.id.pathTv);
        pageNumber = findViewById(R.id.pageNumberTv);
        previousBtn = findViewById(R.id.previousBtn);
        nextBtn = findViewById(R.id.nextBtn);
        voiceConverter = findViewById(R.id.voiceBtn);

        textToSpeech = new TextToSpeech(getApplicationContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                // Get the user's preferred locale
                Locale preferredLocale = Locale.getDefault();

                // Try setting the preferred locale
                int result = textToSpeech.setLanguage(preferredLocale);

                // Handle cases where the preferred locale is not supported
                if (result == TextToSpeech.LANG_MISSING_DATA ||
                        result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Fallback to a default locale (e.g., US English)
                    textToSpeech.setLanguage(Locale.US);
                }
            } else {
                Log.e("TTS", "Initialization failed!");
            }
        });

        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFileFromDevice();

            }
        });




        nextBtn.setOnClickListener(v -> {
            if (book != null && currentPage < book.getNumberOfPages() - 1) {
                currentPage++; // Move to the next page
                displayPageContent(currentPage);
                pageNumber.setText(currentPage+1 + "/" + book.getNumberOfPages());
            }
            else{
                makeToast("No more pages");
            }
        });
        previousBtn.setOnClickListener(v -> {
            if (book != null && currentPage > 0) {
                currentPage--; // Move to the previous page
                displayPageContent(currentPage);
                pageNumber.setText(currentPage+1 + "/" + book.getNumberOfPages());
            }
            else{
                makeToast("No more pages");
            }
        });
        voiceConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakCurrentPage();
            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                float valueX = x2 - x1;

                if (Math.abs(valueX) > MIN_DISTANCE) {

                    if (x2 < x1) {
                        nextBtn.performClick();
                    } else {
                        previousBtn.performClick();
                    }
                }
        }
        return false;
    }

    private int getCurrentPageNumber() {
        String pageText = pageNumber.getText().toString().trim();
        String regex = "(\\d+)\\s*/"; // Pattern to match current page number
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pageText);

        if (matcher.find()) {
            try {
                return Integer.parseInt(matcher.group(1)); // Get the first capturing group
            } catch (NumberFormatException e) {
                Log.e(Tag, "Error parsing current page number: " + e.getMessage());
            }
        } else {
            Log.e(Tag, "Current page number format is incorrect.");
        }
        return 0; // Default to 0 if no valid number is found
    }


    private void speakCurrentPage() {
        if (textToSpeech != null && book != null) {
            // Get the current page number using the regex method
            int currentPage = getCurrentPageNumber() -1; // Use the regex method here

            if (currentPage >= 0 && currentPage < book.getNumberOfPages()) {
                String pageContent = book.getPageContent(currentPage);

                if (pageContent != null && !pageContent.isEmpty()) {
                    // Check TTS engine initialization status before speaking
                    if (textToSpeech.isSpeaking()) {
                        textToSpeech.stop(); // Stop current speech if any
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        textToSpeech.speak(pageContent, TextToSpeech.QUEUE_FLUSH, null, null);
                    } else {
                        textToSpeech.speak(pageContent, TextToSpeech.QUEUE_FLUSH, null);
                    }

                } else {
                    // Handle case where page content is empty or null
                    Toast.makeText(this, "Page content is empty or unavailable", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Handle invalid page number
                Toast.makeText(this, "Invalid page number", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Handle case where textToSpeech or book is not initialized
            Toast.makeText(this, "Text-to-speech engine or book is not initialized", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        // Stop TTS if it's speaking
        if (textToSpeech != null) {
            if (textToSpeech.isSpeaking()) {
                textToSpeech.stop();  // Stop the current speech
            }
            textToSpeech.shutdown();  // Release TTS resources
        }
        super.onDestroy();  // Call the superclass method
    }


    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void chooseFileFromDevice(){
        if (book!=null && currentPage > 0){
            book.clearPages();
        }
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        startActivityForResult(intent,CHOOSE_PDF_FROM_DEVICE);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData){
        super.onActivityResult(requestCode, resultCode, resultData);
        if(requestCode == CHOOSE_PDF_FROM_DEVICE && resultCode == RESULT_OK){
            if(resultData!=null){
                Uri uri = resultData.getData();
                String fileName = getFileNameFromUri(uri);
                Log.d(Tag, "onActivityResult: " +resultData.getData());
                filePathTv.setText(fileName);
                extractTextFromPdf(resultData.getData());
            }
        }
    }

    private String getFileNameFromUri(Uri uri) {
        String fileName = null;
        String scheme = uri.getScheme();
        if (scheme != null && scheme.equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int displayNameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (displayNameIndex != -1) {
                        fileName = cursor.getString(displayNameIndex);
                    }
                }
            }
        } else if (scheme != null && scheme.equals("file")) {
            fileName = uri.getLastPathSegment();
        }
        return fileName;
    }

    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

    InputStream inputStream;


    private void extractTextFromPdf(Uri uri) {

        try {
            inputStream = PdfToVoiceActivity.this.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Execute the task in the background using ExecutorService
        executor.execute(() -> {
            try {
                PdfReader reader = new PdfReader(inputStream);
                int totalPages = reader.getNumberOfPages();

                // Initialize the book object with the number of pages
                book = new Book(totalPages);

                // Extract text from each page and store in the Book object
                for (int i = 1; i <= totalPages; i++) {
                    String pageContent = PdfTextExtractor.getTextFromPage(reader, i);


                    book.setPageContent(i - 1, pageContent); // Set the content for the corresponding page

                }

                // Close reader and input stream
                reader.close();
                inputStream.close();

                // Update the UI to show the first page
                handler.post(() -> {
                    displayPageContent(0); // Display the first page (page 0)

                    // Update page number to show the first page (1-based index)
                    pageNumber.setText("1/" + book.getNumberOfPages());
                });



            } catch (IOException e) {
                Log.d("TAG", "Error reading PDF: " + e.getMessage());
            }
        });
    }

    private void displayPageContent(int pageNumber) {
        if (book != null) {
            String pageContent = book.getPageContent(pageNumber);
            pdfTv.setText(pageContent);
        }
    }




}