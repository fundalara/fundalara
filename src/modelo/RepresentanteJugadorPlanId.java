package modelo;

// Generated 13/02/2012 08:44:08 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RepresentanteJugadorPlanId generated by hbm2java
 */
@Embeddable
public class RepresentanteJugadorPlanId implements java.io.Serializable {

	private String cedulaFamiliar;
	private String cedulaRif;

	public RepresentanteJugadorPlanId() {
	}

	public RepresentanteJugadorPlanId(String cedulaFamiliar, String cedulaRif) {
		this.cedulaFamiliar = cedulaFamiliar;
		this.cedulaRif = cedulaRif;
	}

	@Column(name = "cedula_familiar", nullable = false)
	public String getCedulaFamiliar() {
		return this.cedulaFamiliar;
	}

	public void setCedulaFamiliar(String cedulaFamiliar) {
		this.cedulaFamiliar = cedulaFamiliar;
	}

	@Column(name = "cedula_rif", nullable = false)
	public String getCedulaRif() {
		return this.cedulaRif;
	}

	public void setCedulaRif(String cedulaRif) {
		this.cedulaRif = cedulaRif;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RepresentanteJugadorPlanId))
			return false;
		RepresentanteJugadorPlanId castOther = (RepresentanteJugadorPlanId) other;

		return ((this.getCedulaFamiliar() == castOther.getCedulaFamiliar()) || (this
				.getCedulaFamiliar() != null
				&& castOther.getCedulaFamiliar() != null && this
				.getCedulaFamiliar().equals(castOther.getCedulaFamiliar())))
				&& ((this.getCedulaRif() == castOther.getCedulaRif()) || (this
						.getCedulaRif() != null
						&& castOther.getCedulaRif() != null && this
						.getCedulaRif().equals(castOther.getCedulaRif())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCedulaFamiliar() == null ? 0 : this.getCedulaFamiliar()
						.hashCode());
		result = 37 * result
				+ (getCedulaRif() == null ? 0 : this.getCedulaRif().hashCode());
		return result;
	}

}
