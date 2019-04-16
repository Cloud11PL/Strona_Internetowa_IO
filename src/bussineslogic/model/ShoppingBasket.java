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
    
    public void addProductToShoppingBasket(Product product){
        for (Product p : productMap.keySet()) {
            if (p.equals(product)) {
                productMap.put(p, productMap.get(p) + 1);
            } else {
                productMap.put(product, 1);
            }
        }
    }

    public String removeProductFromShoppingBasket(Product product){
        String message = "";
        for (Product p : productMap.keySet()) {
            if (p.equals(product)) {
                if (productMap.get(p) > 1) {
                    productMap.put(p, productMap.get(p) - 1);
                    message = p.getName() + " ilość " + productMap.get(p);
                } else {
                    productMap.remove(p);
                    message = "Usunięto: " + p.getName() + " z koszyka";
                }
            }
        }
        return message;
    }
    
    @Override
    public String toString() {
        return "ShoppingBasket{" +
                "productMap=" + productMap +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
