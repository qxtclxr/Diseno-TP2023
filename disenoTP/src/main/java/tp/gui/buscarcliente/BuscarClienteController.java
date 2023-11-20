package tp.gui.buscarcliente;

import tp.dto.*;
import tp.entidad.TipoDocumento;
import tp.exception.ObjetoNoEncontradoException;

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
import tp.logica.GestorCliente;

public class BuscarClienteController {
	
		@FXML
	   private Button confirmarButton;
	    @FXML
	   private Button exitButton;
	    
	   private ClienteDTO clienteDTO = new ClienteDTO(); 
	    
	    @FXML
	   private void confirmarCliqueado(ActionEvent event) throws IOException, ObjetoNoEncontradoException {
	    	
	    	//Mockup
	    	ClienteDTO clienteDTO = GestorCliente.getClienteDTO(GestorCliente.getByNroCliente("123456789")) ;
	    	
	    	FXMLLoader loader = new FXMLLoader();
	    	AltaPolizaFormularioPolizaController altaPolizaC = new AltaPolizaFormularioPolizaController();
	    	altaPolizaC.setClienteDTO(clienteDTO);
	    	loader.setController(altaPolizaC);
	    	
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
	    
	    public void setClienteDTO(ClienteDTO c) {
	    	clienteDTO = c;
	    }
}
