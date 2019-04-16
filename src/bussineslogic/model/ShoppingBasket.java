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
                "Product Map=" + productMap +
                ", Total Price=" + totalPrice +
                '}';
    }
    
    public String browseBasket(String[] filters){
        
        Map<Product, Integer>  filteredBasket=productMap;
        for(Product p : productMap.keySet()){
            if(p.isValid(filters) != null){
                filteredBasket.put(p, productMap.get(p));
            }
        }
        
        return "ShoppingBasket{" +
                "productMap=" + filteredBasket +"}";
    }
}
