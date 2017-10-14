package com.example.edgar.apiclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.edgar.apiclient.Util.CommentHelper;
import com.example.edgar.apiclient.Util.PostHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RequestQueue queue = Volley.newRequestQueue(this);
        final CommentHelper commentHelper = new CommentHelper(this);
        final PostHelper postHelper = new PostHelper(this);
        final String urlPosts = "https://jsonplaceholder.typicode.com/posts";
        final String urlComments = "https://jsonplaceholder.typicode.com/comments";

        Button btn_get_posts = (Button)  findViewById(R.id.btn_get_posts);
        Button btn_get_comments = (Button)  findViewById(R.id.btn_get_comments);
        Button btn_see_posts = (Button)  findViewById(R.id.btn_see_posts);
        Button btn_see_comments = (Button)  findViewById(R.id.btn_see_comments);

        btn_get_posts.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlPosts, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try {
                                    postHelper.open();
                                    postHelper.deleteAllPosts();
                                    for (int i = 0; i < response.length(); i++) {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        Post post = new Post();
                                        post.setPostID(jsonObject.getInt("id"));
                                        post.setUserID(jsonObject.getInt("userId"));
                                        post.setTitle(jsonObject.getString("title"));
                                        post.setBody(jsonObject.getString("body"));
                                        postHelper.addPost(post.getPostID(),post.getUserID(),post.getTitle(),post.getBody());
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(MainActivity.this, "Error retrieving posts", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error retrieving posts", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(jsonArrayRequest);
            }
        });

        btn_get_comments.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlComments, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try {
                                    commentHelper.open();
                                    commentHelper.deleteAllComments();
                                    for (int i = 0; i < response.length(); i++) {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        Comment comment = new Comment();
                                        comment.setCommentID(jsonObject.getInt("id"));
                                        comment.setPostID(jsonObject.getInt("postId"));
                                        comment.setName(jsonObject.getString("name"));
                                        comment.setEmail(jsonObject.getString("email"));
                                        comment.setBody(jsonObject.getString("body"));
                                        commentHelper.addComment(comment.getCommentID(),comment.getPostID(),comment.getName(),comment.getEmail(),comment.getBody());
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(MainActivity.this, "Error retrieving comments", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error retrieving comments", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(jsonArrayRequest);
            }
        });

        btn_see_posts.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                postHelper.open();
                ArrayList<Post> postsList = postHelper.getAllPosts();
                postHelper.close();
                if(postsList.size() == 0)
                    Toast.makeText(MainActivity.this, "There are no saved posts", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(getApplicationContext(), NewPostActivity.class);
                    intent.putExtra("data", postsList);
                    startActivity(intent);
                }
            }
        });

        btn_see_comments.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                commentHelper.open();
                ArrayList<Comment> commentsList = commentHelper.getAllComments();
                commentHelper.close();
                if(commentsList.size() == 0)
                    Toast.makeText(MainActivity.this, "There are no saved comments", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(getApplicationContext(), NewCommentActivity.class);
                    intent.putExtra("data", commentsList);
                    startActivity(intent);
                }
            }
        });
    }
}
