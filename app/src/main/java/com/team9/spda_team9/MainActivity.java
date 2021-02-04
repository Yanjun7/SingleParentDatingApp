package com.team9.spda_team9;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private final String[] categories ={"Dating & Relationships", "Teenagers", "Parenting",
    "Child Care", "Finances", "Mental Health Support"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        FirebaseApp.initializeApp(this);



    }

    @Override
    public void onItemClick(AdapterView<?> av,
                            View v, int pos, long id) {

        TextView textView = v.findViewById(R.id.textView);
        String caption = textView.getText().toString();

        Toast toast = Toast.makeText(this, caption, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
