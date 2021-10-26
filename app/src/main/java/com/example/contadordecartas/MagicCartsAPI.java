package com.example.contadordecartas;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MagicCartsAPI {
    private final String BASE_URL = "https://api.magicthegathering.io";
    private final String API_KEY = "<api-key>";

    ArrayList<Carts> getPeliculesMesVistes(String pais) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("discover")
                .appendPath("movie")
                .appendQueryParameter("region", pais)
                .appendQueryParameter("api_key", API_KEY)
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }

    ArrayList<Carts> getProximesEstrenes(String pais) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("movie")
                .appendPath("upcoming")
                .appendQueryParameter("region", pais)
                .appendQueryParameter("api_key", API_KEY)
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }

    private ArrayList<Carts> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJson(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Carts> processJson(String jsonResponse) {
        ArrayList<Carts> carts = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonCarts = data.getJSONArray("results");
            for (int i = 0; i < jsonCarts.length(); i++) {
                JSONObject jsonCart = jsonCarts.getJSONObject(i);

                Carts cart = new Carts();
                cart.setName(jsonCart.getString("name"));
                cart.setManaCost(jsonCart.getInt("cmc"));
                cart.setLayout(jsonCart.getString("layout"));
                cart.setType(jsonCart.getString("type"));
                cart.setRatity(jsonCart.getString("rarity"));
                cart.setPower(jsonCart.getInt("power"));
                cart.setToughness(jsonCart.getInt("toughness"));

                carts.add(cart);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return carts;
    }
}
