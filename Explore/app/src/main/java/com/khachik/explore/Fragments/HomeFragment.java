package com.khachik.explore.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.khachik.explore.R;
import com.khachik.explore.Requests.Requests;

public class HomeFragment extends Fragment {

    private RecyclerView.Adapter adapter;
    private Requests request;
    private FloatingActionButton fab;
    private String city;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            this.city= bundle.getString("city");
        }
        this.request = new Requests(getActivity());
        handleInstanceState(savedInstanceState, view);

        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
                openScanner();
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
        if(this.city != null) {
            request.getArticleByCountry(this.city, view);
            System.out.println("Not null");
        } else {
            System.out.println("null");
            request.getArticleByCountry("Vanadzor", view);
        }
    }


    public void openScanner() {
//        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
//        intent.putExtra("PROMPT_MESSAGE", "Qr code scanner.");
//        startActivityForResult(intent, 0);
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Qr code scanner");
        integrator.setCameraId(0);
        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) if (resultCode != 0) {
            String id = data.getStringExtra("SCAN_RESULT");
            request.getArticleById(id);
        } else {
            Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
        }
    }
}
