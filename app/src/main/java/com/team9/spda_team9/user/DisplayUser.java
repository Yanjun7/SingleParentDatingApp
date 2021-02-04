package com.team9.spda_team9.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team9.spda_team9.MainActivity;
import com.team9.spda_team9.R;

public class DisplayUser extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore DB;
    TextView fullName, username, email, password;
    Button back;

    private static final String TAG = DisplayUser.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        fullName = findViewById(R.id.fullNameV);
        username = findViewById(R.id.usernameV);
        email = findViewById(R.id.emailV);
        password = findViewById(R.id.passwordV);
        back = findViewById(R.id.back);
        back.setOnClickListener(this);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("username");

        DB = FirebaseFirestore.getInstance();

        DB.collection("User").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    //document.get("username") != null
                    if (document.exists()) {
                        fullName.setText(document.get("fullName").toString());
                        username.setText(document.get("username").toString());
                        email.setText(document.get("email").toString());
                        password.setText("********");
                    } else
                    {
                        Toast.makeText(getApplicationContext(), "No such user", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
    }

    @Override
    public void onClick(View view){
        int id = view.getId();

        if(id == R.id.back){
            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
        }
    }

}