package com.suatkkrer.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

public class AddMemory extends AppCompatActivity {

    SaveData saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        saveData = new SaveData(this);

        if (saveData.loadDarkModeState()){
            setTheme(R.style.darkTheme);
        } else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memory);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}