package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the familiar_comision_equipo database table.
 * 
 */
@Embeddable
public class FamiliarComisionEquipoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_comision")
	private String codigoComision;

	@Column(name="cedula_jugador")
	private String cedulaJugador;

	@Column(name="cedula_familiar")
	private String cedulaFamiliar;

	@Column(name="codigo_equipo")
	private String codigoEquipo;

    public FamiliarComisionEquipoPK() {
    }
	public String getCodigoComision() {
		return this.codigoComision;
	}
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
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
		if (!(other instanceof FamiliarComisionEquipoPK)) {
			return false;
		}
		FamiliarComisionEquipoPK castOther = (FamiliarComisionEquipoPK)other;
		return 
			this.codigoComision.equals(castOther.codigoComision)
			&& this.cedulaJugador.equals(castOther.cedulaJugador)
			&& this.cedulaFamiliar.equals(castOther.cedulaFamiliar)
			&& this.codigoEquipo.equals(castOther.codigoEquipo);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoComision.hashCode();
		hash = hash * prime + this.cedulaJugador.hashCode();
		hash = hash * prime + this.cedulaFamiliar.hashCode();
		hash = hash * prime + this.codigoEquipo.hashCode();
		
		return hash;
    }
}