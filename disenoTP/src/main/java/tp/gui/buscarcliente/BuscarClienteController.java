package tp.gui.buscarcliente;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BuscarClienteController {
		@FXML
	    private Button confirmarButton;
	    @FXML
	   private Button exitButton;

	    

	    @FXML
	    private void handleExit(ActionEvent event) throws IOException {
	    	Parent root = FXMLLoader.load((getClass().getResource("../altapoliza/AltaPolizaInicio.fxml")));
	    	
	    	Stage window = (Stage)exitButton.getScene().getWindow();
	    	window.setTitle("Alta de poliza");
	    	window.setScene(new Scene(root));
	    }
	    
	
	
	
}
