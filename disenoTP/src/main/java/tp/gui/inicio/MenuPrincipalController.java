package tp.gui.inicio;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tp.app.App;
import tp.gui.altapoliza.AltaPolizaInicioController;

public class MenuPrincipalController {

	@FXML
	private Button altaPoliza;
	@FXML
	private Button salir;
	@FXML
    private AnchorPane panelBase;
	
	@FXML
	void altaPolizaCliqueado(ActionEvent evento) throws IOException{
		AltaPolizaInicioController controller = new AltaPolizaInicioController();
		
		FXMLLoader loader = new FXMLLoader();
		
		loader.setController(controller);
		
		loader.setLocation(getClass().getResource("../altapoliza/AltaPolizaInicio.fxml"));
		
		AnchorPane altaPoliza = loader.load();
		
		controller.setActual(altaPoliza);
		
		App.switchScreenTo(altaPoliza);
	}
	
	@FXML
	void salirCliqueado(ActionEvent evento) throws IOException{
		Stage stage = (Stage) panelBase.getScene().getWindow();
    	stage.close();
	}
}
