package com.example.zomatolite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartManager {

    private static CartManager instance;
    private final List<Map<String, Object>> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public boolean addToCart(Map<String, Object> item) {
        for (Map<String, Object> cartItem : cartItems) {
            if (cartItem.get("name").equals(item.get("name"))) {
                int currentQuantity = (int) cartItem.getOrDefault("quantity", 0);
                cartItem.put("quantity", currentQuantity + 1);
                return true;
            }
        }
        return cartItems.add(item);
    }

    public List<Map<String, Object>> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public void clearCart() {
        cartItems.clear();
    }
}