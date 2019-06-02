/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussineslogic.dto;

import bussineslogic.model.Product;
import bussineslogic.model.ShoppingBasket;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Juju
 */
public class Client_dto implements Serializable{
    protected ShoppingBasket shoppingBasket;
    protected String id;
    protected String firstName;
    protected String lastName;
    protected String adress;
    protected String phone;
    protected  String email;

    public Client_dto() {
    }

    public Client_dto(ShoppingBasket shoppingBasket, String id, String firstName, String lastName, String adress, String phone, String email) {
        this.shoppingBasket = shoppingBasket;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.phone = phone;
        this.email = email;
    }

    public ShoppingBasket getShoppingBasket() {
        return shoppingBasket;
    }

    public void setShoppingBasket(ShoppingBasket shoppingBasket) {
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
    
    @Override
    public String toString() {
        return firstName + " " + lastName + " " + email;
    }
}
