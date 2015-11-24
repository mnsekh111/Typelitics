package com.mns.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

public class SettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.user_settings);
        logPrefs();
    }


    public static Settings getPreferences(Context context) {
        Settings s = new Settings();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        s.auto_complete = prefs.getBoolean("auto_complete", false);
        s.auto_correct = prefs.getBoolean("auto_correct", false);
        s.suggest = prefs.getBoolean("suggest", false);
        s.keyboard = Integer.parseInt(prefs.getString("keyboard","0"));

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