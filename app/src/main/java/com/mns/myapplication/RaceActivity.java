package com.mns.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RaceActivity extends Activity {

    private EditText etInput;
    private TextView tvPassage, tvWpm, tvPosition, tvAcc;
    private LinearLayout llRace;
    private CardView cvPassage;

    private volatile int timeElapsed = 0;
    private int currentWord = 0;
    private int mistakes = 0;
    private int participants = -1;
    private boolean hasCompleted = false;
    private String[] passageWords;
    private String passage;
    private Spannable spanText = null;

    private ArrayList<LinearLayout> partProgress = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race2);
        initViews();
    }

    private void initViews() {
        etInput = (EditText) findViewById(R.id.etInput);
        etInput.addTextChangedListener(mInpWatcher);

        tvPassage = (TextView) findViewById(R.id.tvRacePassage);
        tvWpm = (TextView) findViewById(R.id.tvWPM);
        tvPosition = (TextView) findViewById(R.id.tvPosition);
        tvAcc = (TextView) findViewById(R.id.tvAcc);

        cvPassage = (CardView) findViewById(R.id.cvPassage);
        llRace = (LinearLayout) findViewById(R.id.llRace);

//        if (participants == -1) {
//            ProgressDialog pd = new ProgressDialog(RaceActivity.this);
//            pd.setCancelable(false);
//            pd.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.cancel), new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                    RaceActivity.this.finish();
//                }
//            });
//            pd.setMessage(getString(R.string.progress_body));
//            pd.show();
//        }
        setPassage(getString(R.string.sample_passage));
        setParticipants(4);
    }


    private TextWatcher mInpWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!mTimerThread.isAlive() && !mTimerThread.isInterrupted() ) {
                mTimerThread.start();
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
                        if(mTimerThread != null && mTimerThread.isAlive())
                            mTimerThread.interrupt();
                        //startActivity(new Intent(getBaseContext(),ResultActivity.class));
                        etInput.removeTextChangedListener(this);
                        cvPassage.setBackgroundColor(getResources().getColor(R.color.primary_dark));
                        tvPassage.setTextColor(getResources().getColor(android.R.color.white));
                        tvPassage.setTextSize(30);
                        tvPassage.setText("Done !");

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
                LinearLayout ll = (LinearLayout) ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.template_user_progress, null);
                ll.setTag("D" + i);
                ll.setId(View.generateViewId());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,0,0,5);
                llRace.addView(ll, params);
                partProgress.add(ll);
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


    private void dummyRacers(){

    }

    @Override
    protected void onDestroy() {
        if (mTimerThread != null) {
            mTimerThread.interrupt();
        }
        super.onDestroy();
    }
}
