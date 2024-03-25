package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainBT6 extends AppCompatActivity {
    Switch lightSwitch;
    ToggleButton toggleBtn;
    ImageView imageView;
    LinearLayout mainContent;

    String lightOnPath = "@drawable/light_on";
    String lightOffPath = "@drawable/light_off";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bt6);
        setControl();
        setEvent();
    }

    private void setControl(){
        lightSwitch = findViewById(R.id.lightSwitch);
        toggleBtn = findViewById(R.id.toggleBtn);
        imageView = findViewById(R.id.imageView);
        mainContent = findViewById(R.id.mainContent);
    }

    private void setEvent(){
        lightSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lightSwitch.isChecked()){
                    mainContent.setVisibility(View.VISIBLE);
                }else mainContent.setVisibility(View.INVISIBLE);
            }
        });

        toggleBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(toggleBtn.isChecked()){
                    imageView.setImageResource(R.drawable.light_on);
                } else imageView.setImageResource(R.drawable.light_off);
            }
        });
    }



}