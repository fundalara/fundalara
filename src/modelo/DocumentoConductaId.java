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
 * DocumentoConductaId generated by hbm2java
 */
@Embeddable
public class DocumentoConductaId implements java.io.Serializable {

	private int codigoDocumentoEntregado;
	private int codigoDatoConducta;

	public DocumentoConductaId() {
	}

	public DocumentoConductaId(int codigoDocumentoEntregado,
			int codigoDatoConducta) {
		this.codigoDocumentoEntregado = codigoDocumentoEntregado;
		this.codigoDatoConducta = codigoDatoConducta;
	}

	@Column(name = "codigo_documento_entregado", nullable = false)
	public int getCodigoDocumentoEntregado() {
		return this.codigoDocumentoEntregado;
	}

	public void setCodigoDocumentoEntregado(int codigoDocumentoEntregado) {
		this.codigoDocumentoEntregado = codigoDocumentoEntregado;
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
		if (!(other instanceof DocumentoConductaId))
			return false;
		DocumentoConductaId castOther = (DocumentoConductaId) other;

		return (this.getCodigoDocumentoEntregado() == castOther
				.getCodigoDocumentoEntregado())
				&& (this.getCodigoDatoConducta() == castOther
						.getCodigoDatoConducta());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodigoDocumentoEntregado();
		result = 37 * result + this.getCodigoDatoConducta();
		return result;
	}

}
