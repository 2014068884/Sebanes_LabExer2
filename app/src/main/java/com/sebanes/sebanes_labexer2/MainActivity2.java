package com.sebanes.sebanes_labexer2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    TextView tv_results;
    Button btn_pref, btn_internal, btn_clear, btn_back;

    SharedPreferences pref;
    FileInputStream in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv_results = (TextView) findViewById(R.id.tv_results);
        btn_pref = (Button) findViewById(R.id.btn_loadPref);
        btn_internal = (Button) findViewById(R.id.btn_storage);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_back = (Button) findViewById(R.id.btn_back);

        pref = getSharedPreferences("pref", Context.MODE_WORLD_READABLE);
    }

    public void loadPref(View view) {
        String username = pref.getString("username", "");
        String password = pref.getString("password", "");

        tv_results.setText("Username: " + username + " and Password: " + password + " in Shared Preferences");
    }

    public void loadIntern(View view) {
        StringBuffer sb = new StringBuffer();
        int num = 0;

        try {
            in = openFileInput("results.txt");

            while((num = in.read()) != -1) {
                sb.append((char)num);
            }
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        tv_results.setText(sb.toString());
    }

    public void clear(View view) {
        tv_results.setText("");
    }

    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
