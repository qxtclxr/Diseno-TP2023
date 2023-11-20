package tp.gui.altapoliza;

import tp.dto.*;
import tp.logica.*;
import tp.gui.buscarcliente.BuscarClienteController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tp.app.App;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class AltaPolizaFormularioPolizaController implements Initializable{
	
	
	
	private PolizaDTO poliza = new PolizaDTO();
	
	private Map<CheckBox,MedidaDeSeguridadDTO> medidasMap = new HashMap<>();
	
	private List<HijoDeclaradoDTO> hijosDeclarados = new ArrayList<>();
	
	@FXML
	private VBox medidasBox;	
	@FXML
	private Text columnaCliente;
	@FXML
	private Text columnaNombre;
	@FXML
	private Text columnaApellido;
	@FXML
	private Text columnaTipoDoc;
	@FXML
	private Text columnaNroDoc;
	@FXML
	private Text columnaDirec;
	
	@FXML
	private Text sumaAsegurada;
	
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
	 
	public void setMedidas() {
		List<MedidaDeSeguridadDTO> medidas = GestorMedidaDeSeguridad.getAllDTOs();
		for(MedidaDeSeguridadDTO medida : medidas) {
			CheckBox cb = new CheckBox(medida.getText());
			cb.setWrapText(true);
			VBox.setMargin(cb,new Insets(0, 0, 0, 5));// top, right, bottom, left
			medidasBox.getChildren().add(cb);
			medidasMap.put(cb, medida);
		}
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
	    	formularioCoberturaC.setActual(form);
	    	formularioCoberturaC.setAnterior(actual);
	    	App.switchScreenTo(form);
		}
	}
	
	@FXML 
	private void volverAtrasClicked( ActionEvent action ) throws IOException {
    	App.switchScreenTo(anterior);
    	
	}
	
	//PROBAMOS - TECHO
	
	public void actualizarDatos(){
		this.motor.setText("SANTI123456");
		this.chasis.setText(poliza.getVehiculo().getChasis());
	}
	///PROBAMOS - PISO	
	
	public void setHijosDeclarados(List<HijoDeclaradoDTO> l) {
		poliza.setHijosDeclarados(l);
		this.setContadorHijosDeclarados();
	}
	/* ESTA ES LA VERSION VIEJA DE LO QUE ESTA ABAJO
	@FXML
	private void declararHijosClicked(ActionEvent action) throws IOException {
	    FXMLLoader loader = new FXMLLoader();
	    DeclararHijosController declararHijosC = new DeclararHijosController();
	    declararHijosC.setListaHijos(this.poliza.getHijosDeclarados());
	    loader.setController(declararHijosC);
	    loader.setLocation(getClass().getResource("../altapoliza/DeclararHijos.fxml"));

	    // Cargar el formulario en un AnchorPane
	    AnchorPane form = loader.load();

	    // Crear un nuevo Stage (ventana) para mostrar el formulario como modal
	    Stage modalStage = new Stage();
	    modalStage.initModality(Modality.APPLICATION_MODAL);
	    modalStage.setTitle("Declarar Hijos");

	    // Configurar el formulario en la nueva ventana modal
	    Scene scene = new Scene(form);
	    modalStage.setScene(scene);
	    
	    // Mostrar la ventana modal y esperar hasta que se cierre
	    modalStage.showAndWait();
	}*/
	
	@FXML
	private AnchorPane rootPane; // Referencia al pane principal de AltaPolizaFormularioPoliza.fxml

	private AnchorPane actual;

	private AnchorPane anterior;

	@FXML
	private void declararHijosClicked(ActionEvent action) throws IOException {
	    FXMLLoader loader = new FXMLLoader();
	    DeclararHijosController declararHijosC = new DeclararHijosController();
	    declararHijosC.setListaHijos(this.poliza.getHijosDeclarados(), this);
	    loader.setController(declararHijosC);
	    loader.setLocation(getClass().getResource("../altapoliza/DeclararHijos.fxml"));

	    // Cargar el formulario en un AnchorPane
	    AnchorPane form = loader.load();

	    // Crear un nuevo Stage (ventana) para mostrar el formulario como modal
	    Stage modalStage = new Stage();
	    modalStage.initModality(Modality.APPLICATION_MODAL);
	    modalStage.setTitle("Declarar Hijos");

	    // Configurar el formulario en la nueva ventana modal
	    Scene scene = new Scene(form);

	    // Crear un Pane transparente para oscurecer el fondo
	    Pane overlay = new Pane();
	    overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);"); // Color negro con opacidad del 50%
	    overlay.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
	    rootPane.getChildren().add(overlay);

	    modalStage.setScene(scene);

	    // Mostrar la ventana modal y esperar hasta que se cierre
	    modalStage.setOnHidden(event -> rootPane.getChildren().remove(overlay)); // Remover el overlay cuando se cierra la ventana modal
	    modalStage.showAndWait();
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
		sumaAsegurada.setText(null);
		AnioModeloDTO anioSelect = anio.getValue();
		if(anioSelect!=null) {
			sumaAsegurada.setText(String.valueOf(anioSelect.getValoracion()));
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
	
	private void setListaHijosDeclarados(){
		List<HijoDeclaradoDTO> a = new ArrayList<HijoDeclaradoDTO>();
		poliza.setHijosDeclarados(a);
		this.setContadorHijosDeclarados();
	}
	
	
	private void setContadorHijosDeclarados() {
		contadorHijosDeclarados.setText("Hijos Declarados: " + poliza.getHijosDeclarados().size());
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
		
		if (patente == null || patente.isEmpty()) {
			return true;
		}
		
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
		poliza.setKmRealizados(this.kmsRealizadosPorAnio.getValue());
		poliza.setCantidadSiniestros(this.nroDeSiniestrosUltAnio.getValue());
		
		poliza.setMedidas(medidasMap.entrySet().stream().
				filter(entry -> entry.getKey().isSelected()).
				map(entry -> entry.getValue()).
				toList());
		
		poliza.setHijosDeclarados(hijosDeclarados);
		
	}
	
	public void mostrarCliente( ) {
		ClienteDTO cliente = poliza.getCliente();
		columnaCliente.setText(cliente.getNroCliente());
		columnaNombre.setText(cliente.getNombre());
		columnaApellido.setText(cliente.getApellido());
		columnaTipoDoc.setText(cliente.getTipoDocumento().toString());
		columnaNroDoc.setText(cliente.getNroDocumento());
		columnaDirec.setText(cliente.getDomicilio().toString());		
	}
	
	public boolean validarDatos( ) {
		
		boolean datosValidos = true;
		
		
		//Comprueba domicilio de riesgo - Pruebo si se pone de rojo el fondo en la segunda linea del if
	    if ((provincia.getValue() == null || localidad.getValue()==null) || localidad.getValue().toString().isEmpty() || provincia.getValue().toString().isEmpty()) {
	        errorDomicilioRiesgo.setVisible(true);
	        //rojoDomicilioRiesgo.setOpacity(0.1);
	        provincia.setStyle("-fx-background-color: #fa8e8e;");
	        localidad.setStyle("-fx-background-color: #fa8e8e;");
	        datosValidos = false;
	    } else {
	       errorDomicilioRiesgo.setVisible(false);
	        provincia.setStyle("-fx-background-color: white;");
	        localidad.setStyle("-fx-background-color: white;");

	    }
	    
	    //Comprueba marca vehiculo y anio
	    if((marca.getValue()==null || modelo.getValue()==null|| anio.getValue()==null)
	    		|| (marca.getValue().toString().isEmpty() || modelo.getValue().toString().isEmpty() || anio.getValue().toString().isEmpty())) {
	    	
	    	errorMarcaVehiculoAnio.setVisible(true);
	    	//rojoMarca.setOpacity(0.1);
	    	marca.setStyle("-fx-background-color: #fa8e8e;");
	    	modelo.setStyle("-fx-background-color: #fa8e8e;");
	    	anio.setStyle("-fx-background-color: #fa8e8e;");
	    	datosValidos = false;
	    }
	    else {
	    	errorMarcaVehiculoAnio.setVisible(false);
	    	marca.setStyle("-fx-background-color: white;");
	    	modelo.setStyle("-fx-background-color: white;");
	    	anio.setStyle("-fx-background-color: white;");
	    }
	    
	    //comprueba nroDeSiniestrosUltAnio
	    
	    if(nroDeSiniestrosUltAnio.getValue()==null || nroDeSiniestrosUltAnio.getValue().toString().isEmpty() ) {
	    	errorNroDeSiniestrosUltAnio.setVisible(true);
	    	//rojoNroSiniestro.setOpacity(0.1);
	    	nroDeSiniestrosUltAnio.setStyle("-fx-background-color: #fa8e8e;");
	    	datosValidos = false;
	    }else {
	    	errorNroDeSiniestrosUltAnio.setVisible(false);
	    	nroDeSiniestrosUltAnio.setStyle("-fx-background-color: white;");
	    }
		
	    //comprueba KmsRealizadosPorAnio
	    
	    if(kmsRealizadosPorAnio.getValue()==null || kmsRealizadosPorAnio.getValue().toString().isEmpty() ) {
	    	errorKmsRealizadosPorAnio.setVisible(true);
	    	//rojoKM.setOpacity(0.1);
	    	kmsRealizadosPorAnio.setStyle("-fx-background-color: #fa8e8e;");
	    	datosValidos = false;
	    }else {
	    	errorKmsRealizadosPorAnio.setVisible(false);
	    	kmsRealizadosPorAnio.setStyle("-fx-background-color: white;");
	    }
	    
	    //comprueba el motor
	    
	    if(motor.getText() == null) {
	    	errorFormatoMotor.setVisible(true);
	    	//rojoMotor.setOpacity(0.1);
	    	motor.setStyle("-fx-background-color: #fa8e8e;");
	    	datosValidos = false;
	    }else {
	    	errorFormatoMotor.setVisible(false);
	    	
	    	if(this.motorYaExiste(motor.getText())){
	    		errorMotorYaExiste.setVisible(true);
	    		//rojoMotor.setOpacity(0.1);
	    		motor.setStyle("-fx-background-color: #fa8e8e;");
	    		datosValidos = false;
	    	}
	    	else {
	    		errorMotorYaExiste.setVisible(false);
	    	}
	    	
	    	if(!this.motorFormatoCorrecto(motor.getText())) {
	    		errorFormatoMotor.setVisible(true);
	    		//rojoMotor.setOpacity(0.1);
	    		motor.setStyle("-fx-background-color: #fa8e8e;");
	    		datosValidos = false;
	    	}else {
	    		errorFormatoMotor.setVisible(false);
	    		motor.setStyle("-fx-background-color: white;");
	    	}
	    	
	    	//comprueba el chasis
	    	
		    if(chasis.getText() == null) {
		    	errorFormatoChasis.setVisible(true);
		    	chasis.setStyle("-fx-background-color: #fa8e8e;");
		    	//rojoChasis.setOpacity(0.1);
		    	datosValidos = false;
		    }else {
		    	errorFormatoChasis.setVisible(false);
		    	chasis.setStyle("-fx-background-color: white;");
		    	
		    	if(this.chasisYaExiste(chasis.getText())){
		    		errorChasisYaExiste.setVisible(true);
		    		//rojoChasis.setOpacity(0.1);
		    		chasis.setStyle("-fx-background-color: #fa8e8e;");
		    		datosValidos = false;
		    	}
		    	else {
		    		errorChasisYaExiste.setVisible(false);
		    		chasis.setStyle("-fx-background-color: white;");
		    	}
		    	
		    	if(!this.chasisFormatoCorrecto(chasis.getText())) {
		    		errorFormatoChasis.setVisible(true);
		    		//rojoChasis.setOpacity(0.1);
		    		chasis.setStyle("-fx-background-color: #fa8e8e;");
		    		datosValidos = false;
		    	}else {
		    		errorFormatoChasis.setVisible(false);
		    		chasis.setStyle("-fx-background-color: white;");
		    	}
		    	
	    	
	    	//comprueba la patente - Prueba de setear color rojo el fondo
	    	
	    	if(patente.getText()==null) {
	    		errorFormatoPatente.setVisible(true);
	    		//rojoPatente.setOpacity(0.1);
	    		patente.setStyle("-fx-background-color: #fa8e8e;");
	    		datosValidos = false;
	    	}
	    	else {
	    		if(!this.patenteFormatoCorrecto(patente.getText())) {
		    		errorFormatoPatente.setVisible(true);
		    		//rojoPatente.setOpacity(0.1);
		    		patente.setStyle("-fx-background-color: #fa8e8e;");
		    		datosValidos = false;
	    		}else {
	    			errorFormatoPatente.setVisible(false);
		    		patente.setStyle("-fx-background-color: white;");

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
	
	public void onActionProvincia() {
		localidad.getSelectionModel().clearSelection();
		localidad.setDisable(false);
	}
	
	public void onActionMarca() {
		modelo.getSelectionModel().clearSelection();
		modelo.setDisable(false);
	}
	
	public void onActionModelo() {
		anio.getSelectionModel().clearSelection();
		anio.setDisable(false);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.mostrarCliente();
		
		this.setErroresFalse();
		
		this.setMarcas();
		
		this.setProvincia();
    
		this.setMedidas();

		this.setListaHijosDeclarados();
		
		//this.setContadorHijosDeclarados();
		
		this.setKms();
		
		this.setSiniestros();
	
		//this.testDatosFormulario(); // deberia ser reeplazo por una funcion que tome los valores de poliza 
		
		//this.cargarDatosFormulario();
		
	}

	public void setActual(AnchorPane form) {
		this.actual = form;
	}

	public void setAnterior(AnchorPane form) {
		this.anterior = form;
	}

		
	
}
