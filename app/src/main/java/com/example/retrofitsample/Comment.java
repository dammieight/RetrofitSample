package com.example.retrofitsample;

import com.google.gson.annotations.SerializedName;

public class Comment {
    private int id;
    private int postId;
    private String email;
    private String name;

    @SerializedName("body")
    private String description;

    public int getId() {
        return id;
    }

    public int getPostId() {
        return postId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
