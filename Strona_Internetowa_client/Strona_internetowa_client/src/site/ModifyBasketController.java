/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Juju
 */
package site;

import bussineslogic.model.Category;
import bussineslogic.model.Client;
import bussineslogic.model.Gender;
import bussineslogic.model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ModifyBasketController implements Initializable {

    private Client client;

    @FXML
    private Pane pane;

    @FXML
    private Button btnAddToBasket;

    @FXML
    private Button btnGoBack;

    @FXML
    private ChoiceBox<Product> cboxAddProduct;

    @FXML
    private Button btnRemoveFromBasket;

    @FXML
    private TableColumn<Product, String> clmName;

    @FXML
    private TableColumn<Product, Double> clmPrice;

    @FXML
    private TableColumn<Product, Category> clmCategory;

    @FXML
    private TableColumn<Product, Gender> clmGender;

    @FXML
    private TableColumn<Product, String> clmSize;

    @FXML
    private TableColumn<Product, String> clmBrand;

    @FXML
    private ComboBox<Client> cboxClient;

    @FXML
    private TableView<Product> tblBasket;

    @FXML
    void btnAddToBasketClicked(ActionEvent event) {
        if (content_validate(cboxClient) != null) {
            String[] productData = getProduct(cboxAddProduct.getSelectionModel().getSelectedItem());
            String[] clientData = getClient();
            if (productData == null || clientData == null) {
                return;
            }
            ClientTier.getFacade().addProductToBasket(clientData, productData);
            refresh_table();
            Alert alert = new Alert(AlertType.INFORMATION, "Dodano produkt do koszyka");
            alert.showAndWait();
        }
    }

    @FXML
    void btnRemoveFromBasketClicked(ActionEvent event) {
        if (content_validate(cboxClient) != null) {
            String[] productData = getProduct(tblBasket.getSelectionModel().getSelectedItem());
            String[] clientData = getClient();
            if (productData == null || clientData == null) {
                return;
            }
            ClientTier.getFacade().removeFromBasket(clientData, productData);
            refresh_table();
            Alert alert = new Alert(AlertType.INFORMATION, "Usunięto produkt z koszyka");
            alert.showAndWait();
        }
    }

    @FXML
    void btnGoBackClicked(ActionEvent event) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/Home.fxml"));
        stageTheEventSourceNodeBelongs.setScene(new Scene((Parent) loader.load()));
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Client> options = FXCollections.observableArrayList(ClientTier.getFacade().getClientList());
        cboxClient.getItems().setAll(options);
        
        ObservableList<Product> options2 = FXCollections.observableArrayList(ClientTier.getFacade().getProductList());
        cboxAddProduct.setItems(options2);
        
        clmName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        clmCategory.setCellValueFactory(new PropertyValueFactory<Product, Category>("category"));
        clmGender.setCellValueFactory(new PropertyValueFactory<Product, Gender>("gender"));
        clmSize.setCellValueFactory(new PropertyValueFactory<Product, String>("size"));
        clmBrand.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
        tblBasket.getColumns().clear();
        tblBasket.getColumns().addAll(clmName, clmPrice, clmCategory, clmGender, clmSize, clmBrand);
        
        cboxClient.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Client>() {
            @Override
            public void changed(ObservableValue<? extends Client> ov, Client t, Client t1) {
                tblBasket.getItems().clear();
            }
        });
    }

    private String[] getClient() {
        client = cboxClient.getSelectionModel().getSelectedItem();
        String[] clientTable = new String[]{client.getId(), client.getFirstName(), client.getLastName(), client.getAdress(), client.getPhone(), client.getEmail()};
        return clientTable;
    }

    public String content_validate(ComboBox val) {
        if (val.getSelectionModel().getSelectedItem() != null) {
            String s = val.getSelectionModel().getSelectedItem().toString();
            s = s.replaceAll(" ", "_");
            System.out.println("Content Validate: "+s);
            return s;
        } else {
            System.out.println("Content Validate: null");
            return null;
        }
    }

    public void refresh_table() {
        ObservableList<Product> data = FXCollections.observableArrayList(make_productlist());
        tblBasket.setItems(data);
    }

    public ArrayList<Product> make_productlist() {
        ArrayList<Product> productList = new ArrayList<>();
        for (Product p : client.getShoppingBasket().getProductMap().keySet()) {
            productList.add(p);
        }
        return productList;
    }

    private String[] getProduct(Product product) {
        if (product != null) {
            String[] productTable = new String[]{product.getName(), String.valueOf(product.getPrice()), product.getCategory().name(), product.getGender().name(), product.getSize(), product.getBrand()};
            return productTable;
        } else {
            Alert alert = new Alert(AlertType.ERROR, "Wybierz produkt");
            alert.showAndWait();
            return null;
        }
    }

    @FXML
    void btnShowClicked(ActionEvent event) {
        if (content_validate(cboxClient) != null) {
            getClient();
            refresh_table();
        } else {
            Alert alert = new Alert(AlertType.ERROR, "Wybierz klienta");
            alert.showAndWait();
        }
    }

}
