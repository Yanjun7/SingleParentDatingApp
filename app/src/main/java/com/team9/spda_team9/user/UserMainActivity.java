package com.team9.spda_team9.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.installations.InstallationTokenResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.team9.spda_team9.R;

public class UserMainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        DB = FirebaseFirestore.getInstance();

        Button newUser = findViewById(R.id.newUser);
        Button viewUser = findViewById(R.id.viewUser);

        newUser.setOnClickListener(this);
        viewUser.setOnClickListener(this);

        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        FirebaseInstallations.getInstance().getToken(false).addOnCompleteListener(new OnCompleteListener<InstallationTokenResult>()
        {

            @Override
            public void onComplete(@NonNull Task<InstallationTokenResult> task) {
                if(task.isSuccessful()){
                    String token=task.getResult().getToken();
                    Log.d("MyToken",token);
                }else
                {
                    String notoken="Token not found";
                    Log.d("MyToken",notoken);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent;

        if (id == R.id.newUser) {

        } else if (id == R.id.viewUser) {
            EditText uname = findViewById(R.id.uname);
            String userId = uname.getText().toString().trim();

            if (!userId.equals("") && userId != null) {
                intent = new Intent(this, DisplayUser.class);
                intent.putExtra("username", userId);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Input Username", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}