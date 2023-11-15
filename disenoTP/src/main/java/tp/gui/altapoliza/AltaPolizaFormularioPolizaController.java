package tp.gui.altapoliza;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import javafx.scene.Scene;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import tp.app.App;

public class AltaPolizaFormularioPolizaController {
	
	@FXML
	private Button declararHijos;
	@FXML 
	private ComboBox localidad;
	@FXML
	private ComboBox provincia;
	@FXML 
	private ComboBox marca;
	@FXML
	private ComboBox vehiculo;
	@FXML
	private ComboBox anio;
	@FXML
	private ComboBox kmsRealizadosPorAnio;
	@FXML
	private ComboBox nroDeSiniestrosUltAnio;
	@FXML
	private Label errorNroDeSiniestrosUltAnio;
	@FXML
	private TextField motor;
	@FXML
	private TextField chasis;
	@FXML
	private TextField patente;
	@FXML
	private CheckBox seGuardaEnGarage;
	@FXML
	private CheckBox tieneAlarma;
	@FXML
	private CheckBox poseeDispositivoRastreo;
	@FXML
	private CheckBox poseeTuercasAntirrobo;
	
	@FXML
	private Label errorDomicilioRiesgo;
	
	@FXML
	private Label errorMarcaVehiculoAnio;
	
	@FXML
	private Label errorMotorYaExiste;
	@FXML 
	private Label errorFormatoMotor;
	
	@FXML 
	private Label errorChasisYaExiste;
	@FXML 
	private Label errorFormatoChasis;
	
	 @FXML
	 private Label errorFormatoPatente;
	 
	 @FXML
	 private Label errorKmsRealizadosPorAnio;
	 
	 @FXML
	 private Label contadorHijosDeclarados;
	
	
	
	
	
	@FXML
	private void confirmarClicked(ActionEvent action) throws IOException {
		
		if(this.validarDatos()) {
		
		
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../altapoliza/AltaPolizaFormularioCobertura.fxml"));
    	AnchorPane form = loader.load();
    	App.switchScreenTo(form);
    	
		}
    	
    	
	}
	@FXML
	private void declararHijosClicked(ActionEvent action) throws IOException {
	    // Cargar el archivo FXML
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("../altapoliza/DeclararHijos.fxml"));
	    AnchorPane form = loader.load();

	    // Crear una nueva ventana modal
	    Stage ventanaModal = new Stage();
	    ventanaModal.initModality(Modality.APPLICATION_MODAL);
	    ventanaModal.setTitle("Declarar Hijos");

	    // Configurar el contenido de la ventana modal
	    Scene modalScene = new Scene(form);
	    ventanaModal.setScene(modalScene);

