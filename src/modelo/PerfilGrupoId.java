package modelo;

// Generated 27/01/2012 03:27:22 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PerfilGrupoId generated by hbm2java
 */
@Embeddable
public class PerfilGrupoId implements java.io.Serializable {

	private int codigoPerfil;
	private int codigoGrupo;

	public PerfilGrupoId() {
	}

	public PerfilGrupoId(int codigoPerfil, int codigoGrupo) {
		this.codigoPerfil = codigoPerfil;
		this.codigoGrupo = codigoGrupo;
	}

	@Column(name = "codigo_perfil", nullable = false)
	public int getCodigoPerfil() {
		return this.codigoPerfil;
	}

	public void setCodigoPerfil(int codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}

	@Column(name = "codigo_grupo", nullable = false)
	public int getCodigoGrupo() {
		return this.codigoGrupo;
	}

	public void setCodigoGrupo(int codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PerfilGrupoId))
			return false;
		PerfilGrupoId castOther = (PerfilGrupoId) other;

		return (this.getCodigoPerfil() == castOther.getCodigoPerfil())
				&& (this.getCodigoGrupo() == castOther.getCodigoGrupo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodigoPerfil();
		result = 37 * result + this.getCodigoGrupo();
		return result;
	}

}
