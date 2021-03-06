package modelo;

// Generated 14/02/2012 08:24:27 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SesionEjecutada generated by hbm2java
 */
@Entity
@Table(name = "sesion_ejecutada", schema = "public")
public class SesionEjecutada implements java.io.Serializable {

	private int codigoSesionEjecutada;
	private DatoBasico datoBasico;
	private Sesion sesion;
	private Equipo equipo;
	private Date fecha;
	private Date horaFin;
	private char estatus;
	private Date horaInicio;
	private String observacionSesion;
	private Set<InstalacionEjecutada> instalacionEjecutadas = new HashSet<InstalacionEjecutada>(
			0);
	private Set<AsistenciaJugador> asistenciaJugadors = new HashSet<AsistenciaJugador>(
			0);
	private Set<AsistenciaPersonalEntrenamiento> asistenciaPersonalEntrenamientos = new HashSet<AsistenciaPersonalEntrenamiento>(
			0);
	private Set<PersonalSuplente> personalSuplentes = new HashSet<PersonalSuplente>(
			0);
	private Set<ActividadEjecutada> actividadEjecutadas = new HashSet<ActividadEjecutada>(
			0);
	private Set<MaterialActividad> materialActividads = new HashSet<MaterialActividad>(
			0);

	public SesionEjecutada() {
	}

	public SesionEjecutada(int codigoSesionEjecutada, Sesion sesion,
			Equipo equipo, Date fecha, Date horaFin, char estatus,
			Date horaInicio) {
		this.codigoSesionEjecutada = codigoSesionEjecutada;
		this.sesion = sesion;
		this.equipo = equipo;
		this.fecha = fecha;
		this.horaFin = horaFin;
		this.estatus = estatus;
		this.horaInicio = horaInicio;
	}

	public SesionEjecutada(
			int codigoSesionEjecutada,
			DatoBasico datoBasico,
			Sesion sesion,
			Equipo equipo,
			Date fecha,
			Date horaFin,
			char estatus,
			Date horaInicio,
			String observacionSesion,
			Set<InstalacionEjecutada> instalacionEjecutadas,
			Set<AsistenciaJugador> asistenciaJugadors,
			Set<AsistenciaPersonalEntrenamiento> asistenciaPersonalEntrenamientos,
			Set<PersonalSuplente> personalSuplentes,
			Set<ActividadEjecutada> actividadEjecutadas,
			Set<MaterialActividad> materialActividads) {
		this.codigoSesionEjecutada = codigoSesionEjecutada;
		this.datoBasico = datoBasico;
		this.sesion = sesion;
		this.equipo = equipo;
		this.fecha = fecha;
		this.horaFin = horaFin;
		this.estatus = estatus;
		this.horaInicio = horaInicio;
		this.observacionSesion = observacionSesion;
		this.instalacionEjecutadas = instalacionEjecutadas;
		this.asistenciaJugadors = asistenciaJugadors;
		this.asistenciaPersonalEntrenamientos = asistenciaPersonalEntrenamientos;
		this.personalSuplentes = personalSuplentes;
		this.actividadEjecutadas = actividadEjecutadas;
		this.materialActividads = materialActividads;
	}

	@Id
	@Column(name = "codigo_sesion_ejecutada", unique = true, nullable = false)
	public int getCodigoSesionEjecutada() {
		return this.codigoSesionEjecutada;
	}

	public void setCodigoSesionEjecutada(int codigoSesionEjecutada) {
		this.codigoSesionEjecutada = codigoSesionEjecutada;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eventualidad_sesion")
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_sesion", nullable = false)
	public Sesion getSesion() {
		return this.sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_equipo", nullable = false)
	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha", nullable = false, length = 13)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "hora_fin", nullable = false, length = 15)
	public Date getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "hora_inicio", nullable = false, length = 15)
	public Date getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	@Column(name = "observacion_sesion")
	public String getObservacionSesion() {
		return this.observacionSesion;
	}

	public void setObservacionSesion(String observacionSesion) {
		this.observacionSesion = observacionSesion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sesionEjecutada")
	public Set<InstalacionEjecutada> getInstalacionEjecutadas() {
		return this.instalacionEjecutadas;
	}

	public void setInstalacionEjecutadas(
			Set<InstalacionEjecutada> instalacionEjecutadas) {
		this.instalacionEjecutadas = instalacionEjecutadas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sesionEjecutada")
	public Set<AsistenciaJugador> getAsistenciaJugadors() {
		return this.asistenciaJugadors;
	}

	public void setAsistenciaJugadors(Set<AsistenciaJugador> asistenciaJugadors) {
		this.asistenciaJugadors = asistenciaJugadors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sesionEjecutada")
	public Set<AsistenciaPersonalEntrenamiento> getAsistenciaPersonalEntrenamientos() {
		return this.asistenciaPersonalEntrenamientos;
	}

	public void setAsistenciaPersonalEntrenamientos(
			Set<AsistenciaPersonalEntrenamiento> asistenciaPersonalEntrenamientos) {
		this.asistenciaPersonalEntrenamientos = asistenciaPersonalEntrenamientos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sesionEjecutada")
	public Set<PersonalSuplente> getPersonalSuplentes() {
		return this.personalSuplentes;
	}

	public void setPersonalSuplentes(Set<PersonalSuplente> personalSuplentes) {
		this.personalSuplentes = personalSuplentes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sesionEjecutada")
	public Set<ActividadEjecutada> getActividadEjecutadas() {
		return this.actividadEjecutadas;
	}

	public void setActividadEjecutadas(
			Set<ActividadEjecutada> actividadEjecutadas) {
		this.actividadEjecutadas = actividadEjecutadas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sesionEjecutada")
	public Set<MaterialActividad> getMaterialActividads() {
		return this.materialActividads;
	}

	public void setMaterialActividads(Set<MaterialActividad> materialActividads) {
		this.materialActividads = materialActividads;
	}

}
