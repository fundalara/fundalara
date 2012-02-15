package modelo;

// Generated 14/02/2012 08:24:27 PM by Hibernate Tools 3.4.0.CR1

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
 * CuentaPagarMaterial generated by hbm2java
 */
@Entity
@Table(name = "cuenta_pagar_material", schema = "public")
public class CuentaPagarMaterial implements java.io.Serializable {

	private CuentaPagarMaterialId id;
	private Material material;
	private CuentaPagar cuentaPagar;
	private int cantidad;
	private double precioUnitario;
	private char estatus;

	public CuentaPagarMaterial() {
	}

	public CuentaPagarMaterial(CuentaPagarMaterialId id, Material material,
			CuentaPagar cuentaPagar, int cantidad, double precioUnitario,
			char estatus) {
		this.id = id;
		this.material = material;
		this.cuentaPagar = cuentaPagar;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.estatus = estatus;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoMaterial", column = @Column(name = "codigo_material", nullable = false)),
			@AttributeOverride(name = "codigoCuentaPagar", column = @Column(name = "codigo_cuenta_pagar", nullable = false)) })
	public CuentaPagarMaterialId getId() {
		return this.id;
	}

	public void setId(CuentaPagarMaterialId id) {
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
	@JoinColumn(name = "codigo_cuenta_pagar", nullable = false, insertable = false, updatable = false)
	public CuentaPagar getCuentaPagar() {
		return this.cuentaPagar;
	}

	public void setCuentaPagar(CuentaPagar cuentaPagar) {
		this.cuentaPagar = cuentaPagar;
	}

	@Column(name = "cantidad", nullable = false)
	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Column(name = "precio_unitario", nullable = false, precision = 17, scale = 17)
	public double getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
