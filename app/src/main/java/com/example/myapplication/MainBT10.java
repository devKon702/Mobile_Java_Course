package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainBT10 extends AppCompatActivity {

    private ArrayList<User> data  = new ArrayList<>();
    private SearchView svUser;
    private ListView lvDanhSachUser;

    private UserAdapter userAdapter;

    private boolean isFirstRun = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bt10);
        initial();
        setControl();
        setEvent();
    }

    private void initial(){
        userAdapter = new UserAdapter(this, R.layout.layout_list_user_item, data);
    }


    private void setControl(){
        lvDanhSachUser = findViewById(R.id.lvDanhSach);
        lvDanhSachUser.setAdapter(userAdapter);
        svUser = findViewById(R.id.svDanhSach);

    }

    private void setEvent(){
        readAPI();

//        if(isFirstRun){
//            userAdapter.saveData(data);
//            isFirstRun = false;
//        }
//        userAdapter.notifyDataSetChanged(); // Lỗi, phải đặt trong lúc lấy response
        svUser.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userAdapter.search(newText);
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        readAPI();
    }

    private void readAPI(){
        data.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://api.github.com/users";

        JsonObjectRequest requestObject = new JsonObjectRequest(Request.Method.GET, "https://api.github.com/search/users?q=mojobo", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("items");
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject item = jsonArray.getJSONObject(i);
                                User user = new User(item.getString("login"), item.getString("avatar_url"), item.getString("url"));
                                data.add(user);
                            }
                            userAdapter.notifyDataSetChanged();
                            if(isFirstRun){
                                userAdapter.saveData(data);
                                isFirstRun = false;
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", error.toString());
                    }
                });
        requestQueue.add(requestObject);


//        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        for(int i=0; i<response.length(); i++){
//                            try {
//                                JSONObject item = response.getJSONObject(i);
//                                User user = new User(item.getString("login"), item.getString("avatar_url") ,item.getString("url") );
//                                data.add(user);
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                        if(data.toArray().length == 0){
//                            data.add(new User("user", "fdsakfjlkds", "fsdfs"));
//                        }
//                        userAdapter.notifyDataSetChanged();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainBT10.this, error.toString(), Toast.LENGTH_SHORT).show();
//
//                        data.add(new User("Unknown", "Unknown", "Unknown"));
//                        userAdapter.notifyDataSetChanged();
//                    }
//                });
//        requestQueue.add(request);
    }
}