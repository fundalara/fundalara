package modelo;

// Generated 05/12/2011 10:49:17 AM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Proveedor generated by hbm2java
 */
@Entity
@Table(name = "proveedor")
public class Proveedor implements java.io.Serializable {

	private String rifNic;
	private Parroquia parroquia;
	private String codMunicipio;
	private String cuentaFacebook;
	private String cuentaTwitter;
	private String correoElectronico;
	private String direccion;
	private String nombre;
	private String telefono1;
	private String telefono2;
	private char estatus;
	private String fax;
	private Set<ProveedorBanco> proveedorBancos = new HashSet<ProveedorBanco>(0);

	public Proveedor() {
	}

	public Proveedor(String rifNic, Parroquia parroquia, String codMunicipio,
			String direccion, String nombre, String telefono1, char estatus) {
		this.rifNic = rifNic;
		this.parroquia = parroquia;
		this.codMunicipio = codMunicipio;
		this.direccion = direccion;
		this.nombre = nombre;
		this.telefono1 = telefono1;
		this.estatus = estatus;
	}

	public Proveedor(String rifNic, Parroquia parroquia, String codMunicipio,
			String cuentaFacebook, String cuentaTwitter,
			String correoElectronico, String direccion, String nombre,
			String telefono1, String telefono2, char estatus, String fax,
			Set<ProveedorBanco> proveedorBancos) {
		this.rifNic = rifNic;
		this.parroquia = parroquia;
		this.codMunicipio = codMunicipio;
		this.cuentaFacebook = cuentaFacebook;
		this.cuentaTwitter = cuentaTwitter;
		this.correoElectronico = correoElectronico;
		this.direccion = direccion;
		this.nombre = nombre;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.estatus = estatus;
		this.fax = fax;
		this.proveedorBancos = proveedorBancos;
	}

	@Id
	@Column(name = "rif_nic", unique = true, nullable = false)
	public String getRifNic() {
		return this.rifNic;
	}

	public void setRifNic(String rifNic) {
		this.rifNic = rifNic;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_parroquia", nullable = false)
	public Parroquia getParroquia() {
		return this.parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}

	@Column(name = "cod_municipio", nullable = false)
	public String getCodMunicipio() {
		return this.codMunicipio;
	}

	public void setCodMunicipio(String codMunicipio) {
		this.codMunicipio = codMunicipio;
	}

	@Column(name = "cuenta_facebook")
	public String getCuentaFacebook() {
		return this.cuentaFacebook;
	}

	public void setCuentaFacebook(String cuentaFacebook) {
		this.cuentaFacebook = cuentaFacebook;
	}

	@Column(name = "cuenta_twitter")
	public String getCuentaTwitter() {
		return this.cuentaTwitter;
	}

	public void setCuentaTwitter(String cuentaTwitter) {
		this.cuentaTwitter = cuentaTwitter;
	}

	@Column(name = "correo_electronico")
	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	@Column(name = "direccion", nullable = false)
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "telefono1", nullable = false)
	public String getTelefono1() {
		return this.telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	@Column(name = "telefono2")
	public String getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@Column(name = "fax")
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proveedor")
	public Set<ProveedorBanco> getProveedorBancos() {
		return this.proveedorBancos;
	}

	public void setProveedorBancos(Set<ProveedorBanco> proveedorBancos) {
		this.proveedorBancos = proveedorBancos;
	}

}
