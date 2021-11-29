package com.example.contadordecartas;

import com.example.contadordecartas.databinding.FragmentSecondBinding;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
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
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;

public class SecondFragment extends Fragment {
    private View view;
    private FragmentSecondBinding binding;

    public SecondFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater);
        view = binding.getRoot();

        Intent i = getActivity().getIntent();

        if (i != null) {
            Cards card = (Cards) i.getSerializableExtra("card");

            if (card != null) {
                updateUi(card);
            }
        }

        SharedViewModel sharedViewModel = ViewModelProviders.of(
                getActivity()
        ).get(SharedViewModel.class);
        sharedViewModel.getSelected().observe(getViewLifecycleOwner(), cards -> {
            updateUi(cards);
        });
        return view;
    }

    private void updateUi(Cards cards) {
        Log.d("CARD", cards.toString());

        binding.cardTitleDetailed.setText(cards.getName());
        binding.cardRarity.setText(cards.getRatity());
        binding.cardType.setText(cards.getType());
        binding.cardToughnessStrength.setText(cards.getToughness()+" / "+cards.getPower());
        Glide.with(getContext()).load(
                cards.getImageUrl()
        ).into(binding.cardImage);
    }
}