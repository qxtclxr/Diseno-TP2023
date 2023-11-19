package tp.gui.altapoliza;

import java.util.ArrayList;
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
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import tp.app.App;

public class AltaPolizaFormularioPolizaController implements Initializable{
	
	
	
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
	private TextFlow sumaAsegurada;
	
	@FXML
	private Button declararHijos;
	@FXML 
	private ComboBox localidad;
	@FXML
	private ComboBox provincia;
	@FXML 
	private ComboBox marca;
	@FXML
	private ComboBox modelo;
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
	 private Label errorFaltaProvincia;
	 @FXML
	 private Label errorFaltaModelo;
	 @FXML
	 private Label errorFaltaMarca;
	 
	
	 
	public void mostrarDatosPoliza( ) {
		
		motor.setText(poliza.getVehiculo().getMotor());
		chasis.setText(poliza.getVehiculo().getChasis());
		patente.setText(poliza.getVehiculo().getPatente());
		localidad.setValue(poliza.getLocalidad().getNombre());
		provincia.setValue(poliza.getLocalidad().getProvincia().getNombre());
		marca.setValue(poliza.getVehiculo().getModelo().getModelo().getMarca());
		modelo.setValue(poliza.getVehiculo().getModelo().getModelo().getNombre());
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
		AltaPolizaFormularioCoberturaController formularioCoberturaC = new AltaPolizaFormularioCoberturaController();
		formularioCoberturaC.setPolizaDTO(this.poliza);
		loader.setController(formularioCoberturaC);
		loader.setLocation(getClass().getResource("../altapoliza/AltaPolizaFormularioCobertura.fxml"));
    	AnchorPane form = loader.load();
    	//formularioCoberturaC = loader.getController();
    	
    	App.switchScreenTo(form);
    	
		}
    	
    	
	}
	
	@FXML 
	private void volverAtrasClicked( ActionEvent action ) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		BuscarClienteController buscarClienteC = new BuscarClienteController();
		buscarClienteC.setClienteDTO(this.poliza.getCliente());
    	loader.setLocation(getClass().getResource("../buscarcliente/BuscarCliente.fxml"));
    	AnchorPane form = loader.load();
    	buscarClienteC = loader.getController();
    	
    	App.switchScreenTo(form);
    	
	}
	
	public void setHijosDeclarados(List<HijoDeclaradoDTO> l) {
		poliza.setHijosDeclarados(l);
	}

	
	@FXML
	private void declararHijosClicked(ActionEvent action) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		DeclararHijosController declararHijosC = new DeclararHijosController();
		declararHijosC.setListaHijos(this.poliza.getHijosDeclarados());
    	loader.setLocation(getClass().getResource("../altapoliza/DeclararHijos.fxml"));
    	AnchorPane form = loader.load();
    	declararHijosC = loader.getController();
    	
    	App.switchScreenTo(form);
		
		
		
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
	
	
	private void setProvincia() {
		
		try {
		
		
		PaisDTO pais = new PaisDTO();
		pais.setId(Long.valueOf(1));
		ObservableList<String> opProvincia = FXCollections.observableArrayList(GestorLocalizacion.getProvinciasByPais(pais).stream().
				map(ProvinciaDTO::getText).
				toList());
		provincia.setItems(opProvincia);
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void setLocalidades() {
		
		try {
		
		if(provincia.getValue()!=null) {
		
		errorFaltaProvincia.setVisible(false);	
			
		GestorLocalizacion g = new GestorLocalizacion();
		ProvinciaDTO p = new ProvinciaDTO();
		p.setId(Long.valueOf(1));
		ObservableList<String> opLocalidad = FXCollections.observableArrayList(GestorLocalizacion.getLocalidadesByProvincia(p).stream().
				map(LocalidadDTO::getText).
				toList());
		localidad.setItems(opLocalidad);
		
		}
		else {
			errorFaltaProvincia.setVisible(true);
		}
		
		}
		catch(Exception e){
				e.printStackTrace();
		}
		
	}	

	
	private void setMarcas( ) {
		
		ArrayList<MarcaDTO> marcas = (ArrayList<MarcaDTO>) GestorVehiculo.getAllMarcaDTOs();
		
		ObservableList<String> op = FXCollections.observableArrayList(marcas.stream().
																			map(MarcaDTO::getNombre).
																			toList());
		marca.setItems(op);

	}
	
	@FXML
	private void setModelos() {
		
		try {
		
		if(marca.getValue()!=null) {
			errorFaltaMarca.setVisible(false);
			MarcaDTO marcaD = new MarcaDTO();
			marcaD.setNombre(marca.getValue().toString());
			
			ObservableList<String> op = FXCollections.observableArrayList(GestorVehiculo.getModelosByMarca(marcaD).stream().
					map(ModeloDTO::getNombre).
					toList());			
			
			modelo.setItems(op);
			
		}
		else {
			errorFaltaMarca.setVisible(false);
		}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void setAnios()  {
		
		try {
		
		if(modelo.getValue()!=null) {
			errorFaltaModelo.setVisible(false);
			
			ModeloDTO modeloD = new ModeloDTO();
			modeloD.setNombre(modelo.getValue().toString());
			
			
			ObservableList<String> op = FXCollections.observableArrayList(
				    GestorVehiculo.getAniosByModelo(modeloD).stream()
				        .map(anioModeloDTO -> Objects.toString(anioModeloDTO.getAnio(), ""))
				        .collect(Collectors.toList())
				);

			anio.setItems(op);
			
			
			
		} else {
			errorFaltaModelo.setVisible(true);
		}
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	/*
	private void setSumaAsegurada() {
	    try {
	        if (anio.getValue() != null && modelo.getValue() != null && marca.getValue() != null) {
	            MarcaDTO marcaD = new MarcaDTO();
	            marcaD.setNombre(marca.getValue().toString());

	            
	            ModeloDTO modelo1 = GestorVehiculo.getModelosByMarca(marcaD)
	                    .stream()
	                    .filter(p -> p.getNombre().equals(modelo.getValue().toString()))
	                    .collect(Collectors.toList()) 
	                    .get(0);
	      

	            
	            
	            //sumaAsegurada.setValue(valoracion);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	 */
	
	
	
	private void setContadorHijosDeclarados() {
		//saca de alguna manera de la base de datos cuantos hijos declarados tiene
		int hijosDeclarados = 0;
		contadorHijosDeclarados.setText("Hijos declarados: "+ hijosDeclarados );
	}
	
	
	private boolean motorYaExiste(String motor) {
		
		return GestorVehiculo.existeMotor(motor);
	}
	
	private boolean chasisYaExiste(String chasis) {
		return GestorVehiculo.existeChasis(chasis);
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
		this.modelo.setValue("Focus");
		this.anio.setValue("2015");
		this.chasis.setText("12345678A90");
		this.patente.setText("ABC123");
		this.kmsRealizadosPorAnio.setValue("123568");
		this.nroDeSiniestrosUltAnio.setValue("dos");
		
		
	}
		
	private void cargarDatosFormulario() {
		
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
		modeloD.setNombre(modelo.getValue().toString());
		marcaD.setNombre(marca.getValue().toString());
		modeloD.setMarca(marcaD);
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
	    if((marca.getValue()==null || modelo.getValue()==null|| anio.getValue()==null)
	    		|| (marca.getValue().toString().isEmpty() || modelo.getValue().toString().isEmpty() || anio.getValue().toString().isEmpty())) {
	    	
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


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		this.mostrarCliente();
		
		this.setErroresFalse();
		
		this.setMarcas();
		
		this.setProvincia();
		
		this.setContadorHijosDeclarados();
	
		this.testDatosFormulario(); // deberia ser reeplazo por una funcion que tome los valores de poliza 
		
		this.cargarDatosFormulario();
		
		ObservableList<String> opKmsRealizadosPorAnio = FXCollections.observableArrayList("Hasta 10000 Km","Hasta 20000 Km", "Hasta 30000 Km","Hasta 40000 Km", "Mas de 40000 Km");
		kmsRealizadosPorAnio.setItems(opKmsRealizadosPorAnio);
		
		ObservableList<String> opNroSiniestrosUltAnio = FXCollections.observableArrayList("Ninguno", "1", "2", "Mas de 2");
		nroDeSiniestrosUltAnio.setItems(opNroSiniestrosUltAnio);
	}

		
	
}
