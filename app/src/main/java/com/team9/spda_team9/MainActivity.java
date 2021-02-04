package com.team9.spda_team9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.team9.spda_team9.forum.TopicNew;
import com.team9.spda_team9.user.UserMainActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button Touser ;
    Button Totopic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Touser = findViewById(R.id.user);
        Totopic = findViewById(R.id.topic);

        Totopic.setOnClickListener(this);
        Touser.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent;

        if (id == R.id.user) {
            intent = new Intent(this, UserMainActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.topic){
            intent = new Intent(this, TopicNew.class);
            startActivity(intent);
        }

    }
}
