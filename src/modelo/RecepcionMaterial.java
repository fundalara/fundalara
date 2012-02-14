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
 * RecepcionMaterial generated by hbm2java
 */
@Entity
@Table(name = "recepcion_material", schema = "public")
public class RecepcionMaterial implements java.io.Serializable {

	private RecepcionMaterialId id;
	private Material material;
	private NotaEntrega notaEntrega;
	private int cantidadRecibida;
	private String observaciones;
	private char estatus;

	public RecepcionMaterial() {
	}

	public RecepcionMaterial(RecepcionMaterialId id, Material material,
			NotaEntrega notaEntrega, int cantidadRecibida, char estatus) {
		this.id = id;
		this.material = material;
		this.notaEntrega = notaEntrega;
		this.cantidadRecibida = cantidadRecibida;
		this.estatus = estatus;
	}

	public RecepcionMaterial(RecepcionMaterialId id, Material material,
			NotaEntrega notaEntrega, int cantidadRecibida,
			String observaciones, char estatus) {
		this.id = id;
		this.material = material;
		this.notaEntrega = notaEntrega;
		this.cantidadRecibida = cantidadRecibida;
		this.observaciones = observaciones;
		this.estatus = estatus;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoMaterial", column = @Column(name = "codigo_material", nullable = false)),
			@AttributeOverride(name = "codigoNotaEntrega", column = @Column(name = "codigo_nota_entrega", nullable = false)) })
	public RecepcionMaterialId getId() {
		return this.id;
	}

	public void setId(RecepcionMaterialId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_material", nullable = false, insertable = false, updatable = false)
	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_nota_entrega", nullable = false, insertable = false, updatable = false)
	public NotaEntrega getNotaEntrega() {
		return this.notaEntrega;
	}

	public void setNotaEntrega(NotaEntrega notaEntrega) {
		this.notaEntrega = notaEntrega;
	}

	@Column(name = "cantidad_recibida", nullable = false)
	public int getCantidadRecibida() {
		return this.cantidadRecibida;
	}

	public void setCantidadRecibida(int cantidadRecibida) {
		this.cantidadRecibida = cantidadRecibida;
	}

	@Column(name = "observaciones")
	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
