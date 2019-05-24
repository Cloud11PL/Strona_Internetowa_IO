/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdata;

import bussineslogic.model.Category;
import bussineslogic.model.Client;
import bussineslogic.model.Gender;
import bussineslogic.model.Product;

/**
 *
 * @author Piotr Janus
 */
public class Data {
    
    public String productString[][] = {
      {"Koszulka biała", "139.99", "T_SHIRTS", "MAN", "M", "Levis"},
        {"Spodnie czarne", "259.47", "JEANS", "MAN", "M", "Zara"},
        {"Skarpetki w renifery","23.99","SOCKS","UNISEX","42-45","Beneton"},
        {"Kurtka puchowa, niebieska","145.89","COATS","WOMAN","XL","Hilfiger Denim"},
        {"Bluza z kapturem","45,23","SWEATHSHIRTS","MAN","L","Niek"},
        {"Bluza z kapturem","45.23","SWEATS","MAN","L","Nike"}
    };
    
    public Product productData[] = {
        new Product("Koszulka biała", 139.99, Category.T_SHIRTS, Gender.MAN, "M", "Levis"),
        new Product("Spodnie czarne", 259.47, Category.JEANS, Gender.MAN, "M", "Zara"),
        new Product("Skarpetki w renifery",23.99,Category.SOCKS,Gender.UNISEX,"42-45","Beneton"),
        new Product("Kurtka puchowa, niebieska",145.89,Category.COATS,Gender.WOMAN,"XL","Hilfiger Denim")

    };
    
    public String clientString[][] = {
        {"1", "Jan", "Kowalski", "Wroclaw1", "732456987", "jan@wp.pl"},
        {"2", "Adam", "Nowak", "Poznań, Krzywa 12", "605123456", "nowak.a@gmail.com"},
        {"3", "Joanna", "Kowalska", "Szczecin, Piękna 67", "707504341", "j.k@gmail.com"},
        {"4", "Maria", "Bednarz", "Gdynia, Długa 11", "223456789", "mb@wp.pl"},
    };
    
    public Client clientData[] = {
        new Client("1", "Jan", "Kowalski", "Wroclaw1", "732456987", "jan@wp.pl"),
        new Client("2", "Adam", "Nowak", "Poznań, Krzywa 12", "605123456", "nowak.a@gmail.com"),
        new Client("3", "Joanna", "Kowalska", "Szczecin, Piękna 67", "707504341", "j.k@gmail.com"),
        new Client("4", "Maria", "Bednarz", "Gdynia, Długa 11", "223456789", "mb@wp.pl")
    };
    
    public String filters[][] = {
        {"", "", "T_SHIRTS", "", "", "",""},
        {"", "", "", "", "", "Zara",""},
        {"", "", "", "", "", "","Skarpetki w renifery"},
        {"", "", "", "", "XL", "",""},
    };
    
}
