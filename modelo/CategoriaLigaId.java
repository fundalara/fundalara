package modelo;

// Generated 10/02/2012 11:18:51 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CategoriaLigaId generated by hbm2java
 */
@Embeddable
public class CategoriaLigaId implements java.io.Serializable {

	private int codigoLiga;
	private int codigoCategoria;

	public CategoriaLigaId() {
	}

	public CategoriaLigaId(int codigoLiga, int codigoCategoria) {
		this.codigoLiga = codigoLiga;
		this.codigoCategoria = codigoCategoria;
	}

	@Column(name = "codigo_liga", nullable = false)
	public int getCodigoLiga() {
		return this.codigoLiga;
	}

	public void setCodigoLiga(int codigoLiga) {
		this.codigoLiga = codigoLiga;
	}

	@Column(name = "codigo_categoria", nullable = false)
	public int getCodigoCategoria() {
		return this.codigoCategoria;
	}

	public void setCodigoCategoria(int codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CategoriaLigaId))
			return false;
		CategoriaLigaId castOther = (CategoriaLigaId) other;

		return (this.getCodigoLiga() == castOther.getCodigoLiga())
				&& (this.getCodigoCategoria() == castOther.getCodigoCategoria());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodigoLiga();
		result = 37 * result + this.getCodigoCategoria();
		return result;
	}

}
