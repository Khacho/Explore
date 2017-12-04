package com.khachik.explore.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.khachik.explore.Activities.ArticleActivity;
import com.khachik.explore.Activities.MainActivity;
import com.khachik.explore.Adapters.ArticlesAdapter;
import com.khachik.explore.Models.ArticlesModel;
import com.khachik.explore.R;
import com.khachik.explore.Requests.Requests;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView.Adapter adapter;
    private ArrayList<ArticlesModel> adapterItems;
    private Requests request;
    private FloatingActionButton fab;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        this.request = new Requests(getActivity());
        handleInstanceState(savedInstanceState, view);
//        this.fab = (FloatingActionButton) view.findViewById(R.id.fab);
//        this.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "FAB", Toast.LENGTH_SHORT).show();
//            }
//        });
//        setupRecyclerview(view);


        return view;
    }

    private void handleInstanceState(Bundle savedInstanceState, View view) {
        adapterItems = new ArrayList<>();
        request.getArticleByCountry("Vanadzor", view);
//        System.out.println("_________________________res______________________" + respons);
//        adapterItems.add(new ArticlesModel("Ardzan_1", "Biiiiiiiiiiiiiiiiiiiiiig data", "1963-08-31T20:00:00.000Z", "Vanadzor", "Armenia"));
//        adapterItems.add(new ArticlesModel("Ardzan_2", "Biiiiiiiiiiiiiiiiiiiiiig data", "1963-08-31T20:00:00.000Z", "Vanadzor", "Armenia"));
//        adapterItems.add(new ArticlesModel("Ardzan_3", "Biiiiiiiiiiiiiiiiiiiiiig data", "1963-08-31T20:00:00.000Z", "Vanadzor", "Armenia"));
//        adapterItems.add(new ArticlesModel("Ardzan_4", "Biiiiiiiiiiiiiiiiiiiiiig data", "1963-08-31T20:00:00.000Z", "Vanadzor", "Armenia"));
    }

    private void setupRecyclerview(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.home_recyclerview);
        adapter = new ArticlesAdapter(adapterItems, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }
}
