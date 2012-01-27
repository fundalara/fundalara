package modelo;

<<<<<<< HEAD
// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1
=======
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f

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
 * AsistenciaJugador generated by hbm2java
 */
@Entity
@Table(name = "asistencia_jugador", schema = "public")
public class AsistenciaJugador implements java.io.Serializable {

	private int codigoAsistencia;
	private Roster roster;
	private RosterPlan rosterPlan;
	private SesionEjecutada sesionEjecutada;
	private DatoBasico datoBasico;
	private boolean asistencia;
	private String observacion;
	private char estatus;
	private Set<DesempennoJugador> desempennoJugadors = new HashSet<DesempennoJugador>(
			0);

	public AsistenciaJugador() {
	}

	public AsistenciaJugador(int codigoAsistencia,
			SesionEjecutada sesionEjecutada, DatoBasico datoBasico,
<<<<<<< HEAD
			boolean asistencia, char estatus) {
=======
			boolean asistencia, String observacion, char estatus) {
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
		this.codigoAsistencia = codigoAsistencia;
		this.sesionEjecutada = sesionEjecutada;
		this.datoBasico = datoBasico;
		this.asistencia = asistencia;
<<<<<<< HEAD
=======
		this.observacion = observacion;
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
		this.estatus = estatus;
	}

	public AsistenciaJugador(int codigoAsistencia, Roster roster,
			RosterPlan rosterPlan, SesionEjecutada sesionEjecutada,
			DatoBasico datoBasico, boolean asistencia, String observacion,
			char estatus, Set<DesempennoJugador> desempennoJugadors) {
		this.codigoAsistencia = codigoAsistencia;
		this.roster = roster;
		this.rosterPlan = rosterPlan;
		this.sesionEjecutada = sesionEjecutada;
		this.datoBasico = datoBasico;
		this.asistencia = asistencia;
		this.observacion = observacion;
		this.estatus = estatus;
		this.desempennoJugadors = desempennoJugadors;
	}

	@Id
	@Column(name = "codigo_asistencia", unique = true, nullable = false)
	public int getCodigoAsistencia() {
		return this.codigoAsistencia;
	}

	public void setCodigoAsistencia(int codigoAsistencia) {
		this.codigoAsistencia = codigoAsistencia;
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
	@JoinColumn(name = "codigo_roster_plan")
	public RosterPlan getRosterPlan() {
		return this.rosterPlan;
	}

	public void setRosterPlan(RosterPlan rosterPlan) {
		this.rosterPlan = rosterPlan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_sesion_ejecutada", nullable = false)
	public SesionEjecutada getSesionEjecutada() {
		return this.sesionEjecutada;
	}

	public void setSesionEjecutada(SesionEjecutada sesionEjecutada) {
		this.sesionEjecutada = sesionEjecutada;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eventualidad", nullable = false)
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	@Column(name = "asistencia", nullable = false)
	public boolean isAsistencia() {
		return this.asistencia;
	}

	public void setAsistencia(boolean asistencia) {
		this.asistencia = asistencia;
	}

<<<<<<< HEAD
	@Column(name = "observacion")
=======
	@Column(name = "observacion", nullable = false)
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "asistenciaJugador")
	public Set<DesempennoJugador> getDesempennoJugadors() {
		return this.desempennoJugadors;
	}

	public void setDesempennoJugadors(Set<DesempennoJugador> desempennoJugadors) {
		this.desempennoJugadors = desempennoJugadors;
	}

}
