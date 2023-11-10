package tp.gui.altapoliza;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp.app.App;
import tp.gui.buscarcliente.BuscarCliente;

public class AltaPolizaInicioController {
    @FXML
    private Button buscarCliente;
    @FXML
    private Button addClientButton;
    @FXML
    private Button exitButton;

    

    @FXML
    private void handleExit(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load((getClass().getResource("../inicio/MenuPrincipal.fxml")));
    	
    	Stage window = (Stage)exitButton.getScene().getWindow();
    	window.setTitle("Menu Principal");
    	window.setScene(new Scene(root));
    }
    @FXML
    private void buscarCliente(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../buscarcliente/BuscarCliente.fxml"));
    	AnchorPane form = loader.load();
    	App.switchScreenTo(form);
    }
    
    @FXML
    private void addClient(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load((getClass().getResource("../altacliente/AltaCliente.fxml")));
    	Stage window = (Stage)addClientButton.getScene().getWindow();
    	window.setTitle("Alta cliente");
    	window.setScene(new Scene(root));
    }

    
}


