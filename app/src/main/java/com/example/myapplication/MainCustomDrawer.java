package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class MainCustomDrawer extends AppCompatActivity {

    private DrawerLayout drawer;
    private ImageButton toggleNavigationBtn;
    private NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custom_drawer);
        setControl();
        setEvent();
    }

    private void setControl(){
        drawer = findViewById(R.id.drawer_layout);
        toggleNavigationBtn = findViewById(R.id.imageBtn);
        navigation = findViewById(R.id.navigation);
    }

    private void setEvent(){
        toggleNavigationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.open();
            }
        });

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                drawer.close();
                return false;
            }
        });
    }


}