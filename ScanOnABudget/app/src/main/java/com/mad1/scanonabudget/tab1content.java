package com.mad1.scanonabudget;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mad1.scanonabudget.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class tab1content extends Fragment implements View.OnClickListener{
    RecyclerView recyclerView;
    ItemAdapter adapter;
    private ScanDB db;
    List<Item> ItemList;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab1, container, false);
        Button btnscan = (Button) root.findViewById(R.id.buttonScan);
        btnscan.setOnClickListener(this);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        db = new ScanDB(getContext());
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");
                String format = data.getStringExtra("SCAN_RESULT_FORMAT");
                if (contents == null) {
                    Toast.makeText(tab1content.this.getActivity(), "Result Not Found", Toast.LENGTH_SHORT).show();
                } else {
                   try {
                        ActivityCompat.requestPermissions(tab1content.this.getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);
                        GpsTracker gt = new GpsTracker(getContext().getApplicationContext());
                        Location l = gt.getLocation();
                        if( l == null){
                            ItemList = new ArrayList<>();
                            ItemList = db.getAllItemFromBarcode(contents);
                            if(ItemList == null){
                                Toast.makeText(tab1content.this.getActivity(), "No Results", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(getContext().getApplicationContext(),"GPS unable to get Value",Toast.LENGTH_SHORT).show();
                        }else {
                            int latNear;
                            int lonNear;
                            double lat = l.getLatitude();
                            double lon = l.getLongitude();
                            latNear = (int) lat;
                            lonNear = (int) lon;
                            //Toast.makeText(getContext().getApplicationContext(), "GPS Lat = " + lat +" latNear = " + latNear+  "\n lon = " + lon + " lonNear = " +lonNear, Toast.LENGTH_SHORT).show();
                            ItemList = new ArrayList<>();
                            ItemList = db.getAllItemFromBarcodewithlocation(contents, latNear, lonNear);
                            if(ItemList == null){
                                Toast.makeText(tab1content.this.getActivity(), "No Results", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }catch (RuntimeException E){
                        Toast.makeText(tab1content.this.getActivity(), "Database Error", Toast.LENGTH_SHORT).show();
                   }
                    adapter = new ItemAdapter(tab1content.this.getActivity(), ItemList);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(tab1content.this.getActivity()));
                    recyclerView.setAdapter(adapter);

                    //then make results page
                    AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(tab1content.this.getActivity());
                    if(ItemList.size() == 0){
                        alertdialogbuilder.setMessage("No Results");
                        //Toast.makeText(tab1content.this.getActivity(), "No Results", Toast.LENGTH_SHORT).show();
                    }
                    alertdialogbuilder.setMessage("Scan Again ?");
                    alertdialogbuilder.setTitle("Result Scanned");
                    alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                       public void onClick(DialogInterface dialog, int which) {
                            scanow();
                       }
                    });
                   alertdialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //finish();
                       }
                    });
                    AlertDialog alertDialog = alertdialogbuilder.create();
                    alertDialog.show();
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    public void scanow() {
        Intent intent = new Intent(tab1content.this.getActivity(), Portrait.class);
        intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
        
        startActivityForResult(intent, 0);
    }

    @Override
    public void onClick(View v) {
        scanow();
    }
    @Override
    public void onResume(){
        super.onResume();

    }
}

