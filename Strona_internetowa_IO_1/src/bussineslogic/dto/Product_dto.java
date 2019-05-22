/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussineslogic.dto;

import java.io.Serializable;

/**
 *
 * @author Juju
 */
public class Product_dto implements Serializable{
    
    protected String name;
    protected double price;
    protected Category_dto category;
    protected Gender_dto gender;
    protected String size;
    protected String brand;

    public Product_dto() {
        
    }

    public Product_dto(String name, double price, Category_dto category, Gender_dto gender, String size, String brand) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.gender = gender;
        this.size = size;
        this.brand = brand;
    }
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category_dto getCategory() {
        return category;
    }

    public void setCategory(Category_dto category) {
        this.category = category;
    }

    public Gender_dto getGender() {
        return gender;
    }

    public void setGender(Gender_dto gender) {
        this.gender = gender;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    @Override
    public String toString() {
        return "Product{"
                + "name='" + name + '\''
                + ", price=" + price
                + ", category=" + category
                + ", gender=" + gender
                + ", size='" + size + '\''
                + ", brand='" + brand + '\''
                + '}';
    }
}
