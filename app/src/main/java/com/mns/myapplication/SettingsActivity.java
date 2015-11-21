package com.mns.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

public class SettingsActivity extends PreferenceActivity {

    private SharedPreferences prefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.user_settings);
        logPrefs();
    }


    public Settings getPreferences() {
        Settings s = new Settings();
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        s.auto_complete = prefs.getBoolean("auto_complete", false);
        s.auto_correct = prefs.getBoolean("auto_correct", false);
        s.suggest = prefs.getBoolean("suggest", false);
        s.keyboard = Integer.parseInt(prefs.getString("keyboard","0"));

        return s;

    }

    public class Settings {
        public boolean auto_correct;
        public boolean auto_complete;
        public boolean suggest;
        public int keyboard;
    }

    private void logPrefs(){
        Settings s = getPreferences();
        Log.i("Pref","Auto Complete "+s.auto_complete);
        Log.i("Pref","Auto Correct  "+s.auto_correct);
        Log.i("Pref","Suggest "+s.suggest);
        Log.i("Pref","keyboard " + s.keyboard);
    }


}