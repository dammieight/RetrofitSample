package com.example.retrofitsample;

import com.google.gson.annotations.SerializedName;

public class Post {
    private Integer id;  //Integer because we need it to be null so that retrofit can ignore it
    private  int userId;
    private String title;

    @SerializedName("body")  //we use this because the String name is different from the key name in the json object of the API
    private String description;

    public Post(int userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
