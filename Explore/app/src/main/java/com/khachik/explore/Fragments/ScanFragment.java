package com.khachik.explore.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.RequestQueue;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.khachik.explore.R;
import com.khachik.explore.Requests.Requests;

import java.io.IOException;

public class ScanFragment extends Fragment  {

    private ImageView scanButton;
    private Requests request;

    public ScanFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_scan, container, false);
        this.request = new Requests(getActivity());
        this.scanButton = (ImageView) view.findViewById(R.id.scan);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScanner();
            }
        });
        return view;

    }

    public void openScanner() {
//        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
//        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
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
            String contents = data.getStringExtra("SCAN_RESULT");
            Toast.makeText(getActivity(), contents, Toast.LENGTH_SHORT).show();
            request.getArticleById(contents);
        } else {
            Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
        }
    }

}
