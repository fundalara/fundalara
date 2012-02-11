package modelo;

// Generated 11/02/2012 01:49:19 AM by Hibernate Tools 3.4.0.CR1

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
 * RosterPlan generated by hbm2java
 */
@Entity
@Table(name = "roster_plan", schema = "public")
public class RosterPlan implements java.io.Serializable {

	private int codigoRosterPlan;
	private JugadorPlan jugadorPlan;
	private Equipo equipo;
	private char estatus;
	private Set<AsistenciaJugador> asistenciaJugadors = new HashSet<AsistenciaJugador>(
			0);
	private Set<TestJugador> testJugadors = new HashSet<TestJugador>(0);

	public RosterPlan() {
	}

	public RosterPlan(int codigoRosterPlan, JugadorPlan jugadorPlan,
			Equipo equipo, char estatus) {
		this.codigoRosterPlan = codigoRosterPlan;
		this.jugadorPlan = jugadorPlan;
		this.equipo = equipo;
		this.estatus = estatus;
	}

	public RosterPlan(int codigoRosterPlan, JugadorPlan jugadorPlan,
			Equipo equipo, char estatus,
			Set<AsistenciaJugador> asistenciaJugadors,
			Set<TestJugador> testJugadors) {
		this.codigoRosterPlan = codigoRosterPlan;
		this.jugadorPlan = jugadorPlan;
		this.equipo = equipo;
		this.estatus = estatus;
		this.asistenciaJugadors = asistenciaJugadors;
		this.testJugadors = testJugadors;
	}

	@Id
	@Column(name = "codigo_roster_plan", unique = true, nullable = false)
	public int getCodigoRosterPlan() {
		return this.codigoRosterPlan;
	}

	public void setCodigoRosterPlan(int codigoRosterPlan) {
		this.codigoRosterPlan = codigoRosterPlan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula_rif", nullable = false)
	public JugadorPlan getJugadorPlan() {
		return this.jugadorPlan;
	}

	public void setJugadorPlan(JugadorPlan jugadorPlan) {
		this.jugadorPlan = jugadorPlan;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rosterPlan")
	public Set<AsistenciaJugador> getAsistenciaJugadors() {
		return this.asistenciaJugadors;
	}

	public void setAsistenciaJugadors(Set<AsistenciaJugador> asistenciaJugadors) {
		this.asistenciaJugadors = asistenciaJugadors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rosterPlan")
	public Set<TestJugador> getTestJugadors() {
		return this.testJugadors;
	}

	public void setTestJugadors(Set<TestJugador> testJugadors) {
		this.testJugadors = testJugadors;
	}

}
