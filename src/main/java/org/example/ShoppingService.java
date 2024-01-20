package org.example;

import java.util.List;

public class ShoppingService {

    public static String finalizePurchase(Customer customer) {
        List<String> productsInCart = customer.getShoppingCart().getProducts();
        double totalCost = calculateTotalAmount(productsInCart);

        if (totalCost <= customer.getWalletBalance()) {
            customer.setWalletBalance(customer.getWalletBalance() - totalCost);
            return "Shopping successful. Remaining balance: " + customer.getWalletBalance();
        } else {
            return "Not enough money. Shopping failed.";
        }
    }

    private static double calculateTotalAmount(List<String> products) {
        double totalCost = 0.0;

        for (String productName : products) {
            Product product = findProductInStorage(productName);
            if (product != null) {
                totalCost += product.getPrice();
            }
        }

        return totalCost;
    }

    private static Product findProductInStorage(String productName) {
        return org.example.Warehouse.getInstance().getOrderPositionsList().stream()
                .filter(orderPosition -> orderPosition.getProduct().getName().equalsIgnoreCase(productName))
                .findFirst()
                .map(OrderPosition::getProduct)
                .orElse(null);
    }
}
