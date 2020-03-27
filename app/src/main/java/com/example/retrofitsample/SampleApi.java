package com.example.retrofitsample;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface SampleApi {

    @GET("posts")
    Call<List<Post>> getPosts();  //Remember methods in interface do not have body (they are abstract)

    //method overloading
    @GET("posts")
    Call<List<Post>> getQueriedPosts( //this reads baseURL/posts?userId=value&_sort=value&_order=value
                                      @Query("userId") int userId,
                                      @Query("_sort") String sort,
                                      @Query("_order") String order
    );  //A simple queried get request

    //method overloading
    @GET("posts")
    Call<List<Post>> getQueriedPosts( //this reads baseURL/posts?userId=array[]&_sort=value&_order=value
                                     @Query("userId") Integer[] userId, //Integer is nullable, int is not... Here, we tried to get multiple users posts
                                     @Query("_sort") String sort,
                                     @Query("_order") String order
    );  // Using multiple parameter values approach

    //method overloading
    @GET("posts")
    Call<List<Post>> getQueriedPosts(@QueryMap Map<String, String> keyValueParameter);  //Using Map approach

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);  //Using dynamic parameter approach

    @GET
    Call<List<Comment>> getComments(@Url String url);  //Using URL approach

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String desc
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> fields);

    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int postId, @Body Post post);

    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int postId, @Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int postId);

}
