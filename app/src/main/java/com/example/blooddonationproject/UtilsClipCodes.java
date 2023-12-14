package com.example.blooddonationproject;

import android.content.Context;
import android.content.SharedPreferences;

public class UtilsClipCodes {

    public static final String FileName  = "MyFileName";

    public static String readShareSetting(Context context, String settingName,String defaultValue)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(settingName,defaultValue);

    }

    public static void savedSharedSetting(Context context, String settingName,String settingValue)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FileName,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(settingName,settingValue);
        editor.apply();
    }

    public static void SharedPreferencesSave(Context context, String Name){

        SharedPreferences sharedPreferences = context.getSharedPreferences("NAME",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name", Name);
        editor.commit();

    }
}
