package tp.gui.altapoliza;

import tp.gui.buscarcliente.*;
import tp.dto.*;
import tp.logica.*;
import tp.entidad.*;
import tp.exception.ObjetoNoEncontradoException;
import tp.gui.buscarcliente.BuscarClienteController;
import javafx.scene.control.Button;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
	
	
	
	private PolizaDTO poliza = new PolizaDTO();
	
	@FXML
	private TableColumn nroCliente;
	@FXML
	private TableColumn apellido;
	@FXML
	private TableColumn nombre;
	@FXML
	private TableColumn tipoDni;
	
	@FXML
	private TableColumn nroDocumento;
	@FXML
	private TableColumn direccion;
	
	
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
	
	 
	public void mostrarDatosPoliza( ) {
		
		motor.setText(poliza.getVehiculo().getMotor());
		chasis.setText(poliza.getVehiculo().getChasis());
		patente.setText(poliza.getVehiculo().getPatente());
		localidad.setValue(poliza.getLocalidad().getNombre());
		provincia.setValue(poliza.getLocalidad().getProvincia().getNombre());
		marca.setValue(poliza.getVehiculo().getModelo().getModelo().getMarca());
		vehiculo.setValue(poliza.getVehiculo().getModelo().getModelo().getNombre());
		anio.setValue(Integer.toString(poliza.getVehiculo().getModelo().getAnio()));
		kmsRealizadosPorAnio.setValue(poliza.getKmRealizados().getNombre());
		nroDeSiniestrosUltAnio.setValue(poliza.getCantidadSiniestros().getNombre());
		
		
	}
	 
	 
	public void setClienteDTO(ClienteDTO clienteDTO) throws IOException {
		poliza.setCliente(clienteDTO);
		
	}
	
	public void setPolizaDTO(PolizaDTO poliza) {
		this.poliza = poliza;
	}
	
	@FXML
	private void confirmarClicked(ActionEvent action) throws IOException {
		
		if(this.validarDatos()) {
			
		this.cargarDatosFormulario();
		
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../altapoliza/AltaPolizaFormularioCobertura.fxml"));
   
    	AnchorPane form = loader.load();
    	
    	AltaPolizaFormularioCoberturaController formularioCoberturaC = loader.getController();
    	
    	formularioCoberturaC.setPolizaDTO(this.poliza);
    	
    	App.switchScreenTo(form);
    	
		}
    	
    	
	}
	
	@FXML 
	private void volverAtrasClicked( ActionEvent action ) throws IOException {
		
		
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../buscarcliente/BuscarCliente.fxml"));
    	AnchorPane form = loader.load();
    	
    	BuscarClienteController buscarClienteC = loader.getController();
    	buscarClienteC.setClienteDTO(poliza.getCliente());
    	//TODO: buscarClienteC.mostrarDatos();
    	
    	App.switchScreenTo(form);
    	
	}
	
	public void setHijosDeclarados(List<HijoDeclaradoDTO> l) {
		poliza.setHijosDeclarados(l);
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
	    
	    	    
	    DeclararHijosController declararHijosC = loader.getController();
	    //TODO: declararHijosC.setListaHijos(poliza.getHijosDeclarados());


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
	@FXML
	private void setProvincia() throws ObjetoNoEncontradoException {
		PaisDTO pais = new PaisDTO();
		pais.setId(Long.valueOf(1));
		ObservableList<String> opProvincia = FXCollections.observableArrayList(GestorLocalizacion.getProvinciasByPais(pais).stream().
				map(ProvinciaDTO::getText).
				toList());
		provincia.setItems(opProvincia);
	}
	
	@FXML
	private void setLocalidades() throws ObjetoNoEncontradoException {
		GestorLocalizacion g = new GestorLocalizacion();
		ProvinciaDTO p = new ProvinciaDTO();
		p.setId(Long.valueOf(1));
		ObservableList<String> opLocalidad = FXCollections.observableArrayList(GestorLocalizacion.getLocalidadesByProvincia(p).stream().
				map(LocalidadDTO::getText).
				toList());
		localidad.setItems(opLocalidad);
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

        // Definir el patrón de la expresión regular para el formato de motor de Ford
        String patron = "^[A-Z0-9]{10,12}$";

        // Compilar el patrón
        Pattern pattern = Pattern.compile(patron);

        // Crear un objeto Matcher
        Matcher matcher = pattern.matcher(motor);

        // Verificar si el número de motor cumple con el formato
        return matcher.matches();
		
		
		
	}
	
	
	private boolean chasisFormatoCorrecto(String chasis) {
        // Definir el patrón de la expresión regular para el formato de chasis
        String patron = "^[A-Za-z0-9]{8,17}$";

        // Compilar el patrón
        Pattern pattern = Pattern.compile(patron);

        // Crear un objeto Matcher
        Matcher matcher = pattern.matcher(chasis);

        // Verificar si el chasis cumple con el formato
        return matcher.matches();
	}
	
	
	private boolean patenteFormatoCorrecto(String patente) {
		
	    // Definir el patrón de la expresión regular para el formato de patente
        String patron = "^[A-Z]{2,3}\\s?\\d{3}\\s?[A-Z]{0,3}$";

        // Compilar el patrón
        Pattern pattern = Pattern.compile(patron);

        // Crear un objeto Matcher
        Matcher matcher = pattern.matcher(patente);

        // Verificar si la patente cumple con el formato
        return matcher.matches();
		
		
		
	}
	
	private void testDatosFormulario( ) { 
		
		//Borrar esto antes de entregar 
		
		this.provincia.setValue("Santa Fe");
		this.localidad.setValue("Esperanza");
		this.motor.setText("A1S2D3F4G5");
		this.marca.setValue("Ford");
		this.vehiculo.setValue("Focus");
		this.anio.setValue("2015");
		this.chasis.setText("12345678A90");
		this.patente.setText("ABC123");
		this.kmsRealizadosPorAnio.setValue("123568");
		this.nroDeSiniestrosUltAnio.setValue("dos");
		
		
	}
		
	private void cargarDatosFormulario() {
		
		AnioModeloDTO anioModelo = new AnioModeloDTO();
		VehiculoDTO vehiculoD = new VehiculoDTO();
		AnioModeloDTO anioD = new AnioModeloDTO();
		ModeloDTO modeloD = new ModeloDTO();
		MarcaDTO marcaD = new MarcaDTO();
		RangoKMRealizadosDTO rangoD = new RangoKMRealizadosDTO(); 
		RangoCantSiniestrosDTO rangoCsD = new RangoCantSiniestrosDTO();
		LocalidadDTO localidadD = new LocalidadDTO();
		ProvinciaDTO provinciaD = new ProvinciaDTO();
		PaisDTO paisD = new PaisDTO();
		
		vehiculoD.setChasis(chasis.getText());
		vehiculoD.setMotor(motor.getText());
		vehiculoD.setPatente(patente.getText());
		anioD.setAnio( Integer.parseInt(anio.getValue().toString()) );
		anioD.setModelo(modeloD);
		modeloD.setNombre(vehiculo.getValue().toString());
		marcaD.setNombre(marca.getValue().toString());
		vehiculoD.setModelo(anioD);
		rangoD.setNombre(kmsRealizadosPorAnio.getValue().toString());
		rangoCsD.setNombre(nroDeSiniestrosUltAnio.getValue().toString());
		paisD.setNombre("Argentina");
		provinciaD.setNombre(provincia.getValue().toString());
		provinciaD.setPais(paisD);
		localidadD.setNombre(localidad.getValue().toString());
		localidadD.setProvincia(provinciaD);
		poliza.setCantidadSiniestros(rangoCsD);
		poliza.setKmRealizados(rangoD);
		poliza.setVehiculo(vehiculoD);
		poliza.setLocalidad(localidadD);
		
		
	}
	
	public void mostrarCliente( ) {
		
		nroCliente.setText(poliza.getCliente().getNroCliente());
		nombre.setText(poliza.getCliente().getNombre());
		apellido.setText(poliza.getCliente().getApellido());
		tipoDni.setText(poliza.getCliente().getTipoDocumento().toString());
		nroDocumento.setText(poliza.getCliente().getNroDocumento());
		//direccion.setText(poliza.getCliente().);
		
	}
	
	
	@FXML
	public void initialize( ) {
		
		this.mostrarCliente();
		
		this.setErroresFalse();
		
		this.setMarcaVehiculoAnio();
		
		this.setContadorHijosDeclarados();
	
		this.testDatosFormulario(); // deberia ser reeplazo por una funcion que tome los valores de poliza 
		
		this.cargarDatosFormulario();
		
		ObservableList<String> opKmsRealizadosPorAnio = FXCollections.observableArrayList("Hasta 10000 Km","Hasta 20000 Km", "Hasta 30000 Km","Hasta 40000 Km", "Mas de 40000 Km");
		kmsRealizadosPorAnio.setItems(opKmsRealizadosPorAnio);
		
		ObservableList<String> opNroSiniestrosUltAnio = FXCollections.observableArrayList("Ninguno", "1", "2", "Mas de 2");
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
