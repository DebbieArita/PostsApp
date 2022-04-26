package com.example.retrorecytry3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    List<Post> postList;
    JSONPlaceholder service;

    private Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_users);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = new Database(this);


        Log.e("BodyText", String.valueOf(R.id.body));

        JSONPlaceholder service = ApiClient.getRetrofitInstance();

        Call<PostList> call = service.getPostData();

        Log.wtf("URL Called", call.request().url()+ " " );

        call.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                database.insertPostList(response.body().getPostList());
                generatePostList();
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something is wrong..." , Toast.LENGTH_SHORT).show();
                generatePostList();
            }
        });

        }



    private void generatePostList() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_users);
        recyclerAdapter = new RecyclerAdapter(database.postList());
        recyclerAdapter.setData(database.postList());
        recyclerView.setAdapter(recyclerAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

    }
}