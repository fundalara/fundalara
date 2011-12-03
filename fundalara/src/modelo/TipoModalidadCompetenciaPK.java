package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tipo_modalidad_competencia database table.
 * 
 */
@Embeddable
public class TipoModalidadCompetenciaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_modalidad_competencia")
	private String codigoModalidadCompetencia;

	@Column(name="codigo_tipo_competencia")
	private String codigoTipoCompetencia;

    public TipoModalidadCompetenciaPK() {
    }
	public String getCodigoModalidadCompetencia() {
		return this.codigoModalidadCompetencia;
	}
	public void setCodigoModalidadCompetencia(String codigoModalidadCompetencia) {
		this.codigoModalidadCompetencia = codigoModalidadCompetencia;
	}
	public String getCodigoTipoCompetencia() {
		return this.codigoTipoCompetencia;
	}
	public void setCodigoTipoCompetencia(String codigoTipoCompetencia) {
		this.codigoTipoCompetencia = codigoTipoCompetencia;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TipoModalidadCompetenciaPK)) {
			return false;
		}
		TipoModalidadCompetenciaPK castOther = (TipoModalidadCompetenciaPK)other;
		return 
			this.codigoModalidadCompetencia.equals(castOther.codigoModalidadCompetencia)
			&& this.codigoTipoCompetencia.equals(castOther.codigoTipoCompetencia);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoModalidadCompetencia.hashCode();
		hash = hash * prime + this.codigoTipoCompetencia.hashCode();
		
		return hash;
    }
}