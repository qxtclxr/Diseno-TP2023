package tp.gui.inicio;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tp.app.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class LoginController {
	@FXML
	private Button acceder;
	@FXML
	private TextField user;
	@FXML
	private PasswordField pass;
	
	@FXML
	private boolean validar() {
		boolean valid = true;
		if(user.getText()=="") {
			user.setStyle("-fx-background-color: #fa8e8e;");
			valid &= false;
		}
		if(pass.getText()=="") {
			pass.setStyle("-fx-background-color: #fa8e8e;");
			valid &= false;
		}
		return valid;
	}
	
	@FXML
	void accederCliqueado(ActionEvent evento) throws IOException {
		if(validar()) {
			App.setUsuarioLogeado(user.getText(), pass.getText());
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MenuPrincipal.fxml"));
			Pane menuPrincipal = loader.load();
			App.switchScreenTo(menuPrincipal);
		}
	}
}
