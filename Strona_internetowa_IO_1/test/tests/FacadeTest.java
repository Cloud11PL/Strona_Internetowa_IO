/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import bussineslogic.Facade;
import bussineslogic.dto.Client_dto;
import bussineslogic.dto.Product_dto;
import bussineslogic.model.Client;
import bussineslogic.model.Product;
import categories.Test_Control;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import testdata.Data;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@Category(Test_Control.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FacadeTest {
    
    static Data data;
    static Facade facade;
    
    @BeforeClass
    public static void setUpClass(){
        facade = new Facade();
        data = new Data();
    }
    
    @Test
    public void test1SearchProduct(){
        System.out.println("searchProduct");
        Product pr;
        
        facade.getProductList().add(data.productData[0]);
        pr = facade.searchProduct(data.productData[0]);
        assertNotNull(pr);
        
        pr = facade.searchProduct(data.productData[1]);
        assertNull(pr);
    }
    
    @Test
    public void test2AddProduct(){
        System.out.println("addProduct");
        String productStr;
        productStr = facade.addProduct(data.productString[0]);
        assertNull(productStr);
        
        productStr = facade.addProduct(data.productString[1]);
        assertTrue(productStr.equals(data.productData[1].toString()));
        
        productStr = facade.addProduct(data.productString[2]);
        assertTrue(productStr.equals(data.productData[2].toString()));
        
    }
    
    @Test
    public void test3modifyProductPrice(){
        System.out.println("modifyProductPrice");
        
        Product product = facade.searchProduct(data.productData[0]);
        facade.modifyProductPrice(987.2, data.productString[0]);
        assertEquals(987.2, product.getPrice(),0.01);
        
    }
    
    @Test
    public void test4RemoveProdut(){
        System.out.println("removeProduct");
        String message;
        
        message = facade.removeProduct(data.productData[1]);
        assertTrue(message.equals("Produkt usunieto"));
        
        message = facade.removeProduct(data.productData[1]);
        assertTrue(message.equals("Brak takiego produktu"));
    }
    
    @Test
    public void test5SearchClient(){
        System.out.println("searchClient");
        Client client;
        
        facade.getClientList().add(data.clientData[0]);
        client = facade.searchClient(data.clientData[0]);
        assertNotNull(client);
        
        client = facade.searchClient(data.clientData[1]);
        assertNull(client);
    }
    
    @Test
    public void test6AddClient(){
        System.out.println("addClient");
        String clientStr;
        clientStr = facade.addClient(data.clientString[0]);
        assertNull(clientStr);
        
        clientStr = facade.addClient(data.clientString[1]);
        assertTrue(clientStr.equals(data.clientData[1].toString()));
    }

    @Test
    public void test7AddProductToBasket(){
        System.out.println("addProductToBasket");
        String message;
        
        message = facade.addProductToBasket(data.clientString[0], data.productString[2]);
        assertTrue(message.equals("Dodano: " + data.productData[2].getName()));
        
        message = facade.addProductToBasket(data.clientString[0], data.productString[6]);
        assertTrue(message.equals("Dodano: " + data.productData[4].getName()));
        
        message = facade.addProductToBasket(data.clientString[0], data.productString[0]);
        assertTrue(message.equals("Nie istnieje taki produkt"));
        
        message = facade.addProductToBasket(data.clientString[3], data.productString[2]);
        assertTrue(message.equals("Nie istnieje taki klient"));
    }
    
    @Test
    public void test8BrowseBasket(){
        System.out.println("browseBasket");
        String message;
        
        message = facade.browseBasket(data.clientString[0], data.filters[2]);
        assertTrue(message.equals("ShoppingBasket{"
                + "productMap=" + data.clientData[0].getShoppingBasket().getFilteredMap() + "}"));
        
        message = facade.browseBasket(data.clientString[3], data.productString[4]);
        assertNull(message);
    }
    
    @Test
    public void test9RemoveFromBasket(){
        System.out.println("removeFromBasket");
        String message;
        
        message = facade.removeFromBasket(data.clientString[0], data.productString[2]);
        assertTrue(message.equals("Usunięto: "+ data.productData[2].getName() + " z koszyka"));
        
        message = facade.removeFromBasket(data.clientString[3], data.productString[2]);
        assertTrue(message.equals("Nie istnieje taki klient"));
    }
    
    @Test
    public void test_10PublicProductsModel(){
        System.out.println("publicProductsModel");
        ArrayList<String> productsStr = facade.publicProductsModel();
        
        assertEquals(Arrays.asList(data.productsModel),productsStr);
    }
    
    @Test
    public void test_11RemoveProduct_dto(){
        System.out.println("removeProduct_dto");
        facade.removeProduct_dto(data.productDtoData[2]);
        assertFalse(facade.getProductList().contains(data.productData[2]));
    }
    
    @Test
    public void test_12GetBasket(){
        System.out.println("getBasket");
        ArrayList<Product_dto> products = new ArrayList<>();
        products.add(data.productDtoData[4]);
        
        ArrayList<Product_dto> basket = facade.getBasket(data.clientString[0]);
        
        assertEquals(products.toString(), basket.toString());
    }
    
    @Test
    public void test_13GetProducts(){
        System.out.println("getProducts");
        ArrayList<Product_dto> productsF = facade.getProducts();
        
        ArrayList<Product_dto> products = new ArrayList<>();
        products.add(data.productDtoData[4]);
        assertEquals(products.toString(), productsF.toString());
    }
    
    @Test
    public void test_14GetClients(){
        System.out.println("getClients");
        ArrayList<Client_dto> clients = facade.getClients();
        
        assertEquals(data.clientData[0].toString(), clients.get(0).toString());
    }
    
    @Test
    public void test_15GetFilteredBasket(){
        System.out.println("getFilteredBasket");
        ArrayList<Product_dto> products = facade.getFilteredBasket(data.clientString[0], data.filters[0]);
        assertNotEquals(products.size(),0);
        
        products = facade.getFilteredBasket(data.clientString[1], data.filters[0]);
        assertEquals(products.size(), 0);
    }
    
}
