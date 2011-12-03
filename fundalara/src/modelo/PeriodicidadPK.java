package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the periodicidad database table.
 * 
 */
@Embeddable
public class PeriodicidadPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_planificacion_mantenimiento")
	private String codigoPlanificacionMantenimiento;

	@Column(name="codigo_periodicidad")
	private String codigoPeriodicidad;

    public PeriodicidadPK() {
    }
	public String getCodigoPlanificacionMantenimiento() {
		return this.codigoPlanificacionMantenimiento;
	}
	public void setCodigoPlanificacionMantenimiento(String codigoPlanificacionMantenimiento) {
		this.codigoPlanificacionMantenimiento = codigoPlanificacionMantenimiento;
	}
	public String getCodigoPeriodicidad() {
		return this.codigoPeriodicidad;
	}
	public void setCodigoPeriodicidad(String codigoPeriodicidad) {
		this.codigoPeriodicidad = codigoPeriodicidad;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PeriodicidadPK)) {
			return false;
		}
		PeriodicidadPK castOther = (PeriodicidadPK)other;
		return 
			this.codigoPlanificacionMantenimiento.equals(castOther.codigoPlanificacionMantenimiento)
			&& this.codigoPeriodicidad.equals(castOther.codigoPeriodicidad);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoPlanificacionMantenimiento.hashCode();
		hash = hash * prime + this.codigoPeriodicidad.hashCode();
		
		return hash;
    }
}