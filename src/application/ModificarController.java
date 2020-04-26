package application;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModificarController {

	@FXML
	private TextField tfCif;
	
	@FXML
	private TextField tfRazon;
	
	@FXML
	private TextField tfRegistro;
	
	@FXML
	private TextField tfResponsabilidad;
	
	@FXML
	private TextField tfSeguroImporte;
	
	@FXML
	private DatePicker dpFecha;
	
	private static boolean update;
	
	private TabbedPaneController sp1;
	
	public void setController(TabbedPaneController sp1) {
		this.sp1 = sp1;
	}
	
	@FXML
	public void altaProveedor(ActionEvent event) {
		
		String cif = tfCif.getText().toString();
		String razon = tfRazon.getText().toString();
		int registro = Integer.parseInt(tfRegistro.getText().toString());
		String seguroResponsabilidad = tfResponsabilidad.getText().toString();
		float seguroImporte = Float.parseFloat(tfSeguroImporte.getText().toString());
		
		LocalDate fecha = dpFecha.getValue();
		
		Date date = java.util.Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		
		Proveedor proveedor = new Proveedor(cif,razon,registro,seguroResponsabilidad,seguroImporte,sqlDate);
		
		if(update) {
			Util.modificarProveedor(proveedor);
			update = false;
		}else
			Util.insertarProveedor(proveedor);
		
		cerrarVentana();
		sp1.cargarListaProveedores(null);
	}
	
	public void cargarProveedorAModificar(Proveedor prov) {
		tfCif.setText(prov.getCif());
		tfCif.setDisable(true);
		tfRazon.setText(prov.getRazon());
		tfRegistro.setText(String.valueOf(prov.getRegNotarial()));
		tfResponsabilidad.setText(prov.getSegResponsabilidad());
		tfSeguroImporte.setText(String.valueOf(prov.getSegImporte()));
		dpFecha.setValue(prov.getFecHomologacion().toLocalDate());
		
		update = true;
	}
	
	@FXML
	public void cerrarVentana() {
		Stage stage = (Stage) dpFecha.getScene().getWindow();
		stage.close();
	}
	
}
