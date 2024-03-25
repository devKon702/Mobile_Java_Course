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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    private List<User> data; // Mảng chứa dữ liệu hiển thị
    private List<User> data_all = new ArrayList<>();
    private Context context; //
    private int resource; // Chỉ số layout cho item của list

    public UserAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.data = objects;
//        this.data_all.addAll(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);
        }

        User user = data.get(position);

        ImageView img = convertView.findViewById(R.id.ivUser);
        TextView login = convertView.findViewById(R.id.tvLogin);
        TextView url = convertView.findViewById(R.id.tvUrl);

        login.setText(user.getLogin());
        url.setText(user.getUrl());
//        Picasso.get().load(user.getAvatarUrl()).into(img);


        return convertView;
    }

    public void saveData(List<User> data){
        this.data_all.addAll(data);
    }

    public void search(String msg){
        Toast.makeText(context, Integer.toString(data_all.size()), Toast.LENGTH_SHORT).show();
        data.clear();
        if(msg.equals("")){
            data.addAll(data_all);
        }else{
            for(User user:data_all){
                if(user.getLogin().contains(msg)){
                    data.add(user);
                }
            }
        }
        notifyDataSetChanged();
    }
}
