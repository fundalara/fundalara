package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the empleado_concepto_nomina database table.
 * 
 */
@Embeddable
public class EmpleadoConceptoNominaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String cedula;

	@Column(name="codigo_concepto_nomina")
	private String codigoConceptoNomina;

    public EmpleadoConceptoNominaPK() {
    }
	public String getCedula() {
		return this.cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getCodigoConceptoNomina() {
		return this.codigoConceptoNomina;
	}
	public void setCodigoConceptoNomina(String codigoConceptoNomina) {
		this.codigoConceptoNomina = codigoConceptoNomina;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EmpleadoConceptoNominaPK)) {
			return false;
		}
		EmpleadoConceptoNominaPK castOther = (EmpleadoConceptoNominaPK)other;
		return 
			this.cedula.equals(castOther.cedula)
			&& this.codigoConceptoNomina.equals(castOther.codigoConceptoNomina);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cedula.hashCode();
		hash = hash * prime + this.codigoConceptoNomina.hashCode();
		
		return hash;
    }
}