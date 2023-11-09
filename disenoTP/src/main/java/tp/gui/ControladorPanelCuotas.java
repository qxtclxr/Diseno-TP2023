package tp.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladorPanelCuotas {
	@FXML
	private Button volverAtras;
	@FXML
	void volverAtrasCliqueado(ActionEvent evento) throws IOException {
		Parent root = FXMLLoader.load((getClass().getResource("MenuPrincipal.fxml")));
    	
    	Stage window = (Stage)volverAtras.getScene().getWindow();
    	window.setTitle("MenuPrincipal");
    	window.setScene(new Scene(root));
	}
}
