package application;

import java.sql.Date;

public class Proveedor {
	private String cif;
	private String razon;
	private int regNotarial;
	private String segResponsabilidad;
	private float segImporte;
	private Date fecHomologacion;
	
	public Proveedor() {}

	public Proveedor(String cif, String razon, int regNotarial, String segResponsabilidad, float segImporte,
			Date fecHomologacion) {
		super();
		this.cif = cif;
		this.razon = razon;
		this.regNotarial = regNotarial;
		this.segResponsabilidad = segResponsabilidad;
		this.segImporte = segImporte;
		this.fecHomologacion = fecHomologacion;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	public int getRegNotarial() {
		return regNotarial;
	}

	public void setRegNotarial(int regNotarial) {
		this.regNotarial = regNotarial;
	}

	public String getSegResponsabilidad() {
		return segResponsabilidad;
	}

	public void setSegResponsabilidad(String segResponsabilidad) {
		this.segResponsabilidad = segResponsabilidad;
	}

	public float getSegImporte() {
		return segImporte;
	}

	public void setSegImporte(float segImporte) {
		this.segImporte = segImporte;
	}

	public Date getFecHomologacion() {
		return fecHomologacion;
	}

	public void setFecHomologacion(Date fecHomologacion) {
		this.fecHomologacion = fecHomologacion;
	}
	
}
