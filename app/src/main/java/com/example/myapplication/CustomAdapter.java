package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<SanPham> {
    private List<SanPham> data; // Mảng chứa dữ liệu hiển thị
    private Context context; //
    private int resource; // Chỉ số layout cho item của list

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<SanPham> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);
        }

        SanPham sp = data.get(position);
        ImageView img = convertView.findViewById(R.id.ivSanPham);
        TextView text = convertView.findViewById(R.id.tvTenSP);
        TextView price = convertView.findViewById(R.id.tvGiaSP);
        Button btnChiTiet = convertView.findViewById(R.id.btnChiTiet);

        text.setText(sp.getName());
        price.setText(Integer.toString(sp.getPrice()));
        switch(sp.getCategory()){
            case "Samsung": img.setImageResource(R.drawable.samsung);break;
            case "Iphone": img.setImageResource(R.drawable.iphone);break;
            case "Nokia": img.setImageResource(R.drawable.nokia);break;
            default: break;
        }

        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainChiTietSP.class);
                intent.putExtra("item",sp);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
