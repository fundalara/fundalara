package modelo;

// Generated 10/02/2012 11:18:51 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RolGrupoId generated by hbm2java
 */
@Embeddable
public class RolGrupoId implements java.io.Serializable {

	private int codigoRol;
	private int codigoGrupo;

	public RolGrupoId() {
	}

	public RolGrupoId(int codigoRol, int codigoGrupo) {
		this.codigoRol = codigoRol;
		this.codigoGrupo = codigoGrupo;
	}

	@Column(name = "codigo_rol", nullable = false)
	public int getCodigoRol() {
		return this.codigoRol;
	}

	public void setCodigoRol(int codigoRol) {
		this.codigoRol = codigoRol;
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
		if (!(other instanceof RolGrupoId))
			return false;
		RolGrupoId castOther = (RolGrupoId) other;

		return (this.getCodigoRol() == castOther.getCodigoRol())
				&& (this.getCodigoGrupo() == castOther.getCodigoGrupo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodigoRol();
		result = 37 * result + this.getCodigoGrupo();
		return result;
	}

}
