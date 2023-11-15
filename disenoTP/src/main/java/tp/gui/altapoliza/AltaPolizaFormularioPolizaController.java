package tp.gui.altapoliza;

import javafx.scene.control.Button;
import java.io.IOException;
import javafx.scene.Scene;

import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import tp.app.App;

public class AltaPolizaFormularioPolizaController {
	
	@FXML
	private Button declararHijos;
	
	@FXML
	private void confirmarClicked(ActionEvent action) throws IOException {
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../altapoliza/AltaPolizaFormularioCobertura.fxml"));
    	AnchorPane form = loader.load();
    	App.switchScreenTo(form);
	}
	@FXML
	private void declararHijosClicked(ActionEvent action) throws IOException {
	    // Cargar el archivo FXML
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("../altapoliza/DeclararHijos.fxml"));
	    AnchorPane form = loader.load();

	    // Crear una nueva ventana modal
	    Stage ventanaModal = new Stage();
	    ventanaModal.initModality(Modality.APPLICATION_MODAL);
	    ventanaModal.setTitle("Declarar Hijos");

	    // Configurar el contenido de la ventana modal
	    Scene modalScene = new Scene(form);
	    ventanaModal.setScene(modalScene);

	    // Mostrar la ventana modal y esperar hasta que se cierre
	    ventanaModal.showAndWait();
	}

	
	
}
