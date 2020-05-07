package com.example.week11uus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    EditText editFont;
    EditText editWidth;
    EditText editNrows;
    EditText editHeight;
    EditText editUser;
    Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Settings settings = Settings.getInstance();
        editFont = findViewById(R.id.editFont);
        editWidth = findViewById(R.id.editWidth);
        editNrows = findViewById(R.id.editNrows);
        editHeight = findViewById(R.id.editHeight);
        editUser = findViewById(R.id.editUser);
        switch1 = findViewById(R.id.switch1);

        editFont.setText(Integer.toString(settings.getFontSize()));
        editWidth.setText(Integer.toString(settings.getWidth()));
        editHeight.setText(Integer.toString(settings.getHeight()));
        editNrows.setText(Integer.toString(settings.getnRows()));
        switch1.setChecked(settings.getEditable());


    }

    public void setSettings(View v) {
        Settings s = Settings.getInstance();
        s.setHeight(Integer.parseInt(editHeight.getText().toString()));
        s.setnRows(Integer.parseInt(editNrows.getText().toString()));
        s.setWidth(Integer.parseInt(editWidth.getText().toString()));
        s.setFontSize(Integer.parseInt(editFont.getText().toString()));
        s.setEditable(switch1.isChecked());
    }

    @Override
    public  void onBackPressed(){
        super.onBackPressed();

    };
}
