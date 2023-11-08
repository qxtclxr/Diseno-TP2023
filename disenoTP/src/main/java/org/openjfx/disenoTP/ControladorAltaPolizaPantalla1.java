package org.openjfx.disenoTP;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControladorAltaPolizaPantalla1 {
    @FXML
    private Button buscarCliente;
    @FXML
    private Button addClientButton;
    @FXML
    private Button exitButton;

    

    @FXML
    private void handleExit(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load((getClass().getResource("MenuPrincipal.fxml")));
    	
    	Stage window = (Stage)exitButton.getScene().getWindow();
    	window.setTitle("Menu Principal");
    	window.setScene(new Scene(root));
    }
    @FXML
    private void buscarCliente(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load((getClass().getResource("BuscarCliente.fxml")));
    	
    	Stage window = (Stage)buscarCliente.getScene().getWindow();
    	window.setTitle("Buscar cliente");
    	window.setScene(new Scene(root));
    }
    
    @FXML
    private void addClient(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load((getClass().getResource("AltaCliente.fxml")));
    	
    	Stage window = (Stage)addClientButton.getScene().getWindow();
    	window.setTitle("Alta cliente");
    	window.setScene(new Scene(root));
    }

    
}


