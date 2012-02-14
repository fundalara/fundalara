package modelo;

// Generated 13/02/2012 08:44:08 PM by Hibernate Tools 3.4.0.CR1

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
 * Rol generated by hbm2java
 */
@Entity
@Table(name = "rol", schema = "public")
public class Rol implements java.io.Serializable {

	private int codigoRol;
	private Rol rol;
	private String nombre;
	private char estatus;
	private Set<Rol> rols = new HashSet<Rol>(0);
	private Set<RolGrupo> rolGrupos = new HashSet<RolGrupo>(0);

	public Rol() {
	}

	public Rol(int codigoRol, String nombre, char estatus) {
		this.codigoRol = codigoRol;
		this.nombre = nombre;
		this.estatus = estatus;
	}

	public Rol(int codigoRol, Rol rol, String nombre, char estatus,
			Set<Rol> rols, Set<RolGrupo> rolGrupos) {
		this.codigoRol = codigoRol;
		this.rol = rol;
		this.nombre = nombre;
		this.estatus = estatus;
		this.rols = rols;
		this.rolGrupos = rolGrupos;
	}

	@Id
	@Column(name = "codigo_rol", unique = true, nullable = false)
	public int getCodigoRol() {
		return this.codigoRol;
	}

	public void setCodigoRol(int codigoRol) {
		this.codigoRol = codigoRol;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_codigo_rol")
	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rol")
	public Set<Rol> getRols() {
		return this.rols;
	}

	public void setRols(Set<Rol> rols) {
		this.rols = rols;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rol")
	public Set<RolGrupo> getRolGrupos() {
		return this.rolGrupos;
	}

	public void setRolGrupos(Set<RolGrupo> rolGrupos) {
		this.rolGrupos = rolGrupos;
	}

}
