package com.khachik.explore.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.khachik.explore.R;
import com.khachik.explore.Requests.Requests;

import java.io.IOException;

public class ScanFragment extends Fragment  {

    private ImageView scanButton;
    private Requests request;

    public ScanFragment() {
        this.request = new Requests();
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
        this.request = new Requests();
        this.scanButton = (ImageView) view.findViewById(R.id.scan);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity());
//                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
//                intentIntegrator.setPrompt("Scan");
//                intentIntegrator.setCameraId(0);
//                intentIntegrator.initiateScan();
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                //intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, 0);
            }
        });
        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//
//        if(result != null) {
//            if(result.getContents() == null) {
//                Toast.makeText(getActivity(), "You canceled the scanning.assa", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getActivity(), result.getContents(), Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
        if (requestCode == 0) {
            if (resultCode != 0) {
                String contents = data.getStringExtra("SCAN_RESULT");
                String format = data.getStringExtra("SCAN_RESULT_FORMAT");
                Toast.makeText(getActivity(), contents, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), request.getArticleById(1), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
