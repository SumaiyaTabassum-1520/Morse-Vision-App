package edu.ewubd.morsevision;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {


    private final MutableLiveData<String> description = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public MainViewModel() {

    }
    public void setIsloading(boolean loading){
        isLoading.postValue(loading);
    }
    public void setDescription(String des){
        description.postValue(des);
    }


    public LiveData<String> getDescription() {
        return description;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }
}

