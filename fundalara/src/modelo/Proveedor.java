package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the proveedor database table.
 * 
 */
@Entity
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="rif_nic")
	private String rifNic;

	@Column(name="correo_electronico")
	private String correoElectronico;

	@Column(name="cuenta_facebook")
	private String cuentaFacebook;

	@Column(name="cuenta_twitter")
	private String cuentaTwitter;

	private String direccion;

	private String estatus;

	private String fax;

	private String nombre;

	private String telefono1;

	private String telefono2;

	//bi-directional many-to-one association to Parroquia
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cod_municipio", referencedColumnName="cod_parroquia"),
		@JoinColumn(name="cod_parroquia", referencedColumnName="cod_municipio")
		})
	private Parroquia parroquia;

	//bi-directional many-to-one association to ProveedorBanco
	@OneToMany(mappedBy="proveedor")
	private Set<ProveedorBanco> proveedorBancos;

    public Proveedor() {
    }

	public String getRifNic() {
		return this.rifNic;
	}

	public void setRifNic(String rifNic) {
		this.rifNic = rifNic;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getCuentaFacebook() {
		return this.cuentaFacebook;
	}

	public void setCuentaFacebook(String cuentaFacebook) {
		this.cuentaFacebook = cuentaFacebook;
	}

	public String getCuentaTwitter() {
		return this.cuentaTwitter;
	}

	public void setCuentaTwitter(String cuentaTwitter) {
		this.cuentaTwitter = cuentaTwitter;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono1() {
		return this.telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public Parroquia getParroquia() {
		return this.parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}
	
	public Set<ProveedorBanco> getProveedorBancos() {
		return this.proveedorBancos;
	}

	public void setProveedorBancos(Set<ProveedorBanco> proveedorBancos) {
		this.proveedorBancos = proveedorBancos;
	}
	
}