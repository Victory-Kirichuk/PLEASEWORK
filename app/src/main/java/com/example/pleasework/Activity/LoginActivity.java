package com.example.pleasework.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pleasework.Network.Authentication;
import com.example.pleasework.R;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Activity activity = LoginActivity.this;

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = Authentication.getUser();

        if (currentUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(activity, "Logged as " + currentUser.getEmail(), Toast.LENGTH_LONG).show();
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);


        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
//        final boolean password1 = password.getText().toString().trim().isEmpty();
//        final boolean email1 = email.getText().toString().trim().isEmpty();
        Button enter = (Button) findViewById(R.id.enter);
        Button registration = (Button) findViewById(R.id.registration);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }


        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email.getText().toString().equals("") && !password.getText().toString().equals("")) {

                    Authentication.signIn(activity, email.getText().toString(), password.getText().toString());

                } else
                    Toast.makeText(activity, "All fields should be filled", Toast.LENGTH_LONG).show();
            }
        });
    }
}
