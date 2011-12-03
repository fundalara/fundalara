package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the roster database table.
 * 
 */
@Entity
public class Roster implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RosterPK id;

	private String estatus;

	//bi-directional many-to-one association to Equipo
    @ManyToOne
	@JoinColumn(name="codigo_equipo")
	private Equipo equipo;

	//bi-directional many-to-one association to Jugador
    @ManyToOne
	@JoinColumn(name="cedula_jugador")
	private Jugador jugador;

    public Roster() {
    }

	public RosterPK getId() {
		return this.id;
	}

	public void setId(RosterPK id) {
		this.id = id;
	}
	
	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	public Jugador getJugador() {
		return this.jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
}