package com.ontro.abhi.diary1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class choose_date1 extends AppCompatActivity {


    DatePicker DOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_date1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        mydb = new DatabaseHelper(this);
        DOB = (DatePicker)findViewById(R.id.datePicker);


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = DOB.getDayOfMonth();
                int month = DOB.getMonth()+1;
                int year = DOB.getYear();

                Intent i = new Intent(choose_date1.this, Temp1.class);

                i.putExtra("day",day);
                i.putExtra("year",year);
                i.putExtra("month",month);

                startActivity(i);
            }
        });
    }

}
