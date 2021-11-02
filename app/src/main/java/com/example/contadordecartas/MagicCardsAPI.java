package com.example.contadordecartas;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MagicCardsAPI {
    private final String BASE_URL = "https://api.magicthegathering.io";

    ArrayList<Cards> getCards() {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("v1")
                .appendPath("cards")
                .build();
        String url = builtUri.toString();
        return doCall(url);
    }

    private ArrayList<Cards> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJson(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Cards> processJson(String jsonResponse) {
        ArrayList<Cards> cards = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonCards = data.getJSONArray("cards");
            for (int i = 0; i < jsonCards.length(); i++) {
                JSONObject jsonCard = jsonCards.getJSONObject(i);

                Cards card = new Cards();
                card.setName(jsonCard.getString("name"));
                card.setManaCost(jsonCard.getInt("cmc"));
                card.setLayout(jsonCard.getString("layout"));
                card.setType(jsonCard.getString("type"));
                card.setRatity(jsonCard.getString("rarity"));
                card.setPower(jsonCard.getInt("power"));
                card.setToughness(jsonCard.getInt("toughness"));


                cards.add(card);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cards;
    }
}
