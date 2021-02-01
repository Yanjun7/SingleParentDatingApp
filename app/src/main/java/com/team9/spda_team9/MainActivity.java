package com.team9.spda_team9;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements android.view.View.OnClickListener {
    private static final String TAG = "DocSnippets";
    public static final String CONTENT_KEY = "Content";
    public static final String TITLE_KEY = "Title";
    Button save ;

    private DocumentReference myForum=FirebaseFirestore.getInstance().document("sampleDate/inspiration");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = (Button)findViewById(R.id.btn1) ;
        save.setOnClickListener(this);
    }


    public void Saveforum(android.view.View view)
    {
        EditText titleView=(EditText) findViewById(R.id.title);
        EditText contentView=(EditText) findViewById(R.id.content);
        String titleText=titleView.getText().toString();
        String contentText=contentView.getText().toString();

        if(titleText.isEmpty()||contentText.isEmpty()){return;}
        Map<String,Object> dataToSave=new HashMap<String,Object>();
        dataToSave.put(TITLE_KEY,titleText);
        dataToSave.put(CONTENT_KEY,contentText);
        myForum.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG,"Success save!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG,"Faild save!");
            }
        });

    }
    @Override
    public void onClick(android.view.View v) {
        Saveforum(v);
    }
}
