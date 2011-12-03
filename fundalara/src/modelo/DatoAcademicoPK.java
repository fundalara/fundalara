package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the dato_academico database table.
 * 
 */
@Embeddable
public class DatoAcademicoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cedula_jugador")
	private String cedulaJugador;

	@Column(name="codigo_institucion")
	private String codigoInstitucion;

	@Column(name="codigo_anno_escolar")
	private String codigoAnnoEscolar;

    public DatoAcademicoPK() {
    }
	public String getCedulaJugador() {
		return this.cedulaJugador;
	}
	public void setCedulaJugador(String cedulaJugador) {
		this.cedulaJugador = cedulaJugador;
	}
	public String getCodigoInstitucion() {
		return this.codigoInstitucion;
	}
	public void setCodigoInstitucion(String codigoInstitucion) {
		this.codigoInstitucion = codigoInstitucion;
	}
	public String getCodigoAnnoEscolar() {
		return this.codigoAnnoEscolar;
	}
	public void setCodigoAnnoEscolar(String codigoAnnoEscolar) {
		this.codigoAnnoEscolar = codigoAnnoEscolar;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatoAcademicoPK)) {
			return false;
		}
		DatoAcademicoPK castOther = (DatoAcademicoPK)other;
		return 
			this.cedulaJugador.equals(castOther.cedulaJugador)
			&& this.codigoInstitucion.equals(castOther.codigoInstitucion)
			&& this.codigoAnnoEscolar.equals(castOther.codigoAnnoEscolar);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cedulaJugador.hashCode();
		hash = hash * prime + this.codigoInstitucion.hashCode();
		hash = hash * prime + this.codigoAnnoEscolar.hashCode();
		
		return hash;
    }
}