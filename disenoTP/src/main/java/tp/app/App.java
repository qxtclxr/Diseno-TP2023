package tp.app;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tp.dao.*;
import tp.entidad.*;
import tp.util.EntityManagerUtil;


public class App extends Application {
    
	private Stage primaryStage;
	private static BorderPane mainFrame;
	private static Usuario usuarioLogeado = new Usuario();
	
    public static void main(String[] args) {
    	EntityManagerUtil.createEntityManagerFactory();
    	PolizaDAO dao = new PolizaDAO();
    	//cargarDatos();
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

	public static Usuario getUsuarioLogeado() {
		return usuarioLogeado;
	}

	public static void setUsuarioLogeado(Usuario usuarioLogeado) {
		App.usuarioLogeado = usuarioLogeado;
	}
	
	public static void cargarDatos() {
		LocalidadDAO dao = new LocalidadDAO();
		Pais pais = new Pais();
		Provincia prov = new Provincia();
		Localidad local = new Localidad();
		
		pais.setNombre("Argentina");
		pais.setCodPais("01");
		
		prov.setNombreProvincia("Entre Rios");
		prov.setPais(pais);
		
		local.setNombre("Parana");
		local.setProvincia(prov);
		
		dao.saveInstance(local);
	}
}