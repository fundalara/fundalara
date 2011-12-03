package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the empleado_tipo_nomina database table.
 * 
 */
@Embeddable
public class EmpleadoTipoNominaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_tipo_nomina")
	private String codigoTipoNomina;

	private String cedula;

    public EmpleadoTipoNominaPK() {
    }
	public String getCodigoTipoNomina() {
		return this.codigoTipoNomina;
	}
	public void setCodigoTipoNomina(String codigoTipoNomina) {
		this.codigoTipoNomina = codigoTipoNomina;
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
		if (!(other instanceof EmpleadoTipoNominaPK)) {
			return false;
		}
		EmpleadoTipoNominaPK castOther = (EmpleadoTipoNominaPK)other;
		return 
			this.codigoTipoNomina.equals(castOther.codigoTipoNomina)
			&& this.cedula.equals(castOther.cedula);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoTipoNomina.hashCode();
		hash = hash * prime + this.cedula.hashCode();
		
		return hash;
    }
}