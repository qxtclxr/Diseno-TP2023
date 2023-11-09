package tp.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladorIngresoSistema {
	
	@FXML
	private Button ingresoProductorButton;
	
	@FXML
	void ingresoProductorCliqueado(ActionEvent evento) throws IOException{
		Parent root = FXMLLoader.load((getClass().getResource("Login.fxml")));
    	
    	Stage window = (Stage)ingresoProductorButton.getScene().getWindow();
    	window.setTitle("Ingrese sus datos de acceso");
    	window.setScene(new Scene(root));
	}
	
}
