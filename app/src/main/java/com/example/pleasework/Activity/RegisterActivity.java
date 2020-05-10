package com.example.pleasework.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pleasework.Network.Authentication;
import com.example.pleasework.R;

public class RegisterActivity extends AppCompatActivity {


    private Activity activity = RegisterActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //  getSupportActionBar().setDisplayShowTitleEnabled(false);

        final EditText email = (EditText) findViewById(R.id.emailR);
        final EditText password = (EditText) findViewById(R.id.passwordR);
        final EditText password2 = (EditText) findViewById(R.id.password2);

        Button registration = (Button) findViewById(R.id.registration);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    email.setError("Enter right E-mail");
                } else  if (password2.getText().equals("")) {
                    password2.setError("Field shouldn't be empty");
                } else if (password.getText().equals("")) {
                    password.setError("Field shouldn't be empty");
                } else if (password.getText().toString().length() < 6) {
                    password.setError("Password should contain at least 6 symbols");
                } else if(!password.getText().toString().equals(password2.getText().toString())){
                    password2.setError("Passwords are different");
                }
                else
                    Authentication.signUp(activity, email.getText().toString(), password.getText().toString());
            }
//                if (!email.getText().equals("") && !password.getText().equals("") && !password2.getText().equals("")){
//                    if (password.getText().toString().equals(password2.getText().toString())){
//                        Authentication.signUp(activity, email.getText().toString(), password.getText().toString());
//                    }
//                    else
//                        Toast.makeText(activity, "Пароли не совпадают", Toast.LENGTH_LONG).show();
//                }
//                else
//                    Toast.makeText(activity, "Заполните все поля", Toast.LENGTH_LONG).show();
//            }

//                if (email.getText().toString().equals("")) {
//                    email.setError("Field shouldn't be empty");
//
//            }
//                else  {
//                    email.setError("Enter right E-mail");}
//               else if(password.getText().toString().equals("")  || password2.getText().toString().trim().isEmpty()) {
//                    password.setError("Field shouldn't be empty");
//                    password2.setError("Field shouldn't be empty");
//               }
//                else if (password.getText().toString().length() < 6) {
//                        password.setError("Password should contain min 6 symbols");
//                    }
//                else
//
//            }
        });
    }


}
