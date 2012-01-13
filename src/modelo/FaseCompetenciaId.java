package modelo;

// Generated 13/01/2012 12:48:04 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FaseCompetenciaId generated by hbm2java
 */
@Embeddable
public class FaseCompetenciaId implements java.io.Serializable {

	private int codigoCompetencia;
	private int numeroFase;

	public FaseCompetenciaId() {
	}

	public FaseCompetenciaId(int codigoCompetencia, int numeroFase) {
		this.codigoCompetencia = codigoCompetencia;
		this.numeroFase = numeroFase;
	}

	@Column(name = "codigo_competencia", nullable = false)
	public int getCodigoCompetencia() {
		return this.codigoCompetencia;
	}

	public void setCodigoCompetencia(int codigoCompetencia) {
		this.codigoCompetencia = codigoCompetencia;
	}

	@Column(name = "numero_fase", nullable = false)
	public int getNumeroFase() {
		return this.numeroFase;
	}

	public void setNumeroFase(int numeroFase) {
		this.numeroFase = numeroFase;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FaseCompetenciaId))
			return false;
		FaseCompetenciaId castOther = (FaseCompetenciaId) other;

		return (this.getCodigoCompetencia() == castOther.getCodigoCompetencia())
				&& (this.getNumeroFase() == castOther.getNumeroFase());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodigoCompetencia();
		result = 37 * result + this.getNumeroFase();
		return result;
	}

}
