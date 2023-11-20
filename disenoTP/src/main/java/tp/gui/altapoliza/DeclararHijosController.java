package tp.gui.altapoliza;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class DeclararHijosController {
	
	@FXML 
	private Label errorSexo;
	@FXML
	private Label errorEstadoCivil;
	@FXML
	private Label errorFechaNacimiento;
	@FXML 
	private TableView tablaDeHijos;
	
	@FXML
	private ComboBox sexo;
	@FXML
	private ComboBox estadoCivil;
	@FXML
	private Button agregarHijo;
	@FXML
	private Button volverAtras2;
	@FXML 
	private DatePicker fechaNacimiento;
	@FXML
	private TableColumn columnaFechaNacimiento = new TableColumn();
	@FXML
	private TableColumn columnaSexo = new TableColumn();
	@FXML
	private TableColumn columnaEstadoCivil = new TableColumn();
	
	
	private List<HijoDeclaradoDTO> listaHijos;
	
	private AltaPolizaFormularioPolizaController controladorPantallaFormulario = new AltaPolizaFormularioPolizaController();
	
	public void setListaHijos(List<HijoDeclaradoDTO> l, AltaPolizaFormularioPolizaController a ) {
		listaHijos = l;
		controladorPantallaFormulario = a;
	}
	
	@FXML
	void volverAtrasClicked2( ActionEvent action ) {
		/*
		FXMLLoader loader = new FXMLLoader();
    	AltaPolizaFormularioPolizaController altaPolizaC = new AltaPolizaFormularioPolizaController();
    	altaPolizaC.setHijosDeclarados(listaHijos);
    	loader.setController(altaPolizaC);
    	
    	loader.setLocation(getClass().getResource("../altapoliza/AltaPolizaFormularioPoliza.fxml"));
    	AnchorPane form = loader.load();
    	
    	App.switchScreenTo(form);
		*/
		controladorPantallaFormulario.setHijosDeclarados(listaHijos);
		
		Stage stage = (Stage) volverAtras2.getScene().getWindow();
		
        stage.close(); // Cierra la ventana modal*/
	}
	
	private void actualizarDatos() {
		
        ObservableList<HijoDeclaradoDTO> datosTabla = FXCollections.observableArrayList(listaHijos);
        tablaDeHijos.setItems(datosTabla);
		
	}

	
	@FXML
	public void initialize() {
		
        // Configurar las columnas
        columnaFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        columnaSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        columnaEstadoCivil.setCellValueFactory(new PropertyValueFactory<>("estadoCivil"));

        // Configurar las celdas de la columna de fecha de nacimiento para formatear la fecha
        columnaFechaNacimiento.setCellFactory(column -> {
            return new TableCell<HijoDeclaradoDTO, LocalDate>() {
                @Override
                protected void updateItem(LocalDate fechaNac, boolean empty) {
                    super.updateItem(fechaNac, empty);
                    if (empty || fechaNac == null) {
                        setText(null);
                    } else {
                        setText(fechaNac.toString()); // Puedes personalizar el formato aquí si es necesario
                    }
                }
            };
        });
        
        this.actualizarDatos();
		
		
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
		
		return null;
		//Este return null es para que no tire error de compilacion
		//Si ponen todos los returns dentro de los "if", Eclipse va a pensar que
		//puede haber un camino que no entre a ningun "if" y no retorne nada.
		//No es lo mas seguro tener un return null, deberian pensar otra manera
	}
	
	private Sexo getValueSexo( ) {
		if(sexo.getValue().toString().equals("Hombre")) {
			return Sexo.MASCULINO;
		}
		else if(sexo.getValue().toString().equals("Mujer")){
			return Sexo.FEMENINO;
		}
		return null;
		//Este return null es para que no tire error de compilacion
		//Si ponen todos los returns dentro de los "if", Eclipse va a pensar que
		//puede haber un camino que no entre a ningun "if" y no retorne nada.
		//No es lo mas seguro tener un return null, deberian pensar otra manera
	}
	
	
	private void addHijo( ) {
	
		HijoDeclaradoDTO hijo = new HijoDeclaradoDTO();
		hijo.setFechaNacimiento(fechaNacimiento.getValue());
		hijo.setEstadoCivil( this.getValueEstadoCivil() );
		hijo.setSexo(this.getValueSexo() );
		
		listaHijos.add(hijo);
		System.out.println("Se agregó un hijo AAAAAAAAAAAA");
		this.actualizarDatos();
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
