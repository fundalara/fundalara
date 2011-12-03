package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tarea_mantenimiento database table.
 * 
 */
@Embeddable
public class TareaMantenimientoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_mantenimiento")
	private String codigoMantenimiento;

	@Column(name="codigo_tarea")
	private String codigoTarea;

    public TareaMantenimientoPK() {
    }
	public String getCodigoMantenimiento() {
		return this.codigoMantenimiento;
	}
	public void setCodigoMantenimiento(String codigoMantenimiento) {
		this.codigoMantenimiento = codigoMantenimiento;
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
		if (!(other instanceof TareaMantenimientoPK)) {
			return false;
		}
		TareaMantenimientoPK castOther = (TareaMantenimientoPK)other;
		return 
			this.codigoMantenimiento.equals(castOther.codigoMantenimiento)
			&& this.codigoTarea.equals(castOther.codigoTarea);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoMantenimiento.hashCode();
		hash = hash * prime + this.codigoTarea.hashCode();
		
		return hash;
    }
}