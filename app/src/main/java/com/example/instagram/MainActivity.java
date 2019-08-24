package com.example.instagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    String btnSign;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLog;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create an intent that will get the data we’ve stored in the MainActivity
        Intent i = getIntent();
        btnSign = i.getStringExtra("btnSign");
        //get data from intent
        btnSign = getIntent().getStringExtra("btnSign");
        //me = (Person) Parcels.unwrap(getIntent().getParcelableExtra("person"));
        //Declare and inflate
        etUsername = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                login(username,password);
            }
        });
        //ParseUser currentUser = ParseUser.getCurrentUser();
        //if (currentUser != null){
            //goSurfActivity();}

    }
    private void login(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e !=  null){
                    //TODO: better error handling
                    Log.e(TAG, "Issue with login");
                    e.printStackTrace();
                    return;
                }
                //TODO: navigate to new activity if the user have sign properly

                else {
                    goSurfActivity();
                }

            }
        });
    }

    private void goSurfActivity() {
        Log.d(TAG, "Navigating to the Surf Activity");
        Intent i = new Intent(MainActivity.this, SurfActivity.class);
        startActivity(i);
        finish();
    }

    public void Back(View view){
        finish();
    }


}
