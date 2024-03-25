package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainBT8 extends AppCompatActivity {
    private EditText edtId, edtName, edtPrice;
    private Spinner spLoaiSP;
    private ArrayList<String> dsLoaiSP = new ArrayList<>();
    private ArrayAdapter adapter_dsLoaiSP;
    private ImageView ivLoaiSP;
    private Button btnThem, btnXoa, btnSua, btnThoat;
    private ListView lvSP;
    private ArrayList<SanPham> dsSanPham = new ArrayList<>();
    private ArrayAdapter adapter_dsSanPham;

    private DatabaseSanPham database;

    private int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bt8);
        setControl();
        setEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void setControl(){
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        spLoaiSP = findViewById(R.id.spLoaiSP);
        ivLoaiSP = findViewById(R.id.ivLoaiSP);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnThoat = findViewById(R.id.btnThoat);
        lvSP = findViewById(R.id.lvSP);

    }

    private void initial(){
        dsLoaiSP.add("Samsung");
        dsLoaiSP.add("Iphone");
        dsLoaiSP.add("Nokia");
        adapter_dsLoaiSP = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dsLoaiSP);
        spLoaiSP.setAdapter(adapter_dsLoaiSP);
        // Sử dụng layout item đơn giản
//        adapter_dsSanPham = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dsSanPham);
        // Sử dụng custom adapter để xử lí hiển thị một custom item (dựa theo layout_list_item_sp)
        adapter_dsSanPham = new CustomAdapter(this, R.layout.layout_list_item_sp, dsSanPham);
        lvSP.setAdapter(adapter_dsSanPham);
        database = new DatabaseSanPham(this);
    }

    private void setEvent(){
        initial();
        getData();
        spLoaiSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(spLoaiSP.getSelectedItem().toString()){
                    case "Samsung": ivLoaiSP.setImageResource(R.drawable.samsung);break;
                    case "Iphone": ivLoaiSP.setImageResource(R.drawable.iphone);break;
                    case "Nokia": ivLoaiSP.setImageResource(R.drawable.nokia);break;
                    default: break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lvSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                SanPham sp = dsSanPham.get(position);
                edtId.setText(sp.getId());
                edtName.setText(sp.getName());
                edtPrice.setText(Integer.toString(sp.getPrice()));
                spLoaiSP.setSelection(dsLoaiSP.indexOf(sp.getCategory()));
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString();
                String name = edtName.getText().toString();
                int price = Integer.parseInt(edtPrice.getText().toString());
                String category = spLoaiSP.getSelectedItem().toString();
                SanPham sp = new SanPham(id,name,price,category);
//                dsSanPham.add(sp);
//                adapter_dsSanPham.notifyDataSetChanged();
                database.add(sp);
                getData();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index == -1) return;
                SanPham sp = dsSanPham.get(index);
                database.delete(sp);
                getData();
                edtId.setText("");
                edtName.setText("");
                edtPrice.setText(null);
                spLoaiSP.setSelection(0);
                index = -1;
//                adapter_dsSanPham.remove(adapter_dsSanPham.getItem(index));
//                adapter_dsSanPham.notifyDataSetChanged();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index == -1) return;
                SanPham sp = dsSanPham.get(index);
                sp.setName(edtName.getText().toString());
                sp.setId(edtId.getText().toString());
                sp.setPrice(Integer.parseInt(edtPrice.getText().toString()));
                sp.setCategory(spLoaiSP.getSelectedItem().toString());
//                adapter_dsSanPham.notifyDataSetChanged();
                database.update(sp);
                getData();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getData(){
        dsSanPham.clear();
        dsSanPham.addAll(database.getData());
        adapter_dsSanPham.notifyDataSetChanged();
    }
}