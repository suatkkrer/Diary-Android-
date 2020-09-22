package com.suatkkrer.diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.text.format.DateFormat;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.text.format.DateFormat.format;


public class AddMemory extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    SaveData saveData;
    EditText memoryAdd,titleAdd;
    String titleHome,dateHome;
    String memoryHome;
    int idHome;
    TextView day;


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
        day = findViewById(R.id.day);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE,\n dd MMM yyyy");
        String date = dateFormat.format(cal.getTime());
        day.setText(date);



        Intent intent = getIntent();
        titleHome = intent.getStringExtra("title");
        memoryHome = intent.getStringExtra("memory");
        idHome = intent.getIntExtra("id",-1);
        dateHome = intent.getStringExtra("date");

        if (titleHome != null && memoryHome != null){
            memoryAdd.setText(memoryHome);
            titleAdd.setText(titleHome);
            day.setText(dateHome);
        }


    }

    public void saveButton(View view) {

        try {
            if (idHome == -1) {
                SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Memories", MODE_PRIVATE, null);

                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS memories(id INTEGER PRIMARY KEY,title TEXT, memory TEXT, date TEXT)");

                String title1 = titleAdd.getText().toString();
                String memory1 = memoryAdd.getText().toString();
                String day11 = day.getText().toString();

                String sql = "INSERT INTO memories (title,memory,date) VALUES (?,?,?)";

                SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
                statement.bindString(1,title1);
                statement.bindString(2,memory1);
                statement.bindString(3,day11);
                statement.execute();

                //  sqLiteDatabase.execSQL("INSERT INTO memories (title,memory,date) VALUES ('" + title1 + "','" + memory1 + "','" + day11 + "')");

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            } else {
                SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Memories", MODE_PRIVATE, null);

                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS memories(id INTEGER PRIMARY KEY,title TEXT, memory TEXT, date TEXT)");

                String title1 = titleAdd.getText().toString();
                String memory1 = memoryAdd.getText().toString();
                String day11 = day.getText().toString();

                String sql = "INSERT INTO memories (title,memory,date) VALUES (?,?,?)";

                // sqLiteDatabase.execSQL("INSERT INTO memories (title,memory,date) VALUES ('" + title1 + "','" + memory1 + "','" + day11 + "')");

                SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
                statement.bindString(1,title1);
                statement.bindString(2,memory1);
                statement.bindString(3,day11);
                statement.execute();

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
            try {
                SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Memories", MODE_PRIVATE, null);

                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS memories(id INTEGER PRIMARY KEY,title VARCHAR, memory VARCHAR,date VARCHAR)");


                sqLiteDatabase.execSQL("DELETE FROM memories WHERE id = " + idHome + "");

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void hide1(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    public void hide2(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    public void calendar(View view) {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(),"Date Picker");
    }
    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
            datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
           Calendar c = Calendar.getInstance();
           c.set(Calendar.YEAR, year);
           c.set(Calendar.MONTH, month);
           c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

//           String date = DateFormat.getDateFormat(this).format(c.getTime());
//           date1.setText(date);

        CharSequence dateChar = DateFormat.format("EEEE,\n dd MMM yyyy",c);
        day.setText(dateChar);


    }
}