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
import tp.gui.buscarcliente.BuscarClienteController;

public class AltaPolizaInicioController {
	
	private AnchorPane actual;
	
    @FXML
    private Button buscarCliente;
    @FXML
    private Button addClientButton;
    @FXML
    private Button exitButton;

    public void setActual(AnchorPane actual) {
    	this.actual = actual;
    }

    @FXML
    private void handleExit(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../inicio/MenuPrincipal.fxml"));
    	AnchorPane menu = loader.load();
    	App.switchScreenTo(menu);
    }
    
    @FXML
    private void buscarCliente(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	BuscarClienteController controller = new BuscarClienteController();
    	loader.setController(controller);
		loader.setLocation(getClass().getResource("../buscarcliente/BuscarCliente.fxml"));
    	AnchorPane form = loader.load();
    	
    	controller.setActual(form);
    	controller.setAnterior(actual);
    	
    	App.switchScreenTo(form);
    }
    
    @FXML
    private void addClient(ActionEvent event) throws IOException {
    	//Falta setear controlador
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../altacliente/AltaCliente.fxml"));
    	AnchorPane form = loader.load();
    	App.switchScreenTo(form);
    }

    
}


