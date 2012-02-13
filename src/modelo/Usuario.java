package modelo;

// Generated 11/02/2012 01:49:19 AM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "usuario", schema = "public")
public class Usuario implements java.io.Serializable {

	private String cedulaRif;
	private Personal personal;
	private String nick;
	private String password;
	private char estatus;
	private Set<GrupoUsuario> grupoUsuarios = new HashSet<GrupoUsuario>(0);
	private Set<SeguridadFuncional> seguridadFuncionals = new HashSet<SeguridadFuncional>(
			0);

	public Usuario() {
	}

	public Usuario(Personal personal, String nick, String password, char estatus) {
		this.personal = personal;
		this.nick = nick;
		this.password = password;
		this.estatus = estatus;
	}

	public Usuario(Personal personal, String nick, String password,
			char estatus, Set<GrupoUsuario> grupoUsuarios,
			Set<SeguridadFuncional> seguridadFuncionals) {
		this.personal = personal;
		this.nick = nick;
		this.password = password;
		this.estatus = estatus;
		this.grupoUsuarios = grupoUsuarios;
		this.seguridadFuncionals = seguridadFuncionals;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "personal"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "cedula_rif", unique = true, nullable = false)
	public String getCedulaRif() {
		return this.cedulaRif;
	}

	public void setCedulaRif(String cedulaRif) {
		this.cedulaRif = cedulaRif;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Personal getPersonal() {
		return this.personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	@Column(name = "nick", nullable = false)
	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<GrupoUsuario> getGrupoUsuarios() {
		return this.grupoUsuarios;
	}

	public void setGrupoUsuarios(Set<GrupoUsuario> grupoUsuarios) {
		this.grupoUsuarios = grupoUsuarios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<SeguridadFuncional> getSeguridadFuncionals() {
		return this.seguridadFuncionals;
	}

	public void setSeguridadFuncionals(
			Set<SeguridadFuncional> seguridadFuncionals) {
		this.seguridadFuncionals = seguridadFuncionals;
	}

}
