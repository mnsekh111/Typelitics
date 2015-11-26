package com.mns.typelytics;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;

public class SettingsActivity extends PreferenceActivity {

    private Button btnKeyBoard;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.user_settings);
        ListView v = getListView();
        v.addFooterView(btnKeyBoard = new Button(this));
        btnKeyBoard.setText(getString(R.string.keyboard_summary));
        btnKeyBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager ime = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (ime != null) {
                    ime.showInputMethodPicker();
                }

            }
        });
    }


    public static Settings getPreferences(Context context) {
        Settings s = new Settings();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        s.auto_complete = prefs.getBoolean("auto_complete", false);
        s.auto_correct = prefs.getBoolean("auto_correct", false);
        s.suggest = prefs.getBoolean("suggest", false);

        return s;

    }

    public static class Settings {
        public boolean auto_correct;
        public boolean auto_complete;
        public boolean suggest;
        public int keyboard;
    }

    private void logPrefs(){
        Settings s = getPreferences(this);
        Log.i("Pref","Auto Complete "+s.auto_complete);
        Log.i("Pref","Auto Correct  "+s.auto_correct);
        Log.i("Pref","Suggest "+s.suggest);
        Log.i("Pref","keyboard " + s.keyboard);
    }


}