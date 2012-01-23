package modelo;

// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PuntuacionJugadorId generated by hbm2java
 */
@Embeddable
public class PuntuacionJugadorId implements java.io.Serializable {

	private int codigoDesempennoJugador;
	private int codigoIndicadorActividadEscala;

	public PuntuacionJugadorId() {
	}

	public PuntuacionJugadorId(int codigoDesempennoJugador,
			int codigoIndicadorActividadEscala) {
		this.codigoDesempennoJugador = codigoDesempennoJugador;
		this.codigoIndicadorActividadEscala = codigoIndicadorActividadEscala;
	}

	@Column(name = "codigo_desempenno_jugador", nullable = false)
	public int getCodigoDesempennoJugador() {
		return this.codigoDesempennoJugador;
	}

	public void setCodigoDesempennoJugador(int codigoDesempennoJugador) {
		this.codigoDesempennoJugador = codigoDesempennoJugador;
	}

	@Column(name = "codigo_indicador_actividad_escala", nullable = false)
	public int getCodigoIndicadorActividadEscala() {
		return this.codigoIndicadorActividadEscala;
	}

	public void setCodigoIndicadorActividadEscala(
			int codigoIndicadorActividadEscala) {
		this.codigoIndicadorActividadEscala = codigoIndicadorActividadEscala;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PuntuacionJugadorId))
			return false;
		PuntuacionJugadorId castOther = (PuntuacionJugadorId) other;

		return (this.getCodigoDesempennoJugador() == castOther
				.getCodigoDesempennoJugador())
				&& (this.getCodigoIndicadorActividadEscala() == castOther
						.getCodigoIndicadorActividadEscala());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodigoDesempennoJugador();
		result = 37 * result + this.getCodigoIndicadorActividadEscala();
		return result;
	}

}
