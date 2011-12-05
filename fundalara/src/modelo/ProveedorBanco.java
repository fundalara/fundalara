package modelo;

// Generated 05/12/2011 10:49:17 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ProveedorBanco generated by hbm2java
 */
@Entity
@Table(name = "proveedor_banco")
public class ProveedorBanco implements java.io.Serializable {

	private String numeroCuenta;
	private Proveedor proveedor;
	private TipoCuentaBanco tipoCuentaBanco;
	private Banco banco;
	private String descripcion;
	private String titular;
	private char estatus;

	public ProveedorBanco() {
	}

	public ProveedorBanco(String numeroCuenta, Proveedor proveedor,
			TipoCuentaBanco tipoCuentaBanco, Banco banco, String descripcion,
			String titular, char estatus) {
		this.numeroCuenta = numeroCuenta;
		this.proveedor = proveedor;
		this.tipoCuentaBanco = tipoCuentaBanco;
		this.banco = banco;
		this.descripcion = descripcion;
		this.titular = titular;
		this.estatus = estatus;
	}

	@Id
	@Column(name = "numero_cuenta", unique = true, nullable = false)
	public String getNumeroCuenta() {
		return this.numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rif_nic", nullable = false)
	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_cuenta_banco", nullable = false)
	public TipoCuentaBanco getTipoCuentaBanco() {
		return this.tipoCuentaBanco;
	}

	public void setTipoCuentaBanco(TipoCuentaBanco tipoCuentaBanco) {
		this.tipoCuentaBanco = tipoCuentaBanco;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_banco", nullable = false)
	public Banco getBanco() {
		return this.banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	@Column(name = "descripcion", nullable = false)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "titular", nullable = false)
	public String getTitular() {
		return this.titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
