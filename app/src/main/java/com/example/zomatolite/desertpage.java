package com.example.zomatolite;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.HashMap;

public class desertpage extends AppCompatActivity {
    private CartManager cartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_desertpage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cartManager = CartManager.getInstance();

        // Cart Icon
        ImageView cartIcon = findViewById(R.id.cartIcon);
        cartIcon.setOnClickListener(v -> {
            Intent intent = new Intent(desertpage.this, cartpage.class);
            startActivity(intent);
        });

        Button addDish1Button = findViewById(R.id.addDish1Button);
        Button addDish2Button = findViewById(R.id.addDish2Button);
        Button addDish3Button = findViewById(R.id.addDish3Button);
        Button addDish4Button = findViewById(R.id.addDish4Button);

        addDish1Button.setOnClickListener(v -> addToCart("Chocolate lava", 150));
        addDish2Button.setOnClickListener(v -> addToCart("Vanilla", 120));
        addDish3Button.setOnClickListener(v -> addToCart("cup cakes", 150));
        addDish4Button.setOnClickListener(v -> addToCart("Donut", 120));



    }

    private void addToCart(String itemName, int price) {
        HashMap<String, Object> item = new HashMap<>();
        item.put("name", itemName);
        item.put("price", price);
        item.put("quantity", 1);

        if (cartManager.addToCart(item)) {
            Toast.makeText(this, itemName + " added to cart!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add " + itemName + " to cart.", Toast.LENGTH_SHORT).show();
        }
    }
}