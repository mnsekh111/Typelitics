package com.mns.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends Activity {

    TextView tvTitle;
    ProgressBar pbLoading;

    private boolean isBackPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tvTitle = (TextView) findViewById(R.id.tvAppName);
        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
        pbLoading.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        new LoaderTask().execute();
    }

    private class LoaderTask extends AsyncTask<Void, Integer, Void> {
        public LoaderTask() {
            super();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            android.util.Log.i("SPLASH", "In Update");
            pbLoading.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 1; i <= 10; i++) {
                android.util.Log.i("SPLASH", "In background");
                try {
                    if (isBackPressed) {
                        break;
                    }
                    Thread.sleep(100);
                    publishProgress(i * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        isBackPressed = true;
        super.onBackPressed();
    }

}
