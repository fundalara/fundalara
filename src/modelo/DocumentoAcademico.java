package modelo;

// Generated 11/01/2012 03:50:04 PM by Hibernate Tools 3.4.0.CR1

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
 * DocumentoAcademico generated by hbm2java
 */
@Entity
@Table(name = "documento_academico", schema = "public")
public class DocumentoAcademico implements java.io.Serializable {

	private DocumentoAcademicoId id;
	private DocumentoEntregado documentoEntregado;
	private DatoAcademico datoAcademico;
	private char estatus;

	public DocumentoAcademico() {
	}

	public DocumentoAcademico(DocumentoAcademicoId id,
			DocumentoEntregado documentoEntregado, DatoAcademico datoAcademico,
			char estatus) {
		this.id = id;
		this.documentoEntregado = documentoEntregado;
		this.datoAcademico = datoAcademico;
		this.estatus = estatus;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoDocumentoEntregado", column = @Column(name = "codigo_documento_entregado", nullable = false)),
			@AttributeOverride(name = "codigoAcademico", column = @Column(name = "codigo_academico", nullable = false)) })
	public DocumentoAcademicoId getId() {
		return this.id;
	}

	public void setId(DocumentoAcademicoId id) {
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
	@JoinColumn(name = "codigo_academico", nullable = false, insertable = false, updatable = false)
	public DatoAcademico getDatoAcademico() {
		return this.datoAcademico;
	}

	public void setDatoAcademico(DatoAcademico datoAcademico) {
		this.datoAcademico = datoAcademico;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
