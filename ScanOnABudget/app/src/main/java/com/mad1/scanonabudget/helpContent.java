package com.mad1.scanonabudget;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mad1.scanonabudget.ui.main.SectionsPagerAdapter;

public class helpContent extends AppCompatActivity implements View.OnClickListener {
    ImageView backimageView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        backimageView = findViewById(R.id.backimageView);
        backimageView.setOnClickListener(this);
    }
    public void onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
