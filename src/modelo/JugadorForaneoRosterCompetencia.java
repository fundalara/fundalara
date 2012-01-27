package modelo;

// Generated 27/01/2012 03:27:22 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * JugadorForaneoRosterCompetencia generated by hbm2java
 */
@Entity
@Table(name = "jugador_foraneo_roster_competencia", schema = "public")
public class JugadorForaneoRosterCompetencia implements java.io.Serializable {

	private int codigoJugadorForaneoRosterCompetencia;
	private RosterCompetencia rosterCompetencia;
	private JugadorForaneo jugadorForaneo;

	public JugadorForaneoRosterCompetencia() {
	}

	public JugadorForaneoRosterCompetencia(
			int codigoJugadorForaneoRosterCompetencia,
			RosterCompetencia rosterCompetencia, JugadorForaneo jugadorForaneo) {
		this.codigoJugadorForaneoRosterCompetencia = codigoJugadorForaneoRosterCompetencia;
		this.rosterCompetencia = rosterCompetencia;
		this.jugadorForaneo = jugadorForaneo;
	}

	@Id
	@Column(name = "codigo_jugador_foraneo_roster_competencia", unique = true, nullable = false)
	public int getCodigoJugadorForaneoRosterCompetencia() {
		return this.codigoJugadorForaneoRosterCompetencia;
	}

	public void setCodigoJugadorForaneoRosterCompetencia(
			int codigoJugadorForaneoRosterCompetencia) {
		this.codigoJugadorForaneoRosterCompetencia = codigoJugadorForaneoRosterCompetencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_roster_competencia", nullable = false)
	public RosterCompetencia getRosterCompetencia() {
		return this.rosterCompetencia;
	}

	public void setRosterCompetencia(RosterCompetencia rosterCompetencia) {
		this.rosterCompetencia = rosterCompetencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula", nullable = false)
	public JugadorForaneo getJugadorForaneo() {
		return this.jugadorForaneo;
	}

	public void setJugadorForaneo(JugadorForaneo jugadorForaneo) {
		this.jugadorForaneo = jugadorForaneo;
	}

}
