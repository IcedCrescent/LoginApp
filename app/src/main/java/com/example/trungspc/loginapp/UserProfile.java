package com.example.trungspc.loginapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

public class UserProfile extends AppCompatActivity {
    private static final String TAG = "UserProfile";

    JSONObject response, profile_pic_data, profile_pic_url;

    TextView username;
    TextView email;
    ImageView userPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Intent intent = getIntent();
        String jsonData = intent.getStringExtra("user_profile");
        Log.d(TAG, "onCreate: " + jsonData);

        username = findViewById(R.id.tv_username);
        email = findViewById(R.id.tv_email);
        userPicture = findViewById(R.id.iv_profile_pic);
        try {
            response = new JSONObject(jsonData);
            email.setText(response.get("email").toString());
            username.setText(response.get("name").toString());
            profile_pic_data = new JSONObject(response.get("picture").toString());
            profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
            Glide.with(this).load(profile_pic_url.getString("url")).into(userPicture);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
