package tp.gui.altapoliza;
import tp.dto.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tp.app.App;
import tp.dto.PolizaDTO;
import tp.entidad.Cliente;
import tp.entidad.TipoPoliza;
import tp.exception.ObjetoNoEncontradoException;
import tp.logica.GestorCobertura;
import tp.logica.GestorPoliza;
import tp.logica.GestorVehiculo;

public class AltaPolizaFormularioCoberturaController {
	
	private AnchorPane actual;
	
	//private AnchorPane anterior;
	
	private PolizaDTO poliza;

	private Map<RadioButton,CoberturaDTO> coberturasMap = new HashMap<>();
	@FXML
	private VBox coberturasBox;
	@FXML
	private ToggleGroup coberturas;
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
	private ComboBox<String> tipoPago;
	@FXML 
	private Label errorTipoPago;
	@FXML
	private Label errorMayor5anios;

	@FXML
	private Text columnaCliente;
	@FXML
	private Text columnaApellido;
	@FXML
	private Text columnaNombre;
	@FXML
	private Text columnaMarca;
	@FXML
	private Text columnaModelo;
	@FXML
	private Text columnaAnio;

	private AnchorPane anterior;

	public void setPolizaDTO(PolizaDTO poliza1) {
		this.poliza = poliza1;
	}
	
	public void setActual(AnchorPane actual) {
		this.actual = actual;
	}
	
	public AltaPolizaFormularioCoberturaController() {
		super();
	}
	
	public void setCoberturas() {
		List<CoberturaDTO> dtos = GestorCobertura.getAllDTOs();
		coberturasBox.setSpacing(10);
		for(CoberturaDTO dto : dtos) {
			RadioButton rb = new RadioButton();
			coberturas.getToggles().add(rb);
			rb.setText(dto.getText());
			rb.setWrapText(true);
			coberturasBox.getChildren().add(rb);
			coberturasMap.put(rb, dto);
		}
		if(GestorVehiculo.esMayorADiezAnios(poliza.getVehiculo())) {
			coberturasMap.entrySet().stream().
			filter(entry -> !entry.getValue().getText().toUpperCase().equals("RESPONSABILIDAD CIVIL")).
			forEach(entry -> entry.getKey().setDisable(true));
		}
	}
	
	@FXML
	private void volverAtrasClicked(ActionEvent action) throws IOException {
    	App.switchScreenTo(anterior);
	}

	
	private void cargarDatosFormulario() throws ObjetoNoEncontradoException {
		poliza.setCobertura(coberturasMap.get(coberturas.getSelectedToggle()));
		poliza.setTipoPoliza((tipoPago.getValue().toString().equals("Mensual"))?TipoPoliza.MENSUAL:TipoPoliza.SEMESTRAL);
		poliza.setFechaInicio(fechaInicioVigencia.getValue());
		poliza.setFechaFin(poliza.getFechaInicio().plusMonths(1));
		this.cargarDatosSobreCobro();
	}


	@FXML
	private void confirmarClicked(ActionEvent action) throws IOException, ObjetoNoEncontradoException {
		
		if(this.validarDatos()) {
			this.cargarDatosFormulario();
			
			FXMLLoader loader = new FXMLLoader();
			
			AltaPolizaConfirmarController confirmar = new AltaPolizaConfirmarController();
			
			confirmar.setPolizaDTO(poliza);
			
			loader.setController(confirmar); //chequear
			
			loader.setLocation(getClass().getResource("../altapoliza/AltaPolizaConfirmar.fxml"));
			AnchorPane form = loader.load();
			
			confirmar.setAnterior(actual);
			
			App.switchScreenTo(form);
		}
	}
	@FXML
	public boolean validarDatos( ) {
		
		boolean validacionExitosa = true;
		
		if(!coberturasMap.entrySet().stream().anyMatch(entry -> entry.getKey().isSelected())) { 
				errorTipoCobertura.setVisible(true);
				validacionExitosa = false;
		}else {
			errorTipoCobertura.setVisible(false);
		}
		
	    if (tipoPago.getValue() == null || tipoPago.getValue().toString().isEmpty()) {
	        errorTipoPago.setVisible(true);
	        tipoPago.setStyle("-fx-control-inner-background: #fa8e8e;");
	        validacionExitosa = false;
	    } else {
	    	errorTipoPago.setVisible(false);
	        tipoPago.setStyle("-fx-control-inner-background: white;");

	    }
		
	    if(fechaInicioVigencia.getValue()==null || !(Period.between(LocalDate.now() , fechaInicioVigencia.getValue()).getDays() >= 0 && Period.between(fechaInicioVigencia.getValue(), LocalDate.now().plusMonths(1)).getDays() >= 0)) {
	    	errorFechaInicioVigencia1.setVisible(true);
	    	errorFechaInicioVigencia2.setVisible(true);
	    	errorFechaInicioVigencia3.setVisible(true);
	    	fechaInicioVigencia.setStyle("-fx-control-inner-background: #fa8e8e;");
	    	validacionExitosa = false;
	    }
	    else {
	    	errorFechaInicioVigencia1.setVisible(false);
	    	errorFechaInicioVigencia2.setVisible(false);
	    	errorFechaInicioVigencia3.setVisible(false);
	    	fechaInicioVigencia.setStyle("-fx-control-inner-background: white;");
	    }
	    
		return validacionExitosa;
		
	}
	
	@FXML
	public void cargarDatosSobreCobro() throws ObjetoNoEncontradoException{
		GestorPoliza.calcularPremioDerechoDeEmisionYDescuentos(poliza);
		poliza.setCuotas(GestorPoliza.generarCuotas(poliza));
	}
	
	@FXML
	public void mostrarResumenDeDatos() {
		ClienteDTO cliente = poliza.getCliente();
		AnioModeloDTO anioModelo = poliza.getVehiculo().getModelo();
		columnaCliente.setText(cliente.getNroCliente());
		columnaApellido.setText(cliente.getApellido());
		columnaNombre.setText(cliente.getNombre());
		columnaMarca.setText(anioModelo.getModelo().getMarca().getText());
		columnaModelo.setText(anioModelo.getModelo().getText());
		columnaAnio.setText(String.valueOf(anioModelo.getAnio()));
	}
	
	@FXML
	public void initialize() {
		
		this.setCoberturas();
		this.mostrarResumenDeDatos();
		
		ObservableList<String> opTipoPago = FXCollections.observableArrayList("Mensual","Semestral");
		tipoPago.setItems(opTipoPago);
		
    	errorFechaInicioVigencia1.setVisible(false);
    	errorFechaInicioVigencia2.setVisible(false);
    	errorFechaInicioVigencia3.setVisible(false);
    	errorTipoPago.setVisible(false);
    	errorTipoCobertura.setVisible(false);
    	
    	
		
	}

	public void setAnterior(AnchorPane anterior) {
		this.anterior = anterior;
	}
	
}
