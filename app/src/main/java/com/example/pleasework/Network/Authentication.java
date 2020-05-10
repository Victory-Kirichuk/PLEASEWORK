package com.example.pleasework.Network;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.pleasework.Activity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Authentication {

    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public static void signUp(final Activity activity, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(activity, "You are registered", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(activity, MainActivity.class);
                            activity.startActivity(intent);

                        }
                    }
                })
                .addOnFailureListener(activity, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity, "Error", Toast.LENGTH_LONG);
                    }
                });
    }

    public static void signIn(final Activity activity, String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(activity, "You are logged as  " + getUser().getEmail(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(activity, MainActivity.class);
                            activity.startActivity(intent);

                        } else {
                            Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());
                        }
                    }
                })
                .addOnFailureListener(activity, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity, "Something get wrong", Toast.LENGTH_LONG).show();

                    }
                });
    }

    public static void logout() {
        mAuth.signOut();
    }

    public static FirebaseUser getUser() {
        return mAuth.getCurrentUser();
    }

}
