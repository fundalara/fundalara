package modelo;

// Generated 16/12/2011 03:51:27 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UmpireJuegoId generated by hbm2java
 */
@Embeddable
public class UmpireJuegoId implements java.io.Serializable {

	private String codigoJuego;
	private String codigoUmpire;

	public UmpireJuegoId() {
	}

	public UmpireJuegoId(String codigoJuego, String codigoUmpire) {
		this.codigoJuego = codigoJuego;
		this.codigoUmpire = codigoUmpire;
	}

	@Column(name = "codigo_juego", nullable = false)
	public String getCodigoJuego() {
		return this.codigoJuego;
	}

	public void setCodigoJuego(String codigoJuego) {
		this.codigoJuego = codigoJuego;
	}

	@Column(name = "codigo_umpire", nullable = false)
	public String getCodigoUmpire() {
		return this.codigoUmpire;
	}

	public void setCodigoUmpire(String codigoUmpire) {
		this.codigoUmpire = codigoUmpire;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UmpireJuegoId))
			return false;
		UmpireJuegoId castOther = (UmpireJuegoId) other;

		return ((this.getCodigoJuego() == castOther.getCodigoJuego()) || (this
				.getCodigoJuego() != null && castOther.getCodigoJuego() != null && this
				.getCodigoJuego().equals(castOther.getCodigoJuego())))
				&& ((this.getCodigoUmpire() == castOther.getCodigoUmpire()) || (this
						.getCodigoUmpire() != null
						&& castOther.getCodigoUmpire() != null && this
						.getCodigoUmpire().equals(castOther.getCodigoUmpire())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodigoJuego() == null ? 0 : this.getCodigoJuego()
						.hashCode());
		result = 37
				* result
				+ (getCodigoUmpire() == null ? 0 : this.getCodigoUmpire()
						.hashCode());
		return result;
	}

}