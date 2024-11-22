package edu.ewubd.objectdetectionwithapi;

import android.graphics.Bitmap;
import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GeminiRepository {

    private GenerativeModelFutures model;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public GeminiRepository() {
        GenerativeModel gm = new GenerativeModel("gemini-1.5-flash", "AIzaSyAtHWZ3ymF15T-1oiU49ep4jf3z1SOTf0E");
        model = GenerativeModelFutures.from(gm);
    }

    // Method to interact with the API
    public ListenableFuture<GenerateContentResponse> generateDescription(Bitmap bitmap) {
        Content content = new Content.Builder()
                .addText("Describe this picture")
                .addImage(bitmap)
                .build();

        return model.generateContent(content);
    }
    public ListenableFuture<GenerateContentResponse> detectCurrency(Bitmap bitmap) {
        Content content = new Content.Builder()
                .addText("Identify the type and value of the currency in the image. If no currency is detected, respond with: 'No currency on the given picture'")
                .addImage(bitmap)
                .build();

        return model.generateContent(content);
    }

    public Executor getExecutor() {
        return executor;
    }
}