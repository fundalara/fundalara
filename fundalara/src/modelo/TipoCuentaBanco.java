package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tipo_cuenta_banco database table.
 * 
 */
@Entity
@Table(name="tipo_cuenta_banco")
public class TipoCuentaBanco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_cuenta_banco")
	private String codigoCuentaBanco;

	private String descripcion;

	private String estatus;

	//bi-directional many-to-one association to ProveedorBanco
	@OneToMany(mappedBy="tipoCuentaBanco")
	private Set<ProveedorBanco> proveedorBancos;

    public TipoCuentaBanco() {
    }

	public String getCodigoCuentaBanco() {
		return this.codigoCuentaBanco;
	}

	public void setCodigoCuentaBanco(String codigoCuentaBanco) {
		this.codigoCuentaBanco = codigoCuentaBanco;
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

	public Set<ProveedorBanco> getProveedorBancos() {
		return this.proveedorBancos;
	}

	public void setProveedorBancos(Set<ProveedorBanco> proveedorBancos) {
		this.proveedorBancos = proveedorBancos;
	}
	
}