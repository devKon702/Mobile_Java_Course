package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSanPham extends SQLiteOpenHelper {

    public DatabaseSanPham(@Nullable Context context) {
        super(context, "dbSanPham", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table tbSanPham (id text, name text, price text, category text)";
        db.execSQL(sql);
    }

    public void add(SanPham sp){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "insert into tbSanPham values(?,?,?,?)";
        db.execSQL(sql,new String[]{sp.getId(), sp.getName(), Integer.toString(sp.getPrice()), sp.getCategory()});
    }

    public void delete(SanPham sp){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete from tbSanPham where id=?";
        db.execSQL(sql,new String[]{sp.getId()});
    }

    public void update(SanPham sp){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Update tbSanPham set name=?,price=?,category=? where id=?";
        db.execSQL(sql,new String[]{sp.getName(), Integer.toString(sp.getPrice()), sp.getCategory(), sp.getId()});
    }

    public List<SanPham> getData(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from tbSanPham";
        List<SanPham> data = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                SanPham sp = new SanPham(cursor.getString(0),cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3));
                data.add(sp);
            }while(cursor.moveToNext());
        }

        return data;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
