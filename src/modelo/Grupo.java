package modelo;

// Generated 10/02/2012 01:24:38 AM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Grupo generated by hbm2java
 */
@Entity
@Table(name = "grupo", schema = "public")
public class Grupo implements java.io.Serializable {

	private int codigoGrupo;
	private String nombre;
	private char estatus;
	private Set<RolGrupo> rolGrupos = new HashSet<RolGrupo>(0);
	private Set<GrupoUsuario> grupoUsuarios = new HashSet<GrupoUsuario>(0);

	public Grupo() {
	}

	public Grupo(int codigoGrupo, String nombre, char estatus) {
		this.codigoGrupo = codigoGrupo;
		this.nombre = nombre;
		this.estatus = estatus;
	}

	public Grupo(int codigoGrupo, String nombre, char estatus,
			Set<RolGrupo> rolGrupos, Set<GrupoUsuario> grupoUsuarios) {
		this.codigoGrupo = codigoGrupo;
		this.nombre = nombre;
		this.estatus = estatus;
		this.rolGrupos = rolGrupos;
		this.grupoUsuarios = grupoUsuarios;
	}

	@Id
	@Column(name = "codigo_grupo", unique = true, nullable = false)
	public int getCodigoGrupo() {
		return this.codigoGrupo;
	}

	public void setCodigoGrupo(int codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo")
	public Set<RolGrupo> getRolGrupos() {
		return this.rolGrupos;
	}

	public void setRolGrupos(Set<RolGrupo> rolGrupos) {
		this.rolGrupos = rolGrupos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo")
	public Set<GrupoUsuario> getGrupoUsuarios() {
		return this.grupoUsuarios;
	}

	public void setGrupoUsuarios(Set<GrupoUsuario> grupoUsuarios) {
		this.grupoUsuarios = grupoUsuarios;
	}

}
