package modelo;

// Generated 13/01/2012 02:49:46 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PersonalActividadId generated by hbm2java
 */
@Embeddable
public class PersonalActividadId implements java.io.Serializable {

	private String cedulaRif;
	private int codigoActividad;

	public PersonalActividadId() {
	}

	public PersonalActividadId(String cedulaRif, int codigoActividad) {
		this.cedulaRif = cedulaRif;
		this.codigoActividad = codigoActividad;
	}

	@Column(name = "cedula_rif", nullable = false)
	public String getCedulaRif() {
		return this.cedulaRif;
	}

	public void setCedulaRif(String cedulaRif) {
		this.cedulaRif = cedulaRif;
	}

	@Column(name = "codigo_actividad", nullable = false)
	public int getCodigoActividad() {
		return this.codigoActividad;
	}

	public void setCodigoActividad(int codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PersonalActividadId))
			return false;
		PersonalActividadId castOther = (PersonalActividadId) other;

		return ((this.getCedulaRif() == castOther.getCedulaRif()) || (this
				.getCedulaRif() != null && castOther.getCedulaRif() != null && this
				.getCedulaRif().equals(castOther.getCedulaRif())))
				&& (this.getCodigoActividad() == castOther.getCodigoActividad());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCedulaRif() == null ? 0 : this.getCedulaRif().hashCode());
		result = 37 * result + this.getCodigoActividad();
		return result;
	}

}
