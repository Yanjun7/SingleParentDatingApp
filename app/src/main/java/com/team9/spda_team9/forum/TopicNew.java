package com.team9.spda_team9.forum;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team9.spda_team9.R;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class TopicNew extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    FirebaseFirestore DB;
    Button button;
    Spinner spinner;
    EditText title,body;
    String dt;
    Topic topic;
    Category category;

    private static final String TAG = TopicNew.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_topic);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        DB = FirebaseFirestore.getInstance();

        title = findViewById(R.id.title);
        body = findViewById(R.id.body);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        dt = LocalDateTime.now(ZoneId.of("Singapore")).format(formatter);
        button = findViewById(R.id.button);
        topic = new Topic();

        button.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        category = Category.values()[position];
    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }

    @Override
    public void onClick(View v){
        if(title==null||title.getText().toString().trim().equals(""))
        {
            new AlertDialog.Builder(TopicNew.this)
                    .setTitle("Error!")
                    .setMessage("Title cannot be empty")
                    .setPositiveButton("ok",null)
                    .show();
        }
        else if (title.length() >40) {
            new AlertDialog.Builder(TopicNew.this)
                    .setTitle("Error!")
                    .setMessage("Title cannot be so long!")
                    .setPositiveButton("ok",null)
                    .show();
        }
        else{
            DB.collection("Topic")
                    .whereEqualTo("title", title.getText().toString().trim())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                if(!task.getResult().isEmpty())
                                {
                                    new AlertDialog.Builder(TopicNew.this)
                                            .setTitle("Error!")
                                            .setMessage("Title exist!")
                                            .setPositiveButton("ok",null)
                                            .show();
                                }
                                else
                                {
                                    topic.setTopicId(UUID.randomUUID().toString());
                                    topic.setTitle(title.getText().toString().trim());
                                    topic.setBody(body.getText().toString().trim());
                                    topic.setTime(dt);
                                    topic.setCategory(category);
                                    topic.setUserId(0);
                                    DB.collection("Topic").add(topic).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error adding document", e);
                                        }
                                    });
                                    //Toast.makeText(this,"Successful",Toast.LENGTH_LONG);
                                    finish();
                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }
    }
}