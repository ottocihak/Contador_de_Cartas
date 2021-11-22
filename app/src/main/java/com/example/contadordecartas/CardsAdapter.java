package com.example.contadordecartas;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CardsAdapter extends ArrayAdapter<Cards> {
    public CardsAdapter(Context context, int resource, List<Cards> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Cards cards = getItem(position);
        Log.w("XXXX", cards.toString());

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.cards_row, parent, false);
        }

        TextView cardTitle = convertView.findViewById(R.id.cardTitle);
        TextView rarity = convertView.findViewById(R.id.rarity);
        ImageView cardPic = convertView.findViewById(R.id.cardPic);

        cardTitle.setText(cards.getName());
        rarity.setText(cards.getRatity());

        Glide.with(getContext())
                .load(cards.getImageUrl())
                .into(cardPic);

        return convertView;
    }
}
