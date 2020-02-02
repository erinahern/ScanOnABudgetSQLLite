package com.mad1.scanonabudget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class tab2content extends Fragment{
    RecyclerView recyclerView;
    ListAdapter adapter;
    ScanDB db;
    List<ListTable> ListTableList;

    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab2, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);

        db = new ScanDB(tab2content.this.getActivity());

        try {
            ListTableList = new ArrayList<>();
            ListTableList = db.getAllItemFromList();
        }catch (RuntimeException E){
            Toast.makeText(tab2content.this.getActivity(), "doesnt like DB", Toast.LENGTH_SHORT).show();
        }

        adapter = new ListAdapter(tab2content.this.getActivity(), ListTableList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(tab2content.this.getActivity()));
        recyclerView.setAdapter(adapter);
        return root;
    }
    @Override
    public void onResume() {

        super.onResume();

        try {
            ListTableList = new ArrayList<>();
            ListTableList = db.getAllItemFromList();
        }catch (RuntimeException E){
            Toast.makeText(tab2content.this.getActivity(), "doesnt like DB", Toast.LENGTH_SHORT).show();
        }

        adapter = new ListAdapter(tab2content.this.getActivity(), ListTableList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(tab2content.this.getActivity()));
        recyclerView.setAdapter(adapter);
    }

}
