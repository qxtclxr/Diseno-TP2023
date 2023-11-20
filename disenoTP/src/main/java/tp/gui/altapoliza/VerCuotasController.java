package tp.gui.altapoliza;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import tp.dto.CuotaDTO;
import tp.dto.PolizaDTO;
import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleObjectProperty;


public class VerCuotasController implements Initializable{
	private PolizaDTO poliza;
	@FXML
	private Button volverAtras;
	@FXML
	private Label montoAPagar;
	@FXML
    private TableView<CuotaDTO> tablaCuotas;
    @FXML
    private TableColumn<CuotaDTO, Integer> columnaCuota;
    @FXML
    private TableColumn<CuotaDTO, Float> columnaMonto;
    @FXML
    private TableColumn<CuotaDTO, LocalDate> columnaUltimoDiaPago;
	
	public VerCuotasController(PolizaDTO poliza) {
		this.poliza = poliza;
	}
	
	@FXML
	void volverAtrasCliqueado(ActionEvent evento) throws IOException {
		/*
		Parent root = FXMLLoader.load((getClass().getResource("../inicio/MenuPrincipal.fxml")));
    	Stage window = (Stage)volverAtras.getScene().getWindow();
    	window.setTitle("MenuPrincipal");
    	window.setScene(new Scene(root));
    	*/
		Stage stage = (Stage) volverAtras.getScene().getWindow();
        stage.close(); // Cierra la ventana modal
	}

	/* Coometno para probar abajo
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		montoAPagar.setText(String.valueOf(poliza.getImporteTotal()));	
	}
	*/
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	montoAPagar.setText(String.valueOf(poliza.getImporteTotal()));
        columnaCuota.setCellValueFactory(null); // Configura según tus necesidades FALTA CARGARLE ACA EL NUMERO 
        columnaMonto.setCellValueFactory(cellData -> {
            float importeTotal = cellData.getValue().getImporteTotal();
            return new SimpleFloatProperty(importeTotal).asObject();
        }); 
        columnaUltimoDiaPago.setCellValueFactory(cellData -> {
            LocalDate fechaVencimiento = cellData.getValue().getFechaVencimiento();
            return new SimpleObjectProperty<>(fechaVencimiento);
        }); // Configura según tus necesidades

        List<CuotaDTO> listaCuotas = poliza.getCuotas();
        ObservableList<CuotaDTO> observableListaCuotas = FXCollections.observableArrayList(listaCuotas);
        tablaCuotas.setItems(observableListaCuotas);
    }
	
}
