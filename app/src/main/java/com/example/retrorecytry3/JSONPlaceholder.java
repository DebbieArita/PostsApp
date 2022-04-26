package com.example.retrorecytry3;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholder {
    @GET("db")
    Call<PostList> getPostData();

}

