package tp.gui.buscarcliente;

import tp.dto.*;
import tp.gui.*;
import tp.gui.altapoliza.AltaPolizaFormularioCoberturaController;

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

public class BuscarClienteController {
	
		private PolizaDTO poliza = new PolizaDTO();
		private ClienteDTO cliente = new ClienteDTO();
		
	
		@FXML
	   private Button confirmarButton;
	    @FXML
	   private Button exitButton;
	    
	    @FXML
	   private void confirmarCliqueado(ActionEvent event) throws IOException {
	    	
	    	poliza.setCliente(this.cliente);
	    	
	    	FXMLLoader loader = new FXMLLoader();
	    	AltaPolizaFormularioCoberturaController controlador = new AltaPolizaFormularioCoberturaController();
	    	controlador.setPoliza(poliza);
	    	
	    	loader.setLocation(getClass().getResource("../altapoliza/AltaPolizaFormularioPoliza.fxml"));
	    	AnchorPane form = loader.load();
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
