package modelo;

// Generated 13/02/2012 08:44:08 PM by Hibernate Tools 3.4.0.CR1

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
 * DocumentoAcreedorMaterial generated by hbm2java
 */
@Entity
@Table(name = "documento_acreedor_material", schema = "public")
public class DocumentoAcreedorMaterial implements java.io.Serializable {

	private DocumentoAcreedorMaterialId id;
	private DocumentoAcreedor documentoAcreedor;
	private Material material;
	private char estatus;
	private int cantidad;

	public DocumentoAcreedorMaterial() {
	}

	public DocumentoAcreedorMaterial(DocumentoAcreedorMaterialId id,
			DocumentoAcreedor documentoAcreedor, Material material,
			char estatus, int cantidad) {
		this.id = id;
		this.documentoAcreedor = documentoAcreedor;
		this.material = material;
		this.estatus = estatus;
		this.cantidad = cantidad;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoMaterial", column = @Column(name = "codigo_material", nullable = false)),
			@AttributeOverride(name = "codigoDocumentoAcreedor", column = @Column(name = "codigo_documento_acreedor", nullable = false)) })
	public DocumentoAcreedorMaterialId getId() {
		return this.id;
	}

	public void setId(DocumentoAcreedorMaterialId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_documento_acreedor", nullable = false, insertable = false, updatable = false)
	public DocumentoAcreedor getDocumentoAcreedor() {
		return this.documentoAcreedor;
	}

	public void setDocumentoAcreedor(DocumentoAcreedor documentoAcreedor) {
		this.documentoAcreedor = documentoAcreedor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_material", nullable = false, insertable = false, updatable = false)
	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@Column(name = "cantidad", nullable = false)
	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
