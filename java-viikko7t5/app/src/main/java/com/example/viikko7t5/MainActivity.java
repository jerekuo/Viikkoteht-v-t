package com.example.viikko7t5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText text;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.editText);
        context = MainActivity.this;

    }

    public void loadText (View v) { //metodi kirjoittamiseen
        EditText text;
        EditText text2;
        text = findViewById(R.id.editText);
        text2 = findViewById(R.id.editText2);

        try {
            InputStream in = context.openFileInput(text2.getText().toString());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String syöte;
            String pitkateksti = "";

            while((syöte=reader.readLine())!= null) {
                System.out.println(syöte);
                pitkateksti = pitkateksti + syöte + "\n";
            }
            text.setText(pitkateksti);


        } catch (IOException e) {
            Log.e("IOEXCEPTION", "tuli virhe!!!");
        }

    }

    public void saveText (View v) { //metodi lukemiseen
        EditText text;
        EditText text2;
        text = findViewById(R.id.editText);
        text2 = findViewById(R.id.editText2);

        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(text2.getText().toString(), Context.MODE_PRIVATE));
            ows.write(text.getText().toString());
            ows.close();

        } catch (IOException e) {
            Log.e("IOEXCEPTION", "tuli virhe!!!");
        }
    }
}