	    // Mostrar la ventana modal y esperar hasta que se cierre
	    ventanaModal.showAndWait();
	}

	
	
	private void setErroresFalse() {
		errorNroDeSiniestrosUltAnio.setVisible(false);
		errorDomicilioRiesgo.setVisible(false);
		errorMarcaVehiculoAnio.setVisible(false);
		errorMotorYaExiste.setVisible(false);
		errorFormatoMotor.setVisible(false);
		errorChasisYaExiste.setVisible(false);
		errorFormatoChasis.setVisible(false);
		errorFormatoPatente.setVisible(false);
		errorKmsRealizadosPorAnio.setVisible(false);
	}

	private void setDomicilioRiesgo() {
		
	//Traer todo de la base de datos	
		
		ObservableList<String> opProvincia = FXCollections.observableArrayList("op1","op2");
		provincia.setItems(opProvincia);
		localidad.setItems(opProvincia);
		
		
		
	}
	
	
	private void setMarcaVehiculoAnio( ) {
		
		//Traer todo de la base de datos
		
		ObservableList<String> op= FXCollections.observableArrayList("op1","op2");
		marca.setItems(op);
		vehiculo.setItems(op);
		anio.setItems(op);
		
	}
	
	
	private void setContadorHijosDeclarados() {
		//saca de alguna manera de la base de datos cuantos hijos declarados tiene
		int hijosDeclarados = 0;
		contadorHijosDeclarados.setText("Hijos declarados: "+ hijosDeclarados );
	}
	
	
	private boolean motorYaExiste(String motor) {
		
		boolean existe = false;
		//Se debe consultar a la base de datos si existe
		return existe;
	}
	
	private boolean chasisYaExiste(String chasis) {
		
		boolean existe = false;
		//Se debe consultar a la base de datos si existe
		return existe;
	}
	
	
	private boolean motorFormatoCorrecto(String motor) {
		//validamos segun formato que nos digan
		return true;
		
	}
	
	
	private boolean chasisFormatoCorrecto(String chasis) {
		//validamos segun formato que nos digan
		return true;
		
	}
	
	
	private boolean patenteFormatoCorrecto(String patente) {
		//validamos segun formato que nos digan
		return true;
		
	}
	
	
	
	@FXML
	public void initialize( ) {
		
		//this.setErroresFalse();
		
		this.setDomicilioRiesgo();
		
		this.setMarcaVehiculoAnio();
		
		this.setContadorHijosDeclarados();
		
		
		ObservableList<String> opKmsRealizadosPorAnio = FXCollections.observableArrayList("op1","op2");
		kmsRealizadosPorAnio.setItems(opKmsRealizadosPorAnio);
		
		ObservableList<String> opNroSiniestrosUltAnio = FXCollections.observableArrayList("op1","op2");
		nroDeSiniestrosUltAnio.setItems(opNroSiniestrosUltAnio);
		
		
	}
	public boolean validarDatos( ) {
		
		boolean datosValidos = true;
		
		
		//Comprueba domicilio de riesgo
	    if ((provincia.getValue() == null || localidad.getValue()==null) || localidad.getValue().toString().isEmpty() || provincia.getValue().toString().isEmpty()) {
	        errorDomicilioRiesgo.setVisible(true);
	        datosValidos = false;
	    } else {
	       errorDomicilioRiesgo.setVisible(false);
	    }
	    
	    //Comprueba marca vehiculo y anio
	    if((marca.getValue()==null || vehiculo.getValue()==null|| anio.getValue()==null)
	    		|| (marca.getValue().toString().isEmpty() || vehiculo.getValue().toString().isEmpty() || anio.getValue().toString().isEmpty())) {
	    	
	    	errorMarcaVehiculoAnio.setVisible(true);
	    	datosValidos = false;
	    }
	    else {
	    	errorMarcaVehiculoAnio.setVisible(false);
	    }
	    
	    //comprueba nroDeSiniestrosUltAnio
	    
	    if(nroDeSiniestrosUltAnio.getValue()==null || nroDeSiniestrosUltAnio.getValue().toString().isEmpty() ) {
	    	errorNroDeSiniestrosUltAnio.setVisible(true);
	    	datosValidos = false;
	    }else {
	    	errorNroDeSiniestrosUltAnio.setVisible(false);
	    }
		
	    //comprueba KmsRealizadosPorAnio
	    
	    if(kmsRealizadosPorAnio.getValue()==null || kmsRealizadosPorAnio.getValue().toString().isEmpty() ) {
	    	errorKmsRealizadosPorAnio.setVisible(true);
	    	datosValidos = false;
	    }else {
	    	errorKmsRealizadosPorAnio.setVisible(false);
	    }
	    
	    //comprueba el motor
	    
	    if(motor.getText() == null) {
	    	errorFormatoMotor.setVisible(true);
	    	datosValidos = false;
	    }else {
	    	errorFormatoMotor.setVisible(false);
	    	
	    	if(this.motorYaExiste(motor.getText())){
	    		errorMotorYaExiste.setVisible(true);
	    		datosValidos = false;
	    	}
	    	else {
	    		errorMotorYaExiste.setVisible(false);
	    	}
	    	
	    	if(!this.motorFormatoCorrecto(motor.getText())) {
	    		errorFormatoMotor.setVisible(true);
	    		datosValidos = false;
	    	}else {
	    		errorFormatoMotor.setVisible(false);
	    	}
	    	
	    	//comprueba el chasis
	    	
		    if(chasis.getText() == null) {
		    	errorFormatoChasis.setVisible(true);
		    	datosValidos = false;
		    }else {
		    	errorFormatoChasis.setVisible(false);
		    	
		    	if(this.chasisYaExiste(chasis.getText())){
		    		errorChasisYaExiste.setVisible(true);
		    		datosValidos = false;
		    	}
		    	else {
		    		errorChasisYaExiste.setVisible(false);
		    	}
		    	
		    	if(!this.chasisFormatoCorrecto(chasis.getText())) {
		    		errorFormatoChasis.setVisible(true);
		    		datosValidos = false;
		    	}else {
		    		errorFormatoChasis.setVisible(false);
		    	}
		    	
	    	
	    	//comprueba la patente
	    	
	    	if(patente.getText()==null) {
	    		errorFormatoPatente.setVisible(true);
	    		datosValidos = false;
	    	}
	    	else {
	    		if(!this.patenteFormatoCorrecto(patente.getText())) {
		    		errorFormatoPatente.setVisible(true);
		    		datosValidos = false;
	    		}else {
	    			errorFormatoPatente.setVisible(false);
	    		}
	    	}
	    	
	    	
	    	
	    }
	    
	   }
		    
		return datosValidos;
	}

	
}
