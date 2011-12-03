package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the empleado_mantenimiento_planificado database table.
 * 
 */
@Embeddable
public class EmpleadoMantenimientoPlanificadoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String cedula;

	@Column(name="codigo_planificacion_mantenimiento")
	private String codigoPlanificacionMantenimiento;

    public EmpleadoMantenimientoPlanificadoPK() {
    }
	public String getCedula() {
		return this.cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getCodigoPlanificacionMantenimiento() {
		return this.codigoPlanificacionMantenimiento;
	}
	public void setCodigoPlanificacionMantenimiento(String codigoPlanificacionMantenimiento) {
		this.codigoPlanificacionMantenimiento = codigoPlanificacionMantenimiento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EmpleadoMantenimientoPlanificadoPK)) {
			return false;
		}
		EmpleadoMantenimientoPlanificadoPK castOther = (EmpleadoMantenimientoPlanificadoPK)other;
		return 
			this.cedula.equals(castOther.cedula)
			&& this.codigoPlanificacionMantenimiento.equals(castOther.codigoPlanificacionMantenimiento);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cedula.hashCode();
		hash = hash * prime + this.codigoPlanificacionMantenimiento.hashCode();
		
		return hash;
    }
}