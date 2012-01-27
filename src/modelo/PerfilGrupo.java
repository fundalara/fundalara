package modelo;

<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PerfilGrupo generated by hbm2java
 */
@Entity
@Table(name = "perfil_grupo", schema = "public")
public class PerfilGrupo implements java.io.Serializable {

	private PerfilGrupoId id;
	private Perfil perfil;
	private Grupo grupo;
	private char estatus;

	public PerfilGrupo() {
	}

	public PerfilGrupo(PerfilGrupoId id, Perfil perfil, Grupo grupo,
			char estatus) {
		this.id = id;
		this.perfil = perfil;
		this.grupo = grupo;
		this.estatus = estatus;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoPerfil", column = @Column(name = "codigo_perfil", nullable = false)),
			@AttributeOverride(name = "codigoGrupo", column = @Column(name = "codigo_grupo", nullable = false)) })
	public PerfilGrupoId getId() {
		return this.id;
	}

	public void setId(PerfilGrupoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_perfil", nullable = false, insertable = false, updatable = false)
	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_grupo", nullable = false, insertable = false, updatable = false)
	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
