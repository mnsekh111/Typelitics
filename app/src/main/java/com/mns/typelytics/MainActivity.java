package com.mns.typelytics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    Button btnNewRace,btnSettings,btnPractice,btnLb,btnStats;
    TextView tvGame,tvOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        btnNewRace = (Button) findViewById(R.id.btnNewRace);
        btnPractice =  (Button) findViewById(R.id.btnPractice);
        btnSettings = (Button) findViewById(R.id.btnSettings);
        btnLb = (Button) findViewById(R.id.btnLeaderboard);
        btnStats = (Button) findViewById(R.id.btnStats);

        tvGame = (TextView) findViewById(R.id.tvGames);
        tvOnline = (TextView) findViewById(R.id.tvOnline);

        btnClickListener = new ButtonClickListener();

        btnSettings.setOnClickListener(btnClickListener);
        btnPractice.setOnClickListener(btnClickListener);
        btnNewRace.setOnClickListener(btnClickListener);
        btnLb.setOnClickListener(btnClickListener);
        btnStats.setOnClickListener(btnClickListener);

    }
    private ButtonClickListener btnClickListener;
    private class ButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            int id = v.getId();
            Intent intent =null;
            if(id == R.id.btnNewRace){
                intent = new Intent(getBaseContext(),RaceActivity.class);
            }else if(id == R.id.btnSettings){
                intent = new Intent(getBaseContext(),SettingsActivity.class);
                intent.putExtra("practice",false);
            }else if(id == R.id.btnPractice){
                intent = new Intent(getBaseContext(),RaceActivity.class);
                intent.putExtra("practice",true);
            }else if(id ==R.id.btnLeaderboard){
                intent = new Intent(getBaseContext(),LeaderboardActivity.class);
            }else if(id == R.id.btnStats){
                intent = new Intent(getBaseContext(),StatsActivity.class);
            }

            if(intent !=null){
                startActivity(intent);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
