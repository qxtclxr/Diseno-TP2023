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
import javafx.scene.layout.VBox;
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
	private TableColumn<HijoDeclaradoDTO, LocalDate> columnaFechaNacimiento = new TableColumn();
	@FXML
	private TableColumn<HijoDeclaradoDTO, String> columnaSexo = new TableColumn();
	@FXML
	private TableColumn<HijoDeclaradoDTO, String> columnaEstadoCivil = new TableColumn();
	
	@FXML 
	private TableView<HijoDeclaradoDTO> tablaDeHijos;

	
	private List<HijoDeclaradoDTO> listaHijos;
	
	public void setListaHijos(List<HijoDeclaradoDTO> l) {
		listaHijos = l;
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
		
		Stage stage = (Stage) volverAtras2.getScene().getWindow();
		
        stage.close(); // Cierra la ventana modal*/
	}
	
	private void actualizarDatos() {
		
        ObservableList<HijoDeclaradoDTO> datosTabla = FXCollections.observableArrayList(listaHijos);
        tablaDeHijos.setItems(datosTabla);
        tablaDeHijos.refresh(); 
        
	}

	
	@FXML
	public void initialize() {
		
		
        // Configurar las columnas
        columnaFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        columnaSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        columnaEstadoCivil.setCellValueFactory(new PropertyValueFactory<>("estadoCivil"));
        
        ObservableList<HijoDeclaradoDTO> datosTabla = FXCollections.observableArrayList(listaHijos);
        tablaDeHijos.setItems(datosTabla);

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
		this.actualizarDatos();
	}
	
	
	@FXML
	public void agregarHijoClicked() {
		if(this.validarDatos()) {
			this.addHijo();
		}
		
	}
	@FXML
	public boolean validarDatos() {
		
		boolean validacionExitosa=true;
		
		
	    if (sexo.getValue() == null || sexo.getValue().toString().isEmpty()) {
	        errorSexo.setVisible(true);
	        validacionExitosa=false;
	    } else {
	        errorSexo.setVisible(false);
	    }

	    if (estadoCivil.getValue() == null || estadoCivil.getValue().toString().isEmpty()) {
	        errorEstadoCivil.setVisible(true);
	        validacionExitosa=false;
	    } else {
	        errorEstadoCivil.setVisible(false);
	    }

	    if (fechaNacimiento.getValue() == null || !(Period.between(fechaNacimiento.getValue(), LocalDate.now()).getYears() >= 18 && Period.between(fechaNacimiento.getValue(), LocalDate.now()).getYears() <= 30)) {
	        errorFechaNacimiento.setVisible(true);
	        validacionExitosa=false;
	    } else {
	        errorFechaNacimiento.setVisible(false);
	    }
	    
	    return validacionExitosa;
	}

	
	
}
