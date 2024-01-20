package org.example;

import org.example.OrderPosition;
import org.example.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Warehouse {
    private static Warehouse warehouse;
    private final List<OrderPosition> warehouseOrderPositions = new ArrayList<>();

    private Warehouse() {
        warehouseBeginningState();
    }

    public static Warehouse getInstance() {
        if (warehouse == null) {
            warehouse = new Warehouse();
        }
        return warehouse;
    }

    private void warehouseBeginningState() {
        addOrderPositionToWarehouse(new OrderPosition(1, new Product(1, "milk", 23.00), 4));
        addOrderPositionToWarehouse(new OrderPosition(2, new Product(2, "beer", 23.00), 5));
    }

    public List<OrderPosition> getOrderPositionsList() {
        return new ArrayList<>(warehouseOrderPositions);
    }

    public Optional<OrderPosition> getOrderPosition(int orderPositionID) {
        return warehouseOrderPositions.stream()
                .filter(orderPosition -> orderPosition.getOrderPositionID() == orderPositionID)
                .findFirst();
    }

    public void addOrderPositionToWarehouse(OrderPosition orderPosition) {
        warehouseOrderPositions.add(orderPosition);
    }

    public void removeOrderPositionFromWarehouse(int orderPositionID) {
        warehouseOrderPositions.removeIf(orderPosition -> orderPosition != null && orderPosition.getOrderPositionID() == orderPositionID);
    }

    public void setOrderPositionProduct(int orderPositionID, Product product) {
        getOrderPosition(orderPositionID).ifPresent(orderPosition -> orderPosition.setProduct(product));
    }

    public void setOrderPositionProductAmount(int orderPositionID, int productAmount) {
        getOrderPosition(orderPositionID).ifPresent(orderPosition -> orderPosition.setProductAmount(productAmount));
    }

    public boolean checkOrderPositionAvailability(OrderPosition orderPosition) {
        Optional<OrderPosition> searchedOrderPosition = getOrderPosition(orderPosition.getOrderPositionID());
        return searchedOrderPosition.isPresent() && searchedOrderPosition.get().getProductAmount() >= orderPosition.getProductAmount();
    }
}
