package org.example;

import java.util.ArrayList;
import java.util.List;

class ShoppingCart {

    private List<String> shoppinCartItems = new ArrayList<>();

    public ShoppingCart() {
        shoppingCartBeginningState();
    }

    private void shoppingCartBeginningState(){
        shoppinCartItems.clear();
    }
    public List<String> getProducts(){
        return shoppinCartItems;
    }
    public void addItem(String itemName) {
        shoppinCartItems.add(itemName);
    }

    public void setProducts(List<String> productList) {
        shoppinCartItems=productList;
    }
}