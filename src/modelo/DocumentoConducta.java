package modelo;

// Generated 13/01/2012 02:49:46 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DocumentoConducta generated by hbm2java
 */
@Entity
@Table(name = "documento_conducta", schema = "public")
public class DocumentoConducta implements java.io.Serializable {

	private DocumentoConductaId id;
	private DocumentoEntregado documentoEntregado;
	private DatoConducta datoConducta;
	private char estatus;

	public DocumentoConducta() {
	}

	public DocumentoConducta(DocumentoConductaId id,
			DocumentoEntregado documentoEntregado, DatoConducta datoConducta,
			char estatus) {
		this.id = id;
		this.documentoEntregado = documentoEntregado;
		this.datoConducta = datoConducta;
		this.estatus = estatus;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoDocumentoEntregado", column = @Column(name = "codigo_documento_entregado", nullable = false)),
			@AttributeOverride(name = "codigoDatoConducta", column = @Column(name = "codigo_dato_conducta", nullable = false)) })
	public DocumentoConductaId getId() {
		return this.id;
	}

	public void setId(DocumentoConductaId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_documento_entregado", nullable = false, insertable = false, updatable = false)
	public DocumentoEntregado getDocumentoEntregado() {
		return this.documentoEntregado;
	}

	public void setDocumentoEntregado(DocumentoEntregado documentoEntregado) {
		this.documentoEntregado = documentoEntregado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_dato_conducta", nullable = false, insertable = false, updatable = false)
	public DatoConducta getDatoConducta() {
		return this.datoConducta;
	}

	public void setDatoConducta(DatoConducta datoConducta) {
		this.datoConducta = datoConducta;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
