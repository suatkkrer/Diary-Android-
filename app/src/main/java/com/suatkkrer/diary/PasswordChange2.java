package com.suatkkrer.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordChange2 extends AppCompatActivity {

    EditText e1,e2,e3,e4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change2);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        e1 = findViewById(R.id.etcc_1);
        e2 = findViewById(R.id.etcc_2);
        e3 = findViewById(R.id.etcc_3);
        e4 = findViewById(R.id.etcc_4);


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

    public void changePassword2(View view) {

        if (e1.getText().length() != 0 && e2.getText().length() != 0 && e3.getText().length() != 0  && e4.getText().length() != 0){

            try {
                SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Password",MODE_PRIVATE,null);

                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS password(pass VARCHAR)");

                String pass1 = String.valueOf(e1.getText());
                String pass2 = String.valueOf(e2.getText());
                String pass3 = String.valueOf(e3.getText());
                String pass4 = String.valueOf(e4.getText());
                String passLast = pass1 + "" + pass2 + "" + pass3 + "" + pass4;

                sqLiteDatabase.execSQL("UPDATE password SET pass = '" + passLast + "'");

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                Toast.makeText(this, "Şifreniz Başarıyla Değişti...", Toast.LENGTH_LONG).show();
                startActivity(intent);

            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Lütfen Şifreyi Doğru Oluşturun", Toast.LENGTH_SHORT).show();
        }

    }
}