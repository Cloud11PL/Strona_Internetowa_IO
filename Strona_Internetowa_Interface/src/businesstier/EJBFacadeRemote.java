/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesstier;

import bussineslogic.dto.Client_dto;
import bussineslogic.dto.Product_dto;
import bussineslogic.model.Client;
import bussineslogic.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Juju
 */
@Remote
public interface EJBFacadeRemote {
    public String addProduct(String[] data);
    public void addProductList();
    public String addClient(String[] data);
    public void removeProduct_dto(Product_dto product_dto);
    public ArrayList<Client_dto> getClients();
    public void addClientList();
    public ArrayList<Product_dto> getProducts();
    public void modifyProductPrice(double price, String[] productTable) ;
    public String addProductToBasket(String[] clientTable, String[] productTable);
    public ArrayList<Product_dto> getBasket(String []clientTable);
    public ArrayList<Product_dto> getFilteredBasket(String[] clientTable,String [] filters);
    public String removeFromBasket(String[] clientTable, String[] productTable);
    public String browseBasket(String[] clientTable, String[] filters) ;
    public void addClient();
    public void addProduct();
    public void changePrice(Double price, String []productTable);
    public void removeProduct(Product_dto product_dto);
    public List<Product> getAllProducts();
    public List<Product_dto> getAllProducts_dto();
    public List<Client> getAllClients();
            
}
