package modelo;

<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DesempennoColectivoId generated by hbm2java
 */
@Embeddable
public class DesempennoColectivoId implements java.io.Serializable {

	private int codigoEquipoJuego;
	private int codigoIndicadorCategoriaCompetencia;

	public DesempennoColectivoId() {
	}

	public DesempennoColectivoId(int codigoEquipoJuego,
			int codigoIndicadorCategoriaCompetencia) {
		this.codigoEquipoJuego = codigoEquipoJuego;
		this.codigoIndicadorCategoriaCompetencia = codigoIndicadorCategoriaCompetencia;
	}

	@Column(name = "codigo_equipo_juego", nullable = false)
	public int getCodigoEquipoJuego() {
		return this.codigoEquipoJuego;
	}

	public void setCodigoEquipoJuego(int codigoEquipoJuego) {
		this.codigoEquipoJuego = codigoEquipoJuego;
	}

	@Column(name = "codigo_indicador_categoria_competencia", nullable = false)
	public int getCodigoIndicadorCategoriaCompetencia() {
		return this.codigoIndicadorCategoriaCompetencia;
	}

	public void setCodigoIndicadorCategoriaCompetencia(
			int codigoIndicadorCategoriaCompetencia) {
		this.codigoIndicadorCategoriaCompetencia = codigoIndicadorCategoriaCompetencia;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DesempennoColectivoId))
			return false;
		DesempennoColectivoId castOther = (DesempennoColectivoId) other;

		return (this.getCodigoEquipoJuego() == castOther.getCodigoEquipoJuego())
				&& (this.getCodigoIndicadorCategoriaCompetencia() == castOther
						.getCodigoIndicadorCategoriaCompetencia());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodigoEquipoJuego();
		result = 37 * result + this.getCodigoIndicadorCategoriaCompetencia();
		return result;
	}

}
