package com.khachik.explore.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xacho on 23.12.17.
 */

public class AddToTheSharedPrefs {
    private static final AddToTheSharedPrefs ourInstance = new AddToTheSharedPrefs();

    public static final String PREFS_NAME = "EXPLORE_PREFS";
    public static final String FAVORITES = "Favorite";

    public static AddToTheSharedPrefs getInstance() {
        return ourInstance;
    }

    private AddToTheSharedPrefs() {
    }


    public void saveFavorites(Context context, List<String> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, String id) {
        List<String> favorites = (List) getFavorites(context);
        boolean found = false;
        if (favorites == null)
            favorites = new ArrayList<String>();
//        for(int i = 0; i < favorites.size(); ++i) {
//            if(favorites.get(i) != id) {
//                continue;
//            } else {
//                found = true;
//            }
//        }
        if(!favorites.contains(id)) {
            System.out.println("ADDED !!!");
            favorites.add(id);
            saveFavorites(context, favorites);
        }
    }

    public void removeFavorite(Context context, String id) {
        ArrayList<String> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(id);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<String> getFavorites(Context context) {
        SharedPreferences settings;
        List<String> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            String[] favoriteItems = gson.fromJson(jsonFavorites,
                    String[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<String>(favorites);
        } else
            return null;

        return (ArrayList<String>) favorites;
    }



}
