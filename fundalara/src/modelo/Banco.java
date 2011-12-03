package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the banco database table.
 * 
 */
@Entity
public class Banco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_banco")
	private String codigoBanco;

	private String descripcion;

	private String estatus;

	//bi-directional many-to-one association to ProveedorBanco
	@OneToMany(mappedBy="banco")
	private Set<ProveedorBanco> proveedorBancos;

    public Banco() {
    }

	public String getCodigoBanco() {
		return this.codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
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