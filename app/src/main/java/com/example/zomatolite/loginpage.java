package com.example.zomatolite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class loginpage extends AppCompatActivity {

    private EditText phoneEditText, passwordEditText;
    private Button loginButton, registerButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loginpage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        phoneEditText = findViewById(R.id.phoneEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.submitButton);
        registerButton = findViewById(R.id.registerButton);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Login Button
        loginButton.setOnClickListener(v -> {
            String phone = phoneEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (dbHelper.checkUser(phone, password)) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
                // Navigate to the next layout
                Intent intent = new Intent(loginpage.this, homepage.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Invalid User!", Toast.LENGTH_SHORT).show();
            }
        });

        // Register Button
        registerButton.setOnClickListener(v -> {
            String phone = phoneEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (dbHelper.registerUser(phone, password)) {
                Toast.makeText(this, "Registration Successful! You can now log in.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "User already exists!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}