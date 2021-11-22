package com.example.contadordecartas;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.contadordecartas.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstFragment extends Fragment {
    private ListView cardsMagic;
    private CardsAdapter adapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        cardsMagic = view.findViewById(R.id.cardMagic);

        ArrayList<Cards> items = new ArrayList<>();

        adapter = new CardsAdapter(
                getContext(),
                R.layout.cards_row,
                items
        );

        cardsMagic.setAdapter(adapter);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }


    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Cards>> {
        @Override
        protected ArrayList<Cards> doInBackground(Void... voids) {
            MagicCardsAPI api = new MagicCardsAPI();
            ArrayList<Cards> result = api.getCards();
            Log.d("DEBUG", result.toString());
            return result;
        }
        @Override
        protected void onPostExecute(ArrayList<Cards> cards) {
            adapter.clear();
            for (Cards card : cards) {
                adapter.add(card);
            }
        }

    }
    public void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    private void refresh() {
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

}
