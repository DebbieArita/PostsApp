package com.example.retrorecytry3;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PostList {
    @SerializedName("postList")
    private List<Post> postList;

    public List<Post> getPostList(){
        return postList;
    }

    public void setPostList(List<Post> postList){
        this.postList = postList;
    }
}


