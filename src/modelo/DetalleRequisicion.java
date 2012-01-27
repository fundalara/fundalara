package modelo;

// Generated 27/01/2012 03:27:22 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DetalleRequisicion generated by hbm2java
 */
@Entity
@Table(name = "detalle_requisicion", schema = "public")
public class DetalleRequisicion implements java.io.Serializable {

	private int codigoDetalleRequisicion;
	private Material material;
	private Requisicion requisicion;
	private int cantidadSolicitada;
	private Integer cantidadEntregada;
	private char estatus;

	public DetalleRequisicion() {
	}

	public DetalleRequisicion(int codigoDetalleRequisicion, Material material,
			Requisicion requisicion, int cantidadSolicitada, char estatus) {
		this.codigoDetalleRequisicion = codigoDetalleRequisicion;
		this.material = material;
		this.requisicion = requisicion;
		this.cantidadSolicitada = cantidadSolicitada;
		this.estatus = estatus;
	}

	public DetalleRequisicion(int codigoDetalleRequisicion, Material material,
			Requisicion requisicion, int cantidadSolicitada,
			Integer cantidadEntregada, char estatus) {
		this.codigoDetalleRequisicion = codigoDetalleRequisicion;
		this.material = material;
		this.requisicion = requisicion;
		this.cantidadSolicitada = cantidadSolicitada;
		this.cantidadEntregada = cantidadEntregada;
		this.estatus = estatus;
	}

	@Id
	@Column(name = "codigo_detalle_requisicion", unique = true, nullable = false)
	public int getCodigoDetalleRequisicion() {
		return this.codigoDetalleRequisicion;
	}

	public void setCodigoDetalleRequisicion(int codigoDetalleRequisicion) {
		this.codigoDetalleRequisicion = codigoDetalleRequisicion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_material", nullable = false)
	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_requisicion", nullable = false)
	public Requisicion getRequisicion() {
		return this.requisicion;
	}

	public void setRequisicion(Requisicion requisicion) {
		this.requisicion = requisicion;
	}

	@Column(name = "cantidad_solicitada", nullable = false)
	public int getCantidadSolicitada() {
		return this.cantidadSolicitada;
	}

	public void setCantidadSolicitada(int cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}

	@Column(name = "cantidad_entregada")
	public Integer getCantidadEntregada() {
		return this.cantidadEntregada;
	}

	public void setCantidadEntregada(Integer cantidadEntregada) {
		this.cantidadEntregada = cantidadEntregada;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
