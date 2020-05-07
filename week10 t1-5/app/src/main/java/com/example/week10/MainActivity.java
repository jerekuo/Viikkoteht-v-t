package com.example.week10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    EditText url;
    WebView web;
    Button buttonini;
    Button buttonShout;
    String currentUrl;
    String memory;
    ArrayList<String> pageHistory = new ArrayList<>();
    ListIterator<String> iterator;
    int i = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void goTo(View v) {
        web = findViewById(R.id.webView);
        buttonini = findViewById(R.id.buttonIni);
        buttonShout = findViewById(R.id.buttonShout);

        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);
        url = findViewById(R.id.editText);
        currentUrl = url.getText().toString();
        pageHistory.add(currentUrl);


        if (currentUrl.equals("index.html")) {
            web.loadUrl("file:///android_asset/index.html");
            buttonini.setVisibility(View.VISIBLE);
            buttonShout.setVisibility(View.VISIBLE);
        } else {
            web.loadUrl("http://" + currentUrl);
            buttonini.setVisibility(View.INVISIBLE);
            buttonShout.setVisibility(View.INVISIBLE);
            pageHistory.add(currentUrl);
        }
        if(i != 10) {
            iterator = pageHistory.listIterator(i);
            while (iterator.hasNext()){
                memory = iterator.next();
                if (memory != currentUrl) {
                    iterator.remove();
                }
            }
        }

        if(pageHistory.size()>10){
            pageHistory.remove(0);
        }

    }

    public void Refresh(View v) {
        web.reload();
    }

    public void goBack(View v) {
        System.out.println("LISTAN KOKO:" +pageHistory.size() +"  INDEKSI: " + pageHistory.indexOf(currentUrl));
        iterator = pageHistory.listIterator(pageHistory.indexOf(currentUrl));
        if (iterator.hasPrevious()){
            currentUrl = iterator.previous();
            i = pageHistory.indexOf(currentUrl)+1;
            web.loadUrl("http://"+currentUrl);
        } else {
            //Do nothing
        }


    }

    public void goForward(View v) {
        iterator = pageHistory.listIterator(pageHistory.indexOf(currentUrl)+1);
        if (iterator.hasNext()){
            currentUrl = iterator.next();
            web.loadUrl("http://"+currentUrl);
        }



    }

    public void loadIni(View v) {
        web.evaluateJavascript("javascript:initialize()", null);

    }


    public void loadShoutOut(View v) {
        web.evaluateJavascript("javascript:shoutOut()", null);
    }



    public void seturlText(String s) {
        url.setText(s);
    }
}
