package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainBT2 extends AppCompatActivity {
    EditText subjectInput, contentInput;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bt2);
        setControl();
        setEvent();
    }

    private void setControl(){
        subjectInput = findViewById(R.id.editSubject);
        contentInput = findViewById(R.id.editContent);
        btnSend = findViewById(R.id.btnSend);
    }

    private void setEvent(){
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectText = subjectInput.getText().toString();
                String contentText = contentInput.getText().toString();
                String msg = "Send to " + subjectText + "\n" + contentText;
                Toast.makeText(MainBT2.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}