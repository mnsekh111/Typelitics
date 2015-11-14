package com.mns.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class ResultActivity extends Activity {

    int participants = -1;
    private LinearLayout llResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        participants = i.getIntExtra("Participant",2);
        setContentView(R.layout.activity_result);
        initViews();
    }

    private void initViews() {

        llResult = (LinearLayout) findViewById(R.id.llResult);
        for(int i=0;i<4;i++){
            View view = ((LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.template_user_race_stats,null);
            LinearLayout l = (LinearLayout)view;
            llResult.addView(l);
        }
    }

}
