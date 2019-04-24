package bussineslogic;

import bussineslogic.model.Category;
import bussineslogic.model.Client;
import bussineslogic.model.Gender;
import bussineslogic.model.Product;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Facade {

    private ArrayList<Product> productList = new ArrayList<>();
    private ArrayList<Client> clientList = new ArrayList<>();

    public static void main(String[] args) {
        Facade facade = new Facade();
        String[] productTable = new String[]{"Koszulka biała", "139.99", "T_SHIRTS", "MAN", "M", "Levis"};
        String[] productTable2 = new String[]{"Spodnie czarne", "259.47", "JEANS", "MAN", "M", "HM"};
        String[] clientTable = new String[]{"1", "Jan", "Kowalski", "Wroclaw1", "732456987", "jan@wp.pl"};

        facade.addProduct(productTable);
        facade.addProduct(productTable2);

        String client = facade.addClient(clientTable);
        System.out.println(client);
        client = facade.addClient(clientTable);
        System.out.println(client);
        System.out.println(Arrays.toString(facade.clientList.toArray()));

        System.out.println(facade.addProductToBasket(clientTable, productTable2));
        System.out.println(facade.removeFromBasket(clientTable, productTable2));
        System.out.println(facade.addProductToBasket(clientTable, productTable));
        
        System.out.println(facade.clientList.get(0));

        
        String[] filters=new String[]{"120", "110", "", "", "", "HM"};
        String basket=facade.browseBasket(clientTable, filters);
        System.out.println(basket);
        
    }

    public ArrayList<String> publicProductsModel() {
        ArrayList<String> products = new ArrayList<>();
        Iterator<Product> help = productList.iterator();
        while (help.hasNext()) {
            Product next = help.next();
            products.add(next.toString());
        }
        return products;
    }

    public void removeProduct(Product product) {
        Product productExist;

        if ((productExist = searchProduct(product)) != null) {
            productList.remove(productExist);
        }
    }

    public void modifyProductPrice(double price, Product product) {
        Product productExist;

        if ((productExist = searchProduct(product)) != null) {
            productExist.setPrice(price);
        }
    }

    public Product addProduct(String[] productTable) {
        Product product1, productExist;
        Factory factory = new Factory();
        product1 = factory.createProduct(productTable);

        if ((productExist = searchProduct(product1)) == null) {
            productList.add(product1);
            return product1;
        }

        return null;
    }

    public String addClient(String[] clientTable) {
        Factory factory = new Factory();
        Client client = factory.createClient(clientTable);

        if (searchClient(client) == null) {
            clientList.add(client);
            return client.toString();
        }
        return null;
    }

    public String addProductToBasket(String[] clientTable, String[] productTable) {
        Factory factory = new Factory();
        Client client = factory.createClient(clientTable);
        if ((client = searchClient(client)) != null) {
            Product product = factory.createProduct(productTable);
            if ((product = (searchProduct(product))) != null) {
                client.addToShoppingBasket(product);
                return "Dodano: " + product.getName();
            }
            return "Nie istnieje taki produkt";
        }
        return "Nie istnieje taki klient";
    }

    public String removeFromBasket(String[] clientTable, String[] productTable) {
        Factory factory = new Factory();
        Client client = factory.createClient(clientTable);
        if ((client = searchClient(client)) != null) {
            Product product = factory.createProduct(productTable);
            if ((product = (searchProduct(product))) != null) {
                return client.removeFromShoppingBasket(product);
            }
            return "Nie istnieje taki klient";
        }
        return "Nie usunięto produktu z koszyka klienta";
    }

    public Client searchClient(Client client) {
        int idx;

        if ((idx = clientList.indexOf(client)) != -1) {
            client = clientList.get(idx);
            return client;
        }
        return null;
    }

    public Product searchProduct(Product product) {
        int idx;

        if ((idx = productList.indexOf(product)) != -1) {
            product = productList.get(idx);
            return product;
        }
        return null;
    }

    public String browseBasket(String[] clientTable, String[] filters) {
        Factory factory = new Factory();
        Client client, clientExist;
        client = factory.createClient(clientTable);

        if ((clientExist = searchClient(client)) != null) {
            return clientExist.browseShoppingBasket(filters);
        }
        return null;

    }

}
