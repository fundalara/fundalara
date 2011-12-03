package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the estado_mantenimiento database table.
 * 
 */
@Embeddable
public class EstadoMantenimientoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_estado")
	private String codigoEstado;

	@Column(name="codigo_mantenimiento")
	private String codigoMantenimiento;

    public EstadoMantenimientoPK() {
    }
	public String getCodigoEstado() {
		return this.codigoEstado;
	}
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
	public String getCodigoMantenimiento() {
		return this.codigoMantenimiento;
	}
	public void setCodigoMantenimiento(String codigoMantenimiento) {
		this.codigoMantenimiento = codigoMantenimiento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EstadoMantenimientoPK)) {
			return false;
		}
		EstadoMantenimientoPK castOther = (EstadoMantenimientoPK)other;
		return 
			this.codigoEstado.equals(castOther.codigoEstado)
			&& this.codigoMantenimiento.equals(castOther.codigoMantenimiento);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoEstado.hashCode();
		hash = hash * prime + this.codigoMantenimiento.hashCode();
		
		return hash;
    }
}