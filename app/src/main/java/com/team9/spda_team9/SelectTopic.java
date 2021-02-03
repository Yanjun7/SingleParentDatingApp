package com.team9.spda_team9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectTopic extends AppCompatActivity implements View.OnClickListener {

    String Categories="Child Care";
    Button addtopic ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_topic);

        addtopic = (Button)findViewById(R.id.add) ;
        addtopic.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        setContentView(R.layout.activity_main);
    }
}