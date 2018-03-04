package com.khachik.explore.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khachik.explore.Models.ArticlesModel;
import com.khachik.explore.R;
import com.google.android.gms.plus.PlusOneButton;
import com.khachik.explore.Requests.Requests;
import com.khachik.explore.utils.AddToTheSharedPrefs;

import java.util.ArrayList;


public class FavoritesFragment extends Fragment {
    private Requests request;
    private ArrayList<ArticlesModel> adapterItems;
    private AddToTheSharedPrefs addToTheSharedPrefs;



    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        this.addToTheSharedPrefs = AddToTheSharedPrefs.getInstance();
        this.request = new Requests(getActivity());
        handleInstanceState(savedInstanceState, view);

        return view;
    }

    private void handleInstanceState(Bundle savedInstanceState, View view) {
        request.getFavoriteArticles(this.addToTheSharedPrefs.getFavorites(this.getContext()),view);
    }

}
