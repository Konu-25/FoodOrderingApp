package com.example.zomatolite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;
public class cartpage extends AppCompatActivity {
    private CartManager cartManager;
    private TextView cartItemsTextView, totalPriceTextView;
    private EditText address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cartpage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        cartManager = CartManager.getInstance();

        cartItemsTextView = findViewById(R.id.cartItemsTextView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        Button button = findViewById(R.id.orderButton);

        displayCartItems();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to SecondActivity
                    Intent intent = new Intent(cartpage.this, finalpage.class);
                    startActivity(intent);
            }
        });
    }
    @SuppressLint("SetTextI18n")
    private void displayCartItems() {
        List<Map<String, Object>> cartItems = cartManager.getCartItems();
        StringBuilder cartDetails = new StringBuilder();
        int totalPrice = 0;

        for (Map<String, Object> item : cartItems) {
            String name = (String) item.get("name");
            int price = (int) item.get("price");
            int quantity = (int) item.get("quantity");

            cartDetails.append(name)
                    .append(" - ₹")
                    .append(price)
                    .append(" x ")
                    .append(quantity)
                    .append("\n");

            totalPrice += price * quantity;
        }

        cartItemsTextView.setText(cartDetails.toString());
        totalPriceTextView.setText("Total: ₹" + totalPrice);
    }
}

