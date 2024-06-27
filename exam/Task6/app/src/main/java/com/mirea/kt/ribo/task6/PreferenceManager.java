package com.mirea.kt.ribo.task6;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private SharedPreferences sharedPreferences;

    public PreferenceManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences("my_shared", Context.MODE_PRIVATE);
    }

    public void saveWord(String word) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("task5", word);
        editor.apply();
    }

    public String getWord() {
        return sharedPreferences.getString("task5", "");
    }
}