package in.androidmate.anujgupta.video_chat_opentok.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import in.androidmate.anujgupta.video_chat_opentok.MyApplication;

/**
 * Created by anujgupta on 08/01/18.
 */

public class PrefManager {


    public static SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
    }

    public static void putString(String prefKey,String prefValue){
        getPreferences().edit().putString(prefKey, prefValue).commit();
    }

    public static String getString(String prefKey,String defaultValue){
        return getPreferences().getString(prefKey,defaultValue);
    }

    public static void putBoolean(String prefKey,boolean prefValue){
        getPreferences().edit().putBoolean(prefKey, prefValue).commit();
    }

    public static Boolean getBoolean(String prefKey,boolean defaultValue){
        return getPreferences().getBoolean(prefKey,defaultValue);
    }

}
