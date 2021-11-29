package com.example.contadordecartas;

import com.example.contadordecartas.databinding.CardsRowBinding;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;

import java.util.List;

public class CardsAdapter extends ArrayAdapter<Cards> {
    public CardsAdapter(Context context, int resource, List<Cards> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Cards cards = getItem(position);

        CardsRowBinding binding = null;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.cards_row,parent,false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }

        binding.cardTitle.setText(cards.getName());
        binding.rarity.setText(cards.getRatity());

        Glide.with(getContext())
                .load(cards.getImageUrl())
                .into(binding.cardPic);

        return binding.getRoot();
    }
}
