package com.team9.spda_team9.forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.installations.InstallationTokenResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firestore.v1.Write;
import com.team9.spda_team9.R;
import com.team9.spda_team9.notification.APIService;
import com.team9.spda_team9.notification.Client;
import com.team9.spda_team9.notification.Data;
import com.team9.spda_team9.notification.MyResponse;
import com.team9.spda_team9.notification.NotificationSender;
import com.team9.spda_team9.user.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteCommentTest extends AppCompatActivity {

    EditText replyId, title, body;
    Button submit;
    private APIService apiService;
    FirebaseFirestore DB;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment_test);

        replyId = findViewById(R.id.replyId);
        title = findViewById(R.id.commentTitle);
        body = findViewById(R.id.commentBody);
        submit = findViewById(R.id.commentSubmit);

        apiService = Client.getClient("https://fcm.googleapis.com").create(APIService.class);
        DB = FirebaseFirestore.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                DB.collection("User").document(replyId.getText().toString().trim()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            String userToken = task.getResult().toObject(User.class).getToken();
                            sendNotifications(userToken, title.getText().toString().trim(), body.getText().toString().trim());
                            Toast.makeText(WriteCommentTest.this, "notification sent", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        UpdateToken();
    }

    private void UpdateToken() {

        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(task.isSuccessful()){
                    String token = task.getResult().getToken();
                    username = "James";
                    FirebaseFirestore.getInstance().collection("User").document(username).update("token", token);
                    Log.d("MyToken",token);
                }
            }
        });

//        FirebaseInstallations.getInstance().getToken(false).addOnCompleteListener(new OnCompleteListener<InstallationTokenResult>()
//        {
//
//            @Override
//            public void onComplete(@NonNull Task<InstallationTokenResult> task) {
//                if(task.isSuccessful()){
//                    String token = task.getResult().getToken();
//                    username = "James";
//                    FirebaseFirestore.getInstance().collection("User").document(username).update("token", token);
//                    Log.d("MyToken",token);
//                }else
//                {
//                    String notoken="Token not found";
//                    Log.d("MyToken", notoken);
//                }
//            }
//        });
    }

    public void sendNotifications(String usertoken, String title, String message) {

        Data data = new Data(title, message);
        NotificationSender sender = new NotificationSender(data, usertoken);

        apiService.sendNotifcation(sender).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (response.code() == 200) {
                    if (response.body().success != 1) {
                        Toast.makeText(WriteCommentTest.this, "Failed ", Toast.LENGTH_LONG);
                    }
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Toast.makeText(WriteCommentTest.this, "Failed ", Toast.LENGTH_LONG);
            }
        });
    }
}