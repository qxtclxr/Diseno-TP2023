package tp.gui.inicio;

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

public class IngresoSistemaController {
	
	@FXML
	private Button ingresoProductorButton;
	
	@FXML
	void ingresoProductorCliqueado() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Login.fxml"));
		AnchorPane login = loader.load();
		App.switchScreenTo(login);
	}
}
