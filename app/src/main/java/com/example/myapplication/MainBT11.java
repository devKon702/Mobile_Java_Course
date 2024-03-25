package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainBT11 extends AppCompatActivity {

    private FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference mySPRef;

    private EditText edtThongBao, edtTenSP;
    private Button btnThongBao, btnThemSP;
    private ListView lvDanhSachSP;
    private ArrayList<SanPham> dsSanPham;
    private ArrayAdapter adapterSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bt11);
        initial();
        setControl();
        setEvent();
    }

    private void initial(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        if(myRef == null){
            Toast.makeText(this, "Error firebase", Toast.LENGTH_SHORT).show();
        }
        mySPRef = database.getReference("product");

        dsSanPham = new ArrayList<SanPham>();
        adapterSP = new CustomAdapter(this, R.layout.layout_list_item_sp, dsSanPham);
    }
    private void setControl(){
        edtThongBao = findViewById(R.id.edtThongBao);
        btnThongBao = findViewById(R.id.btnThongBao);
        edtTenSP = findViewById(R.id.edtTenSP);
        btnThemSP = findViewById(R.id.btnThemSP);
        lvDanhSachSP = findViewById(R.id.lvDanhSachSP);
    }

    private void setEvent(){
        lvDanhSachSP.setAdapter(adapterSP);
//        myRef.setValue("Hello, World!");
        // Xử lí chỉnh sửa firebase 'message'
        btnThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = edtThongBao.getText().toString();
                myRef.setValue(message);
            }
        });

        // Xử lí thêm sản phẩm
        btnThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sp = new SanPham(mySPRef.push().getKey(),edtTenSP.getText().toString(),100000, "Samsung" );

                mySPRef.child(sp.getId()).setValue(sp);
                dsSanPham.add(sp);
                adapterSP.notifyDataSetChanged();
            }
        });

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Toast.makeText(MainBT11.this, value, Toast.LENGTH_SHORT).show();
                edtThongBao.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(MainBT11.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mySPRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dsSanPham.clear();
                // Get Data product from FireBase
                for (DataSnapshot item:dataSnapshot.getChildren()){
                    SanPham sp = new SanPham();

                    sp.setId(item.child("id").getValue().toString());
                    sp.setName(item.child("name").getValue().toString());
                    sp.setPrice(Integer.parseInt(item.child("price").getValue().toString()));
                    sp.setCategory(item.child("category").getValue().toString());

                    dsSanPham.add(sp);
                }

                adapterSP.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(MainBT11.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}