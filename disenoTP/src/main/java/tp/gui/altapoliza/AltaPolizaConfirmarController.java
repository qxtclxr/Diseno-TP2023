package tp.gui.altapoliza;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tp.app.App;
import tp.dto.*;


public class AltaPolizaConfirmarController {

	private PolizaDTO poliza = new PolizaDTO();
	
	
	@FXML
	private Label apellido;
	@FXML
	private Label nombre;
	@FXML
	private Label marca;
	@FXML
	private Label modelo;
	@FXML
	private Label motor;
	@FXML
	private Label chasis;
	@FXML
	private Label patente;
	@FXML
	private Label direccionDeRiesgo;

	@FXML
	private Label inicioVigencia;
	@FXML
	private Label finalVigencia;
	@FXML
	private Button ultimoDiaDePago;
	@FXML
	private Label sumaAsegurada;
	@FXML
	private Label premio;
	@FXML
	private Label importePorDescuento;
	@FXML
	private Button totalAbonar;
	
	
	
	public void setPolizaDTO(PolizaDTO p) {
		this.poliza = p;
	}
	
	@FXML
	private void volverAtrasClicked(ActionEvent action) throws IOException {
		
		
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../altapoliza/AltaPolizaFormularioCobertura.fxml"));
   
    	AnchorPane form = loader.load();
    	
    	AltaPolizaFormularioCoberturaController formularioPolizaCoberturaC = loader.getController();
    	formularioPolizaCoberturaC.setPolizaDTOConfirmar(this.poliza, this);
    	formularioPolizaCoberturaC.mostrarDatos();
    	
    	App.switchScreenTo(form);
		
	}
	
	
	@FXML
	private AnchorPane rootPane2; // Referencia al pane principal de AltaPolizaFormularioPoliza.fxml
	@FXML  //HACE LO MISMO Q EL DE ABAJO PERO ESTE LO HARIA OSCURECIENDO LA PAGINA, HAY Q CHEQEUAR
	private void verCoutasClicked(ActionEvent action) throws IOException {
	    FXMLLoader loader = new FXMLLoader();
	    //DeclararHijosController declararHijosC = new DeclararHijosController();
	    VerCuotasController verCuotasC = new VerCuotasController();
	    
	    //declararHijosC.setListaHijos(this.poliza.getHijosDeclarados()); ///////
	    
	    loader.setController(verCuotasC);
	    loader.setLocation(getClass().getResource("../altapoliza/VerCuotas.fxml"));

	    // Cargar el formulario en un AnchorPane
	    AnchorPane form = loader.load();

	    // Crear un nuevo Stage (ventana) para mostrar el formulario como modal
	    Stage modalStage = new Stage();
	    modalStage.initModality(Modality.APPLICATION_MODAL);
	    modalStage.setTitle("Ver Cuotas");

	    // Configurar el formulario en la nueva ventana modal
	    Scene scene = new Scene(form);

	    // Crear un Pane transparente para oscurecer el fondo
	    Pane overlay = new Pane();
	    overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);"); // Color negro con opacidad del 50%
	    overlay.setPrefSize(rootPane2.getWidth(), rootPane2.getHeight());
	    rootPane2.getChildren().add(overlay);

	    modalStage.setScene(scene);

	    // Mostrar la ventana modal y esperar hasta que se cierre
	    modalStage.setOnHidden(event -> rootPane2.getChildren().remove(overlay)); // Remover el overlay cuando se cierra la ventana modal
	    modalStage.showAndWait();
	}
	
	/*
	@FXML
	private void verCoutasClicked(ActionEvent action) throws IOException {
	    FXMLLoader loader = new FXMLLoader();
	    //DeclararHijosController declararHijosC = new DeclararHijosController();
	    VerCuotasController verCuotasC = new VerCuotasController();
	    //declararHijosC.setListaHijos(this.poliza.getHijosDeclarados());
	    //
	    loader.setController(verCuotasC);
	    loader.setLocation(getClass().getResource("../altapoliza/VerCuotas.fxml"));

	    // Cargar el formulario en un AnchorPane
	    AnchorPane form = loader.load();

	    // Crear un nuevo Stage (ventana) para mostrar el formulario como modal
	    Stage modalStage = new Stage();
	    modalStage.initModality(Modality.APPLICATION_MODAL);
	    modalStage.setTitle("Ver Cuotas");

	    // Configurar el formulario en la nueva ventana modal
	    Scene scene = new Scene(form);
	    modalStage.setScene(scene);
	    
	    // Mostrar la ventana modal y esperar hasta que se cierre
	    modalStage.showAndWait();
	}*/
	
	
	private void mostrarDatosPoliza( ) {
		
		apellido.setText(poliza.getCliente().getApellido());
		nombre.setText(poliza.getCliente().getNombre());
		/* 

		
		
		

		
		*/
		modelo.setText(poliza.getVehiculo().getModelo().getModelo().getNombre());
		direccionDeRiesgo.setText(poliza.getLocalidad().getNombre()+ ", "+ poliza.getLocalidad().getProvincia().getNombre());
		marca.setText(poliza.getVehiculo().getModelo().getModelo().getMarca().getNombre());
		patente.setText(poliza.getVehiculo().getPatente());
		motor.setText(poliza.getVehiculo().getMotor());
		chasis.setText(poliza.getVehiculo().getChasis());
		inicioVigencia.setText( poliza.getFechaInicio().toString() );
		finalVigencia.setText(  poliza.getFechaInicio().plusMonths(1).toString()  );
		
	}
	
	
	@FXML
	public void initialize( ) {
		
		this.mostrarDatosPoliza();
		
	}
	
	
	
	
}

