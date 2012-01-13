package modelo;

// Generated 13/01/2012 12:48:04 AM by Hibernate Tools 3.4.0.CR1

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
	private DatoBasico datoBasicoByEventualidadInstalacion;
	private DatoBasico datoBasicoByEventualidadSesion;
	private Instalacion instalacion;
	private Sesion sesion;
	private Equipo equipo;
	private Date fechaInicio;
	private Date fechaFin;
	private char estatus;
	private Set<AsistenciaJugador> asistenciaJugadors = new HashSet<AsistenciaJugador>(
			0);
	private Set<AsistenciaPersonalEntrenamiento> asistenciaPersonalEntrenamientos = new HashSet<AsistenciaPersonalEntrenamiento>(
			0);
	private Set<ActividadesEjecutadas> actividadesEjecutadases = new HashSet<ActividadesEjecutadas>(
			0);
	private Set<MaterialActividad> materialActividads = new HashSet<MaterialActividad>(
			0);

	public SesionEjecutada() {
	}

	public SesionEjecutada(int codigoSesionEjecutada,
			DatoBasico datoBasicoByEventualidadInstalacion,
			DatoBasico datoBasicoByEventualidadSesion, Instalacion instalacion,
			Sesion sesion, Equipo equipo, Date fechaInicio, Date fechaFin,
			char estatus) {
		this.codigoSesionEjecutada = codigoSesionEjecutada;
		this.datoBasicoByEventualidadInstalacion = datoBasicoByEventualidadInstalacion;
		this.datoBasicoByEventualidadSesion = datoBasicoByEventualidadSesion;
		this.instalacion = instalacion;
		this.sesion = sesion;
		this.equipo = equipo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estatus = estatus;
	}

	public SesionEjecutada(
			int codigoSesionEjecutada,
			DatoBasico datoBasicoByEventualidadInstalacion,
			DatoBasico datoBasicoByEventualidadSesion,
			Instalacion instalacion,
			Sesion sesion,
			Equipo equipo,
			Date fechaInicio,
			Date fechaFin,
			char estatus,
			Set<AsistenciaJugador> asistenciaJugadors,
			Set<AsistenciaPersonalEntrenamiento> asistenciaPersonalEntrenamientos,
			Set<ActividadesEjecutadas> actividadesEjecutadases,
			Set<MaterialActividad> materialActividads) {
		this.codigoSesionEjecutada = codigoSesionEjecutada;
		this.datoBasicoByEventualidadInstalacion = datoBasicoByEventualidadInstalacion;
		this.datoBasicoByEventualidadSesion = datoBasicoByEventualidadSesion;
		this.instalacion = instalacion;
		this.sesion = sesion;
		this.equipo = equipo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estatus = estatus;
		this.asistenciaJugadors = asistenciaJugadors;
		this.asistenciaPersonalEntrenamientos = asistenciaPersonalEntrenamientos;
		this.actividadesEjecutadases = actividadesEjecutadases;
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
	@JoinColumn(name = "eventualidad_instalacion", nullable = false)
	public DatoBasico getDatoBasicoByEventualidadInstalacion() {
		return this.datoBasicoByEventualidadInstalacion;
	}

	public void setDatoBasicoByEventualidadInstalacion(
			DatoBasico datoBasicoByEventualidadInstalacion) {
		this.datoBasicoByEventualidadInstalacion = datoBasicoByEventualidadInstalacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eventualidad_sesion", nullable = false)
	public DatoBasico getDatoBasicoByEventualidadSesion() {
		return this.datoBasicoByEventualidadSesion;
	}

	public void setDatoBasicoByEventualidadSesion(
			DatoBasico datoBasicoByEventualidadSesion) {
		this.datoBasicoByEventualidadSesion = datoBasicoByEventualidadSesion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_instalacion", nullable = false)
	public Instalacion getInstalacion() {
		return this.instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
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
	@Column(name = "fecha_inicio", nullable = false, length = 13)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_fin", nullable = false, length = 13)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
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
	public Set<ActividadesEjecutadas> getActividadesEjecutadases() {
		return this.actividadesEjecutadases;
	}

	public void setActividadesEjecutadases(
			Set<ActividadesEjecutadas> actividadesEjecutadases) {
		this.actividadesEjecutadases = actividadesEjecutadases;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sesionEjecutada")
	public Set<MaterialActividad> getMaterialActividads() {
		return this.materialActividads;
	}

	public void setMaterialActividads(Set<MaterialActividad> materialActividads) {
		this.materialActividads = materialActividads;
	}

}
