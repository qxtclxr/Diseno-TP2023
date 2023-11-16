package tp.gui.altapoliza;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tp.app.App;
import tp.dto.PolizaDTO;

public class AltaPolizaFormularioCoberturaController {
	
	private PolizaDTO poliza;
	
	@FXML
	private Button confirmar;
	@FXML
	private RadioButton respCivil;
	@FXML
	private RadioButton respCivilRoboIncendioTotal;
	@FXML
	private RadioButton todoTotal;
	@FXML
	private RadioButton tercerosCompletos;
	@FXML
	private RadioButton todoRiesgoConFranquicia;
	@FXML
	private Label errorTipoCobertura;
	@FXML
	private DatePicker fechaInicioVigencia;
	@FXML
	private Label errorFechaInicioVigencia1;
	@FXML
	private Label errorFechaInicioVigencia2;
	@FXML
	private Label errorFechaInicioVigencia3;
	@FXML 
	private ComboBox tipoPago;
	@FXML 
	private Label errorTipoPago;
	
	
	public void setPoliza(PolizaDTO poliza1) {
		this.poliza = poliza1;
	}
	
	
	
	public AltaPolizaFormularioCoberturaController() {
		super();
	}



	@FXML
	private void confirmarClicked(ActionEvent action) throws IOException {
		
		if(this.validarDatos()) {
		
		
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../altapoliza/AltaPolizaConfirmar.fxml"));
			AnchorPane form = loader.load();
			App.switchScreenTo(form);
    	
		}
	}
	@FXML
	public boolean validarDatos( ) {
		
		boolean validacionExitosa = true;
		
		if(!(respCivil.isSelected() || respCivilRoboIncendioTotal.isSelected() || todoTotal.isSelected() || tercerosCompletos.isSelected() || todoRiesgoConFranquicia.isSelected()) ) { 
				errorTipoCobertura.setVisible(true);
				validacionExitosa = false;
		}else {
			errorTipoCobertura.setVisible(false);
		}
		
	    if (tipoPago.getValue() == null || tipoPago.getValue().toString().isEmpty()) {
	        errorTipoPago.setVisible(true);
	        validacionExitosa = false;
	    } else {
	    	errorTipoPago.setVisible(false);
	    }
		
	    if(fechaInicioVigencia.getValue()==null || !(Period.between(LocalDate.now() , fechaInicioVigencia.getValue()).getDays() >= 0 && Period.between(fechaInicioVigencia.getValue(), LocalDate.now().plusMonths(1)).getDays() >= 0)) {
	    	errorFechaInicioVigencia1.setVisible(true);
	    	errorFechaInicioVigencia2.setVisible(true);
	    	errorFechaInicioVigencia3.setVisible(true);
	    	validacionExitosa = false;
	    }
	    else {
	    	errorFechaInicioVigencia1.setVisible(false);
	    	errorFechaInicioVigencia2.setVisible(false);
	    	errorFechaInicioVigencia3.setVisible(false);
	    }
	    
	    
	    
		
		return validacionExitosa;
		
	}
	
	@FXML
	public void initialize() {
		
	
		ObservableList<String> opTipoPago = FXCollections.observableArrayList("Mensual","Semestral");
		tipoPago.setItems(opTipoPago);
		
    	errorFechaInicioVigencia1.setVisible(false);
    	errorFechaInicioVigencia2.setVisible(false);
    	errorFechaInicioVigencia3.setVisible(false);
    	errorTipoPago.setVisible(false);
    	errorTipoCobertura.setVisible(false);
		
	}
	
	
	
}
