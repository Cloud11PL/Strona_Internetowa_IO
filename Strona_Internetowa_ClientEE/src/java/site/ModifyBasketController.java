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


import client_tier.ClientGUI;
import bussineslogic.dto.Category_dto;
import bussineslogic.dto.Client_dto;
import bussineslogic.dto.Gender_dto;
import bussineslogic.dto.Product_dto;
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

    private Client_dto client;

    @FXML
    private Pane pane;

    @FXML
    private Button btnAddToBasket;

    @FXML
    private Button btnGoBack;

    @FXML
    private ChoiceBox<Product_dto> cboxAddProduct;

    @FXML
    private Button btnRemoveFromBasket;

    @FXML
    private TableColumn<Product_dto, String> clmName;

    @FXML
    private TableColumn<Product_dto, Double> clmPrice;

    @FXML
    private TableColumn<Product_dto, Category_dto> clmCategory;

    @FXML
    private TableColumn<Product_dto, Gender_dto> clmGender;

    @FXML
    private TableColumn<Product_dto, String> clmSize;

    @FXML
    private TableColumn<Product_dto, String> clmBrand;

    @FXML
    private ComboBox<Client_dto> cboxClient;

    @FXML
    private TableView<Product_dto> tblBasket;

    @FXML
    void btnAddToBasketClicked(ActionEvent event) {
        if (content_validate(cboxClient) != null) {
            String[] productData = getProduct(cboxAddProduct.getSelectionModel().getSelectedItem());
            String[] clientData = getClient();
            if (productData == null || clientData == null) {
                return;
            }
            ClientGUI.getFacade().addProductToBasket(clientData, productData);
            getClient();
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
            ClientGUI.getFacade().removeFromBasket(clientData, productData);
            getClient();
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
        ObservableList<Client_dto> options = FXCollections.observableArrayList(ClientGUI.getFacade().getClients());
        cboxClient.getItems().setAll(options);
        
        ObservableList<Product_dto> options2 = FXCollections.observableArrayList(ClientGUI.getFacade().getProducts());
        cboxAddProduct.setItems(options2);
        
        clmName.setCellValueFactory(new PropertyValueFactory<Product_dto, String>("name"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<Product_dto, Double>("price"));
        clmCategory.setCellValueFactory(new PropertyValueFactory<Product_dto, Category_dto>("category"));
        clmGender.setCellValueFactory(new PropertyValueFactory<Product_dto, Gender_dto>("gender"));
        clmSize.setCellValueFactory(new PropertyValueFactory<Product_dto, String>("size"));
        clmBrand.setCellValueFactory(new PropertyValueFactory<Product_dto, String>("brand"));
        tblBasket.getColumns().clear();
        tblBasket.getColumns().addAll(clmName, clmPrice, clmCategory, clmGender, clmSize, clmBrand);
        
        cboxClient.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Client_dto>() {
            @Override
            public void changed(ObservableValue<? extends Client_dto> ov, Client_dto t, Client_dto t1) {
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
        ObservableList<Product_dto> data = FXCollections.observableArrayList(make_productlist(getClient()));
        tblBasket.setItems(data);
    }

    public ArrayList<Product_dto> make_productlist(String [] clientTable) {
        return ClientGUI.getFacade().getBasket(clientTable);
    }

    private String[] getProduct(Product_dto product) {
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
