package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainBT5 extends AppCompatActivity {
    EditText edtName, edtCCCD;
    RadioButton rdDH, rdCD, rdTC;
    CheckBox cbGame, cbSport, cbMovie;
    Button btnLogin, btnSign, btnExit;
    TextView txtView;

    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bt5);
        setControl();
        setEvent();
    }

    private void setControl(){
        edtName = findViewById(R.id.edtName);
        edtCCCD = findViewById(R.id.edtCCCD);
        rdDH = findViewById(R.id.rdDH);
        rdCD = findViewById(R.id.rdCD);
        rdTC = findViewById(R.id.rdTC);
        cbGame = findViewById(R.id.cbGame);
        cbSport = findViewById(R.id.cbSport);
        cbMovie = findViewById(R.id.cbMovie);
        btnLogin = findViewById(R.id.btnLogin);
        btnSign = findViewById(R.id.btnSign);
        btnExit = findViewById(R.id.btnExit);
        txtView = findViewById(R.id.txtView);
        scrollView = findViewById(R.id.scrollView);
    }

    private void setEvent(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "\nThông tin đăng nhập";
                msg += "\nHọ tên: " + edtName.getText().toString();
                msg += "\nCCCD: " + edtCCCD.getText().toString();
                if(rdDH.isChecked()){
                    msg += "\nTrình độ: " + rdDH.getText().toString();
                }
                else if(rdCD.isChecked()){
                    msg += "\nTrình độ: " + rdCD.getText().toString();
                }
                else if(rdTC.isChecked()){
                    msg += "\nTrình độ: " + rdTC.getText().toString();
                }
                msg += "\nSở thích: ";
                boolean check = false;
                if(cbGame.isChecked()){
                    msg += cbGame.getText().toString();
                    check = true;
                }
                if(cbSport.isChecked()){
                    msg += (check ? ", " : "") + cbSport.getText().toString();
                    check = true;
                }
                if(cbMovie.isChecked()){
                    msg += (check ? ", " : "") + cbMovie.getText().toString();
                }
                txtView.setText(txtView.getText().toString() + msg + "\n");
            }
        });

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "\nThông tin đăng ký";
                msg += "\nHọ tên: " + edtName.getText().toString();
                msg += "\nCCCD: " + edtCCCD.getText().toString();
                if(rdDH.isChecked()){
                    msg += "\nTrình độ: " + rdDH.getText().toString();
                }
                else if(rdCD.isChecked()){
                    msg += "\nTrình độ: " + rdCD.getText().toString();
                }
                else if(rdTC.isChecked()){
                    msg += "\nTrình độ: " + rdTC.getText().toString();
                }
                msg += "\nSở thích: ";
                boolean check = false;
                if(cbGame.isChecked()){
                    msg += cbGame.getText().toString();
                    check = true;
                }
                if(cbSport.isChecked()){
                    msg += (check ? ", " : "") + cbSport.getText().toString();
                    check = true;
                }
                if(cbMovie.isChecked()){
                    msg += (check ? ", " : "") + cbMovie.getText().toString();
                }
                txtView.setText(txtView.getText() + msg +"\n");
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainBT5.this, "Quit", Toast.LENGTH_SHORT).show();
            }
        });
    }
}