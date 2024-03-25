package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainChiTietSP extends AppCompatActivity {
    private EditText edtId, edtName,edtPrice;
    private Button btnDelete, btnEdit, btnReturn;
    DatabaseSanPham database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chi_tiet_sp);
        setControl();
        setEvent();
    }

    private void setControl(){
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        btnEdit = findViewById(R.id.btnSua);
        btnDelete = findViewById(R.id.btnXoa);
        btnReturn = findViewById(R.id.btnQuayLai);
        database = new DatabaseSanPham(this);
    }

    private void setEvent(){
        SanPham sp = (SanPham) getIntent().getSerializableExtra("item");
        edtId.setText(sp.getId());
        edtName.setText(sp.getName());
        edtPrice.setText(Integer.toString(sp.getPrice()));

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.delete(sp);
                Toast.makeText(MainChiTietSP.this, "Del success", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.setName(edtName.getText().toString());
                sp.setPrice(Integer.parseInt(edtPrice.getText().toString()));
                database.update(sp);
                Toast.makeText(MainChiTietSP.this, "Edit success", Toast.LENGTH_SHORT).show();
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}