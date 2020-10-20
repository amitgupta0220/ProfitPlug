package com.example.profitplug;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
    TextView forgotPass, registerFromLogin;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private EditText emailTV, passwordTV;
    private Button registerButton, loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(getApplicationContext());
        super.onCreate(savedInstanceState);
        setTitle("Login Page");
        setContentView(R.layout.activity_login_page);
        mAuth = FirebaseAuth.getInstance();
        forgotPass = findViewById(R.id.forgotPass);
        progressBar = findViewById(R.id.progressBar);
        emailTV = findViewById(R.id.usernameLoginPage);
        passwordTV = findViewById(R.id.passwordLoginPage);
        registerButton = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.loginButton);
        registerFromLogin = findViewById(R.id.registerFromLogin);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        getSupportActionBar().setElevation(0);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginPage.this, "Forgot Password", Toast.LENGTH_SHORT).show();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });
        registerFromLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginPage.this, RegisterPage.class);
                startActivity(i);
            }
        });

    }

    private void loginUserAccount() {


        String email, password;
        email = emailTV.getText().toString();
        password = passwordTV.getText().toString();
        if (!(TextUtils.isEmpty(email)) && !(TextUtils.isEmpty(password))) {
            progressBar.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(LoginPage.this, "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(LoginPage.this, "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginPage.this, "Login successful!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                            Intent intent = new Intent(LoginPage.this, LandingPageBusiness.class);
                            finish();
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginPage.this, "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void registerNewUser() {


        String email, password;
        email = emailTV.getText().toString();
        password = passwordTV.getText().toString();
        if (!(TextUtils.isEmpty(email)) && !(TextUtils.isEmpty(password))) {
            progressBar.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(LoginPage.this, "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(LoginPage.this, "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginPage.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                            Intent intent = new Intent(LoginPage.this, LandingPageBusiness.class);
                            finish();
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginPage.this, "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
