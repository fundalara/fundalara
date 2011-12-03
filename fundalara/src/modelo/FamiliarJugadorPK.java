package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the familiar_jugador database table.
 * 
 */
@Embeddable
public class FamiliarJugadorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cedula_jugador")
	private String cedulaJugador;

	@Column(name="cedula_familiar")
	private String cedulaFamiliar;

    public FamiliarJugadorPK() {
    }
	public String getCedulaJugador() {
		return this.cedulaJugador;
	}
	public void setCedulaJugador(String cedulaJugador) {
		this.cedulaJugador = cedulaJugador;
	}
	public String getCedulaFamiliar() {
		return this.cedulaFamiliar;
	}
	public void setCedulaFamiliar(String cedulaFamiliar) {
		this.cedulaFamiliar = cedulaFamiliar;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FamiliarJugadorPK)) {
			return false;
		}
		FamiliarJugadorPK castOther = (FamiliarJugadorPK)other;
		return 
			this.cedulaJugador.equals(castOther.cedulaJugador)
			&& this.cedulaFamiliar.equals(castOther.cedulaFamiliar);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cedulaJugador.hashCode();
		hash = hash * prime + this.cedulaFamiliar.hashCode();
		
		return hash;
    }
}