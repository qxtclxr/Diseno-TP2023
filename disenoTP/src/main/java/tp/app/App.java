package tp.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tp.dao.PolizaDAO;
import tp.entidad.Poliza;

public class App extends Application {
    
	private Stage primaryStage;
	private static BorderPane mainFrame;
	
    public static void main(String[] args) {
    	System.out.println("ho");
    	/*PolizaDAO p = new PolizaDAO();
    	
    	p.saveInstance(new Poliza());
    	*/
    	PolizaDAO p = new PolizaDAO();
        launch();
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("El Asegurado SRL");
			this.showMainFrame();
			this.showIngresoSistema();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void showMainFrame() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../gui/MainFrame.fxml"));
		mainFrame = loader.load();
		Scene scene = new Scene(mainFrame);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void showIngresoSistema() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../gui/inicio/IngresoSistema.fxml"));
		AnchorPane ingresoSistema = loader.load();
		mainFrame.setCenter(ingresoSistema);
	}
	
	public static void switchScreenTo(Parent parent) {
		mainFrame.setCenter(parent);
	}
}