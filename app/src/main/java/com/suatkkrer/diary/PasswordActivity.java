package com.suatkkrer.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.hanks.passcodeview.PasscodeView;

public class PasswordActivity extends AppCompatActivity {

    PasscodeView passcodeView;
    String passwordConfirm;
    EditText e1,e2,e3,e4;
    String passLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        e1 = findViewById(R.id.edit_1);
        e2 = findViewById(R.id.edit_2);
        e3 = findViewById(R.id.edit_3);
        e4 = findViewById(R.id.edit_4);



        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show start activity
            startActivity(new Intent(PasswordActivity.this, PasswordNewFirst.class));
            Toast.makeText(PasswordActivity.this, "First Run", Toast.LENGTH_LONG)
                    .show();
        }

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();


        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Password",MODE_PRIVATE,null);

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS password(pass VARCHAR)");


            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM password",null);

            int passIx = cursor.getColumnIndex("pass");

            while (cursor.moveToNext()){
                passwordConfirm = cursor.getString(passIx);
            }
            cursor.close();

        } catch (Exception e){
            e.printStackTrace();
        }



        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1){
                    e2.requestFocus();
                }
            }
        });
        e2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1){
                    e3.requestFocus();
                } else if(s.toString().length() == 0){
                    e1.requestFocus();
                }
            }
        });
        e3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1){
                    e4.requestFocus();
                } else if(s.toString().length() == 0){
                    e2.requestFocus();
                }
            }
        });
        e4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 0){
                    e3.requestFocus();
                } else {
                    closeKeyboard();
                }
            }
        });

    }

    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    public void getPassword(View view) {
        String pass1 = String.valueOf(e1.getText());
        String pass2 = String.valueOf(e2.getText());
        String pass3 = String.valueOf(e3.getText());
        String pass4 = String.valueOf(e4.getText());
        passLast = pass1 + "" + pass2 + "" + pass3 + "" + pass4;
        
        
        if (passLast.equals(passwordConfirm)){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            
        } else {
            Toast.makeText(this, "Şifreniz yanlış lütfen tekrar deneyin...", Toast.LENGTH_SHORT).show();
        }

        System.out.println(passLast);
        System.out.println(passwordConfirm + "concon");

    }
}