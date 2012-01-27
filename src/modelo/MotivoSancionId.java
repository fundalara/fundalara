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
 * MotivoSancionId generated by hbm2java
 */
@Embeddable
public class MotivoSancionId implements java.io.Serializable {

	private int codigoMotivo;
	private int codigoDatoConducta;

	public MotivoSancionId() {
	}

	public MotivoSancionId(int codigoMotivo, int codigoDatoConducta) {
		this.codigoMotivo = codigoMotivo;
		this.codigoDatoConducta = codigoDatoConducta;
	}

	@Column(name = "codigo_motivo", nullable = false)
	public int getCodigoMotivo() {
		return this.codigoMotivo;
	}

	public void setCodigoMotivo(int codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}

	@Column(name = "codigo_dato_conducta", nullable = false)
	public int getCodigoDatoConducta() {
		return this.codigoDatoConducta;
	}

	public void setCodigoDatoConducta(int codigoDatoConducta) {
		this.codigoDatoConducta = codigoDatoConducta;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MotivoSancionId))
			return false;
		MotivoSancionId castOther = (MotivoSancionId) other;

		return (this.getCodigoMotivo() == castOther.getCodigoMotivo())
				&& (this.getCodigoDatoConducta() == castOther
						.getCodigoDatoConducta());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodigoMotivo();
		result = 37 * result + this.getCodigoDatoConducta();
		return result;
	}

}
