package com.example.contadordecartas;

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
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.contadordecartas.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstFragment extends Fragment {
    private ListView cardsMagic;
    private ArrayAdapter<Cards> adapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ArrayList<Cards>items = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.cards_row,
                R.id.cardsRow,
                items
        );
        cardsMagic = view.findViewById(R.id.cardMagic);
        cardsMagic.setAdapter(adapter);
        return view;

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
//  some stuff to change
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

//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }

}