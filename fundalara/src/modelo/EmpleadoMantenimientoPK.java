package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the empleado_mantenimiento database table.
 * 
 */
@Embeddable
public class EmpleadoMantenimientoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_mantenimiento")
	private String codigoMantenimiento;

	private String cedula;

    public EmpleadoMantenimientoPK() {
    }
	public String getCodigoMantenimiento() {
		return this.codigoMantenimiento;
	}
	public void setCodigoMantenimiento(String codigoMantenimiento) {
		this.codigoMantenimiento = codigoMantenimiento;
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
		if (!(other instanceof EmpleadoMantenimientoPK)) {
			return false;
		}
		EmpleadoMantenimientoPK castOther = (EmpleadoMantenimientoPK)other;
		return 
			this.codigoMantenimiento.equals(castOther.codigoMantenimiento)
			&& this.cedula.equals(castOther.cedula);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoMantenimiento.hashCode();
		hash = hash * prime + this.cedula.hashCode();
		
		return hash;
    }
}