package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the proveedor_banco database table.
 * 
 */
@Entity
@Table(name="proveedor_banco")
public class ProveedorBanco implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProveedorBancoPK id;

	private String descripcion;

	private String estatus;

	private String titular;

	//bi-directional many-to-one association to Banco
    @ManyToOne
	@JoinColumn(name="codigo_banco")
	private Banco banco;

	//bi-directional many-to-one association to Proveedor
    @ManyToOne
	@JoinColumn(name="rif_nic")
	private Proveedor proveedor;

	//bi-directional many-to-one association to TipoCuentaBanco
    @ManyToOne
	@JoinColumn(name="codigo_cuenta_banco")
	private TipoCuentaBanco tipoCuentaBanco;

    public ProveedorBanco() {
    }

	public ProveedorBancoPK getId() {
		return this.id;
	}

	public void setId(ProveedorBancoPK id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getTitular() {
		return this.titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Banco getBanco() {
		return this.banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public TipoCuentaBanco getTipoCuentaBanco() {
		return this.tipoCuentaBanco;
	}

	public void setTipoCuentaBanco(TipoCuentaBanco tipoCuentaBanco) {
		this.tipoCuentaBanco = tipoCuentaBanco;
	}
	
}