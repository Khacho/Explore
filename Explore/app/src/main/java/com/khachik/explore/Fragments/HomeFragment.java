package com.khachik.explore.Fragments;

import android.content.ClipData;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.home_recyclerview);
        // Show hide fab when recycle view scrolls.
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        fab.show();
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        fab.hide();
                        break;
                }

            }

//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                fab.hide();
//            }
        });
        fab.show();


        return view;
    }

    private void handleInstanceState(Bundle savedInstanceState, View view) {
        adapterItems = new ArrayList<>();
        request.getArticleByCountry("Vanadzor", view);
    }



}
