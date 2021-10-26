package com.example.codehorizonapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText mEmail, mPass;
    private TextView mSignin;
    private Button signInBtn;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mEmail = findViewById(R.id.enterEmailSignUp);
        mPass = findViewById(R.id.enterPasswordSignUp);
        mSignin = findViewById(R.id.signInText);
        signInBtn = findViewById(R.id.signUpBtn);

        auth = FirebaseAuth.getInstance();

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

    }

    private void createUser(){
        String email = mEmail.getText().toString();
        String pass = mEmail.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if(!pass.isEmpty()){

                auth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignUpActivity.this, "Registered Successfully!", Toast.LENGTH_LONG);
                                startActivity(new Intent(SignUpActivity.this, MainActivity.this));
                            }
                        });

            }else{
                mPass.setError("Empty Field Not Allowed");
            }
        }else if(email.isEmpty()){
            mEmail.setError("Empty Field Not Allowed");
        }else{
            mEmail.setError("Please Enter Correct Email");
        }
    }
}