package bussineslogic;

import bussineslogic.model.Category;
import bussineslogic.model.Client;
import bussineslogic.model.Gender;
import bussineslogic.model.Product;

public class Factory {

    public Product createProduct(String[] productTable){
        
        Product product = null;
        product = new Product();
        product.setName(productTable[0]);
        product.setPrice(Double.parseDouble(productTable[1]));
        product.setCategory(Category.valueOf(productTable[2]));
        product.setGender(Gender.valueOf(productTable[3]));
        product.setSize(productTable[4]);
        product.setBrand(productTable[5]);
        
        return product;
    }

    public Client createClient(String[] clientTable){
        return new Client(clientTable[0], clientTable[1], clientTable[2], clientTable[3], clientTable[4], clientTable[5]);
    }
}
