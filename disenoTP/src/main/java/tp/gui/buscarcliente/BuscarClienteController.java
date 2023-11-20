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
	   
	   private AnchorPane actual;
	   private AnchorPane anterior; 
	    
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
	    	
	    	altaPolizaC.setActual(form);
	    	altaPolizaC.setAnterior(actual);
	    	
	    	App.switchScreenTo(form);
	    }
	    

	    @FXML
	    private void handleExit(ActionEvent event) throws IOException {
	    	System.out.println(anterior);
	    	App.switchScreenTo(anterior);
	    }
	    
	    public void setClienteDTO(ClienteDTO c) {
	    	clienteDTO = c;
	    }


		public void setActual(AnchorPane actual) {
			this.actual = actual;
		}
		
		public void setAnterior(AnchorPane anterior) {
			this.anterior = anterior;
		}
}
