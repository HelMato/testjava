package org.example;

public class Product {
    private final int productID;
    private final String name;
    private double price;

    public Product(int productID, String name, double price){
        this.productID=productID;
        this.name=name;
        this.price= price;
    }

    public int getProductID(){
        return productID;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price=price;
    }@Override
    public String toString(){
        return productID + " '"+name+
                " '"+price;
    }



}
