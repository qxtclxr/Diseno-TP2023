package tp.gui.buscarcliente;

import tp.dto.*;
import tp.entidad.TipoDocumento;
import tp.exception.ObjetoNoEncontradoException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import tp.app.App;
import tp.gui.altapoliza.AltaPolizaFormularioPolizaController;
import tp.logica.GestorCliente;

public class BuscarClienteController implements Initializable {
	   private List<ClienteDTO> clientes = new ArrayList<>();
	   private AnchorPane actual;
	   private AnchorPane anterior; 
	   @FXML
	   private Button buscarButton;
	   @FXML
	   private TableView<ClienteDTO> clientesTable;
	   @FXML
	   private TableColumn<ClienteDTO, String> nroClienteColumn;
	   @FXML
	   private TableColumn<ClienteDTO, String> apellidoColumn;
	   @FXML
	   private TableColumn<ClienteDTO, String> nombreColumn;
	   @FXML
	   private TableColumn<ClienteDTO, TipoDocumento> tipoDocColumn;
	   @FXML
	   private TableColumn<ClienteDTO, String> nroDocColumn;
	   @FXML
	   private TextField nombreText;
	   @FXML
	   private TextField apellidoText;
	   @FXML
	   private TextField nroClienteText;
	   @FXML
	   private ComboBox<TipoDocumento> tipoDocCBox;
	   @FXML
	   private TextField nroDocText;
	   @FXML
	   private Text noEncontradosMsg;
	   @FXML
	   private Text confirmarMsg;
	   @FXML
	   private Button confirmarButton;
	   @FXML
	   private Button exitButton;
	    
	    @FXML
	   private void confirmarCliqueado(ActionEvent event) throws IOException, ObjetoNoEncontradoException {
	    	
	    	//Mockup
	    	//ClienteDTO seleccionado = GestorCliente.getClienteDTO(GestorCliente.getByNroCliente("123456789")) ;
	    	
	    	ClienteDTO seleccionado = clientesTable.getSelectionModel().getSelectedItem();
	    	
	    	FXMLLoader loader = new FXMLLoader();
	    	AltaPolizaFormularioPolizaController altaPolizaC = new AltaPolizaFormularioPolizaController();
	    	altaPolizaC.setClienteDTO(seleccionado);
	    	loader.setController(altaPolizaC);
	    	
	    	loader.setLocation(getClass().getResource("../altapoliza/AltaPolizaFormularioPoliza.fxml"));
	    	AnchorPane form = loader.load();
	    	
	    	altaPolizaC.setActual(form);
	    	altaPolizaC.setAnterior(actual);
	    	
	    	App.switchScreenTo(form);
	    }

	    @FXML
	    private void handleExit(ActionEvent event) throws IOException {
	    	App.switchScreenTo(anterior);
	    }
	    
	    public ClienteDTO getClienteDTO() {
	    	ClienteDTO dto = new ClienteDTO();
	    	dto.setNombre(nombreText.getText());
	    	dto.setApellido(apellidoText.getText());
	    	dto.setNroCliente(nroClienteText.getText());
	    	dto.setTipoDocumento(tipoDocCBox.getValue());
	    	dto.setNroDocumento(nroDocText.getText());
	    	return dto;
	    }

		public void setActual(AnchorPane actual) {
			this.actual = actual;
		}
		
		public void setAnterior(AnchorPane anterior) {
			this.anterior = anterior;
		}
		
		public void inicializarTabla() {
			nroClienteColumn.setCellValueFactory(new PropertyValueFactory<>("nroCliente"));
			apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));
			nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			tipoDocColumn.setCellValueFactory(new PropertyValueFactory<>("tipoDocumento"));
			nroDocColumn.setCellValueFactory(new PropertyValueFactory<>("nroDocumento"));
		}
		
		public void actualizarTabla() {
			if(clientes.isEmpty()) {
				clientesTable.setVisible(false);
				noEncontradosMsg.setVisible(true);
				return;
			}
			ObservableList<ClienteDTO> datosTabla = FXCollections.observableArrayList(clientes);
	        clientesTable.setItems(datosTabla);
			noEncontradosMsg.setVisible(false);
			clientesTable.setVisible(true);
			return;
		}
		
		public void buscarCliqueado() {
			clientes = GestorCliente.buscarClienteDTO(this.getClienteDTO());
			this.actualizarTabla();
		}

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			inicializarTabla();
			clientesTable.setVisible(false);
			noEncontradosMsg.setVisible(false);
			confirmarButton.disableProperty().bind(clientesTable.getSelectionModel().selectedItemProperty().isNull());
			confirmarMsg.visibleProperty().bind(clientesTable.visibleProperty());
		}
}
