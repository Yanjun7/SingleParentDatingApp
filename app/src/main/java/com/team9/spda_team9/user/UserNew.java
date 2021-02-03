package com.team9.spda_team9.user;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team9.spda_team9.R;

public class UserNew extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore DB;

    EditText fullName, username, email, password;
    Button submit;
    User user;

    private static final String TAG = UserNew.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_new);

        DB = FirebaseFirestore.getInstance();

        fullName = findViewById(R.id.fullName);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);

        user = new User();
    }

    @Override
    public void onClick(View view) {
        user.setFullName(fullName.getText().toString().trim());
        user.setUsername(username.getText().toString().trim());
        user.setEmail(email.getText().toString().trim());
        user.setPassword(password.getText().toString().trim());

        DB.collection("User").document(user.getUsername()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Successfully added");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Unsuccessful to add new User");
            }
        });
    }
}