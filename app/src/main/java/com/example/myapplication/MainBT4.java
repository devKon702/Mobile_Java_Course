package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainBT4 extends AppCompatActivity {
    private EditText edtFirstNum, edtSecondNum, edtResult;
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bt4);
        setControl();
    }

    private void setControl() {
        edtFirstNum = findViewById(R.id.edtFirstNum);
        edtSecondNum = findViewById(R.id.edtSecondNum);
        edtResult = findViewById(R.id.edtResult);
        txtView = findViewById(R.id.txtView);
    }

    public void calculate(View view) {
        int first = Integer.parseInt(edtFirstNum.getText().toString());
        int second = Integer.parseInt(edtSecondNum.getText().toString());
        int result = Integer.parseInt(edtResult.getText().toString());
        String msg = "";
        if(view.getId() == R.id.btnSum){
            Toast.makeText(this, "Cộng", Toast.LENGTH_SHORT).show();
            msg = first + " + " + second + " = " + result + " is ";
            msg += (first + second == result ? "True" : "False");
            msg += "\n";
        } else if (view.getId() == R.id.btnSubtract) {
            Toast.makeText(this, "Trừ", Toast.LENGTH_SHORT).show();
            msg = first + " - " + second + " = " + result + " is ";
            msg += (first - second == result ? "True" : "False");
            msg += "\n";
        } else if (view.getId() == R.id.btnMulti) {
            Toast.makeText(this, "Nhân", Toast.LENGTH_SHORT).show();
            msg = first + " x " + second + " = " + result + " is ";
            msg += (first * second == result ? "True" : "False");
            msg += "\n";
        } else if (view.getId() == R.id.btnDivide) {
            Toast.makeText(this, "Chia", Toast.LENGTH_SHORT).show();
            msg = first + " / " + second + " = " + result + " is ";
            msg += (first / second == result ? "True" : "False");
            msg += "\n";
        }

        txtView.setText(txtView.getText() + msg);

    }
}