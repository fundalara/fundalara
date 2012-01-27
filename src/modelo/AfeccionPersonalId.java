package modelo;

// Generated 27/01/2012 03:27:22 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AfeccionPersonalId generated by hbm2java
 */
@Embeddable
public class AfeccionPersonalId implements java.io.Serializable {

	private String cedulaRif;
	private int codigoTipoAfeccion;

	public AfeccionPersonalId() {
	}

	public AfeccionPersonalId(String cedulaRif, int codigoTipoAfeccion) {
		this.cedulaRif = cedulaRif;
		this.codigoTipoAfeccion = codigoTipoAfeccion;
	}

	@Column(name = "cedula_rif", nullable = false)
	public String getCedulaRif() {
		return this.cedulaRif;
	}

	public void setCedulaRif(String cedulaRif) {
		this.cedulaRif = cedulaRif;
	}

	@Column(name = "codigo_tipo_afeccion", nullable = false)
	public int getCodigoTipoAfeccion() {
		return this.codigoTipoAfeccion;
	}

	public void setCodigoTipoAfeccion(int codigoTipoAfeccion) {
		this.codigoTipoAfeccion = codigoTipoAfeccion;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AfeccionPersonalId))
			return false;
		AfeccionPersonalId castOther = (AfeccionPersonalId) other;

		return ((this.getCedulaRif() == castOther.getCedulaRif()) || (this
				.getCedulaRif() != null && castOther.getCedulaRif() != null && this
				.getCedulaRif().equals(castOther.getCedulaRif())))
				&& (this.getCodigoTipoAfeccion() == castOther
						.getCodigoTipoAfeccion());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCedulaRif() == null ? 0 : this.getCedulaRif().hashCode());
		result = 37 * result + this.getCodigoTipoAfeccion();
		return result;
	}

}
