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

public class PasswordNewFirst extends AppCompatActivity {

    EditText editText1,editText2,editText3,editText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_new_first);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        editText1 = findViewById(R.id.et_1);
        editText2 = findViewById(R.id.et_2);
        editText3 = findViewById(R.id.et_3);
        editText4 = findViewById(R.id.et_4);

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1){
                    editText2.requestFocus();
                }
            }
        });
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    if (s.toString().length() == 1){
                        editText3.requestFocus();
                    } else if(s.toString().length() == 0){
                        editText1.requestFocus();
                    }
            }
        });
        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1){
                    editText4.requestFocus();
                } else if(s.toString().length() == 0){
                    editText2.requestFocus();
                }
            }
        });
        editText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 0){
                    editText3.requestFocus();
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

    public void setPassword(View view) {

        if (editText1.getText().length() != 0 && editText2.getText().length() != 0 && editText3.getText().length() != 0  && editText4.getText().length() != 0){

            try {
                SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Password",MODE_PRIVATE,null);

                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS password(pass VARCHAR)");

                String pass1 = String.valueOf(editText1.getText());
                String pass2 = String.valueOf(editText2.getText());
                String pass3 = String.valueOf(editText3.getText());
                String pass4 = String.valueOf(editText4.getText());
                String passLast = pass1 + "" + pass2 + "" + pass3 + "" + pass4;

                sqLiteDatabase.execSQL("INSERT INTO password (pass) VALUES ('" + passLast + "')");

                Intent intent = new Intent(getApplicationContext(),PasswordActivity.class);
                startActivity(intent);

            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Lütfen Şifre Oluşturun", Toast.LENGTH_SHORT).show();
        }
    }
}