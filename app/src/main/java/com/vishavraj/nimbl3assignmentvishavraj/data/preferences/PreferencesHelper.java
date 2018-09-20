package com.vishavraj.nimbl3assignmentvishavraj.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.vishavraj.nimbl3assignmentvishavraj.utils.Nimbl3Constants;

import java.util.Map;

/**
 * Created by vishavraj on 16/09/18.
 */

public class PreferencesHelper {

    private static PreferencesHelper instance = null;
    private static SharedPreferences.Editor editor;
    private final SharedPreferences mPref;

    public static final String access_token = "access_token";

    private Context mContext;


    private PreferencesHelper(Context context) {
        this.mContext = context;
        mPref = context.getSharedPreferences(Nimbl3Constants.USER_PREFERENCES, Context.MODE_PRIVATE);
    }


    public static PreferencesHelper getInstance(Context context) {
        if (instance == null) {
            instance = new PreferencesHelper(context);
        }
        return instance;
    }


    public void writeString(String key, String value) {
        if (mPref != null) {
            editor = mPref.edit();

                editor.putString(key, value);
            editor.apply();
            editor.commit();
        }
    }


    public String readString(String key) {
        if (mPref != null) {

            String value = mPref.getString(key, null);
                return value;
        } else {
            return null;
        }
    }




    public void clear() {
        mPref.edit().clear().apply();
    }




    public void removePref(String key) {
        if (mPref != null && key != null) {
            editor = mPref.edit();
            editor.remove(key);
            editor.commit();
        }
    }


    public boolean containsKey(String key) {
        return (mPref != null) && (mPref.contains(key));
    }


    public Map<String, ?> getAllSharedPreferences() {
        if (mPref != null) {
           return mPref.getAll();
        }

        return null;
    }


}
