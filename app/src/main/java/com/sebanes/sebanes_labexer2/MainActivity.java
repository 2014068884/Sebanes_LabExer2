package com.sebanes.sebanes_labexer2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText et_username, et_password;
    Button btn_shared, btn_internal, btn_next;

    SharedPreferences pref;
    FileOutputStream out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = (EditText) findViewById(R.id.et_user);
        et_password = (EditText) findViewById(R.id.et_pass);

        btn_shared = (Button) findViewById(R.id.btn_shared);
        btn_internal = (Button) findViewById(R.id.btn_internal);
        btn_next = (Button) findViewById(R.id.btn_next);

        pref = getSharedPreferences("preferences", Context.MODE_WORLD_READABLE);
    }

    public void savePref(View view) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("username", et_username.getText().toString());
        editor.putString("password", et_password.getText().toString());
        editor.commit();
        Toast.makeText(this, "Shared Preference saved.", Toast.LENGTH_LONG).show();
    }

    public void saveIntern(View view) {
        String text = "Username: " + et_username.getText().toString() + "\nPassword: " + et_password.getText().toString()
                + "\nin Internal Storage.";

        try {
            out = openFileOutput("result.txt", Context.MODE_PRIVATE);
            out.write(text.getBytes());
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        Toast.makeText(this, "Internal Storage saved.", Toast.LENGTH_LONG).show();
    }

    public void next(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}
