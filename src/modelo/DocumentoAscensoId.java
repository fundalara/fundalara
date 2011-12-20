package modelo;

// Generated 19-dic-2011 14:08:48 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DocumentoAscensoId generated by hbm2java
 */
@Embeddable
public class DocumentoAscensoId implements java.io.Serializable {

	private int codigoDocumentoEntregado;
	private int codigoAscenso;

	public DocumentoAscensoId() {
	}

	public DocumentoAscensoId(int codigoDocumentoEntregado, int codigoAscenso) {
		this.codigoDocumentoEntregado = codigoDocumentoEntregado;
		this.codigoAscenso = codigoAscenso;
	}

	@Column(name = "codigo_documento_entregado", nullable = false)
	public int getCodigoDocumentoEntregado() {
		return this.codigoDocumentoEntregado;
	}

	public void setCodigoDocumentoEntregado(int codigoDocumentoEntregado) {
		this.codigoDocumentoEntregado = codigoDocumentoEntregado;
	}

	@Column(name = "codigo_ascenso", nullable = false)
	public int getCodigoAscenso() {
		return this.codigoAscenso;
	}

	public void setCodigoAscenso(int codigoAscenso) {
		this.codigoAscenso = codigoAscenso;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DocumentoAscensoId))
			return false;
		DocumentoAscensoId castOther = (DocumentoAscensoId) other;

		return (this.getCodigoDocumentoEntregado() == castOther
				.getCodigoDocumentoEntregado())
				&& (this.getCodigoAscenso() == castOther.getCodigoAscenso());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodigoDocumentoEntregado();
		result = 37 * result + this.getCodigoAscenso();
		return result;
	}

}
