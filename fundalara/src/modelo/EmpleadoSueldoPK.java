package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the empleado_sueldo database table.
 * 
 */
@Embeddable
public class EmpleadoSueldoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String cedula;

    @Temporal( TemporalType.DATE)
	private java.util.Date fecha;

    public EmpleadoSueldoPK() {
    }
	public String getCedula() {
		return this.cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public java.util.Date getFecha() {
		return this.fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EmpleadoSueldoPK)) {
			return false;
		}
		EmpleadoSueldoPK castOther = (EmpleadoSueldoPK)other;
		return 
			this.cedula.equals(castOther.cedula)
			&& this.fecha.equals(castOther.fecha);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cedula.hashCode();
		hash = hash * prime + this.fecha.hashCode();
		
		return hash;
    }
}