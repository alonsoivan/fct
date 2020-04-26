package application;

import java.io.File;

import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TabbedPaneController {
	
	@FXML
	private ListView<String> lvProveedores;
	
	@FXML
	private Button btModificar;
	
	@FXML
	private Button btEliminar;
	
	@FXML
	private Label labelFichero;
	
	@FXML
	private Rectangle rectangle;
	
	
	private static ArrayList<Proveedor> proveedores;
	
	private ModificarController sc2;
	
	@FXML  
	public void cargarListaProveedores(ActionEvent event) {					
		
		proveedores = Util.getProveedores();
		
		ObservableList<String> items =FXCollections.observableArrayList ();
		
		for(Proveedor prov : proveedores)
			items.add(prov.getCif() + "   -   " +prov.getRazon());
		
		lvProveedores.setItems(items);
	}
	
	@FXML
	public void activarBotones() {
		if(lvProveedores.getSelectionModel().getSelectedIndex() >= 0) {
			btModificar.setDisable(false);
			btEliminar.setDisable(false);
		}
	}
	
	@FXML
	public void desactivarBotones() {
		btModificar.setDisable(true);
		btEliminar.setDisable(true);
	}
	
	@FXML
	public void modificarProveedor(ActionEvent event) {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Modificar.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("MODIFICAR PROVEEDOR");
			primaryStage.show();
		
			sc2 = ((ModificarController) loader.getController());
			sc2.setController(this);
			
			sc2.cargarProveedorAModificar(proveedores.get(lvProveedores.getSelectionModel().getSelectedIndex()));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void anadirProveedor(ActionEvent event) {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Modificar.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("NUEVO PROVEEDOR");
			primaryStage.show();
			
			sc2 = ((ModificarController) loader.getController());
			sc2.setController(this);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void eliminarProveedor(ActionEvent event) {
		mostrarAlerta();
	}
	

	
	@FXML
	public void buscarFichero() {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Fichero");

  /*
        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
  */      

        // Abre el explorador de archivos para seleccionar el fichero
        File file = fileChooser.showOpenDialog((Stage) btEliminar.getScene().getWindow());
        
       
        // Si ha sido seleccionado, procedemos a tratar el fichero
        if(file != null) {
	        System.out.println(file.getAbsolutePath());
	        labelFichero.setText(file.getAbsolutePath());
	    }

	}

	@FXML
	private void mostrarAlerta() {
		
		String proveedor = proveedores.get(lvProveedores.getSelectionModel().getSelectedIndex()).getRazon();
		
		ButtonType eliminar = new ButtonType("ELIMINAR", ButtonData.OK_DONE);
		ButtonType cancelar = new ButtonType("CANCELAR", ButtonData.CANCEL_CLOSE);
		Alert alerta = new Alert(AlertType.NONE, "¿Desea eliminar el proveedor "+proveedor+"?", cancelar, eliminar);
		
		alerta.setTitle("Eliminar proveedor");
		
		Optional<ButtonType> resultado = alerta.showAndWait();
		if(resultado.get() == eliminar) {
			
			Util.eliminarProveedor(proveedores.get(lvProveedores.getSelectionModel().getSelectedIndex()));
			cargarListaProveedores(null);
			desactivarBotones();
		} 
		
	}
	
	@FXML
	public void DragExited(DragEvent event) {
		rectangle.setStroke(Color.DARKBLUE);
	}
	
	@FXML
	public void DragDropped(DragEvent event) {
       
        Dragboard db = event.getDragboard();
                
        if (db.hasFiles()) {
            System.out.println(db.getFiles().get(0).getAbsolutePath());
            labelFichero.setText(db.getFiles().get(0).getAbsolutePath());
            
            event.setDropCompleted(true);
        }   
    }
	
	@FXML
	public void DragEntered(DragEvent event) {
        /* show to the user that it is an actual gesture target */
        if (event.getGestureSource() != lvProveedores && event.getDragboard().hasFiles()) {
        	rectangle.setStroke(Color.DARKRED);
        }
    }
	
	@FXML
	public void DragOver(DragEvent event) {
        if (event.getGestureSource() != lvProveedores && event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        }
    }
    
}
