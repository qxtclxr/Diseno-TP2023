package tp.gui.altapoliza;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tp.dto.PolizaDTO;

public class VerCuotasController implements Initializable{
	private PolizaDTO poliza;
	@FXML
	private Button volverAtras;
	@FXML
	private Label montoAPagar;
	
	public VerCuotasController(PolizaDTO poliza) {
		this.poliza = poliza;
	}
	
	@FXML
	void volverAtrasCliqueado(ActionEvent evento) throws IOException {
		Parent root = FXMLLoader.load((getClass().getResource("../inicio/MenuPrincipal.fxml")));
    	
    	Stage window = (Stage)volverAtras.getScene().getWindow();
    	window.setTitle("MenuPrincipal");
    	window.setScene(new Scene(root));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		montoAPagar.setText(String.valueOf(poliza.getImporteTotal()));	
	}
	
	
}
