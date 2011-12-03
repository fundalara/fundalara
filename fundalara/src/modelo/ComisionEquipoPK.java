package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the comision_equipo database table.
 * 
 */
@Embeddable
public class ComisionEquipoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_comision")
	private String codigoComision;

	@Column(name="codigo_equipo")
	private String codigoEquipo;

    public ComisionEquipoPK() {
    }
	public String getCodigoComision() {
		return this.codigoComision;
	}
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}
	public String getCodigoEquipo() {
		return this.codigoEquipo;
	}
	public void setCodigoEquipo(String codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ComisionEquipoPK)) {
			return false;
		}
		ComisionEquipoPK castOther = (ComisionEquipoPK)other;
		return 
			this.codigoComision.equals(castOther.codigoComision)
			&& this.codigoEquipo.equals(castOther.codigoEquipo);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoComision.hashCode();
		hash = hash * prime + this.codigoEquipo.hashCode();
		
		return hash;
    }
}