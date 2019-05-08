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
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class BrowseBasketController implements Initializable {

    Client client;

    @FXML
    private Pane pane;

    @FXML
    private Button btnBrowse;

    @FXML
    private Button btnGoBack;

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
    private TextField txtName;

    @FXML
    private TextField txtMinPrice;

    @FXML
    private TextField txtMaxPrice;

    @FXML
    private TextField txtSize;

    @FXML
    private TextField txtBrand;

    @FXML
    private ComboBox<Category> cboxCategory;

    @FXML
    private ComboBox<Gender> cboxGender;

    @FXML
    private ComboBox<Client> cboxClient;

    @FXML
    private TableView<Product> tblBasket;
    
    @FXML
    private Button btnClearFilters;

    @FXML
    void btnBrowseClicked(ActionEvent event) {
        if (content_validate(cboxClient) != null) {
            String[] data = form_filters();
            String[] clientData = getClient();
            if (data == null) {
                return;
            }
            ClientTier.getFacade().browseBasket(clientData, data);
            refresh_table();
        }
    }
    
    @FXML
    void btnClearFiltr(ActionEvent event){
        txtName.clear();
        txtBrand.clear();
        txtMaxPrice.clear();
        txtMinPrice.clear();
        txtSize.clear();
        
        cboxCategory.setValue(null);
        cboxGender.setValue(null);
    }

    @FXML
    void btnGoBackClicked(ActionEvent event) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        stageTheEventSourceNodeBelongs.setScene(new Scene((Parent) loader.load()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboxCategory.getItems().setAll(Category.values());
        cboxGender.getItems().setAll(Gender.values());
        ObservableList<Client> options = FXCollections.observableArrayList(ClientTier.getFacade().getClientList());
        cboxClient.getItems().setAll(options);
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

    public String[] form_filters() {
        String cat, gen;
        if (content_validate(cboxCategory) == null) {
            cat = "";
        } else {
            cat = cboxCategory.getSelectionModel().getSelectedItem().toString();
        }
        if (content_validate(cboxGender) == null) {
            gen = "";
        } else {
            gen = cboxGender.getSelectionModel().getSelectedItem().toString();
        }
        String data[] = {txtMinPrice.textProperty().getValue(), txtMaxPrice.textProperty().getValue(), cat, gen,
            txtSize.textProperty().getValue(), txtBrand.textProperty().getValue(), txtName.textProperty().getValue()};
        return data;
    }

    public String content_validate(ComboBox val) {
        if (val.getSelectionModel().getSelectedItem() != null) {
            String s = val.getSelectionModel().getSelectedItem().toString();
            s = s.replaceAll(" ", "_");
            return s;
        } else {
            return null;
        }
    }

    public void refresh_table() {
        ArrayList<Product> list = make_productlist_filtered();
        if (list.isEmpty()) {
            tblBasket.getItems().clear();
        }else{
            ObservableList<Product> data = FXCollections.observableArrayList(list);
            tblBasket.setItems(data);
        }
    }

    public ArrayList<Product> make_productlist_filtered() {
        ArrayList<Product> productList = new ArrayList<>();
        for (Product p : client.getShoppingBasket().getFilteredMap().keySet()) {
            productList.add(p);
        }
        return productList;
    }

    private String[] getClient() {
        client = cboxClient.getSelectionModel().getSelectedItem();
        String[] clientTable = new String[]{client.getId(), client.getFirstName(), client.getLastName(), client.getAdress(), client.getPhone(), client.getEmail()};
        return clientTable;
    }

}
