package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class MainBT12 extends AppCompatActivity {

    EditText edtTaiKhoan, edtMatKhau;
    CheckBox cbNhoMatKhau;
    Button btnDangNhap, btnBieuDo;
    SharedPreferences accountSharedPref;
    PieChart chart;
    int loginCount = 10;
    int successLoginCount = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bt12);
        setControl();
        setEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        accountSharedPref = getSharedPreferences("Account", Context.MODE_PRIVATE);
        edtTaiKhoan.setText(accountSharedPref.getString("username", ""));
        edtMatKhau.setText(accountSharedPref.getString("password", ""));
        cbNhoMatKhau.setChecked(accountSharedPref.getBoolean("remember", false));
    }

    private void setControl(){
       edtTaiKhoan = findViewById(R.id.edtTaiKhoan);
       edtMatKhau = findViewById(R.id.edtMatKhau);
       cbNhoMatKhau = findViewById(R.id.cbNhoMatKhau);
       btnDangNhap = findViewById(R.id.btnDangNhap);
       btnBieuDo = findViewById(R.id.btnBieuDo);
       chart = findViewById(R.id.chart);
    }

    private void setEvent(){
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCount+=1;
                if(edtTaiKhoan.getText().toString().equals(edtMatKhau.getText().toString())){
                    successLoginCount+=1;
                    if(cbNhoMatKhau.isChecked())
                        rememberAccount();
                    else
                        clearSharedPref(accountSharedPref);

                    Toast.makeText(MainBT12.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainBT12.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBieuDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChart();
            }
        });
    }

    private void rememberAccount(){
        SharedPreferences sharedPref = getSharedPreferences("Account", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = accountSharedPref.edit();
        editor.putString("username", edtTaiKhoan.getText().toString());
        editor.putString("password", edtMatKhau.getText().toString());
        editor.putBoolean("remember", cbNhoMatKhau.isChecked());
        editor.apply();
    }

    private void clearSharedPref(SharedPreferences pref){
        pref.edit().clear().apply();
    }

    private void showChart(){
        ArrayList<PieEntry> data = new ArrayList<>();
        data.add(new PieEntry(successLoginCount, 0));
        data.add(new PieEntry(loginCount - successLoginCount, 1));
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        ArrayList<String> labels = new ArrayList<>();
        labels.add("Success");
        labels.add("Fail");
        PieDataSet dataset = new PieDataSet(data, "user");
        dataset.setColors(colors);
        PieData pieData = new PieData(dataset);
        chart.setData(pieData);
        chart.invalidate();
    }
}