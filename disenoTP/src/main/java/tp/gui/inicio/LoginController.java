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
	void accederCliqueado(ActionEvent evento) throws IOException {
		
		App.setUsuarioLogeado(user.getText(), pass.getText());
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MenuPrincipal.fxml"));
		Pane menuPrincipal = loader.load();
		App.switchScreenTo(menuPrincipal);
	}
}
