package client_tier;

import businesstier.EJBFacadeRemote;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.ejb.EJB;

/**
 *
 * @author Juju
 */
public class ClientGUI extends Application {

    @EJB
    private static EJBFacadeRemote eJBFacade;

    public static EJBFacadeRemote getFacade() {
        return eJBFacade;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/res/Home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        initializeFacade();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private void initializeFacade(){
        getFacade().addProductList();
        getFacade().addClientList();
    }
    
    
}
