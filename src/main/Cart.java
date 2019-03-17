package main;

import java.util.ArrayList;

public class Cart {

    private ArrayList<CartItem> cart = new ArrayList<>(0);

    public Cart() {}

    public ArrayList<CartItem> getCart() {
        return cart;
    }

    public void setCart(ArrayList<CartItem> cart) {
        this.cart = cart;
    }

    public void addItem(CartItem newItem){
        this.cart.add(newItem);
    }


    public void printCart() {
        System.out.println("Items in cart: ");
        if (this.cart.size() > 0) {
            for (CartItem item : cart) {
                System.out.println(item);
            }
        } else {
            System.out.println("There is nothing in the cart.");
        }
    }
}
