package com.suatkkrer.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.hanks.passcodeview.PasscodeView;

public class PasswordActivity extends AppCompatActivity {

    PasscodeView passcodeView;
    String passwordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        passcodeView = findViewById(R.id.passcode_view);


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

        if(passwordConfirm != null) {
            passcodeView.setPasscodeLength(4)
                    .setLocalPasscode(passwordConfirm)
                    .setListener(new PasscodeView.PasscodeViewListener() {
                        @Override
                        public void onFail() {
                            Toast.makeText(PasswordActivity.this, "Şifreniz yanlış", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onSuccess(String number) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    });
        }
    }
}