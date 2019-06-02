package bussineslogic;

import bussineslogic.dto.Category_dto;
import bussineslogic.dto.Client_dto;
import bussineslogic.dto.Gender_dto;
import bussineslogic.dto.Product_dto;
import bussineslogic.model.Category;
import bussineslogic.model.Client;
import bussineslogic.model.Gender;
import bussineslogic.model.Product;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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

        String product = facade.addProduct(productTable);
        System.out.println(product);

        System.out.println(facade.addProductToBasket(clientTable, productTable2));
        System.out.println(facade.removeFromBasket(clientTable, productTable2));
        System.out.println(facade.addProductToBasket(clientTable, productTable));

        System.out.println(facade.clientList.get(0));

        String[] filters = new String[]{"", "", "T_SHIRTS", "", "", "", ""};
        String basket = facade.browseBasket(clientTable, filters);
        System.out.println(basket);
        System.out.println(facade.getFilteredBasket(clientTable, filters));

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

    public void removeProduct_dto(Product_dto product_dto) {
        Category cat = Category.values()[product_dto.getCategory().ordinal()];
        Gender gen = Gender.values()[product_dto.getGender().ordinal()];
        Product product = new Product(product_dto.getName(), product_dto.getPrice(), cat, gen, product_dto.getSize(), product_dto.getBrand());
        removeProduct(product);

    }

    public String removeProduct(Product product) {
        if (productList.remove(product)) {
            return "Produkt usunieto";
        }
        return "Brak takiego produktu";
    }

    public void modifyProductPrice(double price, String[] productTable) {
        Product productExist, product;
        Factory factory = new Factory();
        product = factory.createProduct(productTable);
        if ((productExist = searchProduct(product)) != null) {
            productExist.setPrice(price);
        }
    }

    public ArrayList<Product_dto> getBasket(String[] clientTable) {
        ArrayList<Product_dto> products = new ArrayList<>();
        Factory factory = new Factory();
        Client client, clientExist;
        client = factory.createClient(clientTable);

        if ((clientExist = searchClient(client)) != null) {
            for (Product p : clientExist.getShoppingBasket().getProductMap().keySet()) {
                Category_dto cat = Category_dto.values()[p.getCategory().ordinal()];
                Gender_dto gen = Gender_dto.values()[p.getGender().ordinal()];
                Product_dto product = new Product_dto(p.getName(), p.getPrice(), cat, gen, p.getSize(), p.getBrand());
                products.add(product);
            }
            return products;
        }
        return null;
    }

    public String addProduct(String[] productTable) {

        Product product1, productExist;
        Factory factory = new Factory();
        product1 = factory.createProduct(productTable);

        if ((productExist = searchProduct(product1)) == null) {
            productList.add(product1);
            return product1.toString();
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
            return client.removeFromShoppingBasket(product);
        }
        return "Nie istnieje taki klient";

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

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public ArrayList<Product_dto> getProducts() {
        ArrayList<Product_dto> products = new ArrayList<>();
        for (Product p : productList) {
            Category_dto cat = Category_dto.values()[p.getCategory().ordinal()];
            Gender_dto gen = Gender_dto.values()[p.getGender().ordinal()];
            Product_dto product = new Product_dto(p.getName(), p.getPrice(), cat, gen, p.getSize(), p.getBrand());
            products.add(product);
        }
        return products;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public ArrayList<Client_dto> getClients() {
        ArrayList<Client_dto> clients = new ArrayList<>();
        for (Client c : clientList) {
            Client_dto product = new Client_dto(c.getShoppingBasket(), c.getId(), c.getFirstName(), c.getLastName(), c.getAdress(), c.getPhone(), c.getEmail());
            clients.add(product);
        }
        return clients;
    }

    public void setClientList(ArrayList<Client> clientList) {
        this.clientList = clientList;
    }

    public ArrayList<Product_dto> getFilteredBasket(String[] clientTable, String[] filters) {
        ArrayList<Product_dto> products = new ArrayList<>();
        Factory factory = new Factory();
        Client client, clientExist;
        client = factory.createClient(clientTable);
        String s = browseBasket(clientTable, filters);

        if ((clientExist = searchClient(client)) != null) {
            for (Product p : clientExist.getShoppingBasket().getFilteredMap().keySet()) {
                Category_dto cat = Category_dto.values()[p.getCategory().ordinal()];
                Gender_dto gen = Gender_dto.values()[p.getGender().ordinal()];
                Product_dto product = new Product_dto(p.getName(), p.getPrice(), cat, gen, p.getSize(), p.getBrand());
                products.add(product);
            }
            return products;
        }
        return null;
    }

}
