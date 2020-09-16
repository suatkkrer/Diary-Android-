package com.suatkkrer.diary;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveData {

    private SharedPreferences sharedPreferences;

    SaveData(Context context){
        sharedPreferences = context.getSharedPreferences("Action",Context.MODE_PRIVATE);
    }
    void setdarkModeState(Boolean state){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Dark",state);
        editor.apply();
    }

    Boolean loadDarkModeState(){
        final boolean darkMode;
        darkMode = sharedPreferences.getBoolean("Dark",false);
        return darkMode;
    }
}
