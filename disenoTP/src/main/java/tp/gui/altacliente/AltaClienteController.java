package tp.gui.altacliente;


import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AltaClienteController {

	@FXML
	private TextField apellido;
	@FXML
	private TextField nombre;
	@FXML
	private ComboBox<?> tipoDocumento;
	@FXML
	private ComboBox<?> condicionIva;
	@FXML
	private ComboBox<?> sexo;
	@FXML
	private TextField nroDoc;
	@FXML
	private TextField cuil;
	@FXML
	private TextField correoElectronico;
	@FXML
	private TextField profesion;
	@FXML
	private Date fechaNacimiento;
	@FXML
	private TextField calle;
	@FXML
	private TextField piso;
	@FXML
	private TextField depto;
	@FXML
	private ComboBox<?> provincia;
	@FXML
	private ComboBox<?> localidad;
	@FXML
	private ComboBox<?> pais;
	@FXML	
	private Button darDeAltaCliente;
	
	public void holaMundo() {
		apellido.appendText("HolaMundo!");
	}
	
}
