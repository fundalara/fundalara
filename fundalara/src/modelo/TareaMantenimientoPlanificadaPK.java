package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tarea_mantenimiento_planificada database table.
 * 
 */
@Embeddable
public class TareaMantenimientoPlanificadaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_planificacion_mantenimiento")
	private String codigoPlanificacionMantenimiento;

	@Column(name="codigo_tarea")
	private String codigoTarea;

    public TareaMantenimientoPlanificadaPK() {
    }
	public String getCodigoPlanificacionMantenimiento() {
		return this.codigoPlanificacionMantenimiento;
	}
	public void setCodigoPlanificacionMantenimiento(String codigoPlanificacionMantenimiento) {
		this.codigoPlanificacionMantenimiento = codigoPlanificacionMantenimiento;
	}
	public String getCodigoTarea() {
		return this.codigoTarea;
	}
	public void setCodigoTarea(String codigoTarea) {
		this.codigoTarea = codigoTarea;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TareaMantenimientoPlanificadaPK)) {
			return false;
		}
		TareaMantenimientoPlanificadaPK castOther = (TareaMantenimientoPlanificadaPK)other;
		return 
			this.codigoPlanificacionMantenimiento.equals(castOther.codigoPlanificacionMantenimiento)
			&& this.codigoTarea.equals(castOther.codigoTarea);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoPlanificacionMantenimiento.hashCode();
		hash = hash * prime + this.codigoTarea.hashCode();
		
		return hash;
    }
}