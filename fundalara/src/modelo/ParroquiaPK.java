package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the parroquia database table.
 * 
 */
@Embeddable
public class ParroquiaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cod_parroquia")
	private String codParroquia;

	@Column(name="cod_municipio")
	private String codMunicipio;

    public ParroquiaPK() {
    }
	public String getCodParroquia() {
		return this.codParroquia;
	}
	public void setCodParroquia(String codParroquia) {
		this.codParroquia = codParroquia;
	}
	public String getCodMunicipio() {
		return this.codMunicipio;
	}
	public void setCodMunicipio(String codMunicipio) {
		this.codMunicipio = codMunicipio;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ParroquiaPK)) {
			return false;
		}
		ParroquiaPK castOther = (ParroquiaPK)other;
		return 
			this.codParroquia.equals(castOther.codParroquia)
			&& this.codMunicipio.equals(castOther.codMunicipio);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codParroquia.hashCode();
		hash = hash * prime + this.codMunicipio.hashCode();
		
		return hash;
    }
}