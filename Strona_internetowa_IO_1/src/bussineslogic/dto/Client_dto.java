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
public class Client_dto implements Serializable{
    protected ShoppingBasket_dto shoppingBasket = new ShoppingBasket_dto();
    protected String id;
    protected String firstName;
    protected String lastName;
    protected String adress;
    protected String phone;
    protected  String email;

    public Client_dto() {
    }

    //Jeszcze jedne konstruktor????
    
    public ShoppingBasket_dto getShoppingBasket() {
        return shoppingBasket;
    }

    public void setShoppingBasket(ShoppingBasket_dto shoppingBasket) {
        this.shoppingBasket = shoppingBasket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
