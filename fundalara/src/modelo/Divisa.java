package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the divisa database table.
 * 
 */
@Entity
public class Divisa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_divisa")
	private String codigoDivisa;

	@Column(name="codigo_parroquia")
	private String codigoParroquia;

	@Column(name="correo_electronico")
	private String correoElectronico;

	private String direccion;

	private byte[] logo;

	private String nombre;

	@Column(name="persona_contacto")
	private String personaContacto;

	private String telefono;

	//bi-directional many-to-one association to Parroquia
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cod_municipio", referencedColumnName="cod_parroquia"),
		@JoinColumn(name="cod_parroquia", referencedColumnName="cod_municipio")
		})
	private Parroquia parroquia;

	//bi-directional many-to-one association to Equipo
	@OneToMany(mappedBy="divisa")
	private Set<Equipo> equipos;

    public Divisa() {
    }

	public String getCodigoDivisa() {
		return this.codigoDivisa;
	}

	public void setCodigoDivisa(String codigoDivisa) {
		this.codigoDivisa = codigoDivisa;
	}

	public String getCodigoParroquia() {
		return this.codigoParroquia;
	}

	public void setCodigoParroquia(String codigoParroquia) {
		this.codigoParroquia = codigoParroquia;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public byte[] getLogo() {
		return this.logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPersonaContacto() {
		return this.personaContacto;
	}

	public void setPersonaContacto(String personaContacto) {
		this.personaContacto = personaContacto;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Parroquia getParroquia() {
		return this.parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}
	
	public Set<Equipo> getEquipos() {
		return this.equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}
	
}