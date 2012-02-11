package modelo;

// Generated 10/02/2012 11:18:51 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Roster generated by hbm2java
 */
@Entity
@Table(name = "roster", schema = "public")
public class Roster implements java.io.Serializable {

	private int codigoRoster;
	private String cedulaRif;
	private int codigoEquipo;
	private Date fechaIngreso;
	private char estatus;
	private Set<TestJugador> testJugadors = new HashSet<TestJugador>(0);
	private Set<Ascenso> ascensos = new HashSet<Ascenso>(0);
	private Set<Anuario> anuarios = new HashSet<Anuario>(0);
	private Set<AsistenciaJugador> asistenciaJugadors = new HashSet<AsistenciaJugador>(
			0);
	private Set<RosterCompetencia> rosterCompetencias = new HashSet<RosterCompetencia>(
			0);

	public Roster() {
	}

	public Roster(int codigoRoster, String cedulaRif, int codigoEquipo,
			Date fechaIngreso, char estatus) {
		this.codigoRoster = codigoRoster;
		this.cedulaRif = cedulaRif;
		this.codigoEquipo = codigoEquipo;
		this.fechaIngreso = fechaIngreso;
		this.estatus = estatus;
	}

	public Roster(int codigoRoster, String cedulaRif, int codigoEquipo,
			Date fechaIngreso, char estatus, Set<TestJugador> testJugadors,
			Set<Ascenso> ascensos, Set<Anuario> anuarios,
			Set<AsistenciaJugador> asistenciaJugadors,
			Set<RosterCompetencia> rosterCompetencias) {
		this.codigoRoster = codigoRoster;
		this.cedulaRif = cedulaRif;
		this.codigoEquipo = codigoEquipo;
		this.fechaIngreso = fechaIngreso;
		this.estatus = estatus;
		this.testJugadors = testJugadors;
		this.ascensos = ascensos;
		this.anuarios = anuarios;
		this.asistenciaJugadors = asistenciaJugadors;
		this.rosterCompetencias = rosterCompetencias;
	}

	@Id
	@Column(name = "codigo_roster", unique = true, nullable = false)
	public int getCodigoRoster() {
		return this.codigoRoster;
	}

	public void setCodigoRoster(int codigoRoster) {
		this.codigoRoster = codigoRoster;
	}

	@Column(name = "cedula_rif", nullable = false)
	public String getCedulaRif() {
		return this.cedulaRif;
	}

	public void setCedulaRif(String cedulaRif) {
		this.cedulaRif = cedulaRif;
	}

	@Column(name = "codigo_equipo", nullable = false)
	public int getCodigoEquipo() {
		return this.codigoEquipo;
	}

	public void setCodigoEquipo(int codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_ingreso", nullable = false, length = 13)
	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roster")
	public Set<TestJugador> getTestJugadors() {
		return this.testJugadors;
	}

	public void setTestJugadors(Set<TestJugador> testJugadors) {
		this.testJugadors = testJugadors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roster")
	public Set<Ascenso> getAscensos() {
		return this.ascensos;
	}

	public void setAscensos(Set<Ascenso> ascensos) {
		this.ascensos = ascensos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roster")
	public Set<Anuario> getAnuarios() {
		return this.anuarios;
	}

	public void setAnuarios(Set<Anuario> anuarios) {
		this.anuarios = anuarios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roster")
	public Set<AsistenciaJugador> getAsistenciaJugadors() {
		return this.asistenciaJugadors;
	}

	public void setAsistenciaJugadors(Set<AsistenciaJugador> asistenciaJugadors) {
		this.asistenciaJugadors = asistenciaJugadors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roster")
	public Set<RosterCompetencia> getRosterCompetencias() {
		return this.rosterCompetencias;
	}

	public void setRosterCompetencias(Set<RosterCompetencia> rosterCompetencias) {
		this.rosterCompetencias = rosterCompetencias;
	}

}
