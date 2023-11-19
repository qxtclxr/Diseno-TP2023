package tp.gui.buscarcliente;

import tp.dto.*;
import tp.entidad.TipoDocumento;

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
	    
	   private ClienteDTO clienteDTO = new ClienteDTO(); 
	    
	    @FXML
	   private void confirmarCliqueado(ActionEvent event) throws IOException {
	    	
	    	clienteDTO.setNombre("Juan");
	    	clienteDTO.setApellido("Perez");
	    	clienteDTO.setNroCliente("123456789");
	    	clienteDTO.setNroDocumento("1512999191");
	    	clienteDTO.setTipoDocumento(TipoDocumento.DNI);
	    	
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
