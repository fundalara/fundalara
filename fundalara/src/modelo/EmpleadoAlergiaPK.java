package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the empleado_alergia database table.
 * 
 */
@Embeddable
public class EmpleadoAlergiaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_alergia")
	private String codigoAlergia;

	private String cedula;

    public EmpleadoAlergiaPK() {
    }
	public String getCodigoAlergia() {
		return this.codigoAlergia;
	}
	public void setCodigoAlergia(String codigoAlergia) {
		this.codigoAlergia = codigoAlergia;
	}
	public String getCedula() {
		return this.cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EmpleadoAlergiaPK)) {
			return false;
		}
		EmpleadoAlergiaPK castOther = (EmpleadoAlergiaPK)other;
		return 
			this.codigoAlergia.equals(castOther.codigoAlergia)
			&& this.cedula.equals(castOther.cedula);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoAlergia.hashCode();
		hash = hash * prime + this.cedula.hashCode();
		
		return hash;
    }
}