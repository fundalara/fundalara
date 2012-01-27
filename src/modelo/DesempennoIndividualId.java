package modelo;

<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DesempennoIndividualId generated by hbm2java
 */
@Embeddable
public class DesempennoIndividualId implements java.io.Serializable {

	private int codigoIndicadorCategoriaCompetencia;
	private int codigoLineUp;

	public DesempennoIndividualId() {
	}

	public DesempennoIndividualId(int codigoIndicadorCategoriaCompetencia,
			int codigoLineUp) {
		this.codigoIndicadorCategoriaCompetencia = codigoIndicadorCategoriaCompetencia;
		this.codigoLineUp = codigoLineUp;
	}

	@Column(name = "codigo_indicador_categoria_competencia", nullable = false)
	public int getCodigoIndicadorCategoriaCompetencia() {
		return this.codigoIndicadorCategoriaCompetencia;
	}

	public void setCodigoIndicadorCategoriaCompetencia(
			int codigoIndicadorCategoriaCompetencia) {
		this.codigoIndicadorCategoriaCompetencia = codigoIndicadorCategoriaCompetencia;
	}

	@Column(name = "codigo_line_up", nullable = false)
	public int getCodigoLineUp() {
		return this.codigoLineUp;
	}

	public void setCodigoLineUp(int codigoLineUp) {
		this.codigoLineUp = codigoLineUp;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DesempennoIndividualId))
			return false;
		DesempennoIndividualId castOther = (DesempennoIndividualId) other;

		return (this.getCodigoIndicadorCategoriaCompetencia() == castOther
				.getCodigoIndicadorCategoriaCompetencia())
				&& (this.getCodigoLineUp() == castOther.getCodigoLineUp());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodigoIndicadorCategoriaCompetencia();
		result = 37 * result + this.getCodigoLineUp();
		return result;
	}

}
