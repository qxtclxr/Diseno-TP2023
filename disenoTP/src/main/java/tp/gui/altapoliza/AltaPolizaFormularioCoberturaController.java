package tp.gui.altapoliza;
import tp.dto.*;
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
import tp.entidad.TipoPoliza;

public class AltaPolizaFormularioCoberturaController {
	
	private PolizaDTO poliza;
	
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
	@FXML
	private Label errorMayor5anios;
	
	
	public void setPolizaDTO(PolizaDTO poliza1) {
		this.poliza = poliza1;
	}
	
	
	
	public AltaPolizaFormularioCoberturaController() {
		super();
	}

	public void mostrarDatos() {
	
		this.setearTipoCobertura(poliza.getCobertura().getNombre());
		fechaInicioVigencia.setValue(poliza.getFechaInicio().toLocalDate());
		tipoPago.setValue(poliza.getTipoPoliza().toString());
		
	}
	
	
	@FXML
	private void volverAtrasClicked(ActionEvent action) throws IOException {
		
		
		FXMLLoader loader = new FXMLLoader();
    	
    	AltaPolizaFormularioPolizaController formularioPolizaC = new AltaPolizaFormularioPolizaController();
    	
    	formularioPolizaC.setPolizaDTO(this.poliza);
    	
    	loader.setController(formularioPolizaC);
    	
    	loader.setLocation(getClass().getResource("../altapoliza/AltaPolizaFormularioPoliza.fxml"));
    	AnchorPane form = loader.load();
    	//formularioPolizaC.setPolizaDTO(this.poliza);
    	//formularioPolizaC.mostrarDatosPoliza();
    	App.switchScreenTo(form);
		
		
		
	}
	
	private String getTipoCobertura( ) {
		
		if(respCivil.isSelected()) {
			return "Responsabilidad Civil";
		}
		else if(respCivilRoboIncendioTotal.isPickOnBounds() ) {
			return "Responsabilidad Civil + Robo o incendio total";
		}
		else if(todoTotal.isSelected()) {
			return "Todo total";
		}
		else if(tercerosCompletos.isSelected()) {
			return "Terceros completos";
		}
		else if(todoRiesgoConFranquicia.isSelected()) {
			return "Todo riesgo con franquicia";
		}
		
		
		return "Algo anda mal!";
		
		
	}
	
	private void setearTipoCobertura(String tipoCobertura) {
		
		if(tipoCobertura.equals("Responsabilidad Civil")) {
			respCivil.setSelected(true);
		}
		else if(tipoCobertura.equals("Responsabilidad Civil + Robo o incendio total")) {
			respCivilRoboIncendioTotal.setSelected(true);
		}
		else if(tipoCobertura.equals("Todo total") ) {
			todoTotal.setSelected(true);
		}
		else if(tipoCobertura.equals("Terceros completos")) {
			tercerosCompletos.setSelected(true);
		}
		else if(tipoCobertura.equals("Todo riesgo con franquicia") ) {
			todoRiesgoConFranquicia.setSelected(true);
		}
		
	}
	
	
	private void cargarDatosFormulario() {
		CoberturaDTO cobertura = new CoberturaDTO();
		cobertura.setNombre( this.getTipoCobertura() );
		poliza.setCobertura(cobertura);
		poliza.setTipoPoliza((tipoPago.getValue().toString().equals("Mensual"))?TipoPoliza.MENSUAL:TipoPoliza.SEMESTRAL);
		poliza.setFechaInicio(fechaInicioVigencia.getValue().atStartOfDay());	
	}


	@FXML
	private void confirmarClicked(ActionEvent action) throws IOException {
		
		if(this.validarDatos()) {
		
			this.cargarDatosFormulario();
			
			FXMLLoader loader = new FXMLLoader();
			
			AltaPolizaConfirmarController confirmar = loader.getController();
			
			confirmar.setPolizaDTO(this.poliza);
			
			loader.setController(confirmar); //chequear
			
			loader.setLocation(getClass().getResource("../altapoliza/AltaPolizaConfirmar.fxml"));
			AnchorPane form = loader.load();
			App.switchScreenTo(form);
    	
		}
	}
	@FXML
	public boolean validarDatos( ) {
		
		boolean validacionExitosa = true;
		
		if( (LocalDate.now().getYear() - poliza.getVehiculo().getModelo().getAnio() ) >= 10 && !respCivil.isSelected() ) {
			errorMayor5anios.setVisible(true);
			validacionExitosa = false;
		}
		else {
			errorMayor5anios.setVisible(false);
		}
		
		
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
