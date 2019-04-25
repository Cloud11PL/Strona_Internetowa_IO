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
        for (Product p : productMap.keySet()) {
            totalPrice += p.getPrice() * productMap.get(p);
        }
        return totalPrice;
    }

    public void addProductToShoppingBasket(Product product) {
        if (!productMap.keySet().isEmpty()) {
            for (Product p : productMap.keySet()) {
                if (p.equals(product)) {
                    productMap.put(p, productMap.get(p) + 1);
                } else {
                    productMap.put(product, 1);
                }
            }
        }else{
            productMap.put(product, 1);
        }
    }

    public String removeProductFromShoppingBasket(Product product) {
        String message = "Brak takiego produktu w koszyku";
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
        return "ShoppingBasket{"
                + "Product Map=" + productMap
                + ", Total Price=" + totalPrice
                + '}';
    }

    public String browseBasket(String[] filters) {

        Map<Product, Integer> filteredBasket = new HashMap<>();
        for (Product p : productMap.keySet()) {
            if (p.isValid(filters) != null) {
                System.out.println(p.getName());
                filteredBasket.put(p, productMap.get(p));
            }
        }

        return "ShoppingBasket{"
                + "productMap=" + filteredBasket + "}";
    }
}
