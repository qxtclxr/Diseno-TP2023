package tp.gui.altapoliza;

import tp.app.App;
import tp.dto.*;
import tp.entidad.*;
import tp.gui.buscarcliente.BuscarClienteController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class DeclararHijosController {
	
	@FXML 
	private Label errorSexo;
	@FXML
	private Label errorEstadoCivil;
	@FXML
	private Label errorFechaNacimiento;
	
	
	@FXML
	private ComboBox sexo;
	@FXML
	private ComboBox estadoCivil;
	@FXML
	private Button agregarHijo;
	@FXML 
	private DatePicker fechaNacimiento;
	
	private List<HijoDeclaradoDTO> listaHijos;
	
	
	public void setListaHijos(list<HijoDeclaradoDTO> l) {
		listaHijos = l;
	}
	
	@FXML
	public void volverAtrasClicked( ActionEvent action ) throws IOException{
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../altapoliza/AltaPolizaFormularioPoliza.fxml"));
    	AnchorPane form = loader.load();
    	
    	AltaPolizaFormularioPolizaController formularioPolizaC = loader.getController();
    	formularioPolizaC.setHijosDeclarados(this.listaHijos);
    	formularioPolizaC.mostrarDatosPoliza();
    	
    	App.switchScreenTo(form);
	}
	
	
	
	@FXML
	public void initialize() {
		
		ObservableList<String> opEstadoCivil = FXCollections.observableArrayList("Casado/a","Soltero/a","Viudo/a","Divorciado/a");
		ObservableList<String> opSexo = FXCollections.observableArrayList("Hombre", "Mujer");

		sexo.setItems(opSexo);
		estadoCivil.setItems(opEstadoCivil);
		
		

	}
	
	private EstadoCivil getValueEstadoCivil() {
		
		if(estadoCivil.getValue().toString().equals("Casado/a")) {
			return EstadoCivil.CASADO;
		}
		else if(estadoCivil.getValue().toString().equals("Soltero/a") ) {
			return EstadoCivil.SOLTERO;
		}
		else if(estadoCivil.getValue().toString().equals("Viudo/a")) {
			return EstadoCivil.VIUDO;
		}else if(estadoCivil.getValue().toString().equals("Divorciado/a")) {
			return EstadoCivil.DIVORCIADO;
		}
		
	}
	
	private Sexo getValueSexo( ) {
		if(sexo.getValue().toString().equals("Hombre")) {
			return Sexo.MASCULINO;
		}
		else if(sexo.getValue().toString().equals("Mujer")){
			return Sexo.FEMENINO;
		}
	}
	
	
	private void addHijo( ) {
	
		HijoDeclaradoDTO hijo = new HijoDeclaradoDTO();
		hijo.setFechaNacimiento(fechaNacimiento.getValue());
		hijo.setEstadoCivil( this.getValueEstadoCivil() );
		hijo.setSexo(this.getValueSexo() );
		
		listaHijos.add(hijo);
		
	}
	
	
	@FXML
	public void agregarHijoClicked() {
		this.validarDatos();
		this.addHijo();
	}
	@FXML
	public void validarDatos() {
		
	    if (sexo.getValue() == null || sexo.getValue().toString().isEmpty()) {
	        errorSexo.setVisible(true);
	    } else {
	        errorSexo.setVisible(false);
	    }

	    if (estadoCivil.getValue() == null || estadoCivil.getValue().toString().isEmpty()) {
	        errorEstadoCivil.setVisible(true);
	    } else {
	        errorEstadoCivil.setVisible(false);
	    }

	    if (fechaNacimiento.getValue() == null || !(Period.between(fechaNacimiento.getValue(), LocalDate.now()).getYears() >= 18 && Period.between(fechaNacimiento.getValue(), LocalDate.now()).getYears() <= 30)) {
	        errorFechaNacimiento.setVisible(true);
	    } else {
	        errorFechaNacimiento.setVisible(false);
	    }
	    
	}

	
	
}
