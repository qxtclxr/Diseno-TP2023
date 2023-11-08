package org.openjfx.disenoTP;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControladorMenuPrincipal {

	@FXML
	private Button altaPoliza;
	@FXML
	private Button salir;
	@FXML
    private AnchorPane panelBase;
	@FXML
	void altaPolizaCliqueado(ActionEvent evento) throws IOException{
		Parent root = FXMLLoader.load((getClass().getResource("AltaPolizaPantalla1.fxml")));
    	
    	Stage window = (Stage)altaPoliza.getScene().getWindow();
    	window.setTitle("Cuotas");
    	window.setScene(new Scene(root));
	}
	
	@FXML
	void salirCliqueado(ActionEvent evento) throws IOException{
		Stage stage = (Stage) panelBase.getScene().getWindow();
    	stage.close();
	}
}
