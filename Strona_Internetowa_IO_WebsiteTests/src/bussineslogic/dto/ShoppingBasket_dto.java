/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussineslogic.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Juju
 */
public class ShoppingBasket_dto implements Serializable{
    private Map<Product_dto, Integer> productMap = new HashMap<>();
    private double totalPrice = 0;
    private Map<Product_dto, Integer> filteredMap = new HashMap<>();

    public ShoppingBasket_dto() {
    }
    
    //Jeszcze jedne konstruktor????

    public Map<Product_dto, Integer> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<Product_dto, Integer> productMap) {
        this.productMap = productMap;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<Product_dto, Integer> getFilteredMap() {
        return filteredMap;
    }

    public void setFilteredMap(Map<Product_dto, Integer> filteredMap) {
        this.filteredMap = filteredMap;
    }
    
    
}
