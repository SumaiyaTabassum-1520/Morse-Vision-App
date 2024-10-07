package edu.ewubd.objectdetectionwithapi;

import android.graphics.Bitmap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

public class MainViewModel extends ViewModel {

    private final GeminiRepository geminiRepository;
    private final MutableLiveData<String> description = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public MainViewModel() {
        geminiRepository = new GeminiRepository();
    }

    public LiveData<String> getDescription() {
        return description;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void describePicture(Bitmap bitmap) {
        isLoading.setValue(true);

        Futures.addCallback(geminiRepository.generateDescription(bitmap), new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                isLoading.postValue(false);
                description.postValue(result.getText());
            }

            @Override
            public void onFailure(Throwable t) {
                isLoading.postValue(false);
                description.postValue("Error: " + t.getMessage());
            }
        }, geminiRepository.getExecutor());
    }
}

