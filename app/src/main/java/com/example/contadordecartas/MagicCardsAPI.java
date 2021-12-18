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

            Log.d("DEBUG",""+jsonCards.length());

            for (int i = 0; i < jsonCards.length(); i++) {
                JSONObject jsonCard = jsonCards.getJSONObject(i);

                Cards card = new Cards();
                card.setName(jsonCard.has("name")?jsonCard.getString("name"):"Desconocido");
                card.setManaCost(jsonCard.has("cmc")?jsonCard.getDouble("cmc"):0);
                card.setLayout(jsonCard.has("layout")?jsonCard.getString("layout"):"Desconocido");
                card.setType(jsonCard.has("type")?jsonCard.getString("type"):"Desconocido");
                card.setRatity(jsonCard.has("rarity")?jsonCard.getString("rarity"):"Desconocido");
                try {
                    card.setPower(jsonCard.has("power")? jsonCard.getInt("power"):0);
                    card.setToughness(jsonCard.has("toughness")?jsonCard.getInt("toughness"):0);
                } catch (Exception e) {
                    card.setPower(0);
                    card.setToughness(0);
                }
                card.setImageUrl(jsonCard.has("imageUrl")?jsonCard.getString("imageUrl"):"Desconocido");

                Log.d("DEBUG",""+i);

                cards.add(card);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cards;
    }
}
