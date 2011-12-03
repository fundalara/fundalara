package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the empleado_cargo database table.
 * 
 */
@Embeddable
public class EmpleadoCargoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_cargo")
	private String codigoCargo;

	private String cedula;

    public EmpleadoCargoPK() {
    }
	public String getCodigoCargo() {
		return this.codigoCargo;
	}
	public void setCodigoCargo(String codigoCargo) {
		this.codigoCargo = codigoCargo;
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
		if (!(other instanceof EmpleadoCargoPK)) {
			return false;
		}
		EmpleadoCargoPK castOther = (EmpleadoCargoPK)other;
		return 
			this.codigoCargo.equals(castOther.codigoCargo)
			&& this.cedula.equals(castOther.cedula);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoCargo.hashCode();
		hash = hash * prime + this.cedula.hashCode();
		
		return hash;
    }
}