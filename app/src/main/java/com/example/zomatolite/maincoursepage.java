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


public class maincoursepage extends AppCompatActivity {
    private CartManager cartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_maincoursepage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cartManager = CartManager.getInstance();

        // Cart Icon
        ImageView cartIcon = findViewById(R.id.cartIcon);
        cartIcon.setOnClickListener(v -> {
            Intent intent = new Intent(maincoursepage.this, cartpage.class);
            startActivity(intent);
        });

        Button addDish1Button = findViewById(R.id.addDish1Button);
        Button addDish2Button = findViewById(R.id.addDish2Button);
        Button addDish3Button = findViewById(R.id.addDish3Button);
        Button addDish4Button = findViewById(R.id.addDish4Button);


        addDish1Button.setOnClickListener(v -> addToCart("Chicken Biryaani", 150));
        addDish2Button.setOnClickListener(v -> addToCart("Veg Biryaani", 120));
        addDish2Button.setOnClickListener(v -> addToCart("Chicken Burger", 80));
        addDish2Button.setOnClickListener(v -> addToCart("Garlic Bread", 50));

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