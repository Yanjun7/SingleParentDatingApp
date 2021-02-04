package com.team9.spda_team9.forum;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.team9.spda_team9.R;


public class newTopic extends Fragment {

//if user is logged in, display newTopic fragment


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_new_topic, container, false);
        Button b = (Button)root.findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(root.getContext(), TopicNew.class);
                startActivity(i);
            }
        });
        return root;
    }
}


