package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.TaskStackBuilder;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainTrangChu extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar; // androidx.appcompat.widget
    FrameLayout frame;
    NavigationView navigation;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trang_chu);
        setControl();
        setEvent();
    }

    private void setControl(){
        drawer = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        frame = findViewById(R.id.main_frame);
        navigation = findViewById(R.id.navigation);
        context = this;
    }

    private void setEvent() {

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_open, R.string.navigation_close);
        drawer.addDrawerListener(drawerToggle);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện click trên nút navigation ở đây
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.openDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        drawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show();
//                // Xử lý sự kiện click trên nút navigation ở đây
//                if (drawer.isDrawerVisible(GravityCompat.START)) {
//                    drawer.openDrawer(GravityCompat.START);
//                } else {
//                    drawer.openDrawer(GravityCompat.START);
//                }
            }
        });
        drawerToggle.syncState();
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Xử lý sự kiện khi một item trong thanh điều hướng được chọn
                int id = item.getItemId();
                if(id == R.id.menu){
                    Intent intent = new Intent(context, MainBT8.class);
                    startActivity(intent);
                }
                if(id == R.id.gioHang){
                    Toast.makeText(MainTrangChu.this, "Giỏ hàng", Toast.LENGTH_SHORT).show();
                }
                if(id == R.id.account){
                    Toast.makeText(MainTrangChu.this, "Account", Toast.LENGTH_SHORT).show();
                }
                if(id == R.id.caiDat){
                    Toast.makeText(MainTrangChu.this, "Cài đặt", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}