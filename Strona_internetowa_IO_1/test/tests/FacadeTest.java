package tests;

import bussineslogic.Facade;
import bussineslogic.dto.Client_dto;
import bussineslogic.dto.Product_dto;
import bussineslogic.model.Client;
import bussineslogic.model.Product;
import categories.Test_Control;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import testdata.Data;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

@Category(Test_Control.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class FacadeTest {

    static Data data;
    static Facade facade;

    @Parameterized.Parameter
    public int number1;

    @BeforeClass
    public static void setUpClass() {
        facade = new Facade();
        data = new Data();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data1 = new Object[][]{{0}, {1}, {2}};
        return Arrays.asList(data1);
    }

    @Test
    public void test1SearchProduct() {
        System.out.println("searchProduct");
        Product pr;

        if (number1 % 2 != 0) {
            facade.getProductList().add(data.productData[number1]);
            pr = facade.searchProduct(data.productData[number1]);
            assertNotNull(pr);
        } else {
            pr = facade.searchProduct(data.productData[number1]);
            assertNull(pr);
        }

    }

    @Test
    public void test2AddProduct() {
        System.out.println("addProduct");
        String productStr;

        if (number1 % 2 != 0) {
            productStr = facade.addProduct(data.productString[number1]);
            assertNull(productStr);
        } else {
            productStr = facade.addProduct(data.productString[number1 + 1]);
            assertTrue(productStr.equals(data.productData[number1 + 1].toString()));
        }
    }

    @Test
    public void test3modifyProductPrice() {
        System.out.println("modifyProductPrice");
        double price = 987.2;
        Product product = facade.searchProduct(data.productData[number1]);

        if (number1 % 2 != 0) {
            facade.modifyProductPrice(price, data.productString[number1]);
            assertEquals(price, product.getPrice(), 0.01);
        } else {
            assertNull(product);
        }
    }

    @Test
    public void test4RemoveProdut() {
        System.out.println("removeProduct");
        String message;

        if (number1 % 2 != 0) {
            message = facade.removeProduct(data.productData[number1]);
            assertTrue(message.equals("Produkt usunieto"));

            message = facade.removeProduct(data.productData[number1]);
            assertTrue(message.equals("Brak takiego produktu"));
        }
    }

    @Test
    public void test5SearchClient() {
        System.out.println("searchClient");
        Client client;
        if (number1 % 2 != 0) {
            facade.getClientList().add(data.clientData[number1]);
            client = facade.searchClient(data.clientData[number1]);
            assertNotNull(client);
        } else {
            client = facade.searchClient(data.clientData[number1 + 1]);
            assertNull(client);
        }
    }

    @Test
    public void test6AddClient() {
        System.out.println("addClient");
        String clientStr;
        if (number1 % 2 != 0) {
            clientStr = facade.addClient(data.clientString[number1]);
            assertNull(clientStr);
        } else {
            clientStr = facade.addClient(data.clientString[number1]);
            assertTrue(clientStr.equals(data.clientData[number1].toString()));
        }
    }

    @Test
    public void test7AddProductToBasket() {
        System.out.println("addProductToBasket");
        String message,m;

        if (number1 % 2 != 0) {
            message = facade.addProductToBasket(data.clientString[number1], data.productStringMod[number1]);
            assertTrue(message.equals("Dodano: " + data.productData[number1].getName()));
            
            m = facade.addProductToBasket(data.clientString[number1 - 1], data.productStringMod[number1]);
            System.out.println(m);
        } else {
            message = facade.addProductToBasket(data.clientString[number1], data.productString[number1]);
            assertTrue(message.equals("Nie istnieje taki produkt"));

            message = facade.addProductToBasket(data.clientString[number1 + 1], data.productString[number1]);
            assertTrue(message.equals("Nie istnieje taki klient"));

        }
    }

    @Test
    public void test8BrowseBasket() {
        System.out.println("browseBasket");
        String message;

        if (number1 % 2 != 0) {
            message = facade.browseBasket(data.clientString[number1], data.filters[number1]);
            assertTrue(message.equals("ShoppingBasket{"
                    + "productMap=" + data.clientData[number1].getShoppingBasket().getFilteredMap() + "}"));
        } else {
            message = facade.browseBasket(data.clientString[number1 + 1], data.productString[number1]);
            assertNull(message);
        }
    }

    @Test
    public void test9RemoveFromBasket() {
        System.out.println("removeFromBasket");
        String message;
        if (number1 % 2 != 0) {
            message = facade.removeFromBasket(data.clientString[number1], data.productStringMod[number1]);
            assertTrue(message.equals("Usunięto: " + data.productData[number1].getName() + " z koszyka"));
        } else {
            message = facade.removeFromBasket(data.clientString[number1 + 1], data.productString[number1]);
            assertTrue(message.equals("Nie istnieje taki klient"));
        }
    }

    @Test
    public void test_10PublicProductsModel() {
        System.out.println("publicProductsModel");
        ArrayList<String> productsStr = facade.publicProductsModel();
        System.out.println(facade.publicProductsModel());
        assertEquals(Arrays.asList(data.productsModel[number1]), productsStr);
    }

    @Test
    public void test_11RemoveProduct_dto() {
        System.out.println("removeProduct_dto");
        if (number1 % 2 != 0) {
            facade.removeProduct_dto(data.productDtoData2[number1]);
            assertFalse(facade.getProductList().contains(data.productData2[number1]));
        } else {
            assertTrue(facade.getProductList().contains(data.productData2[number1]));
        }
    }

    @Test
    public void test_12GetBasket() {
        System.out.println("getBasket");
        ArrayList<Product_dto> products = new ArrayList<>();
        products.add(data.productDtoData2[number1]);
        if (number1 % 2 != 0) {
            ArrayList<Product_dto> basket = facade.getBasket(data.clientString[number1 - 1]);            
            assertEquals(products.toString(), basket.toString());
        } else {
            ArrayList<Product_dto> basket = facade.getBasket(data.clientString[number1]);
            assertEquals(new ArrayList<>().toString(), basket.toString());
        }
    }
    
    @Test
    public void test_13GetProducts(){
        System.out.println("getProducts");
        ArrayList<Product_dto> productsF = facade.getProducts();
        ArrayList<Product_dto> products = new ArrayList<>();
        
        if (number1 % 2 != 0) {
            assertEquals(products.toString(), productsF.toString());
        }else{
            products.add(data.productDtoData2[number1]);
            assertEquals(products.toString(), productsF.toString());
        }  
    }

    @Test
    public void test_14GetClients(){
        System.out.println("getClients");
        ArrayList<Client_dto> clients = facade.getClients();
        
        assertEquals(data.clientData[number1].toString(), clients.get(number1).toString());
    }
        
    @Test
    public void test_15GetFilteredBasket(){
        System.out.println("getFilteredBasket");
        ArrayList<Product_dto> products;
        if (number1 % 2 != 0) {
            products = facade.getFilteredBasket(data.clientString[number1 - 1], data.filters2[number1]);
            assertNotEquals(products.size(),0);
        }else{
            products = facade.getFilteredBasket(data.clientString[number1], data.filters[number1]);
            assertEquals(products.size(), 0);
        }  
    }
     
}
