package modelo;

<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CategoriaCompetenciaId generated by hbm2java
 */
@Embeddable
public class CategoriaCompetenciaId implements java.io.Serializable {

	private int codigoCompetencia;
	private int codigoCategoria;

	public CategoriaCompetenciaId() {
	}

	public CategoriaCompetenciaId(int codigoCompetencia, int codigoCategoria) {
		this.codigoCompetencia = codigoCompetencia;
		this.codigoCategoria = codigoCategoria;
	}

	@Column(name = "codigo_competencia", nullable = false)
	public int getCodigoCompetencia() {
		return this.codigoCompetencia;
	}

	public void setCodigoCompetencia(int codigoCompetencia) {
		this.codigoCompetencia = codigoCompetencia;
	}

	@Column(name = "codigo_categoria", nullable = false)
	public int getCodigoCategoria() {
		return this.codigoCategoria;
	}

	public void setCodigoCategoria(int codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CategoriaCompetenciaId))
			return false;
		CategoriaCompetenciaId castOther = (CategoriaCompetenciaId) other;

		return (this.getCodigoCompetencia() == castOther.getCodigoCompetencia())
				&& (this.getCodigoCategoria() == castOther.getCodigoCategoria());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodigoCompetencia();
		result = 37 * result + this.getCodigoCategoria();
		return result;
	}

}
