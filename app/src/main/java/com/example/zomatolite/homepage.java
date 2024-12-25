package com.example.zomatolite;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class homepage extends AppCompatActivity {

    private ImageView cartIcon;
    private View snacksCategory;
    private View mainCourseCategory;
    private View dessertsCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homepage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cartIcon = findViewById(R.id.cartIcon);
        snacksCategory = findViewById(R.id.snacksCategory);
        mainCourseCategory = findViewById(R.id.mainCourseCategory);
        dessertsCategory = findViewById(R.id.dessertsCategory);

        // Set onClickListeners
        cartIcon.setOnClickListener(v -> {
            // Open Cart Activity
            Intent cartIntent = new Intent(getApplicationContext(),cartpage.class);
            startActivity(cartIntent);
            Toast.makeText(this, "Cart Clicked", Toast.LENGTH_SHORT).show();
        });

        snacksCategory.setOnClickListener(v -> {
            // Open Snacks Category Activity
            Intent snacksIntent = new Intent(homepage.this, snackspage.class);
            startActivity(snacksIntent);
        });

        mainCourseCategory.setOnClickListener(v -> {
            // Open Main Course Category Activity
            Intent mainCourseIntent = new Intent(homepage.this, maincoursepage.class);
            startActivity(mainCourseIntent);
        });

        dessertsCategory.setOnClickListener(v -> {
            // Open Desserts Category Activity
            Intent dessertsIntent = new Intent(homepage.this, desertpage.class);
            startActivity(dessertsIntent);
        });
    }
}