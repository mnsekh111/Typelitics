package com.mns.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RaceActivity extends Activity {

    private EditText etInput;
    private TextView tvPassage, tvWpm, tvPosition, tvAcc;
    private RelativeLayout rlRace;


    private volatile int timeElapsed = 0;
    private int currentWord = 0;
    private boolean hasCompleted = false;
    private String[] passageWords;
    private String passage;
    private Spannable spanText = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race);
        initViews();
    }

    private void initViews() {
        etInput = (EditText) findViewById(R.id.etInput);
        etInput.addTextChangedListener(mInpWatcher);

        tvPassage = (TextView) findViewById(R.id.tvRacePassage);
        tvWpm = (TextView) findViewById(R.id.tvWPM);
        tvPosition = (TextView) findViewById(R.id.tvPosition);
        tvAcc = (TextView) findViewById(R.id.tvAcc);

        rlRace = (RelativeLayout) findViewById(R.id.rlRace);
        setPassage("My name is Sekharan. I'm a good boy.");
        addProgressBars(4);
    }


    private TextWatcher mInpWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!mTimerThread.isAlive()) {
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
                        //Show the complete activity
                    }
                } else {
                    etInput.setTextColor(Color.RED);
                }
            }
        }
    };


    private void addProgressBars(int n) {
        if (rlRace != null) {

            LinearLayout ll = (LinearLayout)((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.template_user_progress,null);
            ll.setTag("D" + 0);
            ll.setId(View.generateViewId());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ABOVE, R.id.cvPassage);
            rlRace.addView(ll, params);

            for(int i=1;i<n;i++){
                ll = (LinearLayout)((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.template_user_progress,null);
                ll.setId(View.generateViewId());
                ll.setTag("D"+i);
                params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.ABOVE,rlRace.findViewWithTag("D"+(i-1)).getId());
                params.setMargins(0,0,0,5);
                rlRace.addView(ll, params);
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
}
