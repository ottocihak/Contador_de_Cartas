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

import androidx.fragment.app.Fragment;

import com.example.contadordecartas.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ArrayList<Carts> items;
    private ListView cartsMagic;
    private ArrayAdapter<Carts> adapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);


        String[] data = {
                "Los 400 golpes",
                "El odio",
                "El padrino",
                "El padrino. Parte II",
                "Ocurrió cerca de su casa",
                "Infiltrados",
                "Umberto D.",
                "Roberto"
        };

//        items = new ArrayList<>(Arrays.asList(data));

        cartsMagic = view.findViewById(R.id.cartMagic);

        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.carts_row,
                R.id.cartsRow,
                items
        );
        cartsMagic.setAdapter(adapter);

        return view;

    }

    private class RefreshDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            MagicCartsAPI api = new MagicCartsAPI();
            ArrayList<Carts> result = api.getPeliculesMesVistes("es");
//  some stuff to change
            Log.d("DEBUG", result.toString());

            return null;
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
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