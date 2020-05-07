package com.example.week8t1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView textList;
    Spinner spinner;
    Bottle valinta;
    int money;
    SeekBar seekBar;
    BottleDispenser kone = BottleDispenser.getInstance();
    String currentreceipt;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textList = findViewById(R.id.textList);
        //this.spinneri();
        spinner = (Spinner) findViewById(R.id.spinner);

        final ArrayList<Bottle> lista = kone.getBottlelist();




        ArrayAdapter<Bottle> adapter =
                new ArrayAdapter<Bottle>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, lista);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valinta = lista.get(position);
                Toast.makeText(MainActivity.this, "selected : "+ lista.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //seekbari
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                money = progress;
                Toast.makeText(getApplicationContext(), "Money to be added: "+progress, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        context = MainActivity.this;

    }

    public void buyBottle(View v) {
        currentreceipt = "";
        setTextList(kone.buyBottle(valinta));
        currentreceipt = valinta.getName() + "   " + valinta.getPrice() + "€\n";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        currentreceipt += strDate;
    }

    public void listBottles(View v) {

        setTextList(kone.printList());
    }

    public void addMoney(View v) {
        setTextList(kone.addMoney(money));
        seekBar.setProgress(0);
    }

    public void printReceipt(View v) {
        String receipt = "***   RECEIPT ***\n";
        receipt += currentreceipt;

        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("kuitti.txt", Context.MODE_PRIVATE));
            ows.write(receipt);
            ows.close();

        } catch (IOException e) {
            Log.e("IOEXCEPTION", "tuli virhe!!!");
        }

    }

    public void returnMoney(View v) {

        setTextList(kone.returnMoney());
    }

    public void setTextList(String s) {
        textList.setText(s);
    }
}
