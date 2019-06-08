package testdata;

import bussineslogic.dto.Category_dto;
import bussineslogic.dto.Gender_dto;
import bussineslogic.dto.Product_dto;
import bussineslogic.model.Category;
import bussineslogic.model.Client;
import bussineslogic.model.Gender;
import bussineslogic.model.Product;
import java.util.HashMap;
import java.util.Map;

public class Data {
    
    public String productString[][] = {
      {"Koszulka biała", "139.99", "T_SHIRTS", "MAN", "M", "Levis"},
        {"Spodnie czarne", "259.47", "JEANS", "MAN", "M", "Zara"},
        {"Skarpetki w renifery","23.99","SOCKS","UNISEX","42-45","Beneton"},
        {"Kurtka puchowa, niebieska","145.89","COATS","WOMAN","XL","Hilfiger Denim"},
        {"Bluza z kapturem","45,23","SWEATHSHIRTS","MAN","L","Niek"},
        {"Bluza z kapturem","45.23","SWEATS","MAN","L","Nike"},
        {"Koszulka biała", "987.2", "T_SHIRTS", "MAN", "M", "Levis"}
    };
    
    public String productStringMod[][] = {
        {},
        {"Spodnie czarne", "987.2", "JEANS", "MAN", "M", "Zara"},
        {"Kurtka puchowa, niebieska","145.89","COATS","WOMAN","XL","Hilfiger Denim"}
    };
    
    public Product productData[] = {
        new Product("Koszulka biała", 139.99, Category.T_SHIRTS, Gender.MAN, "M", "Levis"),
        new Product("Spodnie czarne", 259.47, Category.JEANS, Gender.MAN, "M", "Zara"),
        new Product("Skarpetki w renifery",23.99,Category.SOCKS,Gender.UNISEX,"42-45","Beneton"),
        new Product("Kurtka puchowa, niebieska",145.89,Category.COATS,Gender.WOMAN,"XL","Hilfiger Denim"),
        //new Product("Koszulka biała", 987.2, Category.T_SHIRTS, Gender.MAN, "M", "Levis")
    };
    
    public Product productData2[] = {
        new Product("Spodnie czarne", 259.47, Category.JEANS, Gender.MAN, "M", "Zara"),
        new Product("Spodnie czarne", 987.2, Category.JEANS, Gender.MAN, "M", "Zara"),
        new Product("Kurtka puchowa, niebieska",145.89,Category.COATS,Gender.WOMAN,"XL","Hilfiger Denim")
    };
    
    public Product_dto productDtoData[] = {
        new Product_dto("Koszulka biała", 139.99, Category_dto.T_SHIRTS, Gender_dto.MAN, "M", "Levis"),
        new Product_dto("Spodnie czarne", 259.47, Category_dto.JEANS, Gender_dto.MAN, "M", "Zara"),
        new Product_dto("Skarpetki w renifery",23.99, Category_dto.SOCKS, Gender_dto.UNISEX, "42-45","Beneton"),
        new Product_dto("Kurtka puchowa, niebieska",145.89, Category_dto.COATS, Gender_dto.WOMAN, "XL","Hilfiger Denim"),
        new Product_dto("Koszulka biała", 987.2, Category_dto.T_SHIRTS, Gender_dto.MAN, "M", "Levis")
    };
    
    public Product_dto productDtoData2[] = {
        new Product_dto("Spodnie czarne", 259.47, Category_dto.JEANS, Gender_dto.MAN, "M", "Zara"),
        new Product_dto("Spodnie czarne", 987.2, Category_dto.JEANS, Gender_dto.MAN, "M", "Zara"),
        new Product_dto("Kurtka puchowa, niebieska",145.89, Category_dto.COATS, Gender_dto.WOMAN, "XL","Hilfiger Denim")
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
    
    public String filters2[][] = {
        {"", "", "", "", "", "","Zara"},
        {"", "", "JEANS", "", "", "",""},
        {"", "", "", "", "", "XL",""}
    };
    
    public String productsModel[][] = {
        {"Product{name='Spodnie czarne', price=259.47, category=JEANS, gender=MAN, size='M', brand='Zara'}"},
        {"Product{name='Spodnie czarne', price=987.2, category=JEANS, gender=MAN, size='M', brand='Zara'}"},
        {"Product{name='Kurtka puchowa, niebieska', price=145.89, category=COATS, gender=WOMAN, size='XL', brand='Hilfiger Denim'}"}
    };
    
    Map<Product,Integer> map1 = new HashMap<>();
    Map<Product,Integer> map2 = new HashMap<>();
    Map<Product,Integer> map3 = new HashMap<>();
    Map<Product,Integer> map4 = new HashMap<>();
    
    public Map baskets[] = {
      creteMap(productData[0]), 
      creteMap(productData[0], productData[1]), 
      creteMap(productData[0], productData[1], productData[2]), 
      creteMap(productData[0], productData[1], productData[2], productData[3])
    };
    
    public Map filtredBaskets[] = {
      creteMap(productData[0]), 
      creteMap(productData[1]), 
      creteMap(productData[2]), 
      creteMap(productData[3])
    };
    
     public Map reovedBaskets[] = {
      creteMap(), 
      creteMap(productData[0]), 
      creteMap(productData[0], productData[1]), 
      creteMap(productData[0], productData[1], productData[2])
    };

    public Map<Product,Integer> creteMap(Product... products){
        Map<Product,Integer> map = new HashMap<>();
        for (Product p : products){
            map.put(p, 1);
        }
        return map;
    };
}
