package tp.gui.altapoliza;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import tp.app.App;

public class AltaPolizaFormularioCoberturaController {
	@FXML
	private void confirmarClicked(ActionEvent action) throws IOException {
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../altapoliza/AltaPolizaConfirmar.fxml"));
    	AnchorPane form = loader.load();
    	App.switchScreenTo(form);
	}
}