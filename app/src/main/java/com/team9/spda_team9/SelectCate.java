package com.team9.spda_team9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectCate extends AppCompatActivity implements View.OnClickListener {

    Button  SelectCate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_cate);

        SelectCate = (Button)findViewById(R.id.button4) ;
        SelectCate.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        setContentView(R.layout.activity_select_topic);
    }
}