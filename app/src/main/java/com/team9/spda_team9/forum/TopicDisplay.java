package com.team9.spda_team9.forum;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team9.spda_team9.R;

import java.util.Map;

public class TopicDisplay extends AppCompatActivity {
    FirebaseFirestore DB;
    private static final String TAG = TopicDisplay.class.getSimpleName();
    LinearLayout ll;
    boolean recreate = false;

    @Override
    protected void onResume() {

        super.onResume();
        if(recreate == true) recreate();
    }
    @Override
    protected void onPause(){
        super.onPause();
        recreate = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);


        DB = FirebaseFirestore.getInstance();
        ll = findViewById(R.id.topicContainer);
        recreate = false;

        Query findAllbyCat = DB.collection("Topic").whereEqualTo("category", "DatingAndRelationships");
        findAllbyCat.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        Map<String,Object> map = document.getData();
                        String title = (String)map.get("title");
                        String time = (String)map.get("time");
                        TextView tv1 = new TextView(TopicDisplay.this);
                        TextView tv2 = new TextView(TopicDisplay.this);
                        tv1.setText(title);
                        tv2.setText(time);
                        ll.addView(tv1);
                        ll.addView(tv2);

                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle("Topics");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopicDisplay.this, TopicNew.class);
                TopicDisplay.this.startActivity(intent);
            }
        });
    }


}