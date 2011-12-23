package modelo;

// Generated Dec 23, 2011 1:26:53 PM by Hibernate Tools 3.4.0.CR1

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
 * Divisa generated by hbm2java
 */
@Entity
@Table(name = "divisa", schema = "public")
public class Divisa implements java.io.Serializable {

	private int codigoDivisa;
	private DatoBasico datoBasico;
	private String nombre;
	private String direccion;
	private String telefono;
	private String correoElectronico;
	private String personaContacto;
	private byte[] logo;
	private char estatus;
	private Set<Equipo> equipos = new HashSet<Equipo>(0);

	public Divisa() {
	}

	public Divisa(int codigoDivisa, DatoBasico datoBasico, String nombre,
			String direccion, String telefono, String correoElectronico,
			String personaContacto, char estatus) {
		this.codigoDivisa = codigoDivisa;
		this.datoBasico = datoBasico;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correoElectronico = correoElectronico;
		this.personaContacto = personaContacto;
		this.estatus = estatus;
	}

	public Divisa(int codigoDivisa, DatoBasico datoBasico, String nombre,
			String direccion, String telefono, String correoElectronico,
			String personaContacto, byte[] logo, char estatus,
			Set<Equipo> equipos) {
		this.codigoDivisa = codigoDivisa;
		this.datoBasico = datoBasico;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correoElectronico = correoElectronico;
		this.personaContacto = personaContacto;
		this.logo = logo;
		this.estatus = estatus;
		this.equipos = equipos;
	}

	@Id
	@Column(name = "codigo_divisa", unique = true, nullable = false)
	public int getCodigoDivisa() {
		return this.codigoDivisa;
	}

	public void setCodigoDivisa(int codigoDivisa) {
		this.codigoDivisa = codigoDivisa;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_parroquia", nullable = false)
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "direccion", nullable = false)
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "telefono", nullable = false)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "correo_electronico", nullable = false)
	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	@Column(name = "persona_contacto", nullable = false)
	public String getPersonaContacto() {
		return this.personaContacto;
	}

	public void setPersonaContacto(String personaContacto) {
		this.personaContacto = personaContacto;
	}

	@Column(name = "logo")
	public byte[] getLogo() {
		return this.logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "divisa")
	public Set<Equipo> getEquipos() {
		return this.equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

}
