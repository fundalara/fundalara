package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the dato_social database table.
 * 
 */
@Embeddable
public class DatoSocialPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cedula_jugador")
	private String cedulaJugador;

	@Column(name="codigo_institucion")
	private String codigoInstitucion;

    public DatoSocialPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatoSocialPK)) {
			return false;
		}
		DatoSocialPK castOther = (DatoSocialPK)other;
		return 
			this.cedulaJugador.equals(castOther.cedulaJugador)
			&& this.codigoInstitucion.equals(castOther.codigoInstitucion);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cedulaJugador.hashCode();
		hash = hash * prime + this.codigoInstitucion.hashCode();
		
		return hash;
    }
}