package tp.gui.altapoliza;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import tp.app.App;
import tp.dto.*;


public class AltaPolizaConfirmarController {

	private PolizaDTO poliza = new PolizaDTO();
	
	@FXML
	private Label apellido;
	@FXML
	private Label nombre;
	@FXML
	private Label marca;
	@FXML
	private Label modelo;
	@FXML
	private Label motor;
	@FXML
	private Label chasis;
	@FXML
	private Label patente;
	@FXML
	private Label direccionDeRiesgo;
	@FXML
	private Label vigencia;
	@FXML
	private Label inicioVigencia;
	@FXML
	private Label finalVigencia;
	@FXML
	private Label ultimoDiaDePago;
	@FXML
	private Label sumaAsegurada;
	@FXML
	private Label premio;
	@FXML
	private Label importePorDescuento;
	@FXML
	private Label totalAbonar;
	
	
	public void setPolizaDTO(PolizaDTO p) {
		this.poliza = p;
	}
	
	@FXML
	private void volverAtrasClicked(ActionEvent action) throws IOException {
		
		
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../altapoliza/AltaPolizaFormularioCobertura.fxml"));
   
    	AnchorPane form = loader.load();
    	
    	AltaPolizaFormularioCoberturaController formularioPolizaCoberturaC = loader.getController();
    	formularioPolizaCoberturaC.setPolizaDTO(this.poliza);
    	
    	App.switchScreenTo(form);
		
		
		
	}
	
	private void mostrarDatosPoliza( ) {
		
		apellido.setText(poliza.getCliente().getApellido());
		nombre.setText(poliza.getCliente().getNombre());
			
	}
	
	
	@FXML
	public void initialize( ) {
		
		this.mostrarDatosPoliza();
		
	}
	
	
	
	
}

