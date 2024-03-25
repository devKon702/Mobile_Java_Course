package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainBT3 extends AppCompatActivity {

    EditText edtUsername, edtPass, edtPhone, edtEmail;
    Button btnSign, btnReset;

    TextView txtInfor;

    Boolean err = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bt3);
        setControl();
        setEvent();
    }

    private void setControl(){
        edtUsername = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        btnSign = findViewById(R.id.btnSign);
        btnReset = findViewById(R.id.btnReset);
        txtInfor = findViewById(R.id.txtInfor);
    }

    private void setEvent(){
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNull(edtUsername);
                checkNull(edtPass);
                checkNull(edtPhone);
                checkNull(edtEmail);
                if(err) return;
                String msg = "\n" + edtUsername.getText().toString();
                msg += "\n" + edtPass.getText().toString();
                msg += "\n" + edtPhone.getText().toString();
                msg += "\n" + edtEmail.getText().toString();
                txtInfor.setText(msg);
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtUsername.setText("");
                edtPass.setText("");
                edtPhone.setText("");
                edtEmail.setText("");
                txtInfor.setText("");
            }
        });
    }

    private void checkNull(EditText comp){
        if(comp.getText().toString().isEmpty()){
            comp.setError("Please type something");
            comp.requestFocus();
            err = true;
        }
    }


}