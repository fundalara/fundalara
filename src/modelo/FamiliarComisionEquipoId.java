package modelo;

// Generated 20-dic-2011 13:32:22 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FamiliarComisionEquipoId generated by hbm2java
 */
@Embeddable
public class FamiliarComisionEquipoId implements java.io.Serializable {

	private int codigoFamiliarJugador;
	private int estatus;

	public FamiliarComisionEquipoId() {
	}

	public FamiliarComisionEquipoId(int codigoFamiliarJugador, int estatus) {
		this.codigoFamiliarJugador = codigoFamiliarJugador;
		this.estatus = estatus;
	}

	@Column(name = "codigo_familiar_jugador", nullable = false)
	public int getCodigoFamiliarJugador() {
		return this.codigoFamiliarJugador;
	}

	public void setCodigoFamiliarJugador(int codigoFamiliarJugador) {
		this.codigoFamiliarJugador = codigoFamiliarJugador;
	}

	@Column(name = "estatus", nullable = false)
	public int getEstatus() {
		return this.estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FamiliarComisionEquipoId))
			return false;
		FamiliarComisionEquipoId castOther = (FamiliarComisionEquipoId) other;

		return (this.getCodigoFamiliarJugador() == castOther
				.getCodigoFamiliarJugador())
				&& (this.getEstatus() == castOther.getEstatus());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodigoFamiliarJugador();
		result = 37 * result + this.getEstatus();
		return result;
	}

}