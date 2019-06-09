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
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import site.locale.LocaleService;

public class AddClientController implements Initializable{

    @FXML
    private Pane pane;

    @FXML
    private Button btnAddClient;

    @FXML
    private Button btnGoBack;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtClientFirstName;

    @FXML
    private TextField txtClientLastName;

    @FXML
    private TextField txtClientAdress;

    @FXML
    private TextField txtClientPhoneNumber;

    @FXML
    private TextField txtClientEmail;
    
    @FXML
    private Label idLabel;
    
    @FXML
    private Label firstNameLabel;
    
    @FXML
    private Label lastNameLabel;
 
    @FXML
    private Label adressLabel;
     
    @FXML
    private Label numerLabel;
      
    @FXML
    private Label emailLabel;

        
    @FXML
    void btnAddClientClicked(ActionEvent event) {
        String[] data = form_product();
        if (data == null) {
            return;
        }
        ClientGUI.getFacade().addClient(data);
        clearAllFields();
        Alert alert = new Alert(AlertType.INFORMATION, LocaleService.INSTANCE.getMessage("clientAdded"));
        alert.showAndWait();
    }
    
    private void clearAllFields(){
        txtid.clear();
        txtClientAdress.clear();
        txtClientEmail.clear();
        txtClientPhoneNumber.clear();
        txtClientFirstName.clear();
        txtClientLastName.clear();
    }

        @FXML
    void btnGoBackClicked(ActionEvent event) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/Home.fxml"));
        stageTheEventSourceNodeBelongs.setScene(new Scene((Parent) loader.load()));

    }
        public String[] form_product() {
        if (content_validate(txtid) == null) {
            return null;
        }
        if (content_validate(txtClientFirstName) == null) {
            return null;
        }
        if (content_validate(txtClientLastName) == null) {
            return null;
        }
        if (content_validate(txtClientAdress)== null) {
            return null;
        }
        if (content_validate(txtClientPhoneNumber)== null) {
            return null;
        }
        if (content_validate(txtClientEmail)== null) {
            return null;
        }
        
        String data[] = {txtid.getText(),
            txtClientFirstName.getText(), txtClientLastName.getText(),txtClientAdress.getText(), txtClientPhoneNumber.getText(),
            txtClientEmail.getText()};
            System.out.println(Arrays.toString(data));
        return data;
    }

    public String content_validate(TextField val) {
        String s = val.getText();
        if (s.equals("")) {
            Alert alert = new Alert(AlertType.ERROR, LocaleService.INSTANCE.getMessage("dataError"));
            alert.showAndWait();
            return null;
        } else {
            s = s.replaceAll(" ", "_");
            val.setText(s);
            return s;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAddClient.setText(LocaleService.INSTANCE.getMessage("addClient"));
        btnGoBack.setText(LocaleService.INSTANCE.getMessage("goBack"));
        idLabel.setText(LocaleService.INSTANCE.getMessage("id"));
        adressLabel.setText(LocaleService.INSTANCE.getMessage("adress"));
        emailLabel.setText(LocaleService.INSTANCE.getMessage("email"));
        numerLabel.setText(LocaleService.INSTANCE.getMessage("phoneNumber"));
        firstNameLabel.setText(LocaleService.INSTANCE.getMessage("firstName"));
        lastNameLabel.setText(LocaleService.INSTANCE.getMessage("lastName"));
    }


}
