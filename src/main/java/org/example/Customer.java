package org.example;

public class Customer {
    private int customerID;
    private final String name;
    private double walletBalance;

    private ShoppingCart shoppingCart;

    public Customer(String name, int customerID) {
        this.name = name;
        this.customerID=customerID;
        this.walletBalance = 100;
        this.shoppingCart=new ShoppingCart();
    }
public int getCustomerID(){
        return customerID;
}
    public String getName() {
        return name;
    }

    public double getWalletBalance() {
        return walletBalance;
    }
    public ShoppingCart getShoppingCart(){
        return  shoppingCart;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

}