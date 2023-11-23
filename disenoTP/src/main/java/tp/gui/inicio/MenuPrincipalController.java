package tp.gui.inicio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tp.app.App;
import tp.gui.altapoliza.AltaPolizaInicioController;

public class MenuPrincipalController implements Initializable {

	@FXML
	private Button altaPoliza;
	@FXML
	private Button salir;
	@FXML
    private AnchorPane panelBase;
	@FXML
	private Text bienvenidoMsg;
	
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
	
	public boolean alertaEstasSeguroSalir(){
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle("Confirmacion");
        alert.setHeaderText(null);
        alert.setContentText("¿Estas seguro de que quieres salir?");
        alert.getDialogPane().getChildren().stream()
                .filter(node -> node instanceof Label)
                .forEach(node -> ((Label) node).setFont(Font.font("Franklin Gothic Medium", 14)));
        
        return alert.showAndWait()
        		.filter(response -> response == ButtonType.OK)
        		.isPresent();
	}
	
	@FXML
	public void salirCliqueado() throws IOException {
		if(alertaEstasSeguroSalir()) {
			Stage stage = (Stage) panelBase.getScene().getWindow();
	    	stage.close();
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bienvenidoMsg.setText("Bienvenid@, " + App.getUsuarioLogeado().getNickname());
	}
}
