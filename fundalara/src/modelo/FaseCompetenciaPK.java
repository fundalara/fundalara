package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the fase_competencia database table.
 * 
 */
@Embeddable
public class FaseCompetenciaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_competencia")
	private String codigoCompetencia;

	@Column(name="numero_fase")
	private Integer numeroFase;

    public FaseCompetenciaPK() {
    }
	public String getCodigoCompetencia() {
		return this.codigoCompetencia;
	}
	public void setCodigoCompetencia(String codigoCompetencia) {
		this.codigoCompetencia = codigoCompetencia;
	}
	public Integer getNumeroFase() {
		return this.numeroFase;
	}
	public void setNumeroFase(Integer numeroFase) {
		this.numeroFase = numeroFase;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FaseCompetenciaPK)) {
			return false;
		}
		FaseCompetenciaPK castOther = (FaseCompetenciaPK)other;
		return 
			this.codigoCompetencia.equals(castOther.codigoCompetencia)
			&& this.numeroFase.equals(castOther.numeroFase);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoCompetencia.hashCode();
		hash = hash * prime + this.numeroFase.hashCode();
		
		return hash;
    }
}