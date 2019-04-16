package bussineslogic.model;

import bussineslogic.model.Product;
import java.util.HashMap;
import java.util.Map;

public class ShoppingBasket {

    private Map<Product, Integer> productMap = new HashMap<>();
    private double totalPrice = 0;


    public Map<Product, Integer> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<Product, Integer> productMap) {
        this.productMap = productMap;
    }

    public double getTotalPrice() {
        for (Product p : productMap.keySet()){
            totalPrice += p.getPrice() * productMap.get(p);
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "ShoppingBasket{" +
                "productMap=" + productMap +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
