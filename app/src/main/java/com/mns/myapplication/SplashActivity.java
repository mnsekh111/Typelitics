package com.mns.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.CircleProgress;

public class SplashActivity extends Activity {

    TextView tvTitle;
    CircleProgress pbLoading;

    private boolean isBackPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tvTitle = (TextView) findViewById(R.id.tvAppName);
        pbLoading = (CircleProgress) findViewById(R.id.pbLoading);
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
            finish();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            pbLoading.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 1; i <= 20; i++) {
                android.util.Log.i("SPLASH", "In background");
                try {
                    if (isBackPressed) {
                        break;
                    }
                    Thread.sleep(100);
                    publishProgress(i * 5);
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
