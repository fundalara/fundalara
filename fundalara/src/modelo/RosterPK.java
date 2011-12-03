package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the roster database table.
 * 
 */
@Embeddable
public class RosterPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cedula_jugador")
	private String cedulaJugador;

	@Column(name="codigo_equipo")
	private String codigoEquipo;

    public RosterPK() {
    }
	public String getCedulaJugador() {
		return this.cedulaJugador;
	}
	public void setCedulaJugador(String cedulaJugador) {
		this.cedulaJugador = cedulaJugador;
	}
	public String getCodigoEquipo() {
		return this.codigoEquipo;
	}
	public void setCodigoEquipo(String codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RosterPK)) {
			return false;
		}
		RosterPK castOther = (RosterPK)other;
		return 
			this.cedulaJugador.equals(castOther.cedulaJugador)
			&& this.codigoEquipo.equals(castOther.codigoEquipo);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cedulaJugador.hashCode();
		hash = hash * prime + this.codigoEquipo.hashCode();
		
		return hash;
    }
}