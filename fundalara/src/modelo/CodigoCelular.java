package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the codigo_celular database table.
 * 
 */
@Entity
@Table(name="codigo_celular")
public class CodigoCelular implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_celular")
	private String codigoCelular;

	@Column(name="codigo_del_celular")
	private String codigoDelCelular;

	private String estatus;

    public CodigoCelular() {
    }

	public String getCodigoCelular() {
		return this.codigoCelular;
	}

	public void setCodigoCelular(String codigoCelular) {
		this.codigoCelular = codigoCelular;
	}

	public String getCodigoDelCelular() {
		return this.codigoDelCelular;
	}

	public void setCodigoDelCelular(String codigoDelCelular) {
		this.codigoDelCelular = codigoDelCelular;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

}