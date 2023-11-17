package tp.gui.altapoliza;


import java.time.LocalDate;
import java.time.Period;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

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

	@FXML
	public void initialize() {
		
		ObservableList<String> opEstadoCivil = FXCollections.observableArrayList("Casado/a","Soltero/a","Viudo/a","Divorciado/a");
		ObservableList<String> opSexo = FXCollections.observableArrayList("Hombre", "Mujer");

		sexo.setItems(opSexo);
		estadoCivil.setItems(opEstadoCivil);
		
		

	}
	
	@FXML
	public void agregarHijoClicked() {
		this.validarDatos();
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
