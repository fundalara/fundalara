package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the codigo_area database table.
 * 
 */
@Entity
@Table(name="codigo_area")
public class CodigoArea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_area")
	private String codigoArea;

	@Column(name="codigo_de_area")
	private String codigoDeArea;

	private String estatus;

    public CodigoArea() {
    }

	public String getCodigoArea() {
		return this.codigoArea;
	}

	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	public String getCodigoDeArea() {
		return this.codigoDeArea;
	}

	public void setCodigoDeArea(String codigoDeArea) {
		this.codigoDeArea = codigoDeArea;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

}