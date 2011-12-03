package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClientePK id;

	@Column(name="correo_electronico")
	private String correoElectronico;

	@Column(name="cuenta_facebook")
	private String cuentaFacebook;

	@Column(name="cuenta_twitter")
	private String cuentaTwitter;

	private String direccion;

	private String estatus;

	private String fax;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

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

    public Cliente() {
    }

	public ClientePK getId() {
		return this.id;
	}

	public void setId(ClientePK id) {
		this.id = id;
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

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
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
	
}