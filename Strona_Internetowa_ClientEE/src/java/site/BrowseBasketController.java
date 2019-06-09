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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import site.locale.LocaleService;

public class BrowseBasketController implements Initializable {

    Client_dto client;

    @FXML
    private Pane pane;

    @FXML
    private Button btnBrowse;

    @FXML
    private Button btnGoBack;

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
    private ComboBox<Category_dto> cboxCategory;

    @FXML
    private ComboBox<Gender_dto> cboxGender;

    @FXML
    private ComboBox<Client_dto> cboxClient;

    @FXML
    private TableView<Product_dto> tblBasket;
    
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
            ClientGUI.getFacade().browseBasket(clientData, data);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/Home.fxml"));
        stageTheEventSourceNodeBelongs.setScene(new Scene((Parent) loader.load()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboxCategory.getItems().setAll(Category_dto.values());
        cboxGender.getItems().setAll(Gender_dto.values());
        ObservableList<Client_dto> options = FXCollections.observableArrayList(ClientGUI.getFacade().getClients());
        cboxClient.getItems().setAll(options);
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clmCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        clmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clmSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        clmBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));

        tblBasket.getColumns().clear();
        tblBasket.getColumns().addAll(clmName, clmPrice, clmCategory, clmGender, clmSize, clmBrand);
        
        cboxClient.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Client_dto>() {
            @Override
            public void changed(ObservableValue<? extends Client_dto> ov, Client_dto t, Client_dto t1) {
                tblBasket.getItems().clear();
            }
        });
        
        /* clmName.setText(LocaleService.INSTANCE.getMessage("name"));
        clmPrice.setText(LocaleService.INSTANCE.getMessage("price"));
        clmCategory.setText(LocaleService.INSTANCE.getMessage("category"));
        clmGender.setText(LocaleService.INSTANCE.getMessage("gender"));
        clmSize.setText(LocaleService.INSTANCE.getMessage("size"));
        clmBrand.setText(LocaleService.INSTANCE.getMessage("brand"));
        */
        
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
        ArrayList<Product_dto> list = make_productlist_filtered(getClient(),form_filters());
        System.out.println("lis"+list);
        if (list.isEmpty()) {
            tblBasket.getItems().clear();
        }else{
            ObservableList<Product_dto> data = FXCollections.observableArrayList(list);
            System.out.println("data"+data);
            tblBasket.setItems(data);
        }
    }

    public ArrayList<Product_dto> make_productlist_filtered(String [] clientTable,String [] filters) {
        return ClientGUI.getFacade().getFilteredBasket(clientTable,filters);
    }

    private String[] getClient() {
        client = cboxClient.getSelectionModel().getSelectedItem();
        String[] clientTable = new String[]{client.getId(), client.getFirstName(), client.getLastName(), client.getAdress(), client.getPhone(), client.getEmail()};
        return clientTable;
    }

}
