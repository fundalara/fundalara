package modelo;

<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EgresoCuentaPagarId generated by hbm2java
 */
@Embeddable
public class EgresoCuentaPagarId implements java.io.Serializable {

	private String origen;
	private int codigoEgreso;

	public EgresoCuentaPagarId() {
	}

	public EgresoCuentaPagarId(String origen, int codigoEgreso) {
		this.origen = origen;
		this.codigoEgreso = codigoEgreso;
	}

	@Column(name = "origen", nullable = false)
	public String getOrigen() {
		return this.origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	@Column(name = "codigo_egreso", nullable = false)
	public int getCodigoEgreso() {
		return this.codigoEgreso;
	}

	public void setCodigoEgreso(int codigoEgreso) {
		this.codigoEgreso = codigoEgreso;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EgresoCuentaPagarId))
			return false;
		EgresoCuentaPagarId castOther = (EgresoCuentaPagarId) other;

		return ((this.getOrigen() == castOther.getOrigen()) || (this
				.getOrigen() != null && castOther.getOrigen() != null && this
				.getOrigen().equals(castOther.getOrigen())))
				&& (this.getCodigoEgreso() == castOther.getCodigoEgreso());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOrigen() == null ? 0 : this.getOrigen().hashCode());
		result = 37 * result + this.getCodigoEgreso();
		return result;
	}

}
