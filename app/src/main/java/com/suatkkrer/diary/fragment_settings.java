package com.suatkkrer.diary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class fragment_settings extends Fragment {

    View v;
    Context thisContext;
    SaveData saveData;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thisContext = container.getContext();
        v = inflater.inflate(R.layout.fragment_settings,container,false);
        final SwitchCompat switch1 = v.findViewById(R.id.switch1);

        saveData = new SaveData(thisContext);

        if (saveData.loadDarkModeState()){
            switch1.setChecked(true);
        }

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch1.isChecked()){
                    saveData.setdarkModeState(true);
                    restartApplication();
                } else {
                    saveData.setdarkModeState(false);
                    restartApplication();
                }
            }
        });


        return v;
    }

    private void restartApplication() {
        Intent in = new Intent(getContext(),MainActivity.class);
        startActivity(in);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
