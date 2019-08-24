package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class SurfActivity extends AppCompatActivity {

    String btnLogin;

    private final String TAG = ".SurfActivity";
    private EditText etDescription;
    private Button btnCaptureImage;
    private ImageView ivPostImage;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surf);

        Intent i = getIntent();
        btnLogin = i.getStringExtra("btnLogin");
        btnLogin = getIntent().getStringExtra("btnLogin");

        etDescription = findViewById(R.id.etDescription);
        btnCaptureImage = findViewById(R.id.btnCaptureImage);
        ivPostImage = findViewById(R.id.ivPostImage);
        btnSubmit = findViewById(R.id.btnSubmit);

        queryPost();

    }

    private void queryPost() {
        final ParseQuery<Post> postQuerry = new ParseQuery<Post>(Post.class);
        postQuerry.include(Post.KEY_USER);
        postQuerry.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Error with query");
                    e.printStackTrace();
                    return;
                }
               for (int i = 0; i < posts.size(); i++){
                   Post post = posts.get(i);
                Log.d(TAG, "Post: " + posts.get(i).getDescription()
                 + ", username: " + posts.get(i).getUser().getUsername());
               }
            }
        });
    }
}
