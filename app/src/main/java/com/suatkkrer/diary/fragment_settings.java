package com.suatkkrer.diary;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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

import static android.content.Context.MODE_PRIVATE;

public class fragment_settings extends Fragment {

    View v;
    Context thisContext;
    SaveData saveData;
    LinearLayout deleteLayout,changePassword,rate;


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

        deleteLayout = v.findViewById(R.id.deleteAllItems);
        changePassword = v.findViewById(R.id.changePassword);
        rate = v.findViewById(R.id.rate);

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),PasswordChange.class);
                startActivity(intent);
            }
        });

        //getActivity().getPackageName()

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getActivity().getPackageName())));
                } catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
                }
            }
        });

        deleteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder aler = new AlertDialog.Builder(getContext());
                aler.setTitle("Emin misiniz?");
                aler.setMessage("Bütün verileriniz silinecek emin misiniz?");
                aler.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            SQLiteDatabase sqLiteDatabase = thisContext.openOrCreateDatabase("Memories", MODE_PRIVATE, null);

                            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS memories(id INTEGER PRIMARY KEY,title TEXT, memory TEXT,date TEXT)");


                            sqLiteDatabase.execSQL("DELETE FROM memories");
                            Toast.makeText(getActivity(), "Verileriniz başarıyla silindi.", Toast.LENGTH_SHORT).show();
                            restartApplication();

                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                aler.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Silme işlemi iptal edildi.", Toast.LENGTH_SHORT).show();
                    }
                });
                aler.create().show();
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
