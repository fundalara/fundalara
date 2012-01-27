package modelo;

<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RepresentanteJugadorPlanId generated by hbm2java
 */
@Embeddable
public class RepresentanteJugadorPlanId implements java.io.Serializable {

	private String cedulaRif;
	private String cedulaFamiliar;

	public RepresentanteJugadorPlanId() {
	}

	public RepresentanteJugadorPlanId(String cedulaRif, String cedulaFamiliar) {
		this.cedulaRif = cedulaRif;
		this.cedulaFamiliar = cedulaFamiliar;
	}

	@Column(name = "cedula_rif", nullable = false)
	public String getCedulaRif() {
		return this.cedulaRif;
	}

	public void setCedulaRif(String cedulaRif) {
		this.cedulaRif = cedulaRif;
	}

	@Column(name = "cedula_familiar", nullable = false)
	public String getCedulaFamiliar() {
		return this.cedulaFamiliar;
	}

	public void setCedulaFamiliar(String cedulaFamiliar) {
		this.cedulaFamiliar = cedulaFamiliar;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RepresentanteJugadorPlanId))
			return false;
		RepresentanteJugadorPlanId castOther = (RepresentanteJugadorPlanId) other;

		return ((this.getCedulaRif() == castOther.getCedulaRif()) || (this
				.getCedulaRif() != null && castOther.getCedulaRif() != null && this
				.getCedulaRif().equals(castOther.getCedulaRif())))
				&& ((this.getCedulaFamiliar() == castOther.getCedulaFamiliar()) || (this
						.getCedulaFamiliar() != null
						&& castOther.getCedulaFamiliar() != null && this
						.getCedulaFamiliar().equals(
								castOther.getCedulaFamiliar())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCedulaRif() == null ? 0 : this.getCedulaRif().hashCode());
		result = 37
				* result
				+ (getCedulaFamiliar() == null ? 0 : this.getCedulaFamiliar()
						.hashCode());
		return result;
	}

}
