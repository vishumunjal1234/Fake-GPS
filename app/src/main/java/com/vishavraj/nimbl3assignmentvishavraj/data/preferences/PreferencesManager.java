package com.vishavraj.nimbl3assignmentvishavraj.data.preferences;

import android.content.Context;

import com.vishavraj.nimbl3assignmentvishavraj.dagger.ApplicationContext;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by bishwajeetkumar on 15/10/17 for TrackNext.
 */

public class PreferencesManager {


    private static PreferencesManager instance = null;
    private final Context context;
    private final PreferencesHelper preferencesHelper;


    @Inject
    public PreferencesManager(@ApplicationContext Context context) {
        this.context = context;
        preferencesHelper = PreferencesHelper.getInstance(this.context);

    }


    public static PreferencesManager getInstance(Context context) {
        if (instance == null) {
            instance = new PreferencesManager(context);
        }
        return instance;
    }


    public void writeString(String key, String value) {
        preferencesHelper.writeString(key, value);
    }


    public String readString(String key) {
        return preferencesHelper.readString(key);
    }





    public boolean containsKey(String key) {
        return preferencesHelper.containsKey(key);
    }


    public void removePref(String key) {
        preferencesHelper.removePref(key);
    }


    public void clearSharedPref() {
        preferencesHelper.clear();
    }


    public Map<String, ?> getAllPreferences(){
        return preferencesHelper.getAllSharedPreferences();
    }


}
