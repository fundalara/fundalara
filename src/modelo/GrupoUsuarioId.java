package modelo;

// Generated 13/02/2012 08:44:08 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * GrupoUsuarioId generated by hbm2java
 */
@Embeddable
public class GrupoUsuarioId implements java.io.Serializable {

	private String cedulaRif;
	private int codigoGrupo;

	public GrupoUsuarioId() {
	}

	public GrupoUsuarioId(String cedulaRif, int codigoGrupo) {
		this.cedulaRif = cedulaRif;
		this.codigoGrupo = codigoGrupo;
	}

	@Column(name = "cedula_rif", nullable = false)
	public String getCedulaRif() {
		return this.cedulaRif;
	}

	public void setCedulaRif(String cedulaRif) {
		this.cedulaRif = cedulaRif;
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
		if (!(other instanceof GrupoUsuarioId))
			return false;
		GrupoUsuarioId castOther = (GrupoUsuarioId) other;

		return ((this.getCedulaRif() == castOther.getCedulaRif()) || (this
				.getCedulaRif() != null && castOther.getCedulaRif() != null && this
				.getCedulaRif().equals(castOther.getCedulaRif())))
				&& (this.getCodigoGrupo() == castOther.getCodigoGrupo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCedulaRif() == null ? 0 : this.getCedulaRif().hashCode());
		result = 37 * result + this.getCodigoGrupo();
		return result;
	}

}
