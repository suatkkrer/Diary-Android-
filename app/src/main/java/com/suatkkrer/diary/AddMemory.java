package com.suatkkrer.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.text.format.DateFormat;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import static android.text.format.DateFormat.format;


public class AddMemory extends AppCompatActivity {

    SaveData saveData;
    EditText memoryAdd,titleAdd;
    String titleHome;
    String memoryHome;
    int idHome;
    TextView day,date1;


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

        memoryAdd = findViewById(R.id.memoryAdd);
        titleAdd = findViewById(R.id.titleAdd);
        date1 = findViewById(R.id.date);
        day = findViewById(R.id.day);

//        long date = 0;
//        String dayOfTheWeek = (String) DateFormat.format("EEEE",date);

   //     date1.setText();

        Intent intent = getIntent();
        titleHome = intent.getStringExtra("title");
        memoryHome = intent.getStringExtra("memory");
        idHome = intent.getIntExtra("id",-1);

        if (titleHome != null && memoryHome != null){
            memoryAdd.setText(memoryHome);
            titleAdd.setText(titleHome);
        }


    }

    public void saveButton(View view) {

        try {
            if (idHome == -1) {
                SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Memories", MODE_PRIVATE, null);

                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS memories(id INTEGER PRIMARY KEY,title VARCHAR, memory VARCHAR)");

                String title1 = String.valueOf(titleAdd.getText());
                String memory1 = String.valueOf(memoryAdd.getText());

                sqLiteDatabase.execSQL("INSERT INTO memories (title,memory) VALUES ('" + title1 + "','" + memory1 + "')");

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            } else {
                SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Memories", MODE_PRIVATE, null);

                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS memories(id INTEGER PRIMARY KEY,title VARCHAR, memory VARCHAR)");

                String title1 = String.valueOf(titleAdd.getText());
                String memory1 = String.valueOf(memoryAdd.getText());

                sqLiteDatabase.execSQL("INSERT INTO memories (title,memory) VALUES ('" + title1 + "','" + memory1 + "')");
                sqLiteDatabase.execSQL("DELETE FROM memories WHERE id = " + idHome + "");

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void Cancel(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void Delete(View view) {

        if (idHome != -1) {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Memories", MODE_PRIVATE, null);

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS memories(id INTEGER PRIMARY KEY,title VARCHAR, memory VARCHAR)");


            sqLiteDatabase.execSQL("DELETE FROM memories WHERE id = " + idHome + "");

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

    }
}