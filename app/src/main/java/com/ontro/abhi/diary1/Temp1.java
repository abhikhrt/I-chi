package com.ontro.abhi.diary1;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Date;

import static android.content.Intent.getIntent;
import static com.ontro.abhi.diary1.R.drawable.notbk;

public class Temp1 extends AppCompatActivity implements Maindry.OnFragmentInteractionListener, Note.OnFragmentInteractionListener, MapFragment.OnFragmentInteractionListener {


    //Bundle bndl=getIntent().getExtras();

    private TextView mTextDate;
    public Bundle args = new Bundle();

    String dates;

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);

                    Maindry fragment = new Maindry();
                    args.putString("dates", dates);
                    fragment.setArguments(args);
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment).commit();

                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);

                    Note fragment1 = new Note();
                    args.putString("dates", dates);
                    fragment1.setArguments(args);
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment1).commit();

                    return true;
                case R.id.navigation_world:
                    //mTextMessage.setText(R.string.title_home);

                    MapFragment fragment2 = new MapFragment();
                    args.putString("dates", dates);
                    fragment2.setArguments(args);
                    android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.content, fragment2).commit();

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException{


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp1);

        Intent intent = getIntent();
        int day = intent.getIntExtra("day", 0);
        int month = intent.getIntExtra("month", 0);

        int year = intent.getIntExtra("year", 0);
        Date d = new Date(year,month,day);
        dates = d.toString();


        mTextDate = (TextView)findViewById(R.id.etdate);
        mTextDate.setText(day+"/"+month+"/"+year);


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        Maindry fragment3 = new Maindry();
        args.putString("dates", dates);
        fragment3.setArguments(args);
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment3).commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
