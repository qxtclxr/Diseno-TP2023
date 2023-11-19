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
import javafx.scene.text.Text;
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
	private ComboBox<LocalidadDTO> localidad;
	@FXML
	private ComboBox<ProvinciaDTO> provincia;
	@FXML 
	private ComboBox<MarcaDTO> marca;
	@FXML
	private ComboBox<ModeloDTO> modelo;
	@FXML
	private ComboBox<AnioModeloDTO> anio;
	@FXML
	private ComboBox<RangoKMRealizadosDTO> kmsRealizadosPorAnio;
	@FXML
	private ComboBox<RangoCantSiniestrosDTO> nroDeSiniestrosUltAnio;
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
	 
	
	/* 
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
	*/
	 
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
			PaisDTO paisSelect = GestorLocalizacion.getPaisDTOByNombre("Argentina");
			ObservableList<ProvinciaDTO> opProvincia = FXCollections.observableArrayList(GestorLocalizacion.getProvinciasByPais(paisSelect));
			provincia.setItems(opProvincia);		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void setLocalidades() {
		
		try {
			ProvinciaDTO provSelect = provincia.getValue();
			if(provSelect != null) {
				errorFaltaProvincia.setVisible(false);
				ObservableList<LocalidadDTO> opLocalidad = FXCollections.observableArrayList(GestorLocalizacion.getLocalidadesByProvincia(provSelect));
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
		
		List<MarcaDTO> marcas = GestorVehiculo.getAllMarcaDTOs();
		ObservableList<MarcaDTO> op = FXCollections.observableArrayList(marcas);
		marca.setItems(op);

	}
	
	@FXML
	private void setModelos() {
		
		try {
			MarcaDTO marcaSelect = marca.getValue();
			if(marcaSelect!=null) {
				errorFaltaMarca.setVisible(false);				
				ObservableList<ModeloDTO> op = FXCollections.observableArrayList(GestorVehiculo.getModelosByMarca(marcaSelect));
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
		System.out.println("ah");
		try {
			ModeloDTO modeloSelect = modelo.getValue();
			if(modelo!=null) {
				errorFaltaModelo.setVisible(false);				
				ObservableList<AnioModeloDTO> op = FXCollections.observableArrayList(GestorVehiculo.getAniosByModelo(modeloSelect));
				anio.setItems(op);				
			} else {
				errorFaltaModelo.setVisible(true);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void setSumaAsegurada() {
		AnioModeloDTO anioSelect = anio.getValue();
		if(anioSelect!=null) {
			sumaAsegurada.getChildren().add(new Text(String.valueOf(anioSelect.getValoracion())));
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
	
	/*
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
	*/
		
	private void cargarDatosFormulario() {
		
		poliza.setLocalidad(localidad.getValue());
		
		VehiculoDTO vehiculoDto = new VehiculoDTO();
		vehiculoDto.setChasis(chasis.getText());
		vehiculoDto.setMotor(motor.getText());
		vehiculoDto.setPatente(patente.getText());
		vehiculoDto.setModelo(anio.getValue());
		poliza.setVehiculo(vehiculoDto);
		
		poliza.setSumaAsegurada(anio.getValue().getValoracion());
		//poliza.setKmRealizados(kmsRealizadosPorAnio.getValue());
		//poliza.setCantidadSiniestros(this.nroDeSiniestrosUltAnio.getValue());
		//getMedidasDeSeguridad
		
		AnioModeloDTO anioD = new AnioModeloDTO();
		ModeloDTO modeloD = new ModeloDTO();
		MarcaDTO marcaD = new MarcaDTO();
		RangoKMRealizadosDTO rangoD = new RangoKMRealizadosDTO(); 
		RangoCantSiniestrosDTO rangoCsD = new RangoCantSiniestrosDTO();
		LocalidadDTO localidadD = new LocalidadDTO();
		ProvinciaDTO provinciaD = new ProvinciaDTO();
		PaisDTO paisD = new PaisDTO();
		
		
		anioD.setAnio( Integer.parseInt(anio.getValue().toString()) );
		anioD.setModelo(modeloD);
		modeloD.setNombre(modelo.getValue().toString());
		marcaD.setNombre(marca.getValue().toString());
		modeloD.setMarca(marcaD);
		vehiculoDto.setModelo(anioD);
		rangoD.setNombre(kmsRealizadosPorAnio.getValue().toString());
		rangoCsD.setNombre(nroDeSiniestrosUltAnio.getValue().toString());
		paisD.setNombre("Argentina");
		provinciaD.setNombre(provincia.getValue().toString());
		provinciaD.setPais(paisD);
		localidadD.setNombre(localidad.getValue().toString());
		localidadD.setProvincia(provinciaD);
		poliza.setCantidadSiniestros(rangoCsD);
		poliza.setKmRealizados(rangoD);
		poliza.setVehiculo(vehiculoDto);
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

	public void setKms() {
		ObservableList<RangoKMRealizadosDTO> opKmsRealizadosPorAnio = FXCollections.observableArrayList(GestorRangoKMRealizados.getAllDTOs());
		kmsRealizadosPorAnio.setItems(opKmsRealizadosPorAnio);
	}
	
	public void setSiniestros() {
		ObservableList<RangoCantSiniestrosDTO> opNroSiniestrosUltAnio = FXCollections.observableArrayList(GestorRangoCantSiniestros.getAllDTOs());
		nroDeSiniestrosUltAnio.setItems(opNroSiniestrosUltAnio);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.mostrarCliente();
		
		this.setErroresFalse();
		
		this.setMarcas();
		
		this.setProvincia();
		
		this.setContadorHijosDeclarados();
		
		this.setKms();
		
		this.setSiniestros();
	
		//this.testDatosFormulario(); // deberia ser reeplazo por una funcion que tome los valores de poliza 
		
		//this.cargarDatosFormulario();
		
		
		

	}

		
	
}
