package tp.gui.inicio;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;

public class LoginController {
	@FXML
	private Button acceder;

	@FXML
	void accederCliqueado(ActionEvent evento) throws IOException {
		Parent root = FXMLLoader.load((getClass().getResource("MenuPrincipal.fxml")));
    	
    	Stage window = (Stage)acceder.getScene().getWindow();
    	window.setTitle("Menu principal");
    	window.setScene(new Scene(root));
	}
}
