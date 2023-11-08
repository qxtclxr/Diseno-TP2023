package org.openjfx.disenoTP;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AltaPolizaController {
    @FXML
    private Button buscarCliente;
    @FXML
    private Button addClientButton;
    @FXML
    private Button exitButton;

    @FXML
    private void handleSearchClient(ActionEvent event) {
        loadScreen("buscarCliente.fxml", "buscarCliente");
    }

    @FXML
    private void handleAddClient(ActionEvent event) {
        loadScreen("AddClient.fxml", "Alta de Cliente");
    }

    @FXML
    private void handleExit(ActionEvent event) {
        loadScreen("ExitConfirmation.fxml", "Confirmacion de Salida");
    }

    private void loadScreen(String fxmlFile, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root);
            Stage stage = (Stage) buscarCliente.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


