package com.mns.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.InputType;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import com.github.lzyzsd.circleprogress.CircleProgress;
import com.mns.myapplication.utils.SoftKeyboard;

import java.util.ArrayList;
import java.util.Random;

public class RaceActivity extends Activity {

    private EditText etInput;
    private TextView tvPassage, tvWpm, tvPosition, tvAcc;
    private LinearLayout llRace;
    private LinearLayout llRace2;
    private LinearLayout llRaceParent;
    private CardView cvPassage;
    private ProgressDialog pd = null;

    private volatile int timeElapsed = 0;
    private int currentWord = 0;
    private int mistakes = 0;
    private int participants = -1;
    private boolean hasCompleted = false;
    private String[] passageWords;
    private String passage;
    private Spannable spanText = null;

    private ArrayList<RelativeLayout> partProgress = new ArrayList<>();
    private ArrayList<LinearLayout> partProgress2 = new ArrayList<>();
    private ArrayList<Integer> partProgressColor = new ArrayList<>();
    private Random random = new Random();


    private InputMethodManager im = null;
    private SoftKeyboard softKeyboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_race2);

        im = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
        initViews();
    }

    private void initViews() {
        pd = new ProgressDialog(this);
        etInput = (EditText) findViewById(R.id.etInput);
        etInput.addTextChangedListener(mInpWatcher);
        setupEditTextOptions(SettingsActivity.getPreferences(this));

        tvPassage = (TextView) findViewById(R.id.tvRacePassage);
        tvWpm = (TextView) findViewById(R.id.tvWPM);
        tvPosition = (TextView) findViewById(R.id.tvPosition);
        tvAcc = (TextView) findViewById(R.id.tvAcc);

        cvPassage = (CardView) findViewById(R.id.cvPassage);
        llRace = (LinearLayout) findViewById(R.id.llRace);
        llRace2 = (LinearLayout) findViewById(R.id.llRace2);
        llRaceParent = (LinearLayout) findViewById(R.id.llRaceParent);


        softKeyboard = new SoftKeyboard(llRaceParent, im);
        softKeyboard.setSoftKeyboardCallback(new SoftKeyboard.SoftKeyboardChanged() {

            @Override
            public void onSoftKeyboardHide() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        llRace2.setVisibility(View.GONE);
                        llRace.setVisibility(View.VISIBLE);
                    }
                });

            }

            @Override
            public void onSoftKeyboardShow() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        llRace2.setVisibility(View.VISIBLE);
                        llRace.setVisibility(View.GONE);
                    }
                });
            }
        });

        new FetchTask(pd).execute();
    }

    private class FetchTask extends AsyncTask<Void, Integer, Void> {

        public FetchTask(ProgressDialog pd) {
            super();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();
            setPassage(getString(R.string.sample_passage_small));
            setParticipants(4);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (participants == -1) {
                pd.setCancelable(false);
                pd.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        RaceActivity.this.finish();
                    }
                });
                pd.setMessage(getString(R.string.progress_body));
                pd.show();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 1; i <= 40; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }


    private void setupEditTextOptions(SettingsActivity.Settings settings) {

        if (etInput != null) {

            if (!settings.suggest)
                etInput.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
            if (settings.auto_correct)
                etInput.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
            if (settings.auto_complete)
                etInput.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);


        }
    }

    private TextWatcher mInpWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!mTimerThread.isAlive() && !mTimerThread.isInterrupted()) {
                mTimerThread.start();
                mDummyUpdaterThread.start();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() >= 1 && s.charAt(s.length() - 1) == ' ') {
                Log.i("COMP", passageWords[currentWord] + "-->" + s.toString().substring(0, s.length() - 1));
                if ((passageWords[currentWord] + " ").contentEquals(s.toString().substring(0, s.length()))) {

                    etInput.setText("");
                    int start = 0;

                    if (currentWord < passageWords.length - 1) {
                        currentWord++;
                        for (int i = 0; i < currentWord; i++) {
                            start += (passageWords[i].length() + 1);
                        }
                        spanText = Spannable.Factory.getInstance().newSpannable(passage);
                        spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), start, start + passageWords[currentWord].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        tvPassage.setText(spanText);
                        etInput.setTextColor(Color.BLACK);

                    } else {
                        if (mTimerThread != null && mTimerThread.isAlive())
                            mTimerThread.interrupt();
                        //startActivity(new Intent(getBaseContext(),ResultActivity.class));
                        etInput.removeTextChangedListener(this);
                        cvPassage.setBackgroundColor(getResources().getColor(R.color.primary_dark));
                        tvPassage.setTextColor(getResources().getColor(android.R.color.white));
                        tvPassage.setTextSize(30);
                        tvPassage.setText("Done !");
                        Button btnProceed = (Button)cvPassage.findViewById(R.id.btnProceed);
                        btnProceed.setVisibility(View.VISIBLE);
                        btnProceed.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                                intent.putExtra("gameid", 10);
                                startActivity(intent);
                                finish();
                            }
                        });
                        hasCompleted = true;

                    }
                } else {
                    mistakes++;
                    etInput.setTextColor(Color.RED);
                }
            }
        }
    };


    private void addProgressBars() {
        if (llRace != null) {
            for (int i = 0; i < participants; i++) {
                RelativeLayout rl = (RelativeLayout) ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.template_user_progress, null);
                rl.setId(View.generateViewId());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 0, 20);
                llRace.addView(rl, params);
                partProgress.add(rl);
            }

        }

        if (llRace2 != null) {
            for (int i = 0; i < participants; i++) {
                LinearLayout ll = (LinearLayout) ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.template_user_progress_alt, null);
                ll.setId(View.generateViewId());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 0, 5);
                llRace2.addView(ll, params);
                partProgress2.add(ll);
            }

        }
    }

    private Thread mTimerThread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {

                while (!hasCompleted) {
                    Thread.sleep(1000);
                    timeElapsed++;
                    updateUserStats();
                }
            } catch (InterruptedException ie) {

            }
        }
    });


    private void setPassage(String str) {
        if (tvPassage != null) {
            passage = str;
            passageWords = passage.split(" ");
            currentWord = 0;
            spanText = Spannable.Factory.getInstance().newSpannable(passage);
            spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), 0
                    , passageWords[currentWord].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvPassage.setText(spanText);
        }
    }

    private void setParticipants(int n) {
        this.participants = n;
        addProgressBars();

        partProgressColor.add(Color.BLUE);
        partProgressColor.add(Color.BLUE);
        partProgressColor.add(Color.BLUE);
        partProgressColor.add(Color.BLUE);
        partProgressColor.add(Color.BLUE);

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.quit)
                .setMessage(R.string.quit_mess)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        RaceActivity.this.finish();
                    }

                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    private void updateUserStats() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    tvWpm.setText("" + currentWord * 60 / timeElapsed);
                    tvAcc.setText("" + currentWord * 100 / (currentWord + mistakes));
                } catch (RuntimeException re) {
                    Log.i("Update", "Division By Zero");
                }
            }
        });

    }


    private Thread mDummyUpdaterThread = new Thread() {
        @Override
        public void run() {
            while (!hasCompleted) {

                try {
                    Thread.sleep(1000);
                    mProgressUpdater.sendMessage(Message.obtain());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private Handler mProgressUpdater = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            IconRoundCornerProgressBar rpb = null;
            CircleProgress cpb = null;
            TextView rpbText = null;
            for (int i = 0; i < partProgress.size(); i++) {
                rpb = (IconRoundCornerProgressBar) partProgress.get(i).findViewById(R.id.rcpb);
                rpbText = (TextView) partProgress.get(i).findViewById(R.id.rcpbText);
                rpb.setProgress(rpb.getProgress() + random.nextInt(3) + 1);
                rpbText.setText("" + rpb.getProgress());
                rpb.setProgressColor(partProgressColor.get(i));

                cpb = (CircleProgress) partProgress2.get(i).findViewById(R.id.cpb);
                cpb.setProgress((int) rpb.getProgress());
            }
        }
    };


    @Override
    protected void onDestroy() {
        if (mTimerThread != null) {
            mTimerThread.interrupt();
        }

        super.onDestroy();
    }


}
