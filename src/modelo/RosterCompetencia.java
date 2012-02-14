package modelo;

// Generated 13/02/2012 08:44:08 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * RosterCompetencia generated by hbm2java
 */
@Entity
@Table(name = "roster_competencia", schema = "public")
public class RosterCompetencia implements java.io.Serializable {

	private int codigoRosterCompetencia;
	private Roster roster;
	private Competencia competencia;
	private JugadorForaneo jugadorForaneo;
	private Equipo equipo;
	private char estatus;
	private Set<LineUp> lineUps = new HashSet<LineUp>(0);

	public RosterCompetencia() {
	}

	public RosterCompetencia(int codigoRosterCompetencia,
			Competencia competencia, Equipo equipo, char estatus) {
		this.codigoRosterCompetencia = codigoRosterCompetencia;
		this.competencia = competencia;
		this.equipo = equipo;
		this.estatus = estatus;
	}

	public RosterCompetencia(int codigoRosterCompetencia, Roster roster,
			Competencia competencia, JugadorForaneo jugadorForaneo,
			Equipo equipo, char estatus, Set<LineUp> lineUps) {
		this.codigoRosterCompetencia = codigoRosterCompetencia;
		this.roster = roster;
		this.competencia = competencia;
		this.jugadorForaneo = jugadorForaneo;
		this.equipo = equipo;
		this.estatus = estatus;
		this.lineUps = lineUps;
	}

	@Id
	@Column(name = "codigo_roster_competencia", unique = true, nullable = false)
	public int getCodigoRosterCompetencia() {
		return this.codigoRosterCompetencia;
	}

	public void setCodigoRosterCompetencia(int codigoRosterCompetencia) {
		this.codigoRosterCompetencia = codigoRosterCompetencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_roster")
	public Roster getRoster() {
		return this.roster;
	}

	public void setRoster(Roster roster) {
		this.roster = roster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_competencia", nullable = false)
	public Competencia getCompetencia() {
		return this.competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_jugador_foraneo")
	public JugadorForaneo getJugadorForaneo() {
		return this.jugadorForaneo;
	}

	public void setJugadorForaneo(JugadorForaneo jugadorForaneo) {
		this.jugadorForaneo = jugadorForaneo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_equipo", nullable = false)
	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rosterCompetencia")
	public Set<LineUp> getLineUps() {
		return this.lineUps;
	}

	public void setLineUps(Set<LineUp> lineUps) {
		this.lineUps = lineUps;
	}

}
