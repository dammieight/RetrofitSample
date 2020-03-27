package com.example.retrofitsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView tvJsonResult;
    Retrofit retrofit;
    SampleApi sampleApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvJsonResult = findViewById(R.id.tvJsonResult);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sampleApi = retrofit.create(SampleApi.class);
        //getPosts();

        /*
         * Method overloading
         * */
        //getComments(4);  //int id required
        //getComments("posts/4/comments"); //String url required

        /*
         * Method overloading
         * */
        //getQueriedPosts(1, "id", "desc");      //with single parameter values
        //getQueriedPosts(new Integer[]{2,3,1,}, "id", "desc"); //with multiple parameter values
        //getQueriedPosts();  //without parameter


        /*
         * Method overloading
         * */
        //createPost();
        //createPost(2, "The Good guy", "A man who doesn't take care of his family can't be rich");

        //patchPost();
        putPost();

        //deletePost();

    }

    //Method Overloading... Compile decide which of the getQueriedPosts methods to run wrt the parameters given
    private void getQueriedPosts(int userId, String sort, String order) {
        Call<List<Post>> callListPosts = sampleApi.getQueriedPosts(userId, sort, order);
        callListPosts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    tvJsonResult.setText("Error code: " + response.code());
                }

                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "Post Id: " + post.getId() + "\n";
                    content += "User Id: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Description: " + post.getDescription() + "\n\n";
                    tvJsonResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tvJsonResult.setText(t.getMessage());
            }
        });
    }

    //Method Overloading... Compile decide which of the getQueriedPosts methods to run wrt the parameters given
    private void getQueriedPosts(Integer[] userId, String sort, String order) {
        Call<List<Post>> callListPosts = sampleApi.getQueriedPosts(userId, sort, order);
        callListPosts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    tvJsonResult.setText("Error code: " + response.code());
                }

                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "Post Id: " + post.getId() + "\n";
                    content += "User Id: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Description: " + post.getDescription() + "\n\n";
                    tvJsonResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tvJsonResult.setText(t.getMessage());
            }
        });
    }

    //Method Overloading... Compile decide which of the getQueriedPosts methods to run wrt the parameters given
    private void getQueriedPosts() {
        //The downside of the approach is that u cannot pass multiple parameter values in array..
        //You have to do "put" several times
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "3");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");

        Call<List<Post>> callListPosts = sampleApi.getQueriedPosts(parameters);
        callListPosts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    tvJsonResult.setText("Error code: " + response.code());
                }

                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "Post Id: " + post.getId() + "\n";
                    content += "User Id: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Description: " + post.getDescription() + "\n\n";
                    tvJsonResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tvJsonResult.setText(t.getMessage());
            }
        });
    }

    private void getComments(int postId) {
        Call<List<Comment>> callListComments = sampleApi.getComments(postId);
        callListComments.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    tvJsonResult.setText("Error code: " + response.code());
                }
                List<Comment> comments = response.body();
                for (Comment comment : comments) {
                    String content = "";
                    content += "Comment Id: " + comment.getId() + "\n";
                    content += "Post Id: " + comment.getPostId() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Description: " + comment.getDescription() + "\n\n";
                    tvJsonResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                tvJsonResult.setText(t.getMessage());
            }
        });
    }

    private void getComments(String url) {
        Call<List<Comment>> callListComments = sampleApi.getComments(url);
        callListComments.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    tvJsonResult.setText("Error code: " + response.code());
                }
                List<Comment> comments = response.body();
                for (Comment comment : comments) {
                    String content = "";
                    content += "Comment Id: " + comment.getId() + "\n";
                    content += "Post Id: " + comment.getPostId() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Description: " + comment.getDescription() + "\n\n";
                    tvJsonResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                tvJsonResult.setText(t.getMessage());
            }
        });
    }

    private void getPosts() {
        Call<List<Post>> callListPosts = sampleApi.getPosts();
        callListPosts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    tvJsonResult.setText("Error code: " + response.code());
                }

                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "Post Id: " + post.getId() + "\n";
                    content += "User Id: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Description: " + post.getDescription() + "\n\n";
                    tvJsonResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tvJsonResult.setText(t.getMessage());
            }
        });
    }

    private void createPost() {
        Post post = new Post(2, "The Good guy", "A man who takes care of his family");

        Map<String, String> fields = new HashMap<>();
        fields.put("userId", "5");
        fields.put("title", "Bad Man");
        fields.put("body", "Bad man goes to London");

        Call<Post> call = sampleApi.createPost(fields /*or post*/);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    tvJsonResult.setText("Error code: " + response.code());
                }

                Post post = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "Post Id: " + post.getId() + "\n";
                content += "User Id: " + post.getUserId() + "\n";
                content += "Title: " + post.getTitle() + "\n";
                content += "Description: " + post.getDescription() + "\n\n";
                tvJsonResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tvJsonResult.setText("Error message: " + t.getMessage());
            }
        });
    }

    private void createPost(int userId, String title, String desc) {
        Call<Post> call = sampleApi.createPost(userId, title, desc);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    tvJsonResult.setText("Error code: " + response.code());
                }

                Post post = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "Post Id: " + post.getId() + "\n";
                content += "User Id: " + post.getUserId() + "\n";
                content += "Title: " + post.getTitle() + "\n";
                content += "Description: " + post.getDescription() + "\n\n";
                tvJsonResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tvJsonResult.setText("Error message: " + t.getMessage());
            }
        });
    }

    //PUT affects the whole row of data and even store new data if non available
    private void putPost(){
        Post post = new Post(3, "New title", null);
        Call<Post> postCall = sampleApi.putPost(3, post);
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    tvJsonResult.setText("Error message: " + response.code());
                }

                Post post = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "Post Id: " + post.getId() + "\n";
                content += "User Id: " + post.getUserId() + "\n";
                content += "Title: " + post.getTitle() + "\n";
                content += "Description: " + post.getDescription() + "\n\n";
                tvJsonResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tvJsonResult.setText("Error message: " + t.getMessage());
            }
        });
    }


    //PATCH only affect the non empty field attributes
    private void patchPost(){
        Post post = new Post(3, "New title", null);
        Call<Post> postCall = sampleApi.patchPost(3, post);
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    tvJsonResult.setText("Error message: " + response.code());
                }

                Post post = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "Post Id: " + post.getId() + "\n";
                content += "User Id: " + post.getUserId() + "\n";
                content += "Title: " + post.getTitle() + "\n";
                content += "Description: " + post.getDescription() + "\n\n";
                tvJsonResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tvJsonResult.setText("Error message: " + t.getMessage());
            }
        });
    }

    private void deletePost(){
        Call<Void> call = sampleApi.deletePost(4);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                tvJsonResult.setText("Request Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                tvJsonResult.setText("Error message: " + t.getMessage());
            }
        });
    }


}
