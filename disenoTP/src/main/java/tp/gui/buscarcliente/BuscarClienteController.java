package tp.gui.buscarcliente;

import tp.dao.*;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tp.app.App;

import tp.gui.altapoliza.AltaPolizaFormularioPolizaController;

public class BuscarClienteController {
	
		@FXML
	   private Button confirmarButton;
	    @FXML
	   private Button exitButton;
	    
	   private ClienteDAO clienteDAO = new ClienteDAO(); 
	    
	    @FXML
	   private void confirmarCliqueado(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader();
	    	
	    	loader.setLocation(getClass().getResource("../altapoliza/AltaPolizaFormularioPoliza.fxml"));
	    	AnchorPane form = loader.load();
	    	
	    	AltaPolizaFormularioController altaPolizaC = loader.getController();

	    	
	    	altaPolizaC.setClienteDAO(clienteDAO);
	    	App.switchScreenTo(form);
	    	
	    }

	    @FXML
	    private void handleExit(ActionEvent event) throws IOException {
	    	
	    	Parent root = FXMLLoader.load((getClass().getResource("../altapoliza/AltaPolizaInicio.fxml")));
	    	
	    	Stage window = (Stage)exitButton.getScene().getWindow();
	    	window.setTitle("Alta de poliza");
	    	window.setScene(new Scene(root));
	    }
}
