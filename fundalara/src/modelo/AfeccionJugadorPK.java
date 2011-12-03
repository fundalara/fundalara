package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the afeccion_jugador database table.
 * 
 */
@Embeddable
public class AfeccionJugadorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_registro")
	private String codigoRegistro;

	@Column(name="codigo_afeccion")
	private String codigoAfeccion;

    public AfeccionJugadorPK() {
    }
	public String getCodigoRegistro() {
		return this.codigoRegistro;
	}
	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}
	public String getCodigoAfeccion() {
		return this.codigoAfeccion;
	}
	public void setCodigoAfeccion(String codigoAfeccion) {
		this.codigoAfeccion = codigoAfeccion;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AfeccionJugadorPK)) {
			return false;
		}
		AfeccionJugadorPK castOther = (AfeccionJugadorPK)other;
		return 
			this.codigoRegistro.equals(castOther.codigoRegistro)
			&& this.codigoAfeccion.equals(castOther.codigoAfeccion);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoRegistro.hashCode();
		hash = hash * prime + this.codigoAfeccion.hashCode();
		
		return hash;
    }
}