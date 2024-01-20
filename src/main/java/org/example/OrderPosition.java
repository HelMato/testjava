package org.example;

import org.example.Product;

public class OrderPosition {
    private final int orderPositionID;
    private Product product;
    private int productAmount;

    public OrderPosition(int orderPositionID, Product product, int productAmount){
        this.product=product;
        this.productAmount=productAmount;
        this.orderPositionID=orderPositionID;
    }
    public Product getProduct(){
        return product;
    }
    public int getProductAmount(){
        return productAmount;
    }
    public int getOrderPositionID(){
        return orderPositionID;
    }
    public void setProduct(Product product){
        this.product=product;
    }
    public void setProductAmount(int productAmount){
        this.productAmount=productAmount;
    }
}
