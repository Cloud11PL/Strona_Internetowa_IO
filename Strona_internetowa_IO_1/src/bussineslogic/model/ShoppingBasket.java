package bussineslogic.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class ShoppingBasket implements Serializable{
private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Transient
    private Map<Product, Integer> productMap = new HashMap<>();
    private double totalPrice = 0;
    @Transient
    private Map<Product, Integer> filteredMap = new HashMap<>();

    public Map<Product, Integer> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<Product, Integer> productMap) {
        this.productMap = productMap;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addProductToShoppingBasket(Product product) {
        int amount=1;
        Integer amountObject = productMap.get(product);      
        if (amountObject !=null) {
            amount = amountObject;
                productMap.put(product,++amount);
        }else{
                productMap.put(product, 1);
        }
         totalPrice += product.getPrice();
        }
    

    public String removeProductFromShoppingBasket(Product product) {
        String message = "Brak takiego produktu w koszyku";
        Integer amountObject = productMap.get(product);
                    
            if (amountObject !=null) {
                int amount = amountObject;
                if (amount > 1) {
                    productMap.put(product, --amount);
                    message = product.getName() + " ilość: " + amount;
                } else {
                    productMap.remove(product);
                    message = "Usunięto: " + product.getName() + " z koszyka";
                }
                totalPrice-=product.getPrice();
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
        filteredMap=new HashMap<>();
        for (Product p : productMap.keySet()) {
            if (p.isValid(filters) != null) {
             filteredMap.put(p, productMap.get(p));
            }
        }

        return "ShoppingBasket{"
                + "productMap=" + filteredMap + "}";
    }

    public Map<Product, Integer> getFilteredMap() {
        return filteredMap;
    }
    
    
}
