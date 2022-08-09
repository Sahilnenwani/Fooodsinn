package com.example.babycare;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    TextView email;
    TextView pass;
    ImageView imageView1;
    ImageView imageView;
    RelativeLayout relativeLayout;
    ProgressBar progressBar;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.INVISIBLE);
        firebaseAuth = FirebaseAuth.getInstance();
        email = (TextView) findViewById(R.id.name);
        pass = (TextView) findViewById(R.id.password);
        relativeLayout = (RelativeLayout) findViewById(R.id.rela);
        pass.setTransformationMethod(new PasswordTransformationMethod());
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        imageView = (ImageView) findViewById(R.id.formBack);
        imageView1 = (ImageView) findViewById(R.id.logo);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardhide();
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardhide();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardhide();
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardhide();
            }
        });

    }

    public void keyboardhide() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);


    }

    public void onclick(View v) {

        if (email.getText().toString().equals("") || pass.getText().toString().equals("")) {

            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(SignUp.this, "You left field empty", Toast.LENGTH_SHORT).show();
        } else {


            progressBar.setVisibility(View.VISIBLE);
            String e = "";
            String p = "";
            e = email.getText().toString();
            p = pass.getText().toString();
            firebaseAuth.createUserWithEmailAndPassword(e, p).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUp.this, "Registered", Toast.LENGTH_SHORT).show();
                        Intent myintent = new Intent(SignUp.this, Login.class);
                        startActivity(myintent);
                    } else {
                        try {
                            throw task.getException();
                        }
                        // if user enters wrong email.
                        catch (FirebaseAuthWeakPasswordException weakPassword) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(SignUp.this, " weak_password", Toast.LENGTH_SHORT).show();
                        }
                        // if user enters wrong password.
                        catch (FirebaseAuthInvalidCredentialsException malformedEmail) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(SignUp.this, " malformed_email", Toast.LENGTH_SHORT).show();


                        } catch (FirebaseAuthUserCollisionException existEmail) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(SignUp.this, "exist_email", Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(SignUp.this, "onComplete: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });


        }
    }


}


