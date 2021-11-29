package com.example.contadordecartas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Cards> selected = new MutableLiveData<Cards>();

    public void select(Cards cards) {
        selected.setValue(cards);
    }
    public LiveData<Cards> getSelected() {
        return selected;
    }
}
