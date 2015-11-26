package com.mns.typelytics;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.mns.typelytics.fragments.LeaderFragment;


public class LeaderboardActivity extends FragmentActivity {
    private Spinner sp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        sp = (Spinner) findViewById(R.id.spinnerKeyboard);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addFragment();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        addFragment();
    }

    private void addFragment() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LeaderFragment frag = new LeaderFragment();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.llFragContainer, frag);
                ft.commit();
            }
        });

    }
}
