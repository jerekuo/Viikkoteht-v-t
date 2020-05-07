package com.example.week11uus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private EditText editText;
    private Settings s = Settings.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        //add sidebar
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.openNavDrawer,
            R.string.closeNavDrawer
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getCurrentSettings(s);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // If settings is chosen
        if (menuItem.getItemId() == 0) {
            //transfer to settings activity
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            intent.getExtras();


        }
        return false;

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public void printSettings(View v) {
        System.out.println("TEXT WIDTH: "+editText.getWidth());
        System.out.println("TEXT HEIGHT:  "+editText.getHeight());
        System.out.println("TEXT SIZE:   " +editText.getTextSize());
        System.out.println("EDITABLE TEXT:   "+  editText.isEnabled());
        System.out.println("MAX LINES:     " + editText.getMaxLines());
    }

    public void getCurrentSettings(Settings s){
        s.setEditable(editText.isEnabled());
        s.setWidth(editText.getWidth());
        s.setFontSize((int) editText.getTextSize());
        s.setnRows(editText.getMaxLines());
        s.setHeight(editText.getHeight());
    }

    public void setCurrentSettings(View v) {
        editText.setWidth(s.getWidth());
        editText.setHeight(s.getHeight());
        editText.setTextSize(s.getFontSize());
        editText.setEnabled(s.getEditable());
        editText.setMaxLines(s.getnRows());
    }

}
