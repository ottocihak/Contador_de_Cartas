package com.example.contadordecartas;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class CardsViewModel extends AndroidViewModel {

    private final Application app;
    private final DataManager dataManager;
    private final CardsDao cardsDao;
    private LiveData<List<Cards>> cards;

    public CardsViewModel (Application application) {
        super(application);

        this.app = application;
        this.dataManager = DataManager.getDatabase(
                this.getApplication());
        this.cardsDao = dataManager.getCardsDao();
    }

    public LiveData<List<Cards>> getCards() {
        return cardsDao.getCards();
    }

    public void reload() {
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    private class RefreshDataTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            MagicCardsAPI api = new MagicCardsAPI();
            ArrayList<Cards> result;

            result = api.getCards();


            cardsDao.deleteCards();
            cardsDao.addCards(result);

            return null;
        }
    }
}
